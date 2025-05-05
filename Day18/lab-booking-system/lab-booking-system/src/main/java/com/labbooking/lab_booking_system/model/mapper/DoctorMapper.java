package com.labbooking.lab_booking_system.model.mapper;


import com.labbooking.lab_booking_system.model.dto.DoctorDto;
import com.labbooking.lab_booking_system.model.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public DoctorDto doctorToDoctorDTO(Doctor doctor) {
        if (doctor == null) {
            return null;
        }

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setDegree(doctor.getDegree());
        doctorDTO.setDesignation(doctor.getDesignation());

        return doctorDTO;
    }

    public Doctor doctorDTOToDoctor(DoctorDTO doctorDTO) {
        if (doctorDTO == null) {
            return null;
        }

        Doctor doctor = new Doctor();
        doctor.setId(doctorDTO.getId());
        doctor.setName(doctorDTO.getName());
        doctor.setDegree(doctorDTO.getDegree());
        doctor.setDesignation(doctorDTO.getDesignation());

        return doctor;
    }
}
