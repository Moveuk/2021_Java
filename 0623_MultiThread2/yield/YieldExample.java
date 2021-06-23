package yield;

public class YieldExample {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();

        // ThreadA, ThreadB ��� ����
        threadA.start();
        threadB.start();

//        try{
//            Thread.sleep(3000);				// ���� �Լ��� �����ϴ� ���� A,B�� ���� �۵���.
//          ThreadA.sleep(3000);			// ������ �ش� �����常 ��� �� �ִ�.���ο��� A �����.
//        } catch (InterruptedException e) {}

        // ThreadB�� ����
        threadA.work = false;

        try{
            Thread.sleep(3000);				// ������ A�� �������� 3�ʰ� B�� ����.
        }catch (InterruptedException e) {}

        // ThreadA, ThreadB ��� ����
        threadA.work = true;
        
        try{
            Thread.sleep(3000);				// ������ A�ٽ� Ű�� 3�ʰ� A,B ��� ����.
        }catch (InterruptedException e) {}

        // ThreadA, ThreadB ��� ����
        threadA.stop = true;
        threadB.stop = true;
    }
}
