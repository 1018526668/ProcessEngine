package com.camunda.bpm.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * 审批同意委托类
 */
public class AgreeDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("审批【金额："
                + execution.getVariablesLocal().get("amount") + "】已被同意。");
    }
}
