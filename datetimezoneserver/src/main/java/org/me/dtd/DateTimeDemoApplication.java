package org.me.dtd;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class DateTimeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DateTimeDemoApplication.class, args);
    }

    @PostConstruct
    void started() {
        // TimeZone.setDefault(TimeZone.getTimeZone("BST"));
    }

}


