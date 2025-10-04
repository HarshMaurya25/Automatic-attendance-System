package com.project.Attendance_System.Config;

import com.project.Attendance_System.Service.JwtService;
import com.project.Attendance_System.Service.UserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Intercepts inbound STOMP messages. On CONNECT frames it extracts token from
 * headers or handshake attributes
 * and validates JWT, then sets the Authentication principal on the message
 * headers so controllers can access it.
 */
@Component
@AllArgsConstructor
public class StompAuthenticationChannelInterceptor implements ChannelInterceptor {

    private final JwtService jwtService;
    private final UserDetailService userDetailService;

    @Override
    public Message<?> preSend(Message<?> message, @NonNull MessageChannel channel) {
        SimpMessageHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message,
                SimpMessageHeaderAccessor.class);
        StompHeaderAccessor stompAccessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor == null)
            return message;

        if (stompAccessor != null && StompCommand.CONNECT.equals(stompAccessor.getCommand())) {
            // try Authorization header first
            List<String> auth = stompAccessor.getNativeHeader("Authorization");
            String token = null;
            if (auth != null && !auth.isEmpty()) {
                String value = auth.get(0);
                if (value.startsWith("Bearer "))
                    token = value.substring(7);
            }

            // fallback: token placed during handshake as attribute
            if (token == null) {
                Object t = accessor.getSessionAttributes() != null ? accessor.getSessionAttributes().get("ws_token")
                        : null;
                if (t != null)
                    token = t.toString();
            }

            if (token != null) {
                try {
                    String username = jwtService.extractUserName(token);
                    if (username != null) {
                        UserDetails userDetails = userDetailService.loadUserByUsername(username);
                        if (jwtService.validateToken(token, userDetails)) {
                            Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                                    userDetails.getAuthorities());
                            // set user on both accessors where available
                            stompAccessor.setUser(authToken);
                            accessor.setUser(authToken);
                        }
                    }
                } catch (Exception e) {
                    // invalid token - ignore or log; leaving unauthenticated
                }
            }
        }

        return message;
    }
}
