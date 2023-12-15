package com.camunda.bpm.serviceTask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("DoingRepairService")
public class DoingRepairService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("正在维修家具");
        String processInstanceId = delegateExecution.getProcessInstanceId();
        String currentActivityName = delegateExecution.getCurrentActivityName();
        System.out.println("流程定义id:"+processInstanceId+",当前活动任务名字："+currentActivityName);
    }
}
