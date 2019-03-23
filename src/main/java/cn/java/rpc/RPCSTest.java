package cn.java.rpc;

public class RPCSTest {
    public static void main(String[] args) {
        RPCServer server = RPCServer.getInstance();
        server.addClass(new SayHelloBean());
        server.start();
    }
}
