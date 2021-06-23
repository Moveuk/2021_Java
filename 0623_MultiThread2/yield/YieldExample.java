package yield;

public class YieldExample {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();

        // ThreadA, ThreadB 모두 실행
        threadA.start();
        threadB.start();

//        try{
//            Thread.sleep(3000);				// 메인 함수를 슬립하는 동안 A,B가 서로 작동함.
//          ThreadA.sleep(3000);			// 슬립은 해당 스레드만 재울 수 있다.메인에서 A 못재움.
//        } catch (InterruptedException e) {}

        // ThreadB만 실행
        threadA.work = false;

        try{
            Thread.sleep(3000);				// 위에서 A를 꺼버려서 3초간 B만 실행.
        }catch (InterruptedException e) {}

        // ThreadA, ThreadB 모두 실행
        threadA.work = true;
        
        try{
            Thread.sleep(3000);				// 위에서 A다시 키고 3초간 A,B 모두 실행.
        }catch (InterruptedException e) {}

        // ThreadA, ThreadB 모두 종료
        threadA.stop = true;
        threadB.stop = true;
    }
}
