package com.network.calendar_api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.network.calendar_api.dto.EventRequestDto;
import com.network.calendar_api.dto.MyCalenderDto;
import com.network.calendar_api.entity.Event;
import com.network.calendar_api.repository.EventRepository;
import com.sun.jdi.request.EventRequest;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SchduleService {
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();
    private EventRepository eventRepository;

    public SchduleService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public List<EventRequestDto> scheduleEvent(int year) {
        String url = "https://www.cau.ac.kr/ajax/FR_SCH_SVC/ScheduleListData.do";
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // 질문
        headers.set("Referer", "https://www.cau.ac.kr/");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("SCH_YEAR", String.valueOf(year));
        params.add("SCH_SITE_NO", "2");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        List<EventRequestDto> result = new ArrayList<>();
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            String json = response.getBody();

            JsonNode array = objectMapper.readTree(json);
            JsonNode arrayNode = array.get("data");

            for (JsonNode item : arrayNode) {
                String title = item.path("SUBJECT").asText("");
                String description = item.path("DESCRIPTION").asText("");
                String startYear = item.path("START_Y").asText("");
                String endYear = item.path("END_Y").asText("");
                String startMonth = item.path("START_M").asText("");
                String endMonth = item.path("END_M").asText("");
                String startDay = item.path("START_D").asText("");
                String endDay = item.path("END_D").asText("");
                String repeatYn = item.path("REPEAT_YN").asText("");
                String startDayRaw = item.path("START_DAY").asText("");
                String endDayRaw = item.path("END_DAY").asText("");
                String date;

                DateTimeFormatter ymdFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

                if (startYear.equals(endYear) && startMonth.equals(endMonth) && startDay.equals(endDay)) {
                    date = String.format("%d-%02d-%02d", Integer.parseInt(startYear), Integer.parseInt(startMonth), Integer.parseInt(startDay));
                } else {
                    date = String.format("%d-%02d-%02d~%d-%02d-%02d", Integer.parseInt(startYear), Integer.parseInt(startMonth), Integer.parseInt(startDay), Integer.parseInt(endYear), Integer.parseInt(endMonth));
                }

                if(date.contains("~")){
                    String[] parts = date.split("~");
                    LocalDate start = LocalDate.parse(parts[0]);
                    LocalDate end = LocalDate.parse(parts[1]);

                    for(LocalDate a = start; !a.isAfter(end); a = a.plusDays(1)) {
                        eventRepository.save(new Event(title, description, a));
                    }
                }else if(repeatYn.equals("Y")&&!startDayRaw.isEmpty()&&!endDayRaw.isEmpty()){
                    LocalDate start = LocalDate.parse(startDayRaw, ymdFormatter);
                    LocalDate end = LocalDate.parse(endDayRaw, ymdFormatter);

                    for (LocalDate d = start; !d.isAfter(end); d = d.plusDays(1)) {
                        eventRepository.save(new Event(title, description, d));
                    }
                }
                else{
                LocalDate localDate = LocalDate.parse(date);
                Event event = new Event(title, description, date);
                eventRepository.save(event);
            }}
        } catch (Exception e) {
            throw new RuntimeException("학사일정 불러오기 실패");
        }
        return result;
    }
}

