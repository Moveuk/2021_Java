// 이것이 자바다 _ 신용권 : 295p
// Key Word : 재정의(Override), 어노테이션(annotation)
// 재정의(Override) : 부모와 다른 기능의 메소드를 자식에서 사용하고 싶을시 재정의(Override)를 통하여 설정한다.
//----------------------------------------------------------------------

// 이글립스 Override 기능
// Source 탭 > Override/Implement Methods..
// 재정의 하고싶은 메소드 체크박스 선택 후 확인.

package com.override;

public class ComputerExample {

	public static void main(String[] args) {
		int r = 10;

		Calculator calculator = new Calculator();
		System.out.println("원면적 : " + calculator.areaCircle(r));
		System.out.println();

		Computer computer = new Computer();
		System.out.println("원면적 : " + computer.areaCircle(r));
	}

}
