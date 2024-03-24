package com.itcast.com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationIssue {
    private int id;
    private String stationName;
    private int totalIssue;
    private int fix_issue;
    private int issue;
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
}
