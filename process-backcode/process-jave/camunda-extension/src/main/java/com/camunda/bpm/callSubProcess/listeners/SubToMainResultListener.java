package com.camunda.bpm.callSubProcess.listeners;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

/**
 * 验证子流程映射输出到主流程：这里设置子流程本地参数targetResult，引擎自动映射到主流程的result
 */
@Component("subToMainResult")
public class SubToMainResultListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) {
        delegateExecution.setVariable("targetResult",true);
        System.out.println("进入到子流程设置本地变量结果targetResult==>"+delegateExecution.getVariable("targetResult"));
    }
}
