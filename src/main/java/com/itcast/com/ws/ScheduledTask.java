package com.itcast.com.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.itcast.com.mapper.Stumapper;
import com.itcast.com.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


@Component
public class ScheduledTask {
    @Autowired
    private Stumapper stumapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0/3 * * * * ?")
    @Transactional
    public void performTask() throws Exception {
        List<String>stationNames=new ArrayList<>();
        stationNames.add("Dundas");
        stationNames.add("Queen");
        stationNames.add("Finch");
        stationNames.add("King");
        stationNames.add("Union");
        stationNames.add("Bloor");
        stationNames.add("York");
        Collections.shuffle(stationNames);
        //这里随机拿到一个stationName
        String randomStationName = stationNames.get(0);

        //出口
        List<String>exits=new ArrayList<>();
        exits.add("Exits A");
        exits.add("Exits B");
        exits.add("Exits C");
        exits.add("Exits D");
        exits.add("Exits E");
        exits.add("Exits F");
       Collections.shuffle(exits);
        //这里随机拿到一个出口
        String randomExits = exits.get(0);


        List<String> status=new ArrayList<>();
        status.add("Maintenance");
        status.add("Operation");
        Collections.shuffle(status);
        //这里随机拿到一个状态
        String randomStatus = status.get(0);
        Item item =new Item();
        item.setName(randomExits);
        item.setPrice(randomStatus);
        item.setSold(100);
        item.setImage("无图片...............");
        item.setCategory(randomStationName);
        item.setBrand("gucci");
        item.setSpec("20*50*110");
        item.setStock(10000);
        item.setCommentCount(20000);
        item.setStatus(1);
        item.setIsAD(false);
        item.setCreateTime(new Date());
        item.setUpdateTime(new Date());

        Date date = new Date();
        System.out.println(date);
        stumapper.insert(item);
//        JsonMapper jsonMapper=new JsonMapper();
//        String string = jsonMapper.writeValueAsString(item);
        System.out.println("数据库插入数据成功");
//        redisTemplate.opsForValue().set(KEY,status);
//        redisTemplate.opsForValue().set(item.getName(),item.toString());
//        String redis= "dataItem";
//        redisTemplate.opsForZSet().add(redis+item.getName(),item.toString(),System.currentTimeMillis());
//        System.out.println("redis数据保存成功");
    }
}
