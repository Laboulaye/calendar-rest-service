package com.study.calendarservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "begin_date_time")
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime beginDateTime;

    @Column(name = "end_date_time")
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime endDateTime;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "usr_id")
    @JsonIgnore
    private User user;
}
