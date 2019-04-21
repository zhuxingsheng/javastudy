跑DistributeLockExp示例，运行结果会是这样：
``````
三个开始
[INFO ] 2019-04-17 18:22:10,196 lock start,tid:thread-0
[INFO ] 2019-04-17 18:22:10,196 lock start,tid:thread-2
[INFO ] 2019-04-17 18:22:10,201 lock start,tid:thread-1

thread-0成功获取锁
[INFO ] 2019-04-17 18:22:10,304 thread-0try lock  success ,path:/lock/lock1 tid:thread-0
[INFO ] 2019-04-17 18:22:10,304 lock success,lockName:lock1,tid:thread-0

thread-0开始解锁
[INFO ] 2019-04-17 18:22:10,315 start unlock:lock1 tid:thread-0

thread-1,thread-2抢占锁失败
[INFO ] 2019-04-17 18:22:10,315 try lock fail, tid:thread-1
[INFO ] 2019-04-17 18:22:10,315 waitforlock start,lockname:lock1,threadId:thread-1

[INFO ] 2019-04-17 18:22:10,330 try lock fail, tid:thread-2
[INFO ] 2019-04-17 18:22:10,331 waitforlock start,lockname:lock1,threadId:thread-2

thread-1等待时，thread-0还没有解锁成功
[INFO ] 2019-04-17 18:22:10,348 waitForLock exists:thread-1

thread-2等待时，thread-0解锁成功
[INFO ] 2019-04-17 18:22:10,355 waitForLock not exists:thread-2

[INFO ] 2019-04-17 18:22:10,357 end unlock:lock1 tid:thread-0
[INFO ] 2019-04-17 18:22:10,357 lock end,tid:thread-0

thread-2加锁成功
[INFO ] 2019-04-17 18:22:10,370 thread-2try lock  success ,path:/lock/lock1 tid:thread-2
[INFO ] 2019-04-17 18:22:10,370 lock success,lockName:lock1,tid:thread-2
[INFO ] 2019-04-17 18:22:10,370 waitforlock end,lockname:lock1,threadId:thread-2
[INFO ] 2019-04-17 18:22:10,380 start unlock:lock1 tid:thread-2
[INFO ] 2019-04-17 18:22:10,389 end unlock:lock1 tid:thread-2
[INFO ] 2019-04-17 18:22:10,389 lock end,tid:thread-2
```
代码来源
https://www.cnblogs.com/nevermorewang/p/8306903.html

刚开始以为是zk的监听事件出现问题，解锁时，没有接受到通知，因此无效wait

经验分析实验，感觉是递归死了


