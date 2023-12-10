package com.camunda.bpm.callSubProcess.listeners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component("mainProcessResult")
public class MainProcessResultListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("进入主流程获取结果==>");
        System.out.println("结果是==>"+delegateExecution.getVariable("result"));
    }
}
