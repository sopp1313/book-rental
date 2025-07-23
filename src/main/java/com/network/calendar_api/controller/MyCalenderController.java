package com.network.calendar_api.controller;

import com.network.calendar_api.dto.EventRequestDto;
import com.network.calendar_api.dto.MyCalenderDto;
import com.network.calendar_api.service.MyCalenderService;
import com.network.calendar_api.service.SchduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/calender")
public class MyCalenderController {
    private final MyCalenderService myCalenderService;
    private final SchduleService schduleService;

    @GetMapping
    public ResponseEntity<List<MyCalenderDto>> getMyCalender(@RequestParam int year,@RequestParam int Month){
        schduleService.scheduleEvent(year);
        List<MyCalenderDto> date = myCalenderService.getMyCalenders(year,Month);

        return ResponseEntity.ok(date);
    }
}
