package ru.denis_ch.my_test_framework;

import java.lang.reflect.Method;

public class Description {
    public String getWhenStop() {
        return whenStop;
    }

    private String whenStop;
    private Method method;
    private Method reason;

    public Description(String whenStop, Method method, Method reason) {
        this.whenStop = whenStop;
        this.method = method;
        this.reason = reason;
    }

    public Method getMethod() {
        return method;
    }

    public Method getReason() {
        return reason;
    }
}
