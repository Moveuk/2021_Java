package yield;

public class ThreadA extends Thread {
    public boolean stop = false; 		// ���� �÷���
    public boolean work = true; 		// �۾� ���� ���� �÷���
    int i = 0;

    @Override
    public void run() {

    	try{
          Thread.sleep(3000);			// ���� �Լ��� �����ϴ� ���� A,B�� ���� �۵���.
      } catch (InterruptedException e) {}
    	
        while (!stop) { 				// stop�� true�� �Ǹ� while �� ����
            if (work) {
                System.out.println("ThreadA �۾� ���� " + i++);
            } else {
                Thread.yield(); 		// work�� false�� �Ǹ� �ٸ� �����忡�� ���� �纸
            }
        }

        System.out.println(this.getName() + "����");
    }
}

