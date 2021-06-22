package threadname;

public class ThreadNameExample {
	public static void main(String[] args) {
		Thread mainThread = Thread.currentThread();
		System.out.println("���α׷� ���� ������ �̸�: " + mainThread.getName());
		
		ThreadA threadA = new ThreadA();
		ThreadB threadB = new ThreadB();
		System.out.println("�۾� ������ �̸�: " + threadA.getName());
		threadA.start();
		
		System.out.println("�۾� ������ �̸�: " + threadB.getName());
		threadB.start();
	}
}
