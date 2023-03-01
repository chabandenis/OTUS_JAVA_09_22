package ru.denis_ch.domain;

import ru.denis_ch.service.Log;

public class TestLoggingImpl implements TestLogging {

    @Log
    public void calculation1(int param001) {
        System.out.println("param001 : " + param001);
    }

    public void calculation2(int param002) {
        System.out.println("param002 : " + param002);
    }

    @Log
    public void calculation3(int param003) {
        System.out.println("param003 : " + param003);
    }

    public void calculation4(int param004) {
        System.out.println("param004 : " + param004);
    }

    @Log
    public void calculation5(int param005) {
        System.out.println("param005 : " + param005);
    }

}
