package mission;

public class Leastjob implements Scheduler {

	@Override
	public void getNextCall() {
		
		System.out.println("상담대기가 가장 작은 상담원에게 연결 대기중입니다.");
		
	}

	@Override
	public void sendCallToAgent() {
		
		System.out.println("상담 대기가 적은 상담원에게 연결합니다.");

	}

}
