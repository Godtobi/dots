package com.ideamasn.transita.service;

import com.ideamasn.transita.dao.UserAccessLogRepository;
import com.ideamasn.transita.model.entity.UserAccessLog;
import com.ideamasn.transita.model.enums.DurationType;
import com.ideamasn.transita.model.enums.LogComment;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;



@Component
@RequiredArgsConstructor
public class UserAccessLogService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd'.'HH:mm:ss");

    private final UserAccessLogRepository userAccessLogRepository;
    private final BlockedIpService blockedIpService;

    @Value("${duration}")
    private String duration;

    @Value("${limit}")
    private String limit;

    @Value("${start}")
    private String start;

    @Value("${accessFile}")
    private String filePath;

    public void importLogs() {
        try {
            File file = new File(filePath);
            List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
            for(String line : lines){
                String[] splitedLine = line.split("\\|");
                UserAccessLog userAccessLog = new UserAccessLog();
                userAccessLog.setDate(dateFormat.parse(splitedLine[0]));
                userAccessLog.setIp(splitedLine[1]);
                userAccessLog.setDevice(splitedLine[2]);
                userAccessLogRepository.save(userAccessLog);
            }
        }
        catch (Exception exception){
            System.out.println("OUT-ERROR");
            System.out.println(exception.getMessage());
        }
    }

    public void parser()  {
        try {
            Calendar cal1 = DateUtils.toCalendar(dateFormat2.parse(start));
            DateTime dateTime = new DateTime(dateFormat2.parse(start)).plusDays(1).withTimeAtStartOfDay();
            if (duration.equalsIgnoreCase(DurationType.HOURLY.getDuration().toLowerCase())) {
                dateTime = new DateTime(dateFormat2.parse(start)).plusHours(1);
            }
            List<String> res = userAccessLogRepository.dataWithDateOnly(start,dateFormat2.format(dateTime.toDate()), Long.valueOf(limit));
            String comment = duration.equals(DurationType.DAILY.getDuration().toLowerCase()) ? LogComment.DAILY.getComment() : LogComment.HOURLY.getComment();
            System.out.println(Arrays.deepToString(res.toArray()));
            for (String ip : res) {
                Long count = userAccessLogRepository.findIpCount(start, dateFormat2.format(dateTime), ip);
                blockedIpService.createBlock(count, ip, comment);
            }
        }
        catch (Exception exception){
            System.out.println("OUT-ERROR");
            System.out.println(exception.getMessage());
        }
    }

    public void run() {
        this.importLogs();
        this.parser();
    }
}



