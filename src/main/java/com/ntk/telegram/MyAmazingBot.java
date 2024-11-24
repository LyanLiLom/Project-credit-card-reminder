package com.ntk.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import com.ntk.config.TelegramConfiguration;
import com.ntk.telegram.consumer.TransactionConsumerImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyAmazingBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    
    private final TelegramConfiguration configuration;

    private final TelegramClient telegramClient;

    private final TransactionConsumerImpl transactionConsumer;

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            try {
                
                SendMessage message = transactionConsumer.handleConsumer(update);
                if(message!= null) {
                    telegramClient.execute(message);
                }
            } catch (TelegramApiException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    @Override
    public String getBotToken() {
        return configuration.getToken();
    }
    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }
}
