package rbe.learning.callable;

import java.util.concurrent.FutureTask;

/**
 * @author : rbe
 * @date : 2024/9/29 15:26
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Thread1 Id："+ Thread.currentThread().getId() +
                "    主线程 is running");

        MyCallable myCallable = new MyCallable(1, 2);
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            System.out.println("Current Thread Id："+ Thread.currentThread().getId() +"   callable 异步计算结果为：" + futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
