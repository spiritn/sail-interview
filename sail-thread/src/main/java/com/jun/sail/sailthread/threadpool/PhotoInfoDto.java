package com.jun.sail.sailthread.threadpool;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Jun
 * 创建时间： 2020/3/22
 */
@Data
public class PhotoInfoDto {
    private String url = "565656";

    private LocalDateTime time = LocalDateTime.now();

    private String deviceId = UUID.randomUUID().toString();


}
