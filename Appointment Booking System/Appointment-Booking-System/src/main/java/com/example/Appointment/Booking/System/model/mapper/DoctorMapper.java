package com.example.Appointment.Booking.System.model.mapper;

import com.example.Appointment.Booking.System.model.dto.DoctorDto;
import com.example.Appointment.Booking.System.model.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public static DoctorDto toDto(Doctor doctor) {
        DoctorDto dto = new DoctorDto();
        dto.setName(doctor.getName());
        dto.setDegree(doctor.getDegree());
        dto.setDesignation(doctor.getDesignation());
        dto.setEmail(doctor.getUser().getEmail());
        return dto;
    }

    public static Doctor toEntity(DoctorDto dto) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setDegree(dto.getDegree());
        doctor.setDesignation(dto.getDesignation());
        return doctor;
    }
}
