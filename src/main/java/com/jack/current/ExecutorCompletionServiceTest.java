package com.jack.current;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Jack on 2018/10/15.
 */
public class ExecutorCompletionServiceTest {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = new ThreadPoolExecutor(20, 50, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100),
                new ThreadFactoryBuilder().setNameFormat("MultiPermissionServiceThread-%d").build());
        ExecutorCompletionService completionService = new ExecutorCompletionService<Integer>(
                executorService, new LinkedBlockingQueue<Future<Integer>>(10));

        for ( int i=0 ; i<10; i++) {
            final int j = i;
            completionService.submit(new Callable() {
                @Override
                public Integer call() throws Exception {
                    if (j < 5) {
                        Thread.currentThread().sleep(4000 + j * 1000);
                    }
                    return j;
                }
            });
            //future.get();
        }

        for ( int i=0 ; i<10; i++) {
            Future<Integer> future = completionService.take();
            List<Integer> resultList = Lists.newArrayList();
            resultList.add(future.get());
            System.out.println(future.get());
        }
        System.out.println("--------------");
        System.out.println("--------------");
        System.out.println("--------------");
        testResult();
    }

    public static ExecutorService executorService = new ThreadPoolExecutor(20, 50, 0,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100),
            new ThreadFactoryBuilder().setNameFormat("MultiPermissionServiceThread-%d").build());

    private static void testResult()throws Exception{

        for (int t = 0;t<10;t++) {
            List<Future<Integer>> futureList = new ArrayList<>();
            final int tt = t;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for ( int i=tt*100 ; i<40+tt*100; i++) {
                        final int j = i;
                        futureList.add(executorService.submit(new Callable() {
                            @Override
                            public Integer call() throws Exception {
                                if (j < 5) {
                                    Thread.currentThread().sleep(4000 + j * 1000);
                                }
                                return j;
                            }
                        }));
                        //future.get();
                    }
                    for ( int i =0;i<10;i++) {
                        try {
                            System.out.println(futureList.get(i).get());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }).start();
        }
    }
}
