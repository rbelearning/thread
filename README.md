## 一文带你了解如何快速创建线程

Java中创建线程的方式主要有三种，继承Thread类，实现Runnable接口，实现Callable接口

话不多说，直接上干货

#### 方式1：继承Thread类

继承 Thread 类，重写 `run()`方法

```java
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
```

调用`start()`方法启动线程

```java
    public static void main(String[] args) {
        System.out.println("Thread1 Id："+ Thread.currentThread().getId() +
                "    主线程 is running");

        MyThread myThread = new MyThread("继承Thread类方式");
        myThread.start();
    }
```

运行结果可以看到除了主线程，我们自定义线程也运行了

> Thread1 Id：1    主线程 is running
> Thread2 Id: 16   继承Thread类方式 is running

直接继承Thread应该最好理解，但有个缺点——Java是单继承的，如果以这种方式启动线程，那么就不能继承其他类了。	那么我还想继承其他类，我该如何解决呢？



#### 方式2：实现Runnable接口

实现Runnable接口，实现`run()`方法

```java
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
```

作为参数传入Thread，调用`start()`方法

```java
    public static void main(String[] args) {

        System.out.println("Thread1 Id："+ Thread.currentThread().getId() +
                "    主线程 is running");

        MyRunnable myRunnable = new MyRunnable("实现Runnable接口方式");
        Thread thread1 = new Thread(myRunnable);
        thread1.start();
    }
```

> Thread1 Id：1    主线程 is running
> Thread2 Id: 16   实现Runnable接口方式 is running

当然了，因为runnable接口只有一个`run()`方法，我们可以用Lambda表达式简写

```java
Thread thread2 = new Thread(()->{
            System.out.println("Thread3 Id: " + Thread.currentThread().getId()+
                    "   匿名内部类方式 is running");
        });
thread2.start();
```

> Thread1 Id：1    主线程 is running
> Thread2 Id: 16   实现Runnable接口方式 is running
> Thread3 Id: 17   匿名内部类方式 is running

因为Java可以一个类实现多个接口，这种方法的优点是可以避免 Java 的单继承限制，也是推荐的方式。

但你有没有发现，这两个方法都是没有返回值的，那我想启动新线程进行计算，并将结果返回，又该如何呢？



#### 方式3：实现Callable接口

实现Callable接口，重写`call()`方法

```java
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
```

创建FutureTask，作为参数传给FutureTask对象

创建Thread，将TutureTask作为参数传给Thread，调用`start()`方法，启动线程

调用FutureTask的`get()`方法，获取线程返回结果

```java
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
```

> Thread1 Id：1    主线程 is running
> Thread2 Id: 16   实现Callable接口方式 is running
> Current Thread Id：1   callable 异步计算结果为：3



好了，以上就是Java创建线程的三种方式了，也有的文章说创建线程的方式其实只有一种，其实是有道理的，大家看完三种方式的创建应该发现，最终都是这种方式

> Thread thread = new Thread(?);
> thread.start();

也有的说是四种，加上了线程池创建线程。

所以到底是 1种/3种/4种，大家知道就好，没必要纠结。



> 有需要源码的小伙伴可以点击
>
> Gitee代码仓库：https://gitee.com/rbelearning/thread
>
> Github代码仓库：https://github.com/rbelearning/thread

