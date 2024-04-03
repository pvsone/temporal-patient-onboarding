package org.acme.patient.onboarding.app;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.acme.patient.onboarding.app.workflows.OnboardingWorkflow;
import org.acme.patient.onboarding.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/onboard")
public class OnboardingController {

    @Value("${onboarding.task-queue}")
    private String taskQueue;

    @Autowired
    private WorkflowClient workflowClient;

    @PostMapping
    public Patient doOnboard(@RequestBody Patient patient) {
        // start a new workflow execution
        // use the patient id for the unique id
        OnboardingWorkflow workflow = workflowClient.newWorkflowStub(
                OnboardingWorkflow.class, WorkflowOptions.newBuilder()
                        .setWorkflowId(patient.getId())
                        .setTaskQueue(taskQueue)
                        .build());

        return workflow.onboardNewPatient(patient);
    }

    @GetMapping
    public String getStatus(@RequestParam(name = "id") String patientId) {
        // query workflow to get the status message
        try {
            OnboardingWorkflow workflow = workflowClient.newWorkflowStub(OnboardingWorkflow.class, patientId);
            return workflow.getStatus();
        } catch (Exception e) {
            e.printStackTrace();
            return "Unable to query workflow with id: " + patientId;
        }
    }
}
