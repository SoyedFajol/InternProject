package com.example.Appointment.Booking.System.repository;

import com.example.Appointment.Booking.System.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
