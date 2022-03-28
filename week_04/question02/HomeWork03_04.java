package coconut.others.question02;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/3/27 19:36
 * File: HomeWork03
 * Project: leetcodeStartApril
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 * 使用 newCachedThreadPool，创建线程池
 */
public class HomeWork03_04 {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int no = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("start:" + no);
                        Thread.sleep(1000L);
                        int result = sum(); //这是得到的返回值
                        // 确保  拿到result 并输出
                        System.out.println("异步计算结果为：" + result);
                        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
                        System.out.println("end:" + no);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
        System.out.println("Main Thread End!");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}


