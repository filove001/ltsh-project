package com.ltsh.chat.service.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by fengjianbo on 2018/1/9.
 */
@Component
@Data
public class GlobalConfig {
    @Value("tokenTimes")
    private int tokenTimes;
    @Value("randomTimes")
    private int randomTimes;

}
