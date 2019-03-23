package cn.java.rpc;

public class RPCCTest {
    public static void main(String[] args) {
        RPCClient client = RPCClient.getInstance();
        client.init("127.0.0.1");
        SayHello sayHello = (SayHello) client.getRemoteProxy(SayHello.class);
        System.out.println("client:" + sayHello.sayHello());
    }
}
