package com.camunda.bpm.sendTask;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("sendTask")
public class sendTask implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("send task 发送消息");
        RuntimeService runtimeService = delegateExecution.getProcessEngineServices().getRuntimeService();
//        接收消息的任务设置的消息id
        runtimeService.createMessageCorrelation("Message_receiveTask_20120908")
//                启动接收任务时输入的BusinessKey,随便输入，唯一就行。用于对应业务绑定。不输入默认为null。
//                作用是：send-receive是成双成对的，接收任务一个流程定义可能有多个实例，每个实例指定一个业务id，这样发送消息就可以绑定到接收任务的对应实例
                .processInstanceBusinessKey("receiveTaskBusinessKey")
                .correlate();
    }
}
