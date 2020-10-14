package com.pet.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Log {

    private Integer log_id;
    private Date log_time;
    private String log_detail;

    public Date getLog_time() {
        return log_time;
    }

    public void setLog_time(Date log_time) {
        this.log_time = log_time;
    }

    public String getLog_detail() {
        return log_detail;
    }

    public void setLog_detail(String log_detail) {
        this.log_detail = log_detail;
    }
}
