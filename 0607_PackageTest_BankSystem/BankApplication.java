//이것이 자바다 _ 신용권 : 284p 20번 문제
// Key Word : 스태틱(static), 배열 정의, 은행 거래, 송금, 출금, 입금.
//-----------------------------------------------------

package quiz20;

//import java.util.Arrays;
import java.util.Scanner;

public class BankApplication {
	private static Account[] accountArray = new Account[100];
	// 스태틱은 선언시 메모리에 바로 존재함.
	// but 인스턴스 변수는 선언 및 초기화를 해주어야만 메모리에 할당됨.
	// 따라서 static은 바로 사용가능, 인스턴스는 선언해야만 사용 가능.
//	System.out.println(Arrays.toString(accountArray));
//	101칸 모두 null값임.
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		boolean run = true;
		while (run) {
			System.out.println("--------------------------------");
			System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.송금 | 6.종료");
			System.out.println("--------------------------------");
			System.out.print("선택> ");

			int selectNo = sc.nextInt();
			System.out.println();

			if (selectNo == 1) {
				createAccount();
			} else if (selectNo == 2) {
				accountList();
			} else if (selectNo == 3) {
				deposit();
			} else if (selectNo == 4) {
				withdraw();
			} else if (selectNo == 5) {
				send();
			} else if (selectNo == 6) {
				run = false;
			}
		}
		System.out.println("프로그램 종료");

	}

	// 계좌생성하기
	private static void createAccount() {
		// 작성위치

		System.out.println("-------------------");
		System.out.println("계좌생성");
		System.out.println("-------------------");

		System.out.print("계좌번호: ");
		String ano = sc.next();
		
		// next 와 nextline의 차이
		// next는 엔터값을 받지 않음. 
		// 따라서 line일때는 값을 넣어준후 한줄 더 넣어줌으로써 빈 값이 들어가는 것을 막아줘야함.
//		sc.nextLine();

		System.out.print("계좌주: ");
		String owner = sc.nextLine();

		System.out.print("초기입금액: ");
		int balance = sc.nextInt();

//		본인 답안 : 직접 지정해서 넣어주려 했음.
//		int count = 0;
//		accountArray[count].setAno(ano); 
//		accountArray[count].setOwner(owner); 
//		accountArray[count].setBalance(balance);
//		count++;
//		

		// 답안 : 인스턴스 만들어서 null값자리에 한번에 넣어줌.
		Account newAccount = new Account(ano, owner, balance);

		for (int i = 0; i < accountArray.length; i++) {
			if (accountArray[i] == null) {
				accountArray[i] = newAccount;
				break;
			}
		}

		System.out.println("결과 : 계좌가 생성되었습니다.");
	}

	// 계좌목록보기
	private static void accountList() {
		// 작성위치
		System.out.println("-------------------");
		System.out.println("계좌목록");
		System.out.println("-------------------");

		// 본인 답안: null값까지 모두 표출됨.
//		for (int i = 0; i < accountArray.length; i++) {
//			System.out.print(accountArray[i].getAno()+" \t");
//			System.out.print(accountArray[i].getOwner()+" \t");
//			System.out.print(accountArray[i].getBalance()+" \t");
//			System.out.println();
//		}

		// 답안 : null 값 빼고 표시함.
		for (Account account : accountArray) {
			if (account != null) {
				System.out.println(account.getAno() + " " + account.getOwner() + " " + account.getBalance());
			}
		}
	}

	// 예금하기
	private static void deposit() {
		// 작성위치
		System.out.println("-------------------");
		System.out.println("예금");
		System.out.println("-------------------");

		System.out.print("계좌번호: ");
		String ano = sc.next();

		System.out.print("예금액: ");
		int money = sc.nextInt();

		Account account = findAccount(ano);

		if (account == null) {
			System.out.println("계좌가 존재하지 않습니다.");
		} else {
			account.setBalance(account.getBalance() + money);
		}

		// 같은 로직
		// if문을 돌려서 해당되면 해당 메소드를 빠져나가고 아니라면 코드 진행.
//		if (account == null) {
//			System.out.println("계좌가 존재하지 않습니다.");
//			return;
//		}
//		account.setBalance(account.getBalance()+money);

//		for (int j = 0; j < accountArray.length; j++) {
//			if (ano.equals(accountArray[j].getAno())) {
//				System.out.println();
//				System.out.print("예금액: ");
//				int balance = accountArray[j].getBalance()+sc.nextInt();
//				accountArray[j].setBalance(balance);
//		} else {
//			System.out.println("잘못된 계좌번호입니다.");
//			return;
//		}
//			
//		}

		System.out.println("결과 : 예금이 성공되었습니다.");
	}

	// 출금하기
	private static void withdraw() {
		// 작성위치
		System.out.println("-------------------");
		System.out.println("출금");
		System.out.println("-------------------");

		System.out.print("계좌번호: ");
		String ano = sc.next();

		System.out.print("출금액: ");
		int money = sc.nextInt();

		Account account = findAccount(ano);

		if (account == null) {
			System.out.println("계좌가 존재하지 않습니다.");
			return;
		}
		account.setBalance(account.getBalance() - money);

//		본인 답안.		
//		for (int j = 0; j < accountArray.length; j++) {
//			if (ano.equals(accountArray[j].getAno())) {
//				System.out.println();
//				System.out.print("출금액: ");
//				int balance = accountArray[j].getBalance()-sc.nextInt();
//				accountArray[j].setBalance(balance);
//		} else {
//			System.out.println("잘못된 계좌번호입니다.");
//			return;
//		}	
//		}
		// 문제점 :
		// 배열과 타입에 대한 정의 이해도 부족.
		// 배열의 순서로 찾는 것이 아니라

		System.out.println("결과 : 예금이 성공되었습니다.");
	}

	// Account 배열에서 ano와 동일한 Account 객체 찾기
	private static Account findAccount(String ano) {
		// 작성위치
		// 지역변수로는 리턴이 불가능함으로 리턴용 변수를 초기화해줌.
		Account account = null;

		for (Account acc : accountArray) {
			if (acc != null) {
				String dbAno = acc.getAno();
				if (dbAno.equals(ano)) {
					account = acc;
					break;
				}
			}

		}

		return account;

	}

	// quiz 송금 기능 추가
	private static void send() {
		// 작성위치
		System.out.println("-------------------");
		System.out.println("송금");
		System.out.println("-------------------");

		System.out.print("본인계좌번호: ");
		String fromAno = sc.next();
		System.out.print("상대계좌번호: ");
		String toAno = sc.next();

		System.out.print("송금액: ");
		int money = sc.nextInt();

		Account fromAccount = findAccount(fromAno);
		Account toAccount = findAccount(toAno);

		if (toAccount == null || fromAccount == null) {
			System.out.println("계좌가 존재하지 않습니다.");
			return;
		}
		toAccount.setBalance(toAccount.getBalance() + money);
		fromAccount.setBalance(fromAccount.getBalance() - money);

		System.out.println("결과 : 송금이 성공되었습니다.");
	}

}
