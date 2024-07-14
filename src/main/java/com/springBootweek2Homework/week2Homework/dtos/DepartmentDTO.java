package com.springBootweek2Homework.week2Homework.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springBootweek2Homework.week2Homework.annotations.PasswordValidation;
import com.springBootweek2Homework.week2Homework.annotations.PrimeNumberValidation;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 20, message = "Title must be between 3 and 20 characters")
    private String title;

    @JsonProperty("isActive")
    private Boolean isActive;


    @PrimeNumberValidation
    private Integer chkPrime;

    @Size(min=10, message = "Password must contain atleast 10 characters")
    @PasswordValidation
    private String Password;
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
