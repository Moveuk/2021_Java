package yield;

public class ThreadA extends Thread {
    public boolean stop = false; 		// 종료 플래그
    public boolean work = true; 		// 작업 진행 여부 플래그
    int i = 0;

    @Override
    public void run() {

    	try{
          Thread.sleep(3000);			// 메인 함수를 슬립하는 동안 A,B가 서로 작동함.
      } catch (InterruptedException e) {}
    	
        while (!stop) { 				// stop이 true가 되면 while 문 종료
            if (work) {
                System.out.println("ThreadA 작업 내용 " + i++);
            } else {
                Thread.yield(); 		// work가 false가 되면 다른 스레드에게 실행 양보
            }
        }

        System.out.println(this.getName() + "종료");
    }
}

