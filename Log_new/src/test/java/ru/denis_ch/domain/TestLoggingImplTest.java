package ru.denis_ch.domain;

import org.junit.jupiter.api.Test;

class TestLoggingImplTest {

    @Test
    void calculation1() throws ClassNotFoundException {
        TestLogging testLogging = IoC.createMyClass();
        //TestLogging testLogging = new TestLoggingImpl();

        testLogging.calculation1(100);
        testLogging.calculation2(200);
        testLogging.calculation3(300);
        testLogging.calculation4(400);
        testLogging.calculation5(500);
    }
}