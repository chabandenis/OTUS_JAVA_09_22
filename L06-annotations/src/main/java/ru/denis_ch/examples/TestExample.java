package ru.denis_ch.examples;

import ru.denis_ch.my_test_framework.After;
import ru.denis_ch.my_test_framework.Before;
import ru.denis_ch.my_test_framework.Test;

public class TestExample {

    @Before
    public void startBefore1() throws Exception {
        System.out.println("        startBefore1");
        throw new Exception();
        //return;
    }

    @Before
    public void startBefore2() {
        System.out.println("        startBefore2");

        return;
    }

    @Before
    public void startBefore3() {
        System.out.println("        startBefore3");
        return;
    }


    @After
    public void startAfter1() {
        System.out.println("        after void1");
        return;
    }

    @After
    public void startAfter2() throws Exception {
        System.out.println("        after void2");
        throw new Exception();
    }

    @After
    public void startAfter3() throws Exception {
        System.out.println("        after void3");
        throw new Exception();
    }


    @Test
    public void startTest1() {
        System.out.println("    test void1");
        return;
    }

    @Test
    public void startTest2() {
        System.out.println("        test void2");
        return;
    }

    @Test
    public void startTest3() throws Exception {
        System.out.println("        test void3");
        throw new Exception();
    }
}