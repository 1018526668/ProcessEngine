package com.camunda.bpm.service.impl;

import com.camunda.bpm.service.ProcessService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    /**
     * 开启流程实例
     *
     * @param amount 申请金额
     * @param role 角色
     * @param useFor 资金方向
     * @return java.lang.String
     */
    @Override
    public String startProcess(long amount, String role, String useFor) {
        Map<String, Object> initialVariables = new HashMap<>(1);
        initialVariables.put("amount", amount);
        //TODO 对应dpmn文件暂时只有role
        if (amount >= 10000) {
            initialVariables.put("role", role);
//            initialVariables.put("useFor", useFor);
        }
        Execution execution = runtimeService.startProcessInstanceByKey("businessRuleTask_23120410", initialVariables);
        return "实例启动成功，实例ID：" + execution.getProcessInstanceId();
    }



    /**
     * 任务认领
     *
     * @param processInstanceId 流程实例ID
     * @return java.lang.String
     */
    @Override
    public String assignTask(String processInstanceId) {
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).active().list();
        if (!CollectionUtils.isEmpty(tasks)) {
            taskService.claim(tasks.get(0).getId(), "admin");
            return "任务认领成功，任务ID：" + tasks.get(0).getId() + "，认领人：admin";
        }
        return "无任务可认领";
    }

    /**
     * 任务审核
     *
     * @param taskId 任务ID
     * @return java.lang.String
     */
    public String approveTask(String taskId, boolean passed) {
        List<Task> tasks = taskService.createTaskQuery().taskId(taskId).taskAssignee("admin").list();
        if (!CollectionUtils.isEmpty(tasks)) {
            Map<String, Object> approveVariables = new HashMap<>(1);
            approveVariables.put("passed", passed);
            taskService.complete(taskId, approveVariables);
            return "任务审核完成，审核"
                    + (passed ? "通过" : "拒绝");
        }
        return "无任务可审核";
    }
}
