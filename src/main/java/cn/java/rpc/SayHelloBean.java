package cn.java.rpc;

public class SayHelloBean implements SayHello {
    @Override
    public String sayHello() {
        return "hello";
    }
}
