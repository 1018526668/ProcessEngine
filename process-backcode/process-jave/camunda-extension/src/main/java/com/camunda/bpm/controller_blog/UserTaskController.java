package com.camunda.bpm.controller_blog;

import com.camunda.bpm.service_blog.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserTaskController {

    @Autowired
    ProcessService processService;


    /**
     * 启动实例
     * @param amount 金额数码
     * @param role 角色
     * @param useFor 用途
     * @return
     */
    @PostMapping("/start")
    public String startProcess(@RequestParam long amount,
                               @RequestParam(required = false) String role,
                               @RequestParam(required = false) String useFor) {
        return processService.startProcess(amount, role, useFor);
    }

    /**
     * 任务认领
     * @param processInstanceId 实例id
     * @return
     */
    @PostMapping("/assign")
    public String assignTask(@RequestParam String processInstanceId) {
        return processService.assignTask(processInstanceId);
    }

    /**
     * 任务审核
     * @param taskId 任务id
     * @param passed
     * @return
     */
    @PostMapping("/approve")
    public String approveTask(@RequestParam String taskId, @RequestParam boolean passed) {
        return processService.approveTask(taskId, passed);
    }




}
