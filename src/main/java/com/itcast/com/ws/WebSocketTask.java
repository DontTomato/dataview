package com.itcast.com.ws;


import com.fasterxml.jackson.databind.json.JsonMapper;
import com.itcast.com.dto.CapturesDTO;
import com.itcast.com.mapper.CapturesMapper;
import com.itcast.com.mapper.Stumapper;
import com.itcast.com.pojo.Item;
import com.itcast.com.pojo.SurveillanceCountStatistics;
import com.itcast.com.service.CapturesCountService;
import com.itcast.com.vo.ItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class WebSocketTask {
    @Autowired
    private WebSocketServer webSocketServer;
    @Autowired
    private WebSocketServer6 webSocketServer6;
    @Autowired
    private WebSocketServer2 webSocketServer2;
    @Autowired
    WebSocketServer3 webSocketServer3;
    @Autowired
    private WebSocketServer4 webSocketServer4;
    @Autowired
    private Stumapper stumapper;
    @Autowired
    private CapturesMapper capturesMapper;
//    @Autowired
//    private CapturesCountService capturesCountService;

    @Scheduled(cron = "0/5 *  * * * ?") //巡查视频问题 左上角
   // @Scheduled(cron = "0 0/1 * * * ?")
    public void sendMessageToClient() throws Exception {
        //从数据库内查到一分钟前的数据
        List<Item> item = stumapper.getItem();
        String message="这是来自服务端的消息：" + item.toString();
        JsonMapper jsonMapper=new JsonMapper();
        String string = jsonMapper.writeValueAsString(item);
//        System.out.println(string);
        webSocketServer.sendToAllClient(string);
    }


 @Scheduled(cron = "0/5 * * * * ?")  //system anno
  //  @Scheduled(cron = "0 0/1 * * * ?")
    public void sendMessageToClient1() throws Exception {
        //这里我查的表 单元 你要内容在这里改就行
        List<Item> item = stumapper.getItem();
        System.out.println(item.size());
        String message="这是来自服务端1的消息：" + item.toString();
        JsonMapper jsonMapper=new JsonMapper();
        String string = jsonMapper.writeValueAsString(item);
//        System.out.println(string);
        webSocketServer6.sendToAllClient(string);
    }

    @Scheduled(cron = "0/5 *  * * * ?")
//    @Scheduled(cron = "0 0/1 * * * ?")    //圆圈
    public void sendMessageToClient2() throws Exception {
        Integer today = stumapper.findToday();
        Integer all = stumapper.findAll();
        CapturesDTO nmubers=new CapturesDTO();
        nmubers.setTodayNumber(today);
        nmubers.setTotalNumber(all);
        JsonMapper jsonMapper =new JsonMapper();
        String number = jsonMapper.writeValueAsString(nmubers);
        webSocketServer2.sendToAllClient(number);
    }

    @Scheduled(cron = "0/5 *  * * * ?")
//    @Scheduled(cron = "0 0/1 * * * ?")    //抓拍数据统计
    public void sendMessageToClient3() throws Exception {
        //Todo
        //SurveillanceCountStatistics surveillanceCountStatistics=new SurveillanceCountStatistics();
        List<SurveillanceCountStatistics> sur = capturesMapper.findSur();
        //surveillanceCountStatistics.setStationName("Dundas");
        //surveillanceCountStatistics.setCameraNumber(15);
        //surveillanceCountStatistics.setCreateTime(new Date());
        //surveillanceCountStatistics.setUpdateTime(new Date());
//        System.out.println(sur+"8888888888888888888888888888888888888");
        JsonMapper jsonMapper =new JsonMapper();
        String number = jsonMapper.writeValueAsString(sur);
        webSocketServer3.sendToAllClient(number);
    }

    @Scheduled(cron = "0/5 *  * * * ?")
//    @Scheduled(cron = "0 0/1 * * * ?")    //右上角 全市点位统计
    public void sendMessageToClient4() throws Exception {

        List<ItemVo> byGroup = stumapper.findByGroup();

        String category = byGroup.stream().map(ItemVo::getCategory).collect(Collectors.joining("-"));
        String total = byGroup.stream().map(ItemVo::getTotal).collect(Collectors.toList()).stream()
                .map(Object::toString)
                .reduce((a, b) -> a + "-"+b)
                .orElse("");
//        System.out.println(category);
//        System.out.println(total);
        List<String> sus =new ArrayList<>();
        sus.add(category);
        sus.add(total);
        JsonMapper jsonMapper =new JsonMapper();
        String number = jsonMapper.writeValueAsString(sus);
        System.out.println(number);
        webSocketServer4.sendToAllClient(number);

    }
}

