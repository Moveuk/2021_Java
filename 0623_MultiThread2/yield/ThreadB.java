package yield;

public class ThreadB extends Thread {
    public boolean stop = false; 			// ���� �÷���
    public boolean work = true; 			// �۾� ���� ���� �÷���
    int i = 0;

    @Override
    public void run() {
        while (!stop) { 					// stop�� true�� �Ǹ� while �� ����
            if (work) {
                System.out.println("ThreadB �۾� ���� " + i++);
            } else {
                Thread.yield(); 			// work�� false�� �Ǹ� �ٸ� �����忡�� ���� �纸
            }
        }

        System.out.println(this.getName() + "����");
    }
}

