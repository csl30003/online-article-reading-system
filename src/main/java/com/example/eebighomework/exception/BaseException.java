package com.example.eebighomework.exception;

import lombok.Getter;

/**
 * <p>
 * 基础异常类
 * </p>
 */
@Getter
public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }
}