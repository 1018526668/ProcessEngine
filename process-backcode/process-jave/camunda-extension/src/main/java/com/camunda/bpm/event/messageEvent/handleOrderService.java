package com.camunda.bpm.event.messageEvent;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("handleOrder")
public class handleOrderService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("处理订餐订单");
        String endpoint = (String) delegateExecution.getVariable("endpoint");
        RuntimeService runtimeService = delegateExecution.getProcessEngineServices().getRuntimeService();
        if ("wechat".equals(endpoint)){
//      Message_wechatpay、   Message_alipay   是两个事件的name
            runtimeService.startProcessInstanceByMessage("Message_wechatpay");
        } else if ("alipay".equals(endpoint)) {
            runtimeService.startProcessInstanceByMessage("Message_alipay");
        }
    }
}
