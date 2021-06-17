# 예외 처리(Exception)
Key Word : 예외 처리(Exception)    
   
<hr/>
   

## 교재 422p : 10.1 예외와 예외 클래스   
   
 에러는 JVM 실행에 문제 생긴 것이라 칭하고, 개발자가 만든 코드에 의한 오류는 예외(Exception)이라고 한다.   
 
 예외는 다음과 같은 구조로 일반 예외들과 실행 예외(Runtime Exception)가 존재한다. 일반 예외는 컴파일시 이클립스 내부에서 잡아낼 수 있는 오류이며 실행 예외를 처리하기 위해서는 개발자의 능력과 경험이 중요해진다.

**에러와 예외 계층 구조.**   
![image](https://user-images.githubusercontent.com/84966961/122313477-40b77e80-cf51-11eb-8bae-3f1224bc6bcd.png)   
   
 붉은 색의 칸들은 컴파일러가 예외를 체크하여 강제적으로 예외 처리를 해주어야 하는 예외들이다.   
   
![image](https://user-images.githubusercontent.com/84966961/122313603-73fa0d80-cf51-11eb-8301-d027bb630e36.png)


<br/><br/>
<hr/>

## 교재 423p : 10.2 실행 예외   

 흔히 발생하는 실행 예외들의 예제를 보고 이해해보자.

<br/><br/>
<hr/>

### 교재 423p : 10.2.1 NullPonterException   
   
 자바 프로그램에서 가장 빈번하게 발생하는 실행 예외는 NullPonterException 일 것이다. 이것은 객체 참조가 없는 상태, 즉 null 값을 갖는 참조 변수로 객체 접근 연산자인 도트를 사용했을 때 발생한다.

```java
package runtimeException;

public class NullPointerExceptionExample {
	public static void main(String[] args) {
		String data = null;
		System.out.println(data.toString());	//null을 스트링으로 바꿀 수 없ㅇ.ㅁ
	}
}
```


<br/><br/>
<hr/>

### 교재 424p : 10.2.2 ArrayIndexOutOfBoundsException   
   
 배열에서 인덱스 범위를 초과하여 실행할 경우 생기는 예외이다.   
    
```java
package runtimeException;

/*public class ArrayIndexOutOfBoundsExceptionExample {
	public static void main(String[] args) {
		String data1 = args[0];
		String data2 = args[1];
		
		System.out.println("args[0]: " + data1);
		System.out.println("args[1]: " + data2);
	}
}*/

public class ArrayIndexOutOfBoundsExceptionExample {
	public static void main(String[] args) {
		if(args.length == 2) {
			String data1 = args[0];
			String data2 = args[1];
			String data3 = args[2];
		
			System.out.println("args[0]: " + data1);
			System.out.println("args[1]: " + data2);
			System.out.println("args[2]: " + data3);
		} else {
			System.out.println("[실행 방법]");
			System.out.print("java  ArrayIndexOutOfBoundsExceptionExample  ");
			System.out.print("값1  값2");
		}
	}
}
```
   
**코드 작성을 위한 Arguments 넣기.**
![image](https://user-images.githubusercontent.com/84966961/122314721-b15f9a80-cf53-11eb-88e7-f1fa1485a213.png)

   
   
 예외를 방지하기 위해서는 배열값을 읽기 전에 배열의 길이를 먼저 조사하여 방지할 수 있다.



<br/><br/>
<hr/>

### 교재 424p : 10.2.3 NumberFormatException   
   
 프로그램을 개발하다 보면 문자열로 되어 있는 데이터를 숫자로 변경하는 경우가 자주 발생한다. 문자열을 숫자로 변환하는 방법은 여러가지가 있지만 가장 많이 사용되는 코드는 다음과 같다.   
    
| 반환 타입 | 메소드명(매개 변수) | 설명 |
|---|---|---|
| int | Integer.parseInt(String s) | 주어진 문자열을 정수로 변환해서 리턴 |
| double | Double.parseDouble(String s) | 주어진 문자열을 실수로 변환해서 리턴 |
    
 Integer와 Double은 포장(Wrapper) 클래스라고 하는데, 11장에서 자세히 설명한다. 이 클래스의 정적 메소드인 parseXXX() 메소드를 이용하면 문자열을 숫자로 변환할 수 있다.   
   
 변환시 변환할 수 없는 문자가 오게 되면 `NumberFormatException`을 발생시킨다.

```java
package runtimeException;

public class NumberFormatExceptionExample {
	public static void main(String[] args) {
		String data1 = "100";
		String data2 = "a100";					// a라는 변환할 수 없는 숫자가 있기 때문.
				
		int value1 = Integer.parseInt(data1);
		int value2 = Integer.parseInt(data2);
		
		int result = value1 + value2;
		System.out.println(data1 + "+" + data2 + "=" + result);
	}
}
```


<br/><br/>
<hr/>

### 교재 427p : 10.2.4 ClassCastException   
   
 타입 변환(Casting)은 상위 클래스와 하위 클래스 간에 발생하고 구현 클래스와 인터페이스 간에도 발생한다. 이러한 관계가 아니라면 클래스는 다른 클래스로 타입 변환 할 수 없다. 억지로 타입 변환을 실행할 경우 발생하는 예외가 `ClassCastException`이다.   
   
```java
public class ClassCastExceptionExample {
	public static void main(String[] args) {
		Dog dog = new Dog();
		changeDog(dog);
		
		Cat cat = new Cat();
		changeDog(cat);								// Dog dog = (Dog) cat; 이기 때문에 Dog과 Cat은 아무런 관계가 없으므로 캐스팅 불가.
	}
	
	public static void changeDog(Animal animal) {
		//if(animal instanceof Dog) {
			Dog dog = (Dog) animal; 				//ClassCastException 발생 가능
		//} 
	}
}

class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}
```

<br/><br/>
<hr/>

## 교재 429p : 10.3 예외 처리 코드   
   
 프로그램에서 예외가 발생했을 경우 브로그램의 갑작스러운 종료를 막고, 정상 실행을 유지할 수 있도록 처리하는 코드를 `예외 처리 코드`라고 한다. 자바 컴파일러는 소스 파일을 컴파일할 때 일반 예외가 발생할 가능성이 있는 코드를 발생하면 컴파일 오류를 발생시켜 개발자로 하여금 강제적으로 예외 처리 코드를 작성하도록 요구한다. 예외 처리 코드는 `try-catch-finally 블록`을 이용한다. `try-catch-finally 블록`은 생성자 내부와 메소드 내부에서 작성되어 일반 예외와 실행 예외가 발생할 경우 처리를 할 수 있도록 해준다.   
   
![image](https://user-images.githubusercontent.com/84966961/122316219-6c893300-cf56-11eb-8587-e2a252e24664.png)
   
 예외가 발생할 것 같은 코드를 try 내부에 작성해준다. 코드가 실행되던 도중 예외가 발생하면 즉각 catch로 넘어가 해당 예외를 처리해준 후 finally 로 넘어가서 코드가 진행되게 된다. 반대로 예외가 발생하지 않는다면 catch문은 건너 뛰어지고 finally로 이동하여 진행될 것이다. finally는 항상 실행 되는 블록이다.   
    
 일 전에 사용했던 예제 `NumberFormatExceptionExample`를 이용하여 try-catch 문을 작성해보았다.
 
```java
public class NumberFormatExceptionExample {
	public static void main(String[] args) {
		String data1 = "100";
		String data2 = "a100";
				
		int value1 = Integer.parseInt(data1);
		int value2;
		try {
			value2 = Integer.parseInt(data2);			
		} catch (NumberFormatException e) {		// catch문이 처리해야할 예외를 적어준다.
			System.out.println("숫자 형태의 구성이 아닙니다.");
			value2 = 200; 						// 오류발생시 강제 200으로 처리하겠다.
		}
		
		int result = value1 + value2;
		System.out.println(data1 + "+" + data2 + "=" + result);
	}
}
```
    
**Class.forName()**    
   
 Class.forName() 메소드는 매개값으로 주어진 클래스가 존재하면 Class 객체를 리턴하지만, 존재하지 않으면 ClassNotFoundException 예외를 발생시킨다.   
   
**TryCatchFinallyExample.java**
```java
public class TryCatchFinallyExample {
	public static void main(String[] args) {
		try {
			Class clazz = Class.forName("java.lang.String2");
		} catch(ClassNotFoundException e) {
			System.out.println("클래스가 존재하지 않습니다.");
		}
	}
}
```
   
 try 내부의 코드에 String2 객체가 존재하지 않으므로 `ClassNotFoundException`을 발생시키며 예외 발생 후 catch로 이동하여 오류 처리를 하는 것을 볼 수 있다.   
   
 다음은 실행 예외에 대한 예제이다. 실행 예외는 컴파일러가 예외에 대해서 확인해주지 않으므로 개발자의 경험에 따라 다음과 같이 try-catch-finally 블록을 사용해주어야 한다.
   
**TryCatchFinallyRuntimeExceptionExample.java**
```java
public class TryCatchFinallyRuntimeExceptionExample {
	public static void main(String[] args) {
		String data1 = null;
		String data2 = null;
		try {
			data1 = args[0];
			data2 = args[1];
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("실행 매개값의 수가 부족합니다.");
			System.out.println("[실행 방법]");
			System.out.println("java TryCatchFinallyRuntimeExceptionExample  num1  num2");
			return;
		} 
		
		try { 
			int value1 = Integer.parseInt(data1);
			int value2 = Integer.parseInt(data2);
			int result = value1 + value2;
			System.out.println(data1 + "+" + data2 + "=" + result);
		} catch(NumberFormatException e) {
			System.out.println("숫자로 변환할 수 없습니다.");
		} finally {
			System.out.println("다시 실행하세요.");
		}
	}
}

```
   


<br/><br/>
<hr/>

## 교재 433p : 10.4 예외 종류에 따른 처리 코드   
<br/>
<hr/>

### 교재 433p : 10.4.1&2 다중 catch & catch 순서    
   
 try 블록 내부에는 다양한 종류의 예외가 발생할 수 있다. 이럴 때 다중 catch 블록을 작성하여 예외 처리의 경우의 수를 늘려주면 된다. 단, 예외 처리가 좁은(하위 예외 클래스) 부분부터 넓은(상위 예외 클래스) 부분으로 점차적으로 넓어져야 한다. 만약 첫번째 catch문에 `Exception e`와 같은 상위 예외가 조건으로 들어간다면 아래의 예외는 모두 실행되지 않을 것이다. 모든 예외가 첫번째 catch문에서 빠져나가기 때문이다.   
     
**다중 catch 예시 이미지**   
![image](https://user-images.githubusercontent.com/84966961/122316237-74e16e00-cf56-11eb-81ca-a23394271b7f.png)    
   
 **다중 catch문 예제 코드**
```java
public class CatchByExceptionKindExample {
	public static void main(String[] args) {
		try {
			String data1 = args[0];
			String data2 = args[1];
			int value1 = Integer.parseInt(data1);
			int value2 = Integer.parseInt(data2);
			int result = value1 + value2;
			System.out.println(data1 + "+" + data2 + "=" + result);
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("실행 매개값의 수가 부족합니다.");
			System.out.println("[실행 방법]");
			System.out.println("java CatchByExceptionKindExample  num1  num2");
		} catch(NumberFormatException e) {
			System.out.println("숫자로 변환할 수 없습니다.");
		} finally {
			System.out.println("다시 실행하세요.");
		}
	}
}
```

 **수업 예제**   
```java
public class NumberFormatExceptionExample {
	public static void main(String[] args) {
		String data1 = "100";
		String data2 = "a100";
		String string = null;
				
		int value1 = Integer.parseInt(data1);
		int value2 = 0;
		try {
			System.out.println(string.toString());	// NullPointException 이 catch에 없지만 마지막 Exception이 잡아줌.
//			System.out.println(value1 / 0);		// catch문 조건에서 예외 처리 타입이 다르면 처리하지 못한다. 그래서 다른 catch문을 추가한다.(다중catch문)
			value2 = Integer.parseInt(data2);
		} catch (ArithmeticException e) {		// catch문이 처리해야할 예외를 적어준다.
			System.out.println(e.getMessage() + " : 정수를 0으로 나누시면 안됩니다.");
		} catch (NumberFormatException e) {		// catch문이 처리해야할 예외를 적어준다.
			System.out.println("숫자 형태의 구성이 아닙니다.");
			value2 = 200; 						// 오류발생시 강제 200으로 처리하겠다.
		} catch (Exception e) {					// 가장 상위 예외 클래스이므로 모든 예외를 받아준다. 
			System.out.println(e.getMessage() + " : 예외가 발생했습니다.");
		} finally {
			
		}
		
		int result = value1 + value2;
		System.out.println(data1 + "+" + data2 + "=" + result);
	}
}
```

<br/><br/>
<hr/>
   
### 교재 436p : 10.4.3 멀티 catch 

 자바 7부터 하나의 catch 블록에서 여러 개의 예외를 처리할 수 있도록 멀티 (multi) catch 기능을 추가했다. 여러개 예외를 하나의 catch문에서 받고 싶을때 `|` 를 사용하면 된다.   

```java
try {
	...
} catch (ArithmeticException | NumberFormatException e) {		// 멀티 catch문
	...
} catch (Exception e) {
	...
} finally {
	...
}
```
   
**멀티 catch 예제**
```java
public class NumberFormatExceptionExample {
	public static void main(String[] args) {
		String data1 = "100";
		String data2 = "a100";
		String string = null;
				
		int value1 = Integer.parseInt(data1);
		int value2 = 0;
		try {
			System.out.println(string.toString());	// NullPointException 이 catch에 없지만 마지막 Exception이 잡아줌.
//			System.out.println(value1 / 0);		// catch문 조건에서 예외 처리 타입이 다르면 처리하지 못한다. 그래서 다른 catch문을 추가한다.(다중catch문)
			value2 = Integer.parseInt(data2);
		} catch (ArithmeticException | NumberFormatException e) {		// 멀티 catch문
			System.out.println(e.getMessage() + " : 정수를 0으로 나누시면 안됩니다.");
		} catch (Exception e) {					// 가장 상위 예외 클래스이므로 모든 예외를 받아준다. 
			System.out.println(e.getMessage() + " : 예외가 발생했습니다.");
		} finally {				// 예외와는 별개로 무조건 한번은 실행해야 할 때 작성
			System.out.println("예외 처리에 대한 실행을 완료합니다.");
		}
		
		int result = value1 + value2;
		System.out.println(data1 + "+" + data2 + "=" + result);
	}
}
```

<br/><br/>
<hr/>

## 교재 438p : 자동 리소스 닫기   
      




<br/><br/>
<hr/>

## 교재 440p : 예외 떠넘기기   
      





<br/><br/>
<hr/>

### 교재 438p : 자동 리소스 닫기   
      




<br/><br/>
<hr/>

### 교재 438p : 자동 리소스 닫기   
      
