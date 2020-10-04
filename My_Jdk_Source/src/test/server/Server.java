package test.server;

public class Server {

    public void add(int i, int j,Client client) {
        // 要返回的结果
        int res = i+j;
        // 线程休息2秒
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 调用异步通知方法
        client.result(res);
    }

}