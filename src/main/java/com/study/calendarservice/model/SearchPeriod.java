package com.study.calendarservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchPeriod {

    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime beginSearchPeriod;

    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime endSearchPeriod;
}
