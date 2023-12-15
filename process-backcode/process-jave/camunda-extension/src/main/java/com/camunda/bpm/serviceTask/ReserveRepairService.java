package com.camunda.bpm.serviceTask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


/**
 * Java class方式，写类的全限定名
 */
public class ReserveRepairService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("进入预约家电维修");
        String processInstanceId = delegateExecution.getProcessInstanceId();
        String currentActivityName = delegateExecution.getCurrentActivityName();
        System.out.println("流程定义id:"+processInstanceId+",当前活动任务名字："+currentActivityName);
    }
}
