package com.camunda.bpm.delegate_blog;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendTaskDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.getProcessEngineServices()
                .getRuntimeService()
                .createMessageCorrelation("message")
                .processInstanceBusinessKey("messageBusinessKey")
                .correlate();
    }
}
