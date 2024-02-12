package org.example;

import org.example.security.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.example")
@EnableJpaRepositories(basePackages = "org.example")
public class MyApplication {


    AppConfig appConfig = new AppConfig();

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class);
    }


    @Bean
    CommandLineRunner run(RoleService roleService) {
        return args -> {
            var admin = roleService.save(new Role(1L, "ADMIN"));
        };
    }
}