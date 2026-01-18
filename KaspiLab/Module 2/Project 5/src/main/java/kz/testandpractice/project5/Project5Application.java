package kz.testandpractice.project5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
public class Project5Application {
    public static void main(String[] args) {
        SpringApplication.run(Project5Application.class, args);
    }
}
