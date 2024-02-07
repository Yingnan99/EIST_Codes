package de.tum.in.ase.eist;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        detectDeadlock(new SwimmingPool());
    }

    public static void detectDeadlock(SwimmingPool swimmingPool) {
        // TODO 2

        //建两个Swimmer对象，代表两个泳者，swimmer1 和 swimmer2 去游泳池，但他们的行为顺序不同
        Swimmer swimmer1 = new Swimmer();
        swimmer1.goToSwimmingPool(swimmingPool, SwimmingPoolActionOrder.CHANGING_ROOM_BEFORE_LOCKER);

        Swimmer swimmer2 = new Swimmer();
        swimmer2.goToSwimmingPool(swimmingPool,SwimmingPoolActionOrder.LOCKER_BEFORE_CHANGING_ROOM);

        //为了模拟现实生活中的并发情况，我们为这两个泳者创建了两个线程。每个线程将处理一个泳者的入池请求
        Thread thread1 = new Thread(() -> {
            swimmingPool.handleEntryRequest(swimmer1, SwimmingPoolActionOrder.CHANGING_ROOM_BEFORE_LOCKER);
        });

        Thread thread2 = new Thread(() -> {
            swimmingPool.handleEntryRequest(swimmer2, SwimmingPoolActionOrder.LOCKER_BEFORE_CHANGING_ROOM);
        });

        //我们启动了这两个线程，模拟这两个泳者几乎同时开始他们的行动
        thread1.start();
        thread2.start();

        //thread1.join(); 的意思是当前线程（可能是主线程）会等待 thread1 完成后再继续执行。同理，thread2.join(); 会等待 thread2 完成。
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
