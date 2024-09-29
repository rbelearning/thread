package rbe.learning.runnable;

/**
 * @author : rbe
 * @date : 2024/9/29 15:20
 */
public class MyRunnable implements Runnable{
    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Thread2 Id: " + Thread.currentThread().getId()+
                "   " + name + " is running");
    }
}
