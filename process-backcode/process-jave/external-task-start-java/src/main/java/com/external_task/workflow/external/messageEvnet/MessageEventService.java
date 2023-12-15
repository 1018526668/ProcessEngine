package com.external_task.workflow.external.messageEvnet;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageEventService {
    @Bean
    @ExternalTaskSubscription(topicName = "wechatpay",processDefinitionKeyIn = {"Process_messageEvent"},lockDuration = 50000)
    public ExternalTaskHandler wechatPay(){
        return (externalTask, externalTaskService)->{
            System.out.println("进入微信支付");
            externalTaskService.complete(externalTask);
        };
    }

    @Bean
    @ExternalTaskSubscription(topicName = "alipay",processDefinitionKeyIn = {"Process_messageEvent"},lockDuration = 50000)
    public ExternalTaskHandler aliPay(){
        return (externalTask, externalTaskService)->{
            System.out.println("进入阿里支付");
            externalTaskService.complete(externalTask);
        };
    }

    @Bean
    @ExternalTaskSubscription(topicName = "sucess_pay",processDefinitionKeyIn = {"Process_messageEvent"},lockDuration = 50000)
    public ExternalTaskHandler successPay(){
        return (externalTask, externalTaskService)->{
            System.out.println("成功支付");
            externalTaskService.complete(externalTask);
        };
    }

}
