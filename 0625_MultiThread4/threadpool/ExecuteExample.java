package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecuteExample {
    public static void main(String[] args) throws Exception {
        
        ExecutorService executorService = Executors.newFixedThreadPool(2); // 최대 스레드 개수가 2인 스레드풀 생성
        // 총 10개 스레드 만들지만 풀에서는 2개까지 작업 처리가능.
        System.out.println(Runtime.getRuntime().availableProcessors());	// 사용가능한 프로세서 수 보여주는 건듯.
        
        for (int i = 0; i < 10; i++) {	// 스레드 10개 발생.
        	// 작업 정의
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // 스레드 총 개수 및 작업 스레드 이름 출력
                    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
                    int poolSize = threadPoolExecutor.getPoolSize();
                    String threadName = Thread.currentThread().getName();
                    System.out.println("[총 스레드 개수: " + poolSize + "] 작업 스레드 이름: " + threadName);

                    // 예외 발생 시킴
                    int value = Integer.parseInt("삼");
                }
            };
            
//            executorService.execute(runnable); // 작업 처리요청. 작업 처리 결과 받지 못함
            executorService.submit(runnable);	// 작업 처리요청. 리턴된 Future를 통해 작업 처리 결과를 얻을 수 있음.
            
            
            
            Thread.sleep(100);	// 콘솔에 출력 시간을 주기 위해 0.01초 일시 정지 시킴
        }

        executorService.shutdown();				// 스레드 풀 종료
    }
}
