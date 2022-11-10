package ru.denis_ch.my_test_framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class TestExe {
    private int allCntBefore;
    private int allCntTest;
    private int allCntAfter;

    private int errCntBefore;
    private int errCntTest;
    private int errCntAfter;


    public void doTest(String className) {

        Set<Method> setBefor = new HashSet<>();
        Set<Method> setTest = new HashSet<>();
        Set<Method> setAfter = new HashSet<>();

        Class cl = null;
        try {
            cl = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Method[] method = cl.getMethods();

        for (Method md : method) {
            if (md.isAnnotationPresent(Before.class)) {
                setBefor.add(md);
            } else if (md.isAnnotationPresent(Test.class)) {
                setTest.add(md);
            } else if (md.isAnnotationPresent(After.class)) {
                setAfter.add(md);
            }
        }

        System.out.println("methods with annotation @Before " + setBefor.size());
        System.out.println("methods with annotation @After " + setAfter.size());
        System.out.println("methods with annotation @Test " + setTest.size());

        for (Method test : setTest) {
            System.out.println("Checking the method " + test.getName() + "; " + test.getParameterCount());

            Object object = null;
            try {
                object = cl.getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

            System.out.println("    ru.denisch.Before ");
            for (Method before : setBefor) {
                allCntBefore++;
                try {
                    before.invoke(object);
                } catch (Exception e) {
                    errCntBefore++;
                }
            }

            allCntTest++;
            System.out.println("    ru.denisch.Test " + test);
            try {
                test.invoke(object);
            } catch (Exception e) {
                errCntTest++;
            }


            System.out.println("    ru.denisch.After ");
            for (Method after : setAfter) {
                allCntAfter++;
                try {
                    after.invoke(object);
                } catch (Exception e) {
                    errCntAfter++;
                }
            }

        }

        System.out.println("Launch statistics");

        System.out.println("Total tests before " + allCntBefore + "; success " + (allCntBefore - errCntBefore) + "; errors " + errCntBefore);
        // тестов
        System.out.println("tests " + allCntTest + "; success " + (allCntTest - errCntTest) + "; errors " + errCntTest);
        // всего тестов после
        System.out.println("Total tests after " + allCntAfter + "; success " + (allCntAfter - errCntAfter) + "; errors " + errCntAfter);

    }
}
