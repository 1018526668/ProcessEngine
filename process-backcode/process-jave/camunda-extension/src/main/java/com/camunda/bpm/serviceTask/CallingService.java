package com.camunda.bpm.serviceTask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Service("callService")
public class CallingService {
    public void call(DelegateExecution delegateExecution){
        System.out.println("正在电话回访");
    }
}
