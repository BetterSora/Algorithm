package cn.java.loader;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.DriverManager;

public class URLClassLoaderTest {
    public static void main(String[] args) throws Exception {
        URL[] urls = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();
        for (URL url : urls) {
            System.out.println(url);
        }

        //DriverManager.getConnection("");

        // 第一个未使用@CallerSensitive注解的类的类加载器
        //Class.forName("");
        // 返回Reflection类
        System.out.println(Reflection.getCallerClass(0));
        // 返回自己的类
        System.out.println(Reflection.getCallerClass(1));
        // 返回调用者的类
        System.out.println(Reflection.getCallerClass(2));

        test();
    }

    @CallerSensitive
    public static void test() {
        Reflection.getCallerClass();
    }
}