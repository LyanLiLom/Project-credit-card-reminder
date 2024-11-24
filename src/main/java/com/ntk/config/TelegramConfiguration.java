package com.ntk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import com.ntk.telegram.TransactionConsumer;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class TelegramConfiguration {

    @Value("${telegram.token}")
    private String token;

    @Bean
    public TelegramClient telegramClient() {
        return new OkHttpTelegramClient(token);
    }
}
