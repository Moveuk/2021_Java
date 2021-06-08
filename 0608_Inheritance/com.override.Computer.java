// 이것이 자바다 _ 신용권 : 295p
// Key Word : 재정의(Override), 어노테이션(annotation), Final
// 재정의(Override) : 부모와 다른 기능의 메소드를 자식에서 사용하고 싶을시 재정의(Override)를 통하여 설정한다.
//----------------------------------------------------------------------

// 이글립스 Override 기능
// Source 탭 > Override/Implement Methods..
// 재정의 하고싶은 메소드 체크박스 선택 후 확인.

// Final 클래스, 메소드, 변수
// Final 클래스 : 상속 불가, 변경 수정 불가
// Final 메소드 : 재정의(Override) 불가
// Final 변수 : 상수 - 변경이 불가능한 변수 값. 

package com.override;

public class Computer extends Calculator {

	@Override
	double areaCircle(double r) {
		// TODO Auto-generated method stub
		System.out.println("Computer 객체의 areaCircle() 실행");
		return Math.PI * r * r;
//		return super.areaCircle(r);
		// super.라는 키워드를 통해서 부모의 메소드(본체)를 호출 가능함.
	}
}
