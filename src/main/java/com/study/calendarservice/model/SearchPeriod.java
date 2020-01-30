package com.study.calendarservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class SearchPeriod {

    @JsonFormat(pattern = "HH:mm dd-MM-YYYY")
    private LocalDateTime beginSearchPeriod;

    @JsonFormat(pattern = "HH:mm dd-MM-YYYY")
    private LocalDateTime endSearchPeriod;
}
