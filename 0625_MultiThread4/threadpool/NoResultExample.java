package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NoResultExample {
    public static void main(String[] args) {

        System.out.println("이 컴퓨터는 "+Runtime.getRuntime().availableProcessors()+"개의 프로세서를 가지고 있어요.");
        System.out.println("------------------------------------");
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        
        // Runtime.getRuntime().availableProcessors(): 코어 갯수
        // 코어 갯수만큼 스레드 풀에 스레드 생성 가능하게 만듬.

        System.out.println("[작업 처리 요청]");

        Runnable runnable = new Runnable() {			// 한개 스레드 생성함.
            @Override
            public void run() {
                int sum = 0;
                
                System.out.println(100/sum); 	//예외 발생 코드
                
                for (int i = 1; i <= 10; i++) {
                    sum += i;
                }
                System.out.println("스레드 이름 : " + Thread.currentThread().getName());
                System.out.println("[처리 결과]: " + sum);
            }
        };

        Future future = executorService.submit(runnable);		// submit 문제가 생겨도 계속 반복함.

        try {
            future.get();					// null 혹은 exception을 받기 때문에 try catch에서 걸러짐.
            System.out.println("[작업 처리 완료]");
        } catch (Exception e) {
            System.out.println("[실행 예외 발생함]" + e.getMessage());
        }

        executorService.shutdown();
    }
}
