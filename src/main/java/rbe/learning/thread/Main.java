package rbe.learning.thread;

/**
 * @author : rbe
 * @date : 2024/9/29 15:03
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Thread1 Id："+ Thread.currentThread().getId() +
                "    主线程 is running");

        MyThread myThread = new MyThread("继承Thread类方式");
        myThread.start();
    }
}
