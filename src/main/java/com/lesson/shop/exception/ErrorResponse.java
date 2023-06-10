package com.lesson.shop.exception;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Data
@NoArgsConstructor
public class ErrorResponse {
    private Integer status;
    private List<String> messages;
    private ErrorMeta meta;

    public ErrorResponse(Integer status, String message, String path) {
        this.status = status;
        this.messages = List.of(message);
        this.meta = new ErrorMeta(path);
    }

    public ErrorResponse(Integer status, List<String> messages, String path) {
        this.status = status;
        this.messages = messages;
        this.meta = new ErrorMeta(path);
    }

    @Data
    @NoArgsConstructor
    private static class ErrorMeta {
        private Long timestamp;
        private String path;

        public ErrorMeta(String path) {
            this.path = path;
            this.timestamp = System.currentTimeMillis();
        }
    }
}
