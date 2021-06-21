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
   
```java
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
   
```java
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

```java
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
   
 
### 교재 498p : 11.7.2 String 메소드   
   
**주로 쓰이는 메소드**

1. 교재 499p : 문자 추출 (charAt())   
   
 
```java
public class StringCharAtExample {
	public static void main(String[] args) {
		String ssn = "010624-1230123";	// 주민번호
		char sex = ssn.charAt(7);		// 7자리 (0부터시작)
		switch (sex) {	// 1이 문자형태로 반환됨. `1`
			case '1':
			case '3':
				System.out.println("남자 입니다.");
				break;
			case '2':
			case '4':
				System.out.println("여자 입니다.");
				break;
		}
	}
}
```

<br/>
<hr/>

2. 교재 500p : 문자열 비교 (equals())   

```java
public class StringEqualsExample {
	public static void main(String[] args) {
		String strVar1 = new String("신민철");
		String strVar2 = "신민철";

		if(strVar1 == strVar2) {
			System.out.println("같은 String 객체를 참조");
		} else {
			System.out.println("다른 String 객체를 참조");
		}
		
		if(strVar1.equals(strVar2)) {	// String.equals 는 이미 String으로 오버라이딩이 된 상태이다.
			System.out.println("같은 문자열을 가짐");
		} else {
			System.out.println("다른 문자열을 가짐");
		}
	}
}
```
   
 `String.equals()` 는 이미 `String`으로 오버라이딩이 된 상태이다.

<br/>
<hr/>

3. 교재 505p : 문자열 길이(length())   
   

```java
package string;

public class StringLengthExample {
	public static void main(String[] args) {
		String ssn = "7306241230123";
		int length = ssn.length();		// 문자열의 길이 리턴.
		if(length == 13) {
			System.out.println("주민번호 자리수가 맞습니다.");
		} else {
			System.out.println("주민번호 자리수가 틀립니다.");
		}
	}
}

```
   
 문자열의 길이(문자의 수)를 리턴한다.
   

<br/>
<hr/>

4. 교재 504p : 문자열 찾기(indexOf())
   
```java
public class StringIndexOfExample {
	public static void main(String[] args) {
		String subject = "자바 프로그래밍";
		
		int location = subject.indexOf("프로그래밍");	// "프로그래밍"이 들어간 첫 인덱스를 리턴한다.
		System.out.println(location);
		
		if(subject.indexOf("자바") != -1) {		// -1의 의미 : 못찾은 값에 대해서는 -1을 리턴한다.
			System.out.println("자바와 관련된 책이군요");
		} else {
			System.out.println("자바와 관련없는 책이군요");
		}
	}
}
```
   
 매개값으로 주어진 문자열이 시작되는 인덱스를 리턴한다. 다만 못찾은 값에 대해서는 `-1`을 리턴한다.


<br/>
<hr/>

5. 교재 506p : 문자열 대치(replace())   
   
```java
public class StringReplaceExample {
	public static void main(String[] args) {
		String oldStr = "자바는 객체지향언어 입니다. 자바는 풍부한 API를 지원합니다.";
		String newStr = oldStr.replace("자바", "JAVA");
		
		System.out.println(oldStr);
		System.out.println(newStr);
	}
}
```

 찾아 바꾸기 와 같은 기능이다. 매개 변수의 값을 찾아 두번째 매개 변수 값으로 바꿔준다.

<br/>
<hr/>

6. 교재 507p : 문자열 잘라내기(substring())   

```java
public class StringSubstringExample {
	public static void main(String[] args) {	
		String ssn = "880815-1234567 ";
		
		String firstNum = ssn.substring(0, 6);	// 0(포함) ~ 6(제외) 사이의 문자열을 추출하는 것.
		System.out.println(firstNum);		
		
		String secondNum = ssn.substring(7);	// 7부터의 문자열 추출
		System.out.println(secondNum);
	} 
}
```
   
 주어진 인덱스에서 문자열을 추출한다.


<br/>
<hr/>

7. 교재 508p : 알파벳 소,대문자 변경(toLowerCase(),toUpperCase())   

```java
public class StringToLowerUpperCaseExample {
	public static void main(String[] args) {
		String str1 = "Java Programming";
		String str2 = "JAVA Programming";		
		
		System.out.println(str1.equals(str2));
		
		String lowerStr1 = str1.toUpperCase();		// 대문자로
		String lowerStr2 = str2.toLowerCase();		// 소문자로
		System.out.println(lowerStr1.equals(lowerStr2));
		
		System.out.println(str1.equalsIgnoreCase(str2));	// 소문자 대문자 무시 비교하여 같은지 확인
		}
}
```

 모두 소문자로 혹은 대문자로 변경해준다.

<br/>
<hr/>

8. 교재 509p : 문자열 앞뒤 공백 잘라내기(trim())   
   
```java
public class StringTrimExample {
	public static void main(String[] args) {
		String tel1 = "  02";
		String tel2 = "123   ";
		String tel3 = "   1234   ";
		
		String tel = tel1.trim() + tel2.trim() + tel3.trim();
		System.out.println(tel);
	}
}
```

<br/>
<hr/>

9. 교재 510p : 문자열 변환(valueOf())   
   
```java
public class StringValueOfExample {
	public static void main(String[] args) {
		String str1 = String.valueOf(10);		// 해당 타입을 문자열로 바꾸어줌. `10`
		String str2 = String.valueOf(10.5);		// 해당 타입을 문자열로 바꾸어줌. `10.5`
		String str3 = String.valueOf(true);		// 해당 타입을 문자열로 바꾸어줌. `true`	
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
	}
}
```

 해당 타입을 문자열로 바꾸어 주는 기능이다. 이와 반대로 문자열을 숫자로 바꾸어주는 `Integer.parseInt()`라는 기능이 있었다.   
   

<br/><br/>
<hr/>

## 교재 511p : 11.8 StringTokenizer 클래스   
   
 문자열이 특정 구분자...
 
 생략

<br/><br/>
<hr/>

## 교재 514p : 11.9 StringBuffer, StringBuilder 클래스   
   
 String 클래스의 단점을 없애기 위한 클래스이다. 기존에 메모리에 할당된 경우에 값의 변화를 주면 새로운 객체를 생성하여 메모리가 소비되게 된다. 이런 문제를 없애기 위한 클래스들인 것이다.    
 다음의 그림처럼 기존의 메모리를 사용(변경)하는 것이 아니라 새로운 객체를 생성하므로 잦은 변경이 있는 시스템에서는 비효율적인 데이터 처리가 되게 된다.
    
 ![image](https://user-images.githubusercontent.com/84966961/122710896-31f80100-d29c-11eb-9da7-6d65c51abac8.png)   
   
 `StringBuffer`, `StringBuilder` 클래스는 기능과 사용 방법 모두가 똑같다. 하지만 이 둘의 차이는 동기화 기능의 유무 차이이다. `StringBuffer`는 멀티 스레드 환경에서 사용할 수 있도록 동기화가 적용되어 있어 스레드에 안전하지만, `StringBuilder`는 단일 스레드 환경에서만 사용하도록 설계되어 있다. 그러므로 `StringBuffer`가 좀 더 무거운 클래스이다.   

 `StringBuffer`, `StringBuilder` 클래스는 기존 String과는 달리 인스턴스 객체를 만든 이후에 사용하여야 한다.

```java
public class StringBuilderExample {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();	// 멀티 스레드 환경이 아니면 굳이 버퍼를 쓸 이유가 없다.
		// 버퍼로 바꾸어도 아래 기능은 똑같다.
		
		sb.append("Java ");
		sb.append("Program Study");		
		// 기존 String은 기존 메모리를 무시하고 새로운 메모리를 할당하지만 append는 기존 메모리에 추가한다.
		// 따라서 메모리 효율성이 증가한다.
		System.out.println(sb.toString());	// StringBuilder는 String 타입이 아니므로 반드시 사용전 toString()으로 타입을 바꾸어줘야 한다.
		
		sb.insert(4, "2");
		System.out.println(sb.toString());

		sb.setCharAt(4, '6');		// 5번째를 6으로 바꾸어라(2를 6으로)
		System.out.println(sb.toString());
		
		sb.replace(6, 13, "Book");	// 지정한 칸수(7칸)와 상관없이 book으로 바꾼다.
		System.out.println(sb.toString());
		
		sb.delete(4, 5);		// 삭제도 범위로 삭제 가능하다.
		System.out.println(sb.toString());
		
		int length = sb.length();
		System.out.println("총문자수: " + length);
		
		String result = sb.toString();
		System.out.println(result);		
	}
}
```

<br/><br/>
<hr/>

## 교재 517p : 11.11 정규표현식과 Pattern 클래스   
   
 생략...


<br/><br/>
<hr/>

## 교재 527p : 11.12 Wrapper(포장) 클래스   
   
 자바는 기본 타입(byte, char, short, int, long, float, double, boolean)의 값을 갖는 객체를 생성할 수 있다. 이런 객체를 포장(Wrapper) 객체라고 하는데, 그 이유는 기본 타입의 값을 내부에 두고 포장하기 때문이다. (기본 타입을 객체로 만든다는 뜻이다.)   
    
 기본형을 참조형으로 형변환 한다.(오토박싱)
   
| 기본 타입 | 포장 클래스 |
|---|---|
| byte | Byte |
| char | Character |
| short | Short |
| int | Integer |
| long | Long |
| float | Float |
| double | Double |
| boolean | Boolean |

 기본형은 참조형으로 원래는 형변환이 불가능하다.

```java
 Car car = (Car) 1;         (X)
```
   
 다음과 같이 형변환이 불가능하다. 하지만 다음과 같은 오토박싱은 가능하다.    
    
```java
		int x = 10;
		Integer i = new Integer(100);	// 100을 가지고 있는 것이 아니라 힙영역의 주소값을 가지고 있는 것이다.
		Double d = new Double(20.3);
```
   
 기본 타입으로는 객체를 구성할 수 없으니 Wapper 클래스를 사용하는 것이다. i와 d를 `System.out.println`으로 찍어보면 다음과 같다.
   
**결과 화면 Console**   
   
![image](https://user-images.githubusercontent.com/84966961/122713560-d4b27e80-d2a0-11eb-89ab-64f661d53456.png)   
   
 i와 d가 출력될 때 주소값이 나와야 하지만 이들은 toString이 오버라이드 되어서 출력된다는 것을 알 수 있다.    
    
```java
		System.out.println(i.toString());
		System.out.println(d.toString());
```
   
**Wapper 클래스만 가지는 오토박싱의 특이한 특징**    
   
```java
		int x = 10;
		Integer i = x;
		
		System.out.println(i);
```
    
 x의 기본 타입이 Integer 참조 타입에 들어가면 안된다. 하지만 위의 코드가 정상작동 되는 것을 알 수 있다. 이는 바로 Wapper 클래스의 오토박싱 특징 때문이다.   
   
```java
		int x = 10;
		Integer i = (Integer) x; // 오토박싱으로 형변환이 된다.
		
		Integer i2 = 30; 		// 바로 변수를 넣을 수 있다.
		int y = i2;				// 심지어 참조를 기본 타입에 넣어도 된다.
		
		Object obj = y;			// new Integer(y); 객체가 생성되서 들어감.
				
		System.out.println(i + i2);	// int + Integer 계산 가능
```
   
 Wrapper 클래스는 기본형 처리되어서 처리됨으로 기본형처럼 사용이 가능하다.    
   
<br/><br/>
<hr/>

### 교재 529p : 11.12.2 자동 박싱과 언박싱   
   
 **오토박싱 VS 오토언박싱**
   
   
<br/><br/>
<hr/>

### 교재 530p : 11.12.3 문자열을 기본 타입 값으로 변환   
   
 포장 클래스의 주요 용도는 기본 타입의 값을 박싱해서 포장 객체로 만드는 것이지만, 무자열을 기본 타입 값으로 변환할 때에도 많이 사용된다. 대부분의 포장 클래스에는 `"parse+기본타입"` 명으로 되어 있는 정적(static) 메소드가 있다. 이 메소드는 문자열을 매개값으로 받아 기본 타입 값으로 변환한다.

| 기본 타입 | 포장 클래스 |
|---|---|
| byte | num = Byte.parseByte("10"); |
| short | num = Short.parseShort("100"); |
| int | num = Integer.parseInteger("1000"); |
| long | num = Long.parseLong("10000"); |
| float | num = Float.parseFloat("2.5F"); |
| double | num = Double.parseDouble("3.5"); |
| boolean | num = Boolean.parseBoolean("true"); |





<br/><br/>
<hr/>

## 교재 538p : 11.14 Date, Calender 클래스   

<hr/>
   
## 교재 538p : 11.14 Date 클래스   
    
```java
		Date d = new Date();	//임포트 주의사항 : import java.util.Date; 을 해야함
		
		System.out.println(d);
```
   
<hr/>
   
 다음은 Date의 양식을 설정하여 표출한 방식이다.
   

```java
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시");	// 해당 양식이 정해져 있음.
		
		sdf.format(d); // sdf의 양식으로 변환해줌.
```
**출력 화면**   
![image](https://user-images.githubusercontent.com/84966961/122715317-a7b39b00-d2a3-11eb-94b9-392c480bb607.png)   

<hr/>

 다음은 연, 월을 표기한 방식이다. 컴퓨터는 1900년부터 시작하고 월의 경우에는 0월부터 시작하여 0 ~ 11월을 가진다.

```java
		// 년 (프로그램은 1900부터 시작)
		System.out.println(d.getYear());
		System.out.println(1900+d.getYear());
		// 월 (프로그램은 0월부터 시작)
		System.out.println(d.getMonth());
```
   
**출력 화면**   
![image](https://user-images.githubusercontent.com/84966961/122715548-fd884300-d2a3-11eb-9783-24c1f724d280.png)


<hr/>
   
### 교재 539p : 11.14.2 Calender 클래스  

```java
Calendar now = Calendar.getInstance(); 
		// 자기 타입의 메소드를 호출한다. Private으로 정의 되어있어서 인스턴스를 못만들기 때문이다.
		
		System.out.println(now);
```
   
**출력 화면** 
```
java.util.GregorianCalendar[time=1624256500418,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Asia/Seoul",offset=32400000,dstSavings=0,useDaylight=false,transitions=30,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2021,MONTH=5,WEEK_OF_YEAR=26,WEEK_OF_MONTH=4,DAY_OF_MONTH=21,DAY_OF_YEAR=172,DAY_OF_WEEK=2,DAY_OF_WEEK_IN_MONTH=3,AM_PM=1,HOUR=3,HOUR_OF_DAY=15,MINUTE=21,SECOND=40,MILLISECOND=418,ZONE_OFFSET=32400000,DST_OFFSET=0]
```
   
 Calender에는 다양항 상수(final)값들이 존재한다. YEAR, May, HOUR, MIN 등등..   
   
```java
public class CalendarExample {
	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();
		
		int year    = now.get(Calendar.YEAR);			// YEAR 상수 받기.          
		int month  = now.get(Calendar.MONTH) + 1;		// MONTH 상수 + 1(0월부터이므로) 
		int day    = now.get(Calendar.DAY_OF_MONTH);	// DAY_OF_MONTH 날짜 21 출력

		int week    = now.get(Calendar.DAY_OF_WEEK);	// DAY_OF_WEEK 요일 상수 출력
		String strWeek = null;
		switch(week) {									// 상수 변수 출력 문자열로 반환.
			case Calendar.MONDAY:
				strWeek = "월";
				break;
			case Calendar.TUESDAY:
				strWeek = "화";
				break;
			case Calendar.WEDNESDAY:
				strWeek = "수";
				break;
			case Calendar.THURSDAY:
				strWeek = "목";
				break;
			case Calendar.FRIDAY:
				strWeek = "금";
				break;
			case Calendar.SATURDAY:
				strWeek = "토";
				break;
			default:
				strWeek = "일";
		}
		
		int amPm  = now.get(Calendar.AM_PM);   // 오전 오후 값 반환
		String strAmPm = null;
		if(amPm == Calendar.AM) {
			strAmPm = "오전";
		} else {
			strAmPm = "오후";
		}
		
		int hour    = now.get(Calendar.HOUR);	// 시간 값 반환
		int minute  = now.get(Calendar.MINUTE);	// 분 값 반환
		int second  = now.get(Calendar.SECOND);	// 초 값 반환   

		//출력
		System.out.print(year + "년 ");
		System.out.print(month + "월 ");
		System.out.println(day + "일 ");
		
		System.out.print(strWeek + "요일 ");
		System.out.println(strAmPm + " ");
		
		System.out.print(hour + "시 ");
		System.out.print(minute + "분 ");
		System.out.println(second + "초 ");
	}
}
```
   
 코드를 보고 이해하면 될 듯 싶다. 이해가 안되면 주석을 확인하자.









