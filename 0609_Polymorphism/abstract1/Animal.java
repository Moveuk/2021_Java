package abstract1;

public abstract class Animal {
	public String kind;
	
	private void breathe() {
		System.out.println("숨을 쉰다.");
	}
	
	public abstract void sound(); 
	//{중괄호로 설명하지 않음. 자식에게 주고 싶은 메소드를 정할 때 사용할듯.}
}
