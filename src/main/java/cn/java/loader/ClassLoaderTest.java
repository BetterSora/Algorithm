package cn.java.loader;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    System.out.println("name:" + name);
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    System.out.println("fileName:" + fileName);
                    InputStream is = getClass().getResourceAsStream(fileName);

                    if (is == null) {
                        return super.loadClass(name);
                    }

                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myLoader.loadClass("cn.java.loader.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());  //class ClassLoaderTest
        System.out.println(obj instanceof ClassLoaderTest);  //false
    }
}
