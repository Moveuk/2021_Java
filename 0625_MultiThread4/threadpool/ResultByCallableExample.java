package threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResultByCallableExample {
    public static void main(String[] args) {
    	// 프로세서 갯수만큼 스레드풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        System.out.println("[작업 처리 요청]");
        Callable<Integer> task = new Callable<Integer>() {	// 제네틱 타입으로 처리.
            @Override
            public Integer call() throws Exception {	// call()해서 리턴값 Integer로 받음
                int sum = 0;
                for (int i = 1; i <= 10; i++) {
                    sum += i;
                }

                return sum;								// 리턴 sum
            }
        };
        Future<Integer> future = executorService.submit(task);

        try {
           int sum = future.get();						// sum이 일로옴.
            System.out.println("[처리 결과] " + sum);
            System.out.println("[작업 처리 완료] ");


        } catch (Exception e) {
            System.out.println("[실행 예외 발생함]" + e.getMessage());
        }

        executorService.shutdown();
    }
}