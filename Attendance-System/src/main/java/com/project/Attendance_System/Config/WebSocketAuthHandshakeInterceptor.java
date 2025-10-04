package com.project.Attendance_System.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * Intercepts the WebSocket handshake to extract a JWT from the HTTP Upgrade
 * request.
 * Example expects token in an "Authorization" header (Bearer <token>) or in a
 * "token" query param.
 */
@Component
public class WebSocketAuthHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
            WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // If underlying request is servlet-based, we can access headers and query
        // params
        if (request instanceof ServletServerHttpRequest servletRequest) {
            HttpServletRequest httpServletRequest = servletRequest.getServletRequest();

            // 1) Prefer Authorization header
            String authHeader = httpServletRequest.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                attributes.put("ws_token", token);
                return true;
            }

            // 2) Fallback to query param: ?token=<jwt>
            String qtoken = httpServletRequest.getParameter("token");
            if (qtoken != null && !qtoken.isEmpty()) {
                attributes.put("ws_token", qtoken);
                return true;
            }
        }

        // No token found, allow the handshake to continue but applications should
        // reject unauthorized STOMP frames.
        // Alternatively return false to reject the handshake.
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
            WebSocketHandler wsHandler, @Nullable Exception exception) {
        // no-op
    }
}
