package com.external_task.workflow.external;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class SelfRepairService {
    @Bean
    /**
     * topicName 流程中外部任务的topic,processDefinitionKeyIn 这个外部任务定义在哪个流程定义中，
     * lockDuration 锁定时间，会覆盖掉配置文件中的全局的配置，这个时间执行这个任务，其它活动不能调用这个任务，
     * 也就是这个时间要>这个外部任务的执行完的时间
     */
    @ExternalTaskSubscription(topicName = "try_self_repair",processDefinitionKeyIn = {"Process_Repair23120811"},lockDuration = 50000)
    public ExternalTaskHandler doSelfRepair(){
        return (externalTask, externalTaskService)->{
            System.out.println("外部任务尝试自修");
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            Boolean isFree = (Boolean) externalTask.getVariable("isFree");
            if(isFree){
                System.out.println("免费维修");
//                1 是重试的次数，5000是时间间隔
                externalTaskService.handleFailure(externalTask,"维修是免费的我不想自修了","这里打印异常stacktrace",1,5000);
            }else {
                System.out.println("收费维修");
//                externalTask 这里是自修
                externalTaskService.complete(externalTask);
            }
        };
    }
}
