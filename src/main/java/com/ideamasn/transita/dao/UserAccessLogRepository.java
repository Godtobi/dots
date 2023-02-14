package com.ideamasn.transita.dao;


import com.ideamasn.transita.model.entity.UserAccessLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface UserAccessLogRepository extends BaseRepository<UserAccessLog, Long> {

    @Query(value = "select distinct ip from dots.user_access_log t1 where (select count(*) from dots.user_access_log t2 where t1.ip = t2.ip) > :limit and t1.date >= :yesterday and t1.date <= :today", nativeQuery = true)
    List<String> dataWithDate(@Param("yesterday") Calendar yesterday, @Param("today") Calendar today,@Param("limit") Long limit);

    @Query(value = "select distinct ip from dots.user_access_log t1 where (select count(*) from dots.user_access_log t2 where t1.ip = t2.ip) > :limit and t1.date between :start and :end ", nativeQuery = true)
    List<String> dataWithDateOnly(@Param("start") String start,@Param("end") String end, @Param("limit") Long limit);

    @Query(value = "select count(*) from dots.user_access_log t1 where t1.ip=:ip and t1.date >= :yesterday and t1.date <= :today", nativeQuery = true)
    Long findIpCount(@Param("yesterday") String yesterday, @Param("today") String today,@Param("ip") String ip);
}
