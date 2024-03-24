package com.itcast.com.service;



import com.itcast.com.dto.CapturesDTO;
import org.springframework.stereotype.Service;

@Service
public interface CapturesCountService {

    public CapturesDTO findNmubers();

}
