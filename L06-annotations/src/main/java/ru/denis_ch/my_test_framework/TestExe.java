package ru.denis_ch.my_test_framework;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestExe {

    private List<Method> errorsBefore = new ArrayList<>();
    private List<Method> errorsTests = new ArrayList<>();
    private List<Method> errorsAfter = new ArrayList<>();

    private int allCntBefore;
    private int allCntTest;
    private int allCntAfter;

    private void showStatistic()
    {
        System.out.println("Launch statistics");

        // test before
        System.out.println("Total tests before " + allCntBefore + "; success " + (allCntBefore - errorsBefore.size()) + "; errors " + errorsBefore.size());
        for (Method m : errorsBefore) {
            System.out.println("    Error in method before " + m.getName());
        }

        // tests
        System.out.println("tests " + allCntTest + "; success " + (allCntTest - errorsTests.size()) + "; errors " + errorsTests.size());
        for (Method m : errorsTests) {
            System.out.println("    Error in test " + m.getName());
        }

        // tests after
        System.out.println("Total tests after " + allCntAfter + "; success " + (allCntAfter - errorsAfter.size()) + "; errors " + errorsAfter.size());
        for (Method m : errorsAfter) {
            System.out.println("    Error in test after " + m.getName());
        }
    }


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

        boolean raiseBefore = false;
        boolean raiseTest = false;
        boolean raiseAfter = false;

        for (Method test : methodsTest) {
            System.out.println("Checking the method " + test.getName() + "; " + test.getParameterCount());

            raiseBefore = false;
            raiseTest = false;
            raiseAfter = false;

            Object object = null;
            try {
                object = cl.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            System.out.println("    ru.denisch.Before ");
            for (Method before : methodsBefore) {
                if (raiseBefore == false) {
                    System.out.println("        method: " + before.getName());
                    allCntBefore++;
                    try {
                        before.invoke(object);
                    } catch (Exception e) {
                        System.out.println("            error during running");
                        raiseBefore = true;
                        errorsBefore.add(before);
                    }
                }
            }

            if (raiseBefore == false) {
                allCntTest++;
                System.out.println("    ru.denisch.Test " + test);
                try {
                    test.invoke(object);
                } catch (Exception e) {
                    raiseTest = true;
                    errorsTests.add(test);
                }
            }

            if (raiseBefore == false && raiseTest == false) {
                System.out.println("    ru.denisch.After ");
                for (Method after : methodsAfter) {
                    if (raiseAfter == false) {
                        System.out.println("        method: " + after.getName());
                        allCntAfter++;
                        try {
                            after.invoke(object);
                        } catch (Exception e) {
                            System.out.println("            error during running: ");
                            raiseAfter = true;
                            errorsAfter.add(after);
                        }
                    }
                }
            }
        }
        showStatistic();
    }
}
