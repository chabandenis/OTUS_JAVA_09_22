package ru.denis_ch.domain;

import ru.chaban.service.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

public class IoC {

    static Set<String> methods = new HashSet<>();

    static {
        System.out.println("start 1");
        try {
            Class cl = Class.forName("ru.chaban.domain.TestLoggingImpl");
            Method[] method = cl.getMethods();

            for (Method md : method) {
                if (md.isAnnotationPresent(Log.class)) {
                    System.out.println("    methods with log: " + md.getName());
                    methods.add(md.getName());
                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    static TestLogging createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLoggingImpl());
        return (TestLogging) Proxy.newProxyInstance(IoC.class.getClassLoader(), new Class<?>[]{TestLogging.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLogging myClass;

        DemoInvocationHandler(TestLogging myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (IoC.methods.contains(method.getName())) {
                System.out.println("invoking method: " + method);
                int i = 0;
                for (Parameter parameter : method.getParameters()) {
                    System.out.println("Секретное залогированное значение параметра " + parameter.getName() + "=" + args[i++]);
                }
            }
            return method.invoke(myClass, args);
        }
    }
}
