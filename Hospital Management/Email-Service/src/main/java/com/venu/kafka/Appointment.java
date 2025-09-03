package com.venu.kafka;



import lombok.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Appointment {

    private Long id;

    
    private Long  patientid;
    private String patientName;
    private String email;
    private String description;
   
   

    private LocalDateTime appointmentDateTime;

    
    private Boolean AcceptStatus;
}

