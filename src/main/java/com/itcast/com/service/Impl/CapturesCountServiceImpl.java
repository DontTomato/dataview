package com.itcast.com.service.Impl;



import com.itcast.com.dto.CapturesDTO;
import com.itcast.com.mapper.CapturesMapper;
import com.itcast.com.service.CapturesCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CapturesCountServiceImpl implements CapturesCountService {

    @Autowired
    private CapturesMapper capturesMapper;

    public CapturesDTO findNmubers() {
        CapturesDTO capturesDTO = new CapturesDTO();
        Integer all = capturesMapper.findAll();
        Integer today = capturesMapper.findToday();
        capturesDTO.setTotalNumber(all);
        capturesDTO.setTodayNumber(today);
        return capturesDTO;
    }





}
