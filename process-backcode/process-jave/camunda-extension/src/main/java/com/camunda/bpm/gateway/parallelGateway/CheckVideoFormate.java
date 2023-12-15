package com.camunda.bpm.gateway.parallelGateway;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("checkVideoFormate")
public class CheckVideoFormate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("进入视频格式校验===");
//        videoName是之前只有主流程的主流程的全局参数
//        String videoName = (String) delegateExecution.getVariable("videoName");
//        targetVideoName是子流程接收主流程的接收本地参数
        String videoName = (String) delegateExecution.getVariable("targetVideoName");
        System.out.println("视频的名称是==>"+videoName);
    }
}
