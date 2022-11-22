package com.neoflex.application.dto;

import com.neoflex.application.enumeration.EmploymentStatus;
import com.neoflex.application.enumeration.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
public class EmploymentDTO {
    EmploymentStatus employmentStatus;
    String employerINN;
    BigDecimal salary;
    Position position;
    Integer workExperienceTotal;
    Integer workExperienceCurrent;

}
