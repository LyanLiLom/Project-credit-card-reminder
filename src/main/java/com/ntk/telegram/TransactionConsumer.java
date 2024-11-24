package com.ntk.telegram;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public interface TransactionConsumer {

    public SendMessage handleConsumer(Update update);
}
