package org.acme.patient.onboarding.app.utils;

import org.acme.patient.onboarding.model.Doctor;
import org.acme.patient.onboarding.model.Hospital;
import org.acme.patient.onboarding.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OnboardingServiceClient {

    private final RestTemplate restTemplate;

    @Value("${onboarding.service.address}")
    String onboardingServiceAddress;

    @Autowired
    public OnboardingServiceClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public Hospital assignHospitalToPatient(String zip) {
        return restTemplate.postForObject(onboardingServiceAddress + "/onboard/assignhospital", zip, Hospital.class);
    }

    public Doctor assignDoctorToPatient(String condition) {
        return restTemplate.postForObject(onboardingServiceAddress + "/onboard/assigndoctor", condition, Doctor.class);
    }

    public Patient notifyPatient(String email) {
        return restTemplate.postForObject(onboardingServiceAddress + "/onboard/notify", email, Patient.class);
    }
}
