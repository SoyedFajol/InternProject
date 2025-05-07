package com.example.Appointment.Booking.System.service;

import com.example.Appointment.Booking.System.model.entity.Doctor;
import com.example.Appointment.Booking.System.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    // Add a new doctor
    public Doctor addDoctor(Doctor doctor) {
        doctor.setUser(doctor.getUser());  // Ensure user is associated with doctor
        return doctorRepository.save(doctor);
    }

    // Get doctor by ID
    public Doctor getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    // Update doctor information
    public Doctor updateDoctor(Long doctorId, Doctor updatedDoctor) {
        Doctor existingDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        existingDoctor.setName(updatedDoctor.getName());
        existingDoctor.setDegree(updatedDoctor.getDegree());
        existingDoctor.setDesignation(updatedDoctor.getDesignation());

        return doctorRepository.save(existingDoctor);
    }

    // Delete doctor
    public void deleteDoctor(Long doctorId) {
        Doctor existingDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctorRepository.delete(existingDoctor);
    }
}
