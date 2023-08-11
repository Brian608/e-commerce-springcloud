package org.feather.ecommerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.feather.ecommerce.service.SleuthTraceInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: e-commerce-springcloud
 * @package: org.feather.ecommerce.controller
 * @className: SleuthTraceInfoController
 * @author: feather
 * @description: 打印跟踪信息
 * @since: 2023-08-09 7:31
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/sleuth")
public class SleuthTraceInfoController {

    private final SleuthTraceInfoService sleuthTraceInfoService;

    public SleuthTraceInfoController (SleuthTraceInfoService sleuthTraceInfoService){
        this.sleuthTraceInfoService=sleuthTraceInfoService;
    }

    /**
     * 打印日志跟踪信息
     */
        @GetMapping("/trace-info")
        public  void logCurrentTraceInfo(){
          sleuthTraceInfoService.logCurrentTraceInfo();
        }



}
