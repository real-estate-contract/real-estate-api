package com.realEstate.realEstate.Controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T>{
    private String resultCode;
    private T result;

    // 실패했을 경우
    public static Response<Void> error(String errorCode) {
        return new Response<>(errorCode, null);
    }

    // 성공했을 경우
    public static <T> Response<T> success(T result) {
        return new Response<>("SUCCESS", null);
    }

    public String toStream() {
        if (result == null) {
            return "{" +
                    "\"resultCode\":" + "\"" + resultCode + "\"," +
                    "\"result\":" + null +
                    "}";
        }
        return "{" +
                "\"resultCode\":" + "\"" + resultCode + "\"," +
                "\"result\":" + "\"" + result + "\"," +
                "}";
    }
}
