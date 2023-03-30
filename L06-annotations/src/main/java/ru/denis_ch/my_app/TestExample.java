package ru.denis_ch.my_app;

import ru.denis_ch.my_test_framework.After;
import ru.denis_ch.my_test_framework.Before;
import ru.denis_ch.my_test_framework.Test;

public class TestExample {

    @Before
    public void startBefore1() throws Exception {
        System.out.println("        do startBefore1");
        throw new Exception("error do startBefore1");
        //return;
    }

    @Before
    public void startBefore2() {
        System.out.println("        do startBefore2");

        return;
    }

    @Before
    public void startBefore3() {
        System.out.println("        do startBefore3");
        return;
    }


    @After
    public void startAfter1() {
        System.out.println("        do after void1");
        return;
    }

    @After
    public void startAfter2() throws Exception {
        System.out.println("        do after void2");
        throw new Exception("error do after void2");
    }

    @After
    public void startAfter3() throws Exception {
        System.out.println("        do after void3");
        throw new Exception("error do after void3");
    }


    @Test
    public void startTest1() {
        System.out.println("    do test void1");
        return;
    }

    @Test
    public void startTest2() {
        System.out.println("        do test void2");
        return;
    }

    @Test
    public void startTest3() throws Exception {
        System.out.println("        do test void3");
        throw new Exception("error do test void3");
    }
}