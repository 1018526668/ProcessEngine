package com.camunda.bpm.service_blog;

public interface ProcessService {
     String startProcess(long amount, String role, String useFor);

     String assignTask(String processInstanceId);

     String approveTask(String taskId, boolean passed);
}
