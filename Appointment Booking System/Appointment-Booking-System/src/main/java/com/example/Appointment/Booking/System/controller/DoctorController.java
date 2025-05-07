package com.example.Appointment.Booking.System.controller;

import com.example.Appointment.Booking.System.model.entity.Doctor;
import com.example.Appointment.Booking.System.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // Add a new doctor
    @PostMapping("/add")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.addDoctor(doctor);
        return ResponseEntity.ok(savedDoctor);
    }

    // Get doctor by ID
    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long doctorId) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        return ResponseEntity.ok(doctor);
    }

    // Update doctor information
    @PutMapping("/{doctorId}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long doctorId, @RequestBody Doctor updatedDoctor) {
        Doctor updated = doctorService.updateDoctor(doctorId, updatedDoctor);
        return ResponseEntity.ok(updated);
    }

    // Delete doctor
    @DeleteMapping("/{doctorId}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long doctorId) {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok("Doctor deleted successfully");
    }
}
