package com.itcast.com.mapper;


import com.itcast.com.pojo.SurveillanceCountStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CapturesMapper {
    //@Select("SELECT count(id) FROM captures")
    @Select("SELECT count(id) FROM hmall.tb_item")
    // List<Captures> findAll();
    Integer findAll();

    //@Select("SELECT count(id) FROM captures WHERE creat_time >= CURDATE() AND creat_time < DATE_ADD(CURDATE(), INTERVAL 1 DAY)")
    // List<Captures> findToday();
    @Select("SELECT count(id) FROM hmall.tb_item WHERE create_time >= CURDATE() AND create_time < DATE_ADD(CURDATE(), INTERVAL 1 DAY)")
    Integer findToday();

    @Select("SELECT * FROM surveillance_count_statistics")
        // List<Captures> findAll();
    List<SurveillanceCountStatistics> findSur();
}

