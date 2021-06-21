package mission;

public class PriorityAllocation implements Scheduler {

	@Override
	public void getNextCall() {

		System.out.println("전용 상담원에게 바로 연결 해드리겠습니다.");
		
	}

	@Override
	public void sendCallToAgent() {
		
		System.out.println("전용 상담원에게 즉시 연결 했습니다.");

	}

}
