import java.util.concurrent.*;

/**
 * Created by lengshan on 2018/3/7 0007.
 *
 * ����java�̳߳�,������
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

 corePoolSize �����߳���
 maximumPoolSize ����߳���
 keepAliveTime ���������߳���С������߳�����ʱ���߳̿���ʱ�䡣�����������١�
 TimeUnit ʱ�䵥λ

 */
public class TaskExcutor {

    public static void main(String[]args){
        ThreadPoolExecutor excutor = new ThreadPoolExecutor(10,20,200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(20));
        /**
         * Ӧ��Executors�����߳����ַ�ʽ
         * Executors.newCachedThreadPool();        //����һ������أ������������СΪInteger.MAX_VALUE
         Executors.newSingleThreadExecutor();   //��������Ϊ1�Ļ����
         Executors.newFixedThreadPool(int);    //�����̶�������С�Ļ���أ����������Ļ������еȴ�
         newScheduledThreadPool(int)           //�����̶�������С�Ļ���أ�֧�ֶ�ʱ������������ִ�С�

         ExecutorService��java�̳߳صĽӿ�
         */
        ExecutorService executors = Executors.newFixedThreadPool(10);



        for(int i =0;i<30;i++){
            MyTask task = new MyTask(i);
            //excutor.execute(task);
            //executors.execute(task);//execute û�з���ֵ
           Future future = executors.submit(task); //submit  ִ���߳��з���ֵ������һ��future����
            System.out.println(future);//����߳�ִ�����˻᷵��һ��null

           // System.out.println("�̳߳����߳�����"+excutor.getPoolSize()+",�����еȴ�ִ�е���������"+excutor.getQueue().size()+",��ִ����ɵ�������Ŀ��"+excutor.getCompletedTaskCount());


        }
        excutor.shutdown();
    }



}
class MyTask implements Runnable{

    private int taskNum;

    /**
     * ����һ���в����Ĺ��췽��
     * @param num
     */
    public  MyTask(int num){
        this.taskNum = num;
    }

    public void run(){
        System.out.println("����ִ�е�task"+taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ִ����ϵ�task"+taskNum);
    }


}
