package com.camunda.bpm.userTask.controller;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.model.dmn.instance.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class UserTasksController {
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService  runtimeService;

    @PostMapping("/startTask")
    public String start(@RequestParam("processKey") String processKey){
//        aoji账户创建的流程实例,aoji去请假
        identityService.setAuthenticatedUserId("aoji");
        VariableMap variables = Variables.createVariables();
//        variables.put("isFree",false);
//        串行化任务，执行人，只能按照添加的顺序依次审批，才会到下一个审批人手中
//        并行化 任务，任意一个审批人同意即可
        List<String> leaders = new LinkedList<>();
        leaders.add("aoji");
        leaders.add("test");
        variables.put("leaders",leaders);



        Execution execution = runtimeService.startProcessInstanceByKey(processKey,variables);
        return "实例启动成功，实例ID：" + execution.getProcessInstanceId();
    }

//    @GetMapping("/deleteProcessInstances/{processKey}")
//    public String deleteProcessInstance(@PathVariable("processKey") String processKey){
//
//        deleteAllProcessInstanceByProcessKey(processKey);
//        return "删除成功";
//    }
//
//    private void deleteAllProcessInstanceByProcessKey(String processKey){
//        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery()
//                .processDefinitionId(processKey)
//                .list();
//
//        for (ProcessInstance processInstance : processInstances) {
//            System.out.println(processInstance.getProcessInstanceId());
////            runtimeService.deleteProcessInstance(processInstance.getId(), "Your delete reason");
//        }
//    }

}
