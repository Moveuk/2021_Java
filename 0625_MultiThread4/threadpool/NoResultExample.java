package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NoResultExample {
    public static void main(String[] args) {

        System.out.println("�� ��ǻ�ʹ� "+Runtime.getRuntime().availableProcessors()+"���� ���μ����� ������ �־��.");
        System.out.println("------------------------------------");
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        
        // Runtime.getRuntime().availableProcessors(): �ھ� ����
        // �ھ� ������ŭ ������ Ǯ�� ������ ���� �����ϰ� ����.

        System.out.println("[�۾� ó�� ��û]");

        Runnable runnable = new Runnable() {			// �Ѱ� ������ ������.
            @Override
            public void run() {
                int sum = 0;
                
                System.out.println(100/sum); 	//���� �߻� �ڵ�
                
                for (int i = 1; i <= 10; i++) {
                    sum += i;
                }
                System.out.println("������ �̸� : " + Thread.currentThread().getName());
                System.out.println("[ó�� ���]: " + sum);
            }
        };

        Future future = executorService.submit(runnable);		// submit ������ ���ܵ� ��� �ݺ���.

        try {
            future.get();					// null Ȥ�� exception�� �ޱ� ������ try catch���� �ɷ���.
            System.out.println("[�۾� ó�� �Ϸ�]");
        } catch (Exception e) {
            System.out.println("[���� ���� �߻���]" + e.getMessage());
        }

        executorService.shutdown();
    }
}
