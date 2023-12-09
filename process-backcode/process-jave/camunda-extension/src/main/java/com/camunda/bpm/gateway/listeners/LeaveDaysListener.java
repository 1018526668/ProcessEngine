package com.camunda.bpm.gateway.listeners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component("leaveDaysListener")
public class LeaveDaysListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("进入监听器==========>");
        Long leaveDays = (Long) delegateExecution.getVariable("leaveDays");
        List<String> leaders = new LinkedList<>();
        leaders.add("test");
        if (leaveDays>3&&leaveDays<=5){
            leaders.add("aoji");
        }
        if (leaveDays>5){
            leaders.add("aoji");
            leaders.add("admin");
        }
        delegateExecution.setVariable("leaders",leaders);
    }
}
