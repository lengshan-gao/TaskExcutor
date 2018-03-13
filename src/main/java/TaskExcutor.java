import java.util.concurrent.*;

/**
 * Created by lengshan on 2018/3/7 0007.
 *
 * 创建java线程池,并测试
 */

/**
 *public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
 BlockingQueue<Runnable> workQueue);

 public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
 BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory);

 public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
 BlockingQueue<Runnable> workQueue,RejectedExecutionHandler handler);

 public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
 BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory,RejectedExecutionHandler handler);

 corePoolSize 核心线程数
 maximumPoolSize 最大线程数
 keepAliveTime 超出核心线程数小于最大线程数的时候，线程空闲时间。超过即被销毁。
 TimeUnit 时间单位

 */
public class TaskExcutor {

    public static void main(String[]args){
        ThreadPoolExecutor excutor = new ThreadPoolExecutor(10,20,200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(20));
        /**
         * 应用Executors创建线池四种方式
         * Executors.newCachedThreadPool();        //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
         Executors.newSingleThreadExecutor();   //创建容量为1的缓冲池
         Executors.newFixedThreadPool(int);    //创建固定容量大小的缓冲池，超过容量的会进入队列等待
         newScheduledThreadPool(int)           //创建固定容量大小的缓冲池，支持定时及周期性任务执行。

         ExecutorService是java线程池的接口
         */
        ExecutorService executors = Executors.newFixedThreadPool(10);



        for(int i =0;i<30;i++){
            MyTask task = new MyTask(i);
            //excutor.execute(task);
            //executors.execute(task);//execute 没有返回值
           Future future = executors.submit(task); //submit  执行线程有返回值，返回一个future对象
            System.out.println(future);//如果线程执行完了会返回一个null

           // System.out.println("线程池中线程数："+excutor.getPoolSize()+",队列中等待执行的任务数："+excutor.getQueue().size()+",已执行完成的任务数目："+excutor.getCompletedTaskCount());


        }
        excutor.shutdown();
    }



}
class MyTask implements Runnable{

    private int taskNum;

    /**
     * 声明一个有参数的构造方法
     * @param num
     */
    public  MyTask(int num){
        this.taskNum = num;
    }

    public void run(){
        System.out.println("正在执行的task"+taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行完毕的task"+taskNum);
    }


}
