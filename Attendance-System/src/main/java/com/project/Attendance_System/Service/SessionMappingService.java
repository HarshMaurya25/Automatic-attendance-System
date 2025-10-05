package com.project.Attendance_System.Service;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionMappingService {

    private final Map<UUID, String> sessionIdToUsername = new ConcurrentHashMap<>();

    public void register(UUID sessionId, String username) {
        sessionIdToUsername.put(sessionId, username);
    }

    public String getUsername(UUID sessionId) {
        return sessionIdToUsername.get(sessionId);
    }
}
