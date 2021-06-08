// 이것이 자바다 _ 신용권 : 295p
// Key Word : 재정의(Override), 어노테이션(annotation)
// 재정의(Override) : 부모와 다른 기능의 메소드를 자식에서 사용하고 싶을시 재정의(Override)를 통하여 설정한다.
//----------------------------------------------------------------------

package com.override;

public class Calculator {	
	double areaCircle(double r) { 
		System.out.println("Calculator 객체의 areaCircle() 실행");
		return 3.14159 * r * r; 
	}
}
