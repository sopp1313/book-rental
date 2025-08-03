package com.network.calendar_api.controller;

import com.network.calendar_api.dto.BaseResponse;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.network.calendar_api.controller.CalendarNotFoundException;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/calender")
public class MyCalenderController {
    private final MyCalenderService myCalenderService;
    private final SchduleService schduleService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 캘린더조회함"),
            @ApiResponse(responseCode = "400", description = " 실패했다...")
    })
    public ResponseEntity<BaseResponse<List<MyCalenderDto>>> getMyCalender(@RequestParam int year, @RequestParam int Month){
        schduleService.scheduleEvent(year);
        List<MyCalenderDto> date = myCalenderService.getMyCalenders(year,Month);
        if(date.isEmpty()){
            throw new CalendarNotFoundException("캘린더 불러오기에 실패했습니다.");
        }
        return ResponseEntity.ok(new BaseResponse<>(200, "성공했습니다~", date));
    }
}
