# API(Application Programming Interface)   
Key Word : API(Application Programming Interface)    
   
<hr/>
   

## 교재 454p : 11.1 자바 API 도큐먼트   
   
 

<br/><br/>
<hr/>

## 교재 457p : 11.3 Object 클래스   
   
<hr/>

### 교재 458p : 11.3.1 객체 비교(equals())   

 모든 클래스는 Object를 상속받고 있다. 클래스를 만들때 다음 화면과 같이 확인할 수 있다.   
 ![image](https://user-images.githubusercontent.com/84966961/122693494-11688080-d275-11eb-9893-cf355a8ef090.png)   
    
	
**Member.java**      
```java
package member;

public class Member {
	
	public String id;

	public Member(String id) {
		this.id = id;
	}
}
```   
**MemberTest.java**   
```java
public class MemberTest {

	public static void main(String[] args) {

		Member m1 = new Member("123");	// 아이디가 같은 사람이 존재하진 않는다.
		Member m2 = new Member("123");	// 주민번호와 같이 PK는 하나만 존재해야한다.
		
		// 그러므로 다음과 같이 확인해준다.
		
		if (m1==m2) {	// 동등연산자는 주소값을 비교하는 것이기 때문에 m1,m2 선언한 순간 주소값이 달라진다.(설령 내부 데이터가 같더라도.)
			System.out.println("m1과 m2는 같다.");
		}

		if (m1.equals(m2)) {	// equals는 Object의 메소드로 매개값으로 들어가는 모든 값이 자동 형변환이 일어난다.
			System.out.println("m1과 m2는 같다.");			
		}  // 오버라이딩 전에는 같지 않아서 나오지 않음
	}

}

```

 아이디나 주민번호와 같은 PK 값은 단 하나만 존재해야 한다. 그러므로 둘의 값을 비교해보고 싶다면 다음과 같은 변환을 하여 비교해야한다.   
 다음은 `Member.java`의 파일의 equals를 재정의(모든 클래스는 `Object`의 자식이므로 오버라이드가 가능하다.)하여 Member의 값을 비교해보고자 한다. 재정의는 기존의 Member를 비교하는 것이아니라 내부의 id 값을 비교할 수 있도록 오버라이딩을 해버린다.   
   
**오버라이딩된 Member.java**      
```java
public class Member {
	
	public String id;

	public Member(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Member) {
// 멤버 매개값 id를 직접적으로 비교하는 것으로 메소드를 재정의한다.
			Member other = (Member)obj;
			
			return this.id.equals(other.id);
		}
			return false;	// 반드시 if 구문 외부에 return을 주어야 프로그램에서 타입 값을 인식한다.
	}
}
``` 
   
**오버라이딩된 후 MemberTest.java**   
```java
public class MemberTest {

	public static void main(String[] args) {

		Member m1 = new Member("123");	// 아이디가 같은 사람이 존재하진 않는다.
		Member m2 = new Member("123");	// 주민번호와 같이 PK는 하나만 존재해야한다.
		
		// 그러므로 다음과 같이 확인해준다.
		
		if (m1==m2) {	// 동등연산자는 주소값을 비교하는 것이기 때문에 m1,m2 선언한 순간 주소값이 달라진다.(설령 내부 데이터가 같더라도.)
			System.out.println("m1과 m2는 같다.");
		}

		if (m1.equals(m2)) {	// 이제 equals는 멤버 변수를 직접적으로 비교하는 메소드이다. (값이나옴)
			System.out.println("m1과 m2는 같다.");			
		}  // 오버라이딩 전에는 같지 않아서 나오지 않음
	}

}

```
**오버라이딩 이후 결과 console**   
   
![image](https://user-images.githubusercontent.com/84966961/122695129-812d3a00-d27a-11eb-9580-18ed3498ecc6.png)


<br/><br/>
<hr/>

### 교재 461p : 11.3.1 객체 해시코드(hashCode())   
   
 해시코드란 객체를 식별하는 하나의 정수값을 말한다. 메모리 번지에 들어있는 각 객체의 값이기 때문에 이 값이 다르다면 두 객체가 다른 객체임을 뜻한다.   
   
**해시코드 오버라이딩 전 MemberTest.java**    

```java
public class MemberTest {

	public static void main(String[] args) {

		Member m1 = new Member("123");	// 아이디가 같은 사람이 존재하진 않는다.
		Member m2 = new Member("123");	// 주민번호와 같이 PK는 하나만 존재해야한다.
		
		// 그러므로 다음과 같이 확인해준다.
		
		if (m1==m2) {	// 동등연산자는 주소값을 비교하는 것이기 때문에 m1,m2 선언한 순간 주소값이 달라진다.(설령 내부 데이터가 같더라도.)
			System.out.println("m1과 m2는 같다.");
		}

		if (m1.equals(m2)) {	// equals는 Object의 메소드로 매개값으로 들어가는 모든 값이 자동 형변환이 일어난다.
			System.out.println("m1과 m2는 같다.");			
		}	// 오버라이딩 전에는 같지 않아서 나오지 않음
		// 이제 equals는 멤버 변수를 직접적으로 비교하는 메소드이다. (값이나옴)
		
		System.out.println("--------hashcode 변경 전-------");
		
		System.out.println(m1.hashCode());
		System.out.println(m2.hashCode());
		// 해시코드가 다르다. 하지만 오버라이드를 통해 각 객체가 같은 해시코드를 같도록 만들 수 있다.
	}
}
```
   

**결과 화면**    
![image](https://user-images.githubusercontent.com/84966961/122695555-a7070e80-d27b-11eb-8251-5379e767b4f9.png)

**해시코드를 오버라이딩한 Member.java**      

```java
public class Member {
	
	public String id;

	public Member(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
    // 멤버 매개값 id를 직접적으로 비교하는 것으로 메소드를 재정의한다.
		if (obj instanceof Member) {
			
			Member other = (Member)obj;
			
			return this.id.equals(other.id);
			
		}
			return false;	// 반드시 if 구문 외부에 return을 주어야 프로그램에서 타입 값을 인식한다.
	}

	@Override
	public int hashCode() {
		return id.hashCode();	// id값의 해시코드로 통일 시켜버린다.
	}
}
``` 
   
**결과 화면**    
![image](https://user-images.githubusercontent.com/84966961/122695734-40cebb80-d27c-11eb-8ad3-963a56ac3ecd.png)    
   
 마지막 해시코드의 오버라이딩을 이용해서 인위적으로 해시코드를 같게 만들 수 있다.(실제 값은 다르다. 단지, 같은 값처럼 보이게 만들었을 뿐이다.)   
   
 객체의 해시코드가 아니라 id의 해시코드를 뜻한다.


<br/><br/>
<hr/>

### 교재 466p : 11.3.4 객체 복제(clone())   
   
 기본형의 객체 복제는 다음과 같이 a의 값을 b에게 주면 쉽게 복제가 된다.

```java
int a = 10;

int b = 10;

b = a;
```

 하지만 복잡한 변수의 경우 복제하기가 쉽지 않으므로 그럴 때 사용하는 메서드가 `clone()` 이다. 복제에는 얕은 복제(thin clone)과 깊은 복제(deep clone)가 있다.   
   
 또한, 복제를 하고 싶은 클래스가 있다면 해당 클래스는 `Cloneable`이라는 인터페이스를 상속, 구현해야만 한다.(단순 선언적 의미이다. 내부에 재정의할 메소드들이 없다.)

```
public class Member2 implements Cloneable{
}
```

 이렇게 선언을 하게 되면 `Member2`는 복제를 하고 싶은 선언적 의미이다.   
   
 다음은 `getMember()`라는 메소드를 만들어 복제하는 기능을 구현한 코드이다.   
     
```java
public class Member2 implements Cloneable{

	public String id;
	public String name;
	public String password;
	public int age;
	public boolean adult;
	
	public Member2(String id, String name, String password, int age, boolean adult) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.adult = adult;
	}
	
	public Member getMember() {	// 복제를 해주는 메소드.
		Member cloend = null;
		
		try {
			cloend= (Member)clone(); // Cloneable이 없으면 예외가 생김 그래서 예외 처리를 해야함.
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}	
		
		return cloend;
	}
}


 이 후 복제의 패스워드 값을 변경하게 되면 기존 인스턴스의 값과 복제 인스턴스의 값이 같을까?   
   
```java
public class Member2Test {

	public static void main(String[] args) {

		Member2 original = new Member2("blue", "홍길동", "1234", 20, true);

		Member2 cloned = original.getMember();
		
		cloned.password = "qqqqqq";
		
		System.out.println(original.password);
		System.out.println(cloned.password);	
	}
}
```
   
 당연하게도 값이 같지 않다. 복제된 이후 복제의 값이 바뀌었기 때문.    
    
 **결과 화면**   
    
![image](https://user-images.githubusercontent.com/84966961/122697757-f7cd3600-d280-11eb-961f-c912aa3df6ed.png)
   
<hr/>
   
**깊은 복제**    

```java
public class DeepClone implements Cloneable{

	public String name;
	public int age;
	public int[] scores;	// 얕은 복제와 달리 배열,
	public Car car;			// 클래스와 같은 참조 타입 필드가 존재한다.
	// 참조형은 주소값이 생기게 되고, clone을 만들면 값이 복제된다.
	
	public DeepClone(String name, int age, int[] scores, Car car) {
		super();
		this.name = name;
		this.age = age;
		this.scores = scores;
		this.car = car;
	}
}
```

 이럴 경우에는 단순히 얕은 복제를 하게 되면 단순히 주소만 참조하게 된다. 그렇게 되면 참조한 데이터가 변동되면 복제한 자신도 영향을 받으므로 통째로 객체를 새로 만들어 복제를 해야한다. 이럴 경우에는 `clone()`을 재정의하여 구조 자체를 복제해주어야만 한다.   
   
 다음은 구조를 복제한 즉, 깊은 복제를 한 코드이다.   
   
**깊은 복제 코드**    
```java
public class DeepClone implements Cloneable{

	public String name;
	public int age;
	public int[] scores;	// 얕은 복제와 달리 배열,
	public Car car;			// 클래스와 같은 참조 타입 필드가 존재한다.
	// 참조형은 주소값이 생기게 되고, clone을 만들면 값이 복제된다.
	
	public DeepClone(String name, int age, int[] scores, Car car) {
		super();
		this.name = name;
		this.age = age;
		this.scores = scores;
		this.car = car;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {

		DeepClone cloned = (DeepClone) super.clone();	// 일반 멤버 변수는 그대로 복제한다.
		// 기본형은 값만 복제되기 때문(교재 466p 아래서 3번째줄 내용.)
		// 하지만 참조형(배열, 클래스 등)은 주소만 복제되기 때문에 새 객체를 형성해준다.
		
		cloned.scores = Arrays.copyOf(this.scores, this.scores.length);
		// 특정한 길이의 어레이를 만들어 기존 값을 통째로 복사하여 객체를 만듬.
		
		cloned.car = new Car(this.car.model);
		// 기존 car의 모델을 매개값 그대로 넣어서 새로 객체를 만듬.
		
		
		return cloned;
	}

	public DeepClone getClone() {
		DeepClone cloned = null;
		
		try {
			cloned = (DeepClone)clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return cloned;
	}
}
```

 `scores`와 `Car`를 각각 메서드를 통해 혹은 새로운 인스턴스를 만들어 정의 내려줌으로써 새로운 객체로 만들었다.


<hr/>

**깊은 복제 테스트**    
    
 깊은 복제를 다음 코드로 테스트해보자.   
   
```java
public class DeepCloneTest {

	public static void main(String[] args) {

		DeepClone original = new DeepClone("홍길동", 25, new int[] {90,90}, new Car("소나타"));
		
		DeepClone cloned = original.getClone();
		
		// 복사본의 내용 변경
		cloned.scores[0] = 100;
		cloned.car.model = "그랜져";
		
		// 기존과 복사본의 내용 변경 차이 프린트
		
		System.out.println("-------original-----");
		System.out.println(original.scores[0]);
		System.out.println(original.scores[1]);
		System.out.println(original.car.model);
		System.out.println("-------------------");
		System.out.println("-------cloned------");
		System.out.println(cloned.scores[0]);
		System.out.println(cloned.scores[1]);
		System.out.println(cloned.car.model);
	}
}
```

**결과 화면**    
    
![image](https://user-images.githubusercontent.com/84966961/122699632-b63e8a00-d284-11eb-8084-c1a301f3f6d6.png)
   

   
 이미지를 비교해보면 복제 객체의 값을 변경하더라도 기존 객체의 값에는 영향을 주지 않는 것을 알 수 있다. 하지만 얕은 복제의 경우에는 참조형의 주소값만 불러오므로 얕은 복제를 한 복제 객체의 값을 바꾸게 되면 기존 객체에서도 값이 바뀐다는 점을 기억하면 된다.   
    

**얕은 객체와 깊은 객체 이미지**   
   
![image](https://user-images.githubusercontent.com/84966961/122699806-0fa6b900-d285-11eb-85ba-2d79837da747.png)   
   
 
<br/><br/>
<hr/>

### 교재 472p : 11.3.5 객체 소멸자(finalize())  

 생략



<br/><br/>
<hr/>

## 교재 473p : 11.4 Objects 클래스     


생략




<br/><br/>
<hr/>

### 교재 477p : 11.4.3 해시코드 생성(hash(), hashCode())    
   
 기존에는 존재하던 해시코드를 복사하여 사용했었지만 다른 방법으로는 `Objects.hash();`를 통해 직접 해시코드를 새로 생성할 수도 있다. 기존 해시코드를 사용할 수 없는 경우도 있기 때문에 `Objects`의 `hash()`기능을 활용하여 만들면 될 것이다.


<br/><br/>
<hr/>

### 교재 478p : 11.4.4 널 여부 조사(isNull(), nonNull(), requireNonNull())    
   
생략


<br/><br/>
<hr/>

### 교재 480p : 11.4.5 객체 문자 정보(toString())    
   
 일전에 스타크래프트의 탱크 이름을 그대로 불러오고 싶어서 `toString()`을 오버라이딩을 하면서 주소값이 아닌 직접 이름을 불러왔던 적이 있다.   
   
**0611_Interface2 starcraft.tank class**
```java
public class Tank extends GrountUnit implements Repairable {

	public Tank() {
		super(150);
		hitPoint = MAX_HP;

	}

	@Override
	public String toString() {
		return "Tank";
	}
}
```
   
 Objects.toString()은 객체의 문자 정보를 리턴하는데, 다음 두 가지로 오버로딩되어 있다.   
    
 | 리턴 타입 | 메소드(매개 변수) | 설명 |
 |---|---|---|---|
 | String | toString(Object o) | not null -> o.toString() / null -> "null" |
 | String | toString(Object o, String nullDefault) | not null -> o.toString() / null -> "nullDefault" |
   
 첫 번째 매개값이 not null이면 toString()으로 얻은 값을 리턴하고, null이면 "null" 또는 두 번째 매개값인 "nullDefault"를 리턴한다.   
    
  "nullDefault"에는 출력을 원하는 내용을 넣어주면 된다.
 
```java
 	System.out.println(Objects.toString(str2, "이름이 없습니다."));
```
 
 


<br/><br/>
<hr/>

## 교재 481p : 11.5 System 클래스   
    
생략


<br/><br/>
<hr/>

## 교재 489p : 11.6 Class 클래스   
   
 자바는 클래스와 인터페이스의 메타 데이터를 java.lang 패키지에 소속된 Class 클래스로 관리ㅏㄴ다. 여기서 메타 데이터란 클래스의 이름, 생성자 정보, 필드 정보, 메소드 정보를 말한다.   
   
<br/><br/>
<hr/>

### 교재 489p : 11.6.1 Class 클래스 객체 얻기(getClass(), forName())      
   
 java API 8 : Class 클래스 > getclasses
![image](https://user-images.githubusercontent.com/84966961/122702020-82199800-d289-11eb-942c-670713931e2e.png)


**클래스 정보 얻기**
```
public class ClassTest {

	public static void main(String[] args) {
		
		Car car = new Car("마티즈");
		
		Class clazz = car.getClass();	// 클래스의 정보를 알아낼 수 있다.
		
		System.out.println(clazz.getName());
		System.out.println(clazz.getSimpleName());
		System.out.println(clazz.getPackage().getName());
		System.out.println(clazz.getPackageName());
	}
}
```

**클래스 정보 화면**   
   
![image](https://user-images.githubusercontent.com/84966961/122702087-a37a8400-d289-11eb-8f82-df641ca64652.png)
   
```
		// 클래스를 생성하지 않더라도 Car 클래스의 정보를 get할 수 있는 방법.
		try {
			Class Clazz2 = Class.forName("clone.Car");	// 클래스가 있는지 없는지 알고 싶을 때 사용한다.
			// 클래스 생성 이전에 오류를 대비하여 생성함.
			System.out.println(clazz.getName());
			System.out.println(clazz.getSimpleName());
			System.out.println(clazz.getPackage().getName());
			System.out.println(clazz.getPackageName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
```
   
 클래스 생성 전에 미리 `forName()` 메서드를 통해 클래스가 있는지 확인하여 미리 오류를 확인할 수 있다. `forName()`은 객체를 생성하는 것이 아니다. `forName()`을 통해서 객체를 생성하는 방법은 `11.6.3 동적 객체 생성(newInstance())`에 나와 있다.   
   

   
<br/><br/>
<hr/>

### 교재 493p : 11.6.3 동적 객체 생성(newInstance())      

 Class 객체를 이용하면 new 연산자를 사용하지 않아도 동적으로 객체를 생성할 수 있다. 이 방법은 코드 작성시에 클래스 이름을 결정할 수 없고, 런타임 시에 클래스 이름이 결정되는 경우에 매우 유용하게 사용된다. 다음 코드처럼 `Class.forName()` 메소드로 `Class` 객체를 얻은 다음 `newInstance()` 메소드를 호출하면 `SendAction` 타입의 객체를 얻을 수 있다.

```java
public class NewInstanceTest {

	public static void main(String[] args) {

		try {
			Class clazz = Class.forName("newinstance.SendAction"); // 해당 객체의 존재 유무만 판단.
			SendAction action = (SendAction) clazz.newInstance(); // newInstance 예외 경우
			action.exectue();
			Class clazz2 = Class.forName("newinstance.ReceiveAction"); // 해당 객체의 존재 유무만 판단.
			ReceiveAction action2 = (ReceiveAction) clazz2.newInstance(); // newInstance 예외 경우
			action2.exectue();
		} catch (ClassNotFoundException e) { // forName 예외 경우
			// forName 예외 경우 : 해당 클래스를 찾지 못할 경우
			e.printStackTrace();
		} catch (InstantiationException e) {
			// newInstance 예외 경우1 : 해당 클래스가 추상 클래스이거나 인터페이스일 경우
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// newInstance 예외 경우2 : 클래스나 생성자가 접근 제한자로 인해 접근할 수 없을 경우
			e.printStackTrace();
		}
	}
}
```

**결과 화면**   
 ![image](https://user-images.githubusercontent.com/84966961/122703607-f30e7f00-d28c-11eb-97e2-645681f2d608.png)   
    
   
<br/><br/>
<hr/>

## 교재 496p : 11.7 String 클래스   
   
 어떤 프로그램이건 문자열은 데이터로서 아주 많이 사용된다. 그렇기 때문에 문자열을 생성하는 방법과 추출, 비교, 찾기, 분리, 변환 등을 제공하는 메소드를 잘 익혀두어야 한다.

<br/><br/>
<hr/>

### 교재 496p : 11.7.1 String 생성자   
   
 자바의 문자열은 java.lang 패키지의 String 클래스의 인스턴스로 관리된다. 소스상에서 문자열 리터럴은 String 객체로 자동 생성되지만, String 클래스의 다양한 생성자를 이용해서 직접 Sring 객체를 생성할 수도 있다.

java API 8 String : https://docs.oracle.com/javase/8/docs/api/

**많은 13개의 String 생성자들**   
    
![image](https://user-images.githubusercontent.com/84966961/122703977-a6777380-d28d-11eb-983c-3cb4c70f0336.png)   
   
 `Deprecated`는 사용을 추천하지 않는 생성자를 의미한다. 예전 자바에서는 사용되었으나, 현재 버전과 차후 버전에서는 사용하지 말라는 뜻이다.   

 메소드 또한 많은 양이 있다.(같은 이름의 메소드도 많음. 오버로딩된 메소드도 많다는 뜻임.)   

```
public class ByteToStringExample {
	public static void main(String[] args) {
		byte[] bytes = { 72, 101, 108, 108, 111, 32, 74, 97, 118, 97  };
		
		String str1 = new String(bytes);	// bytes로 초기화
		System.out.println(str1);	// Hello Java
		
		String str2 = new String(bytes, 6, 4);	// 6번째 이후로 4열까지 초기화
		System.out.println(str2);	// Java
	}
}
```

**출력 이미지**   
![image](https://user-images.githubusercontent.com/84966961/122704531-e3903580-d28e-11eb-87d9-1623abcffe59.png)
   
 































