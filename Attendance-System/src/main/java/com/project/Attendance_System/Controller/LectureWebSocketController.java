package com.project.Attendance_System.Controller;

import com.project.Attendance_System.Domain.Dtos.Lecture.QrUpdateDto;
import com.project.Attendance_System.Service.LectureService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class LectureWebSocketController {

    private final LectureService lectureService;

    @MessageMapping("/qr/update")
    public void handleQrUpdate(QrUpdateDto dto){
        lectureService.qrupdate(dto);
    }
}
