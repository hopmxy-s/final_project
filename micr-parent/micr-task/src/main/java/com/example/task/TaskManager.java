package com.example.task;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.api.service.IncomeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("taskManager")
public class TaskManager {

//    @Scheduled(cron = "01 12 13 * * ?")
//    public void testCron(){
//        System.out.println("执行了定时任务的方法：" + new Date());
//    }
    @DubboReference(interfaceClass = IncomeService.class, version = "1.0")
    private IncomeService incomeService;
    @Scheduled(cron = "0 0 1 * * ?")
    public void invokeGenerateIncomePlan(){
        incomeService.generateIncomePlan();
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void invokeGenerateIncomeBack() {
        incomeService.generateIncomeBack();
    }


}
