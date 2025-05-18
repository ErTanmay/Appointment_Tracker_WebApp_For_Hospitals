package com.tsl.appointmenttracker.service;

import com.tsl.appointmenttracker.model.AppointmentDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {

    public AppointmentDetails addAppointment(AppointmentDetails appointmentDetails);

    public List<AppointmentDetails> getAllAppointments();

    public void deleteAppointment(int id);
}
