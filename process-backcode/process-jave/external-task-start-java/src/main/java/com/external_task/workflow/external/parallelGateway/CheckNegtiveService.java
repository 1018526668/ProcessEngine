package com.external_task.workflow.external.parallelGateway;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckNegtiveService {

    @Bean
    @ExternalTaskSubscription(topicName = "check_Negtive",processDefinitionKeyIn = {"Process_parallel-gateway"},lockDuration = 50000)
    public ExternalTaskHandler checkNegtive(){
        return (externalTask, externalTaskService)->{
            System.out.println("进入检查是否负面影响");
            String videoName = externalTask.getVariable("videoName");
            System.out.println("视频名称是==>"+videoName);
            externalTaskService.complete(externalTask);
        };
    }
}
