package com.project.Attendance_System.Controller;

import com.project.Attendance_System.Domain.Dtos.Lecture.QrUpdateDto;
import com.project.Attendance_System.Service.LectureService;
import com.project.Attendance_System.Service.SessionMappingService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class LectureWebSocketController {

    private final LectureService lectureService;
    private final SessionMappingService sessionMappingService;

    @MessageMapping("/qr/update")
    @SendTo("/topic/send")
    public String handleQrUpdate(QrUpdateDto dto , StompHeaderAccessor headerAccessor) {
        System.out.println("Received QR update: " + dto.getQr_id());

        lectureService.qrupdate(dto);
        return "QR Update Received: ";
    }

}
