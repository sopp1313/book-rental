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

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("year", String.valueOf(year));
        params.add("site_number", "2");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        List<EventRequestDto> result = new ArrayList<>();
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            String json = response.getBody();

            JsonNode arrayNode = objectMapper.readTree(json);

            for (JsonNode item : arrayNode) {
                String title = item.get("SUBJECT").asText();
                String description = item.get("DESCRIPTION").asText();
                String startYear = item.get("START_Y").asText();
                String endYear = item.get("END_Y").asText();
                String startMonth = item.get("START_M").asText();
                String endMonth = item.get("END_M").asText();
                String startDay = item.get("START_D").asText();
                String endDay = item.get("END_D").asText();
                String date;

                if (startYear.equals(endYear) && startMonth.equals(endMonth) && startDay.equals(endDay)) {
                    date = String.format("%d-%02d-%02d", Integer.parseInt(startYear), Integer.parseInt(startMonth), Integer.parseInt(startDay));
                } else {
                    date = String.format("%d-%02d-%02d~%d-%02d-%02d", Integer.parseInt(startYear), Integer.parseInt(startMonth), Integer.parseInt(startDay), Integer.parseInt(endYear), Integer.parseInt(endMonth));
                }

                LocalDate localDate = LocalDate.parse(date);
                Event event = new Event(title, description, date);
                eventRepository.save(event);
            }
        } catch (Exception e) {
            throw new RuntimeException("학사일정 불러오기 실패");
        }
        return result;
    }
}

