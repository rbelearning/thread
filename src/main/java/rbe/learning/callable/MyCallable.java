package rbe.learning.callable;

import java.util.concurrent.Callable;

/**
 * @author : rbe
 * @date : 2024/9/29 15:25
 */
public class MyCallable implements Callable<Integer> {
    private Integer num1;

    private Integer num2;

    public MyCallable(Integer num1, Integer num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public Integer call() {
        System.out.println("Thread2 Id: " + Thread.currentThread().getId()+
                "   实现Callable接口方式 is running");
        return num1 + num2;
    }
}
