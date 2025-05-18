package com.tsl.appointmenttracker.dao;

import com.tsl.appointmenttracker.model.AppointmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDeatilsDAO extends JpaRepository<AppointmentDetails, Integer> {

    @Override
    <S extends AppointmentDetails> S save(S entity);

    @Override
    void deleteById(Integer integer);
}
