package com.tsl.appointmenttracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppointmentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentType;
    private String appointmentStatus;
    private String patientName;
    private String mobileNumber;
    private String doctorName;
    private String appointmentDescription;


}
