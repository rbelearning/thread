package rbe.learning.runnable;

/**
 * @author : rbe
 * @date : 2024/9/29 15:21
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Thread1 Id："+ Thread.currentThread().getId() +
                "    主线程 is running");

        MyRunnable myRunnable = new MyRunnable("实现Runnable接口方式");
        Thread thread1 = new Thread(myRunnable);
        thread1.start();

        Thread thread2 = new Thread(()->{
            System.out.println("Thread3 Id: " + Thread.currentThread().getId()+
                    "   匿名内部类方式 is running");
        });
        thread2.start();
    }
}
