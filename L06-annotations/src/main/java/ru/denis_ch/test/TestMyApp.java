package ru.denis_ch.test;

import ru.denis_ch.my_test_framework.TestExe;

public class TestMyApp {
    private static TestExe testExe = new TestExe();

    public static void main(String[] args) {
            testExe.do_test("ru.denis_ch.my_app.TestExample");
    }
}
