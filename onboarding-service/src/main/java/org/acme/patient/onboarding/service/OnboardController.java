package org.acme.patient.onboarding.service;

import org.acme.patient.onboarding.model.Doctor;
import org.acme.patient.onboarding.model.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/onboard")
public class OnboardController {

    @Autowired
    private final List<Hospital> hospitals;

    @Autowired
    private final List<Doctor> doctors;

    @Autowired
    public OnboardController(List<Hospital> hospitals, List<Doctor> doctors) {
        this.hospitals = hospitals;
        this.doctors = doctors;
    }

    @PostMapping("/assignhospital")
    public Hospital assignHospitalToPatient(String zip) {
        Hospital hospital = hospitals.stream().filter(h -> h.zip().equals(zip))
                .findFirst()
                .orElse(new Hospital("Local Hospital", "123 Local Street", "555-55-5555", "12345"));

        return hospital;
    }

    @PostMapping("/assigndoctor")
    public Doctor assignDoctorToPatient(String condition) {
        Doctor doctor = doctors.stream().filter(d -> d.specialty().equals(condition))
                .findFirst()
                .orElse(new Doctor("Michael Scott", "img/docfemale.png", "General"));
        return doctor;
    }

    @PostMapping("/notify")
    public void notifyPatient(String contact) {
        // do nothing here for demo...
        // irl would send email or text message or both
    }
}
