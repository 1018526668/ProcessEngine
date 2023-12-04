package com.camunda.bpm.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * 审批拒绝委托类
 */
public class RejectDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("审批【金额：" + execution.getVariables().get("amount")
                + ", 角色：" + execution.getVariables().get("role")
                + ", 资金方向：" + execution.getVariables().get("usefor") + "】已被拒绝。");
    }
}