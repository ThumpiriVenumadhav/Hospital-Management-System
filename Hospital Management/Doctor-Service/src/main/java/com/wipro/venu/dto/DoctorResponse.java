package com.wipro.venu.dto;




import com.wipro.venu.entity.Doctor.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponse {
    private Long id;
    private String name;
    private String specialization;
    private String email;
    private String phone;
    private String department;
    private int experience;
    private Gender gender;
}
