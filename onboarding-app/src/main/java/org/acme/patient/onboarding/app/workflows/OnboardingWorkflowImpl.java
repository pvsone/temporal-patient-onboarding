package org.acme.patient.onboarding.app.workflows;

import org.acme.patient.onboarding.app.activities.OnboardingActivities;
import org.acme.patient.onboarding.app.utils.ActivityStubUtils;
import org.acme.patient.onboarding.model.Patient;

public class OnboardingWorkflowImpl implements OnboardingWorkflow {

    private final OnboardingActivities activities = ActivityStubUtils.getActivitiesStub();

    private String status;

    @Override
    public Patient onboardNewPatient(Patient patient) {
        try {
            // 1. assign hospital to patient
            status = "Assigning hospital to patient: " + patient.getName();
            patient.setHospital(
                    activities.assignHospitalToPatient(patient.getZip()));

            // 2. assign doctor to patient
            status = "Assigning doctor to patient: " + patient.getName();
            patient.setDoctor(
                    activities.assignDoctorToPatient(patient.getCondition()));

            // 3. notify patient with preferred contact method
            status = "Notifying patient: " + patient.getName();
            switch (patient.getContactMethod()) {
                case PHONE:
                    activities.notifyViaEmail(patient.getEmail());
                    break;
                case TEXT:
                    activities.notifyViaText(patient.getPhone());
                    break;
            }

            // 4. finalize onboarding
            status = "Finalizing onboarding for: " + patient.getName();
            patient.setOnboarded(
                    activities.finalizeOnboarding());
        } catch (Exception e) {
            patient.setOnboarded("no");
        }

        return patient;
    }

    @Override
    public String getStatus() {
        return status;
    }

}
