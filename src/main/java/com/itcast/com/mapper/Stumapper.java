package com.itcast.com.mapper;


import com.itcast.com.pojo.Item;
import com.itcast.com.vo.ItemVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.scheduling.annotation.Scheduled;


import java.util.List;

@Mapper
public interface Stumapper {
//    @Scheduled(cron = "0/5 * * * * ?")
    @Select("select id from hmall.tb_item")
    List<Long> getIds();

    @Select("select * from hmall.tb_item where id=#{id};")
    Item getById(Long id);
    @Insert("insert into hmall.tb_item(name, price, stock, image, category, brand, spec, sold, comment_count, isAD, status, create_time, update_time) values" +
            "(#{name},#{price},#{stock}, #{image}, #{category}, #{brand}, #{spec}, #{sold}, #{commentCount}, #{isAD}, #{status}, #{createTime}, #{updateTime})")
    void insert(Item item);

   // @Select( "select  * from  hmall.tb_item  order by create_time desc  limit 1")
    //@Select("select  * from  hmall.tb_item   where create_time BETWEEN NOW() - INTERVAL 1 MINUTE AND NOW() order by create_time desc")
    @Select("SELECT * FROM tb_item WHERE create_time >= CURDATE() AND create_time < DATE_ADD(CURDATE(), INTERVAL 1 DAY)")
    List<Item> getItem();

    @Select("SELECT count(id) FROM hmall.tb_item WHERE create_time >= CURDATE() AND create_time < DATE_ADD(CURDATE(), INTERVAL 1 DAY)")
    Integer findToday();

    @Select("SELECT count(id) FROM hmall.tb_item")
    Integer findAll();

    @Select("SELECT category, count(category) total FROM hmall.tb_item group by category")
    List<ItemVo> findByGroup();
}

