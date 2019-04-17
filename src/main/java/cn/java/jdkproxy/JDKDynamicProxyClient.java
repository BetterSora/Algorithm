package cn.java.jdkproxy;

import sun.misc.ProxyGenerator;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDKDynamicProxyClient {
    public static void main(String[] args) {
        InvocationHandler handler = new SubjectProxyHandler(ConcreteSubject.class);

        // 运行时动态生存代理类
        ISubject proxy = (ISubject) Proxy.newProxyInstance(JDKDynamicProxyClient.class.getClassLoader(),
                new Class[]{ISubject.class}, handler);

        proxy.action();
        proxy.doSomething();

        // 获取代理类的字节码文件
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", ConcreteSubject.class.getInterfaces());
        try {
            FileOutputStream fos = new FileOutputStream("/Users/qinzhen/Desktop/proxy.class");

            fos.write(classFile, 0, classFile.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}