package test.multithreading2;


/**
 * <br>
 * 主要提供线程的私有变量，该变量对线程可见
 *
 * @author bangsun
 * @data 2021/6/23 10:01
 * @verson 1.0
 */
public class ThreadLocalTest2 {

    public static void main(String[] args) {
        User user = new User("花Gie");
        ThreadLocalInfo.userThreadLocal.set(user);
        //1.调用获取地址方法
        new AddressService().getAddress();
    }
}
class AddressService{
    public void getAddress(){
        User user = ThreadLocalInfo.userThreadLocal.get();
        System.out.println("根据用户信息"+user.getUserName()+"获取用户地址");
        //2.调用优惠券方法
        new TicketService().getTicket();
    }
}

class TicketService{
    public void getTicket(){
        User user = ThreadLocalInfo.userThreadLocal.get();
        System.out.println("根据用户信息"+user.getUserName()+"获取用户优惠券");
        //3.调用发送消息
        new MessageService().sendMessage();
    }
}
class MessageService{
    public void sendMessage(){
        User user = ThreadLocalInfo.userThreadLocal.get();
        System.out.println("根据用户"+user.getUserName()+"发送消息");
    }
}

class User {
    String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
/**
 * 创建ThreadLocal变量
 * */
class ThreadLocalInfo {
    public static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();
}
