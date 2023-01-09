package threadlocal;

/**
 * ThreadLocal用法2
 * 使用ThreadLocal 存储用户信息，避免参数层层传递
 */
public class ThreadLocalNormal2Usage00 {

    public static void main(String[] args) {
        new Service1().process();
    }

}

class Service1 {
    /**
     * 模拟类似拦截器，每个请求都要获取用户信息
     */
    public void process() {
        User user = new User("超哥");
        UserContextHolder.holder.set(user);
        // 调用方法2
        new Service2().process();
        // 调用方法3
        new Service2().process();
    }
}

class Service2 {
    public void process() {
        // 通过ThreadLocal获取用户信息
        User user = UserContextHolder.holder.get();
        System.out.println("Service2拿到用户名:" + user.getName());
    }
}

class Service3 {
    public void process() {
        // 通过ThreadLocal获取用户信息
        User user = UserContextHolder.holder.get();
        System.out.println("Service3拿到用户名:" + user.getName());
    }
}

class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();

}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
