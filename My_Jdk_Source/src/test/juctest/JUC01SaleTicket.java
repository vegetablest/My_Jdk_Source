package test.juctest;

/**
 * juc01入门买票
 * 线程操作资源类
 * @author bangsun
 */
public class JUC01SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{for(int i = 0;i<=100;i++) ticket.sale();},"A").start();
        new Thread(()->{for(int i = 0;i<=100;i++) ticket.sale();},"B").start();
        new Thread(()->{for(int i = 0;i<=100;i++) ticket.sale();},"C").start();
    }
}

/**
 * 资源类
 * */
class Ticket {
    private int ticketNum = 300;
    public void sale(){
        if (ticketNum>0){
            System.out.println("线程"+Thread.currentThread().getName()+":卖出第"+(ticketNum--)+"张票,还剩余"+ticketNum+"张票");
        }
    }
}
