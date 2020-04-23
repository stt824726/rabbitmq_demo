package com.stt.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue DAMessage() {
        return new Queue("direct.A");
    }

    @Bean
    public Queue DBMessage() {
        return new Queue("direct.B");
    }

    @Bean
    public Queue DCMessage() {
        return new Queue("direct.C");
    }

    @Bean
    public Queue DDMessage() {
        return new Queue("direct.D");
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    Binding bindingExchangeDA(Queue DAMessage, DirectExchange directExchange) {
        return BindingBuilder.bind(DAMessage).to(directExchange).with("direct");
    }

    @Bean
    Binding bindingExchangeDB(Queue DBMessage, DirectExchange directExchange) {
        return BindingBuilder.bind(DBMessage).to(directExchange).with("direct");
    }

    @Bean
    Binding bindingExchangeDC(Queue DCMessage, DirectExchange directExchange) {
        return BindingBuilder.bind(DCMessage).to(directExchange).with("direct");
    }

    @Bean
    Binding bindingExchangeDD(Queue DDMessage, DirectExchange directExchange) { 
        return BindingBuilder.bind(DDMessage).to(directExchange).with("direct_A");
    }
}
