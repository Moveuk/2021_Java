//이것이 자바다 _ 신용권 : 284p 20번 문제
// Key Word : 스태틱(static), 배열 정의, 은행 거래, 송금, 출금, 입금.
//-----------------------------------------------------

package quiz20;

public class Account {
	private String ano;
	private String owner;
	private int balance;
	
	public Account(String ano, String owner, int balance) {
		this.ano = ano;
		this.owner = owner;
		this.balance = balance;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
		
	
	
}
