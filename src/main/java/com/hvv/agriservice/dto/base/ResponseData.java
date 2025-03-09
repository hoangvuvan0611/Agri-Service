package com.hvv.agriservice.dto.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hvv.agriservice.config.lang.Translator;
import com.hvv.agriservice.utils.TranslatorUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> {
    private int status;
    private String message;
    private T data;

    public static <T> ResponseData<T> success(String message, T data) {
        return new ResponseData<>(200, message, data);
    }

    public static <T> ResponseData<T> error(int status, String message) {
        return new ResponseData<>(status, message, null);
    }
}
