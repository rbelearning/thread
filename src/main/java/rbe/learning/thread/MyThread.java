package rbe.learning.thread;

/**
 * @author : rbe
 * @date : 2024/9/29 15:02
 */
public class MyThread extends Thread{
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Thread2 Id: " + Thread.currentThread().getId()+
                "   " + name + " is running");
    }
}
