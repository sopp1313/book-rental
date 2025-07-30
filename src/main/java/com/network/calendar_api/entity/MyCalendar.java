package com.network.calendar_api.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
// H2 데이터베이스 예약어 충돌 방지를 위해 컬럼명 지정 
@Table(name = "my_calendar")
public class MyCalendar{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cal_month")
    private Integer month;

    @Column(name = "cal_day") 
    private Integer day;

    @Column(name = "cal_year")
    private Integer year;

}
