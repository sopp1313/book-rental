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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/calender")
@Tag(name = "Calendar", description = "CAU Academic Calendar API")
public class MyCalenderController {
    private final MyCalenderService myCalenderService;
    private final SchduleService schduleService;

    @GetMapping
    @Operation(summary = "월별 캘린더 조회", description = "특정 연도와 월의 학사일정을 조회합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 캘린더조회함"),
            @ApiResponse(responseCode = "400", description = "실패했다..."),
            @ApiResponse(responseCode = "404", description = "캘린더 데이터를 찾을 수 없습니다")
    })
    public ResponseEntity<BaseResponse<List<MyCalenderDto>>> getMyCalender(
            @Parameter(description = "연도 (예: 2025)", required = true, example = "2025") 
            @RequestParam int year, 
            @Parameter(description = "월 (1-12)", required = true, example = "3") 
            @RequestParam int Month){
        schduleService.scheduleEvent(year);
        List<MyCalenderDto> date = myCalenderService.getMyCalenders(year,Month);
        if(date.isEmpty()){
            throw new CalendarNotFoundException("캘린더 불러오기에 실패했습니다.");
        }
        return ResponseEntity.ok(new BaseResponse<>(200, "성공했습니다~", date));
    }
}
