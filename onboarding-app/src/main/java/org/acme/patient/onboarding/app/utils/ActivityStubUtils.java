package org.acme.patient.onboarding.app.utils;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import org.acme.patient.onboarding.app.activities.OnboardingActivities;

import java.time.Duration;

public class ActivityStubUtils {
    // we use setScheduleToCloseTimeout for the demo
    // in order to limit the activity retry time
    // this is done, so we don't have to wait too long in demo to show failure
    public static OnboardingActivities getActivitiesStub() {
        return Workflow.newActivityStub(
                OnboardingActivities.class,
                ActivityOptions.newBuilder()
                        .setScheduleToCloseTimeout(Duration.ofSeconds(60))
                        .setRetryOptions(RetryOptions.newBuilder()
                                .setBackoffCoefficient(1)
                                .build())
                        .build());
    }
}
