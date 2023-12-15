package com.camunda.bpm.serviceTask.listeners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component("checkHomeAddress")
public class CheckHomeAddreesEndListener implements ExecutionListener {
    private final String DEFAULT_ADDRESS="用户注册地址";
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("进入external task 后置监听!!!");
        System.out.println("输入检查用户地址任务");
        Object homeAddress = delegateExecution.getVariable("homeAddress");
        if (homeAddress==null){
            System.out.println("用户没有填写地址，使用默认地址");
            delegateExecution.setVariable("homeAddress",DEFAULT_ADDRESS);
        }
    }
}
