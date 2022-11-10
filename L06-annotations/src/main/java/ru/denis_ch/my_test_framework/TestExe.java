package ru.denis_ch.my_test_framework;

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

        Set<Method> methodsBefore = new HashSet<>();
        Set<Method> methodsTest = new HashSet<>();
        Set<Method> methodsAfter = new HashSet<>();

        Class cl = null;
        try {
            cl = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Method[] method = cl.getMethods();

        for (Method md : method) {
            if (md.isAnnotationPresent(Before.class)) {
                methodsBefore.add(md);
            } else if (md.isAnnotationPresent(Test.class)) {
                methodsTest.add(md);
            } else if (md.isAnnotationPresent(After.class)) {
                methodsAfter.add(md);
            }
        }

        System.out.println("methodsTest with annotation @Before " + methodsBefore.size());
        System.out.println("methodsTest with annotation @After " + methodsAfter.size());
        System.out.println("methodsTest with annotation @Test " + methodsTest.size());

        for (Method test : methodsTest) {
            System.out.println("Checking the method " + test.getName() + "; " + test.getParameterCount());

            Object object = null;
            try {
                object = cl.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            System.out.println("    ru.denisch.Before ");
            for (Method before : methodsBefore) {
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
            for (Method after : methodsAfter) {
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
