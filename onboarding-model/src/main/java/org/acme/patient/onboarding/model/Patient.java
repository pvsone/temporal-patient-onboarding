package org.acme.patient.onboarding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private String id;
    private String name;
    private String age;
    private String zip;
    private String insurance;
    private String insuranceId;
    private String condition;
    private Hospital hospital;
    private Doctor doctor;
    private String onboarded;
    private String phone;
    private String email;
    private ContactMethod contactMethod = ContactMethod.TEXT;
}
