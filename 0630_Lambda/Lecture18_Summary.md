# 람다 (Lambda)
Key Word : 람다식, 익명구현 객체, 람다식 생성 방법.

<hr/>

## 교재 678p : 14.1 람다식이란?    
  
 란다식은 이름없는 함수이다.




```java
Runnable runnable = new Runnable() {
  public void run() {
};
```
  
 다음과 같이 익명 구현 객체를 람다식으로 표현 가능하다.  
  
```java
Runnable runnable = () -> {...};
```

 `"(매개변수) -> {실행코드};"` 형태로 작성되는 람다식은 마치 함수 정의 형태를 띠고 있지만 런타임 시에 인터페이스 익명 구현 객체로 생성된다. 위의 람다식은 Runnable의 익명 구현 객체를 생성하게 된다.
 
 <br/><br/>
 <hr/>
 
 ## 교재 679p : 14.2 람다식 기본 몬법   

 함수적 스타일의 람다식을 작성하는 방법은 다음과 같다.   
   
```
(타입 매개변수, ...) -> { 실행문; ... }
```
   
`(타입 매개변수, ...)`는 오른쪽 `중괄호 {}` 블록을 실행하기 위해 필요한 값을 제공하는 역할을 한다. **매개 변수의 이름은 개발자가 자유롭게 줄 수 있다.** `->` 기호는 매개 변수를 이용해서 중괄호  {}를 실핸한다는 뜻으로 해성하면 된다. 예를들어 `int 매개 변수 a`의 값을 콘솔에 출력하기 위해 다음과 같이 람다식을 작성할 수 있다.   
    
```
(int a) -> { System.out.print(a); }
```

 매개 변수 타입은 런타임시에 대입되는 값에 따라 자동으로 인식될 수 있기 때문에 람다식에서는 매개 변수의 타입을 일판적으로 언급하지 않는다. 그래서 위 코드는 다음과 같이 작성할 수 있다.   
   
```
(a) -> { System.out.print(a); }
```

 하나의 매개 변수와 하나의 실행문만 있다면 괄호 ()와 중괄호 {}를 생략할 수 있다.   
   
```
a -> System.out.print(a);
```
   
매개 변수가 없을 때는 반드시 빈 괄호로 명시해줘야 한다.   
   
```
() -> { 실행문; ... }
```
   
 중괄호 {} 실행 후 리턴값이 필요하면 다음과 같이 리턴받을 수 도 있다.   
    
 ```
  (x, y) -> {return x + y;};
 ```
 
 단, 중괄호에 리턴문만 있을 경우 리턴과 중괄호 {}를 생략할 수 있다.   
     
 ```
  (x, y) -> x + y;
 ```

 <br/><br/>
 <hr/>
 
 ## 교재 680p : 14.3 타겟 타입과 함수적 인터페이스   
   
```
	인터페이스 변수 = 람다식;
```
   
 람다식은 인터페이스 타입을 통해 익명 구현 클래스를 구현하고 객체화 한다. 람다식은 대입될 인터페이스의 종류에 따라 작성 방법이 달라지기 때문에 람다식이 대입될 인터페이스를 람다식의 타겟 타입(target type)이라고 한다.   

   

<br/><br/>
---
 
 ### 교재 680p : 14.3.1 함수적 인터페이스(@FunctionalInterface)   
   
   
함수형 인터페이스 : 추상메소드는 하나만 정의한다.   
   
 컴파일시 학수적 인터페이스를 체크해주도록 하고 싶다면 @FunctionalInterface 어노테이션을 붙이면 된다. 다만, 인터페이스 내부에 두 개 이상의 추상 메소드가 존재 할 경우 컴파일 에러를 발생 시킨다.   

```
@FunctionalInterface
public interface MyFunc{
    int sum(int x,int y);
}
```

  @FunctionalInterface 어노테이션은 선택사항이다. 이 어노테이션이 없더라도 하나의 추상 메소드만 있다면 모두 함수적 인터페이스이다. 그러나 실수로 두 개 이상의 추상 메소드를 선언하는 것을 방지하고 싶다면 붙여주는 것이 좋다. 람다식은 타켓 타입인 함수적 인터페이스가 가지고 있는 추상 메소드의 선언 형태에 따라서 작성 방법이 달라지는데, 이것에 대하여 자세히 알아보자.   
   
<br/><br/>
---
 
 ### 교재 681p : 14.3.2 매개 변수와 리턴값이 없는 람다식   
  
**MyFunctionalInterface.java 함수적 인터페이스**
```java
@FunctionalInterface
public interface MyFunctionalInterface {
    public void method();
}
```

 이 인터페이스를 타켓 타입으로 갖는 람다식은 다음과 같은 형태로 작성해야 한다. 람다식에서 매개 변수가 없는 이유는 method()가 매개 변수를 가지지 않기 때문이다.   
   
```java
MyFunctionalInterface fi = () -> {...}
```

 람다식이 대입된 인터페이스의 참조 변수는 다음과 같이 method()를 호출할 수 있다. **method() 호출은 람다식의 중괄호 {}를 실행**시킨다.   
   
```java
fi.method();
```
   
**MyFunctionalInterfaceExample.java 람다식**
```java
public class MyFunctionalInterfaceExample { 
	public static void main(String[] args) {
		MyFunctionalInterface fi;
		
		fi= () -> { 
			String str = "method call1";
			System.out.println(str);
		};
		fi.method();
		
		fi = () -> { System.out.println("method call2"); };
		fi.method();
		
		fi = () -> System.out.println("method call3");
		fi.method();
	}
}
```

 fi 라는 필드를 선언한 후 변수처럼 계속 초기화를 거쳐 사용할 수 있다.



<br/><br/>
---
 
 ### 교재 682p : 14.3.3 매개 변수가 있는 람다식   
  
 기존의 예제에 매개변수 `int x`를 넣게 되면 이클립스에서는 다음과 같이 오류가 발생한다.

**MyFunctionalInterface.java 함수적 인터페이스 **	
```java
@FunctionalInterface
public interface MyFunctionalInterface {
    public void method(int x);
}
```
   
**오류 화면**   
![image](https://user-images.githubusercontent.com/84966961/123897037-b97cf880-d99d-11eb-8707-caecbbc8113e.png)
   
그렇기 때문에 다음과 같이 람다식에도 매개변수를 넣어 바꿔줘야 한다.   
   

**MyFunctionalInterfaceExample.java**	
```java
public class MyFunctionalInterfaceExample { 
	public static void main(String[] args) {
		MyFunctionalInterface fi;
		
		fi= (x) -> { 
			String str = "method call1";
			System.out.println(str);
		};
		fi.method(10);
		
		fi = (x) -> { System.out.println("method call2"); };
		fi.method(20);
		
		fi = (x) -> System.out.println("method call3");
		fi.method(30);
	}
}
```
   
**결과 화면**   
   
![image](https://user-images.githubusercontent.com/84966961/123897214-f34dff00-d99d-11eb-9af5-768554331095.png)
   




<br/><br/>
---
 
 ### 교재 683p : 14.3.4 리턴값이 있는 람다식   
  
 이번에는 함수적 인터페이스의 추상 메소드의 `int`라는 리턴값이 있고, 매개 변수는 `int x`와 `int y` 2개를 가질 때를 알아보자.   
   
**MyFunctionalInterface.java 함수적 인터페이스 **	
```java
@FunctionalInterface
public interface MyFunctionalInterface {
    public int method(int x, int y);
}
```

 똑같이 오류가 발생한다.

**오류 화면**   
![image](https://user-images.githubusercontent.com/84966961/123897392-46c04d00-d99e-11eb-8aa6-4b23f1f16c81.png)   
   
다음과 같이 바꿔준다.
   
**MyFunctionalInterfaceExampl.java**	
```java
public class MyFunctionalInterfaceExample {
	public static void main(String[] args) {
		MyFunctionalInterface fi;
		
		fi= (x,y) -> x + y;
		
		// alter : 리턴문이 있는 경우
//		fi= (x,y) -> { 
//			return x + y;
//		};
		
		System.out.println(fi.method(10,20));

		System.out.println(fi.method(20,30));
		
		System.out.println(fi.method(30,40));
	}
}
```
   
 다음 리턴값이 존재하는 람다식의 결과로 `30, 50, 70` 이 출력되는 것을 알 수 있다.

 ---
 
 만약 중괄호 {}에 return문만 있고, returen문 뒤에 연산식이나 메소드 호출이 오는 경우라면 다음과 같이 작성할 수 있다.   
   
```java
	public static int sum(int x, int y) {
		return x + y;
	}
	
	-------------------
	
	fi= (x,y) -> sum(x, y);
```

 이렇게 따로 메소드를 정의하여 람다식 내부에 메소드를 사용할 수 있다.

**MyFunctionalInterfaceExample.java**
```java
public class MyFunctionalInterfaceExample {
	public static void main(String[] args) {
		MyFunctionalInterface fi;
		
		fi= (x,y) -> x + y;
		
		// alter : 리턴문이 있는 경우
//		fi= (x,y) -> { 
//			return x + y;
//		};
		
		System.out.println(fi.method(10,20));

		System.out.println(fi.method(20,30));
		
		System.out.println(fi.method(30,40));
		
		fi= (x,y) -> sum(x, y);
		
		System.out.println(fi.method(100, 23));
		
	}
	
	public static int sum(int x, int y) {
		return x + y;
	}
}
```

<br/><br/><br/>
---

#### 람다식을 사용하는 이유

```java
public class StringConcatTest {

	public static void main(String[] args) {

		StringConcatImpl sc = new StringConcatImpl();
		String s1 = "java ";
		String s2 = "Version 10";
		
		System.out.println("클래스를 이용한 방식 : 인터페이스 + 구현 클래스 + 테스트 클래스");
		sc.makeString(s1, s2);
				
		System.out.println("람다식를 이용한 방식 : 인터페이스 + 람다식 테스트 클래스");
		StringConcat scfi = (a, b) -> {
			System.out.println(a + b);
		};
		
		scfi.makeString(s1, s2);
		
		System.out.println("");
		System.out.println("람다식을 활용하게 되면 중간의 구현 클래스가 필요 없어져 효율이 증가한다.");
	}
}
```


**결과 화면**   
   
![image](https://user-images.githubusercontent.com/84966961/123899330-f1863a80-d9a1-11eb-94cf-393e0fcee8cf.png)



<br/><br/>
<hr/>
 
 ## 교재 685p : 14.4 클래스 멤버와 로컬 변수 사용   
     
 람다식의 실행 블록에는 클래스의 멤버 (필드와 메소드) 및 로컬 변수를 사용할 수 있다. 클래스의 멤버는 제약 사항 없이 사용 가능하지만, 로컬 변수는 제약 사항이 따른다. 자세한 내용을 알아보기로 하자.   
   

<br/>
---
 
 ### 교재 685p : 14.4.1 클래스의 멤버 사용   
       
 람다식 실행 블록에는 클래스의 멤버인 필드와 메소드를 제약 사항 없이 사용할 수 있다. 하지만 this 키워드를 사용할 때에는 주의가 필요하다. 일반적으로 익명 객체 내부에서 this는 익명 객체의 참조이지만, 람다식에서 this는 내부적으로 생성되는 익명 객체의 참조가 아니라 람다식을 실행한 객체의 참조이다. 다음 예제는 람다식에서 바깥 객체와 중첩 객체의 참조를 얻어 필드값을 출력하는 방법을 보여주고 있다. 중첩 객체 Inner에서 람다식을 실행했기 때문에 람다식 내부에서는 this는 중첩 객체 Inner이다.   
   

