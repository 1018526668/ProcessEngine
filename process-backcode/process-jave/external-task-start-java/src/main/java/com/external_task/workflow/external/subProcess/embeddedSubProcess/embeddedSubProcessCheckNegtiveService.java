package com.external_task.workflow.external.subProcess.embeddedSubProcess;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class embeddedSubProcessCheckNegtiveService {
    @Bean
    @ExternalTaskSubscription(topicName = "subProcess_check_Negtive",processDefinitionKeyIn = {"Process_subProcess"},lockDuration = 50000)
    public ExternalTaskHandler subCheckNegtive(){
        return (externalTask, externalTaskService)->{
            System.out.println("进入检查是否负面影响");
            String videoName = externalTask.getVariable("videoName");
            System.out.println("视频名称是==>"+videoName);
            externalTaskService.complete(externalTask);
        };
    }
}
