package com.project.Attendance_System.Config;

import com.project.Attendance_System.Service.JwtService;
import com.project.Attendance_System.Service.UserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StompAuthenticationChannelInterceptor implements ChannelInterceptor {

    private final JwtService jwtService;
    private final UserDetailService userDetailService;

    @Override
    public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            try {
                String authHeader = accessor.getFirstNativeHeader("Authorization");
                String token = null;

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    token = authHeader.substring(7);
                } else if (accessor.getSessionAttributes() != null) {
                    Object attr = accessor.getSessionAttributes().get("ws_token");
                    if (attr instanceof String s) token = s;
                }

                if (token == null) throw new AccessDeniedException("Missing WebSocket auth token");

                String username = jwtService.extractUserName(token);
                if (username == null) throw new AccessDeniedException("Invalid token (no subject)");

                UserDetails userDetails = userDetailService.loadUserByUsername(username);
                if (!jwtService.validateToken(token, userDetails)) {
                    throw new AccessDeniedException("Invalid or expired token");
                }

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                accessor.setUser(auth);
                SecurityContextHolder.getContext().setAuthentication(auth);

                System.out.println("[STOMP CONNECT] Authenticated user: " + username);

            } catch (Exception e) {
                System.err.println("[STOMP CONNECT ERROR] " + e.getMessage());
            }
        }

        return message;
    }
}
