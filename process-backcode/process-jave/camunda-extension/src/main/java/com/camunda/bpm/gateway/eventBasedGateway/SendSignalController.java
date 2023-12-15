package com.camunda.bpm.gateway.eventBasedGateway;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendSignalController {
    @Autowired
    private RuntimeService runtimeService;

    @GetMapping("/sendSignal")
    public Boolean sendSignal(){
//        绑定其中信号事件的name
        runtimeService.createSignalEvent("Signal_direct_leader").send();
        return true;
    }
}
