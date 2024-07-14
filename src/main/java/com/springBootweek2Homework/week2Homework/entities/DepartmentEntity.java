package com.springBootweek2Homework.week2Homework.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "Department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @JsonProperty("isActive")
    private Boolean isActive;

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime createdAt;
    private Integer chkPrime;

    private String Password;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
