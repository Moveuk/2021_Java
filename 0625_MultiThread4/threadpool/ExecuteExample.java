package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecuteExample {
    public static void main(String[] args) throws Exception {
        
        ExecutorService executorService = Executors.newFixedThreadPool(2); // �ִ� ������ ������ 2�� ������Ǯ ����
        // �� 10�� ������ �������� Ǯ������ 2������ �۾� ó������.
        System.out.println(Runtime.getRuntime().availableProcessors());	// ��밡���� ���μ��� �� �����ִ� �ǵ�.
        
        for (int i = 0; i < 10; i++) {	// ������ 10�� �߻�.
        	// �۾� ����
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // ������ �� ���� �� �۾� ������ �̸� ���
                    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
                    int poolSize = threadPoolExecutor.getPoolSize();
                    String threadName = Thread.currentThread().getName();
                    System.out.println("[�� ������ ����: " + poolSize + "] �۾� ������ �̸�: " + threadName);

                    // ���� �߻� ��Ŵ
                    int value = Integer.parseInt("��");
                }
            };
            
//            executorService.execute(runnable); // �۾� ó����û. �۾� ó�� ��� ���� ����
            executorService.submit(runnable);	// �۾� ó����û. ���ϵ� Future�� ���� �۾� ó�� ����� ���� �� ����.
            
            
            
            Thread.sleep(100);	// �ֿܼ� ��� �ð��� �ֱ� ���� 0.01�� �Ͻ� ���� ��Ŵ
        }

        executorService.shutdown();				// ������ Ǯ ����
    }
}
