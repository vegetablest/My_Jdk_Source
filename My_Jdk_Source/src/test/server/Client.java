package test.server;

public interface Client {

        // 调用服务提供者的处理方法
        void process(int a,int b);

        //  异步回调方法
        void result(int i);

}
