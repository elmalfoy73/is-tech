package org.example;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableJpaRepositories(basePackages = {"org.example"})
@EntityScan(basePackages = {"org.example"})
@ComponentScan(basePackages = {"org.example"})
public class AppConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public Queue addCatQueue() {
        return new Queue("addCatQueue", false, false, true);
    }

    @Bean
    public Queue deleteCatByIdQueue() {
        return new Queue("deleteCatByIdQueue", false, false, true);
    }

    @Bean
    public Queue deleteAllCatQueue() {
        return new Queue("deleteAllCatQueue", false, false, true);
    }

    @Bean
    public Queue getCatByIdQueue() {
        return new Queue("getCatByIdQueue", false, false, true);
    }

    @Bean
    public Queue getAllCatQueue() {
        return new Queue("getAllCatQueue", false, false, true);
    }

    @Bean
    public Queue getAllCatByOwnerIdQueue() {
        return new Queue("getAllCatByOwnerIdQueue", false, false, true);
    }

    @Bean
    public Queue getAllCatByNameQueue() {
        return new Queue("getAllCatByNameQueue", false, false, true);
    }

    @Bean
    public Queue addOwnerQueue() {
        return new Queue("addOwnerQueue", false, false, true);
    }

    @Bean
    public Queue deleteOwnerByIdQueue() {
        return new Queue("deleteOwnerByIdQueue", false, false, true);
    }

    @Bean
    public Queue deleteAllOwnerQueue() {
        return new Queue("deleteAllOwnerQueue", false, false, true);
    }

    @Bean
    public Queue getOwnerByIdQueue() {
        return new Queue("getOwnerByIdQueue", false, false, true);
    }

    @Bean
    public Queue getAllOwnerQueue() {
        return new Queue("getAllOwnerQueue", false, false, true);
    }

    @Bean
    public Queue getOwnerByNameQueue() {
        return new Queue("getOwnerByNameQueue", false, false, true);
    }

    @Bean
    public Queue addFleaQueue() {
        return new Queue("addFleaQueue", false, false, true);
    }

    @Bean
    public Queue deleteFleaByIdQueue() {
        return new Queue("deleteFleaByIdQueue", false, false, true);
    }

    @Bean
    public Queue deleteAllFleaQueue() {
        return new Queue("deleteAllFleaQueue", false, false, true);
    }

    @Bean
    public Queue getByIdQueue() {
        return new Queue("getByIdQueue", false, false, true);
    }

    @Bean
    public Queue getAllFleaQueue() {
        return new Queue("getAllFleaQueue", false, false, true);
    }

    @Bean
    public Queue getAllFleaByCatIdQueue() {
        return new Queue("getAllFleaByCatIdQueue", false, false, true);
    }

    @Bean
    public Queue getFleaByNameQueue() {
        return new Queue("getFleaByNameQueue", false, false, true);
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }


}
