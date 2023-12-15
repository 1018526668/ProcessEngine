package com.camunda.bpm.serviceTask.listeners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component("noticeCustomer")
public class NoticeUserStartListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("进入service task 前置监听!!!!");
        System.out.println("地址已填写为:"+delegateExecution.getVariable("homeAddress")+"修理员工正在前往修理的路上");
        Object homeAddress = delegateExecution.getVariable("homeAddress");
        System.out.println("修理工马上着手修理");
    }
}
