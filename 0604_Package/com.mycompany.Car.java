// 이것이 자바다 _ 신용권 : 248p ~
// Key Word : 패키지(Package), 접근 제한자(Access Modifier), public, protected, default, private, 
//------------------------------------

package com.mycompany;


//패키지가 써있어야 클래스를 사용할수 있음.

//import com.hankook.SnowTire;
//import com.hankook.Tire;
// 위의 com.hankook의 모든 클래스를 다음과 같이 불러올수 있음.
import com.hankook.*;
// 단, 접근 제한자(Access Modifier) 에 의해서 접근이 제한될 수 있음.
// public 이기에 불러올 수 있음.

//import com.kumho.Tire;

// ctrl shift o 로 바로 필요한 클래스를 import 시킬 수 있음


public class Car {
	
	// 차에서 타이어가 필요한 경우 생성시킴. : 패키지 내부의 클래스를 임포트함.
	Tire t = new Tire(); // com.hankook.tire의 접근 제한자가 default(없음)일시 호출 불가.
	SnowTire sTire = new SnowTire();
//	Tire t2 = new Tire();
	com.kumho.Tire t2 = new com.kumho.Tire();
	// -> 다음과 같이 인스턴스를 만듬.
	// 클래스 이름이 같은 경우에는 어떤 패키지에서 가져올지 구분이 힘듬
	
	
}
