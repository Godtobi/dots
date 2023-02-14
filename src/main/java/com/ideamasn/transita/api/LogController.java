package com.ideamasn.transita.api;

import com.ideamasn.transita.service.UserAccessLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dot")
public class LogController {


    private final UserAccessLogService userAccessLogService;

    public LogController(UserAccessLogService userAccessLogService) {
        this.userAccessLogService = userAccessLogService;
    }


    @GetMapping
    public void dot()throws Exception {
        //userAccessLogService.importLogs();
        userAccessLogService.parser();
    }

}
