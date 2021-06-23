package wait_notify2;

public class DataBox {
	private String data;
	
	public synchronized String getData() {
		if(this.data == null) {				// 가져갈 데이터가 없으면 만들어 질 때까지 웨이팅으로 만듬.
			try {
				wait();
			} catch(InterruptedException e) {}
		}
		String returnValue = data;
		System.out.println("ConsummerThread가 읽은 데이터: " + returnValue);
		data = null;
		notify();
		return returnValue;
	}
	
	public synchronized void setData(String data) {
		if(this.data != null) {				// 데이터가 있으면 빼갈 때까지 웨이팅으로 만듬.
			try {
				wait();
			} catch(InterruptedException e) {}
		}
		this.data = data;
		System.out.println("ProducerThread가 생성한 데이터: " + data);
		notify();
	}
}


