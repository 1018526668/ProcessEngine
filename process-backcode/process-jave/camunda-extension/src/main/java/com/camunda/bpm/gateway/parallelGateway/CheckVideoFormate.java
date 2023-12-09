package com.camunda.bpm.gateway.parallelGateway;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("checkVideoFormate")
public class CheckVideoFormate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("进入视频格式校验===");
        String videoName = (String) delegateExecution.getVariable("videoName");
        System.out.println("视频的名称是==>"+videoName);
    }
}
