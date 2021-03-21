import jdk.nashorn.internal.ir.CallNode;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadThread2 {

    Object lock1 = new Object();
    Object lock2 = new Object();

    Lock r = new ReentrantLock();


    public void noDeadLock(){
        //
        synchronized (lock1){
            synchronized (lock1){
                System.out.println("sychronized重入锁");
            }
        }
        r.lock();
        r.lock();
        System.out.println("ReentrantLock重入锁1");
        r.unlock();
        System.out.println("ReentrantLock重入锁2");
        r.unlock();

        System.out.println("运行结束");
    }


    public void deadLock() throws InterruptedException {
        //死锁
        inteputThread.start();
        thread.start();

        synchronized (lock1){
            Thread.sleep(3000);
            synchronized (lock2){
                System.out.println("开始解除死锁");
            }
        }



    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (lock2){
                try {
                    System.out.println("准备开始死锁");
                   Thread.sleep(5000);
                    System.out.println("死锁");
                    //死锁

                    //进入等待, 释放锁
                    lock2.wait(300);
                    synchronized (lock1){
                        System.out.println("解除死锁");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    });

    Thread inteputThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(10000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    });
}
