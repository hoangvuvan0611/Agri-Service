package com.hvv.agriservice.utils;

import com.hvv.agriservice.config.lang.Translator;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class TranslatorUtils implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static String toLocale(String message, Object... args) {
        Translator translator = context.getBean(Translator.class);
        return translator.toLocale(message, args);
    }
}
