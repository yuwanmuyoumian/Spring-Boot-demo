package com.spring.lemon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
////必须加这个，不加报错，如果不加，也可以在每个mapper.java上添加@Mapper注释
@MapperScan("com.spring.lemon.mapper")
public class LemonApplication
{
    public static void main(String[] args) {
        SpringApplication.run(LemonApplication.class, args);
    }
}
