# Generic
Key Word : 제네릭, 멀티 타입 파라미터, 제네릭 메소드, 

<hr/>

## 교재 659p : 13.3 멀티 타입 파라미터(class<K, V,... >, interface<K, V,...>)

 제네릭 타입은 두 개 이상의 멀티 타입 파라미터를 사용할 수 있는데, 이 경우 각 타입 파라미터를 콤마로 구분한다.	
	 
 -> 파라미터 타입의 양식처럼 지정해서 사용할 수 있다?!


**Product.java**
```java
public class Product<T, M> {
	private T kind;
	private M model;
	
	public T getKind() { return this.kind; }
	public M getModel() { return this.model; }
	
	public void setKind(T kind) { this.kind = kind; }
	public void setModel(M model) { this.model = model; }
}

```

**ProductExample.java**
```java
public class ProductExample {
	public static void main(String[] args) {
		Product<Tv, String> product1 = new Product<Tv, String>();
		product1.setKind(new Tv());
		product1.setModel("스마트Tv");
		Tv tv = product1.getKind();
		String tvModel = product1.getModel();
		System.out.println(tvModel);
		
		Product<Car, String> product2 = new Product<Car, String>();
		product2.setKind(new Car());
		product2.setModel("디젤");
		Car car = product2.getKind();
		String carModel = product2.getModel();
		System.out.println(carModel);
	}
}
```

 -> Product 타입으로 만들어서 Car와 Tv를 private하게 저장해둠.


<br/><br/>
<hr/>
  
## 교재 660p : 13.4 제네릭 메소드(<T, R> R method(T d))	  
   
 제네릭 메소드는 매개 타입과 리턴 타입으로 파라미터를 갖는 메소드를 말한다. 제네릭 메소드를 선언하는 방법은 리턴 타입 앞에 <>기호를 추가하고 타입 파라미터를 기술한 다음, 리턴 타입과 매개 타입으로 타입 파라미터를 사용하면 된다.	   
   
```
public <타입 파라미터,...> 리턴타입 메소드명(매개변수,...){...}
```
   
 제네릭 메소드는 두가지 방식으로 호출할 수 있다. 코드에서 타입 파라미터의 구체적인 타입을 명시적으로 지정해도 되고, 컴파일러가 매개값의 타입을 보고 구체적인 타입을 추정하도록 할 수 도 있다.   
   
```
리턴타입 변수 = <구체적인 타입> 메소드명(매개 값);
리턴타입 변수 = 메소드명(매개 값);
```
   
 다음 코드는 boxing() 메소드를 호출하는 코드이다.   
   
```java
Box<Integer> box = <Interger>boxing(100);		// 타입 파라미터를 명시적으로 Integer로 지정
Box<Integer> box = boxing(100);					// 타입 파라미터를 Integer로 추정
```

 -> boxing의 매개값 `100`이 int이므로 타입 파라미터를 Integer로 추정한다.	


<br/><br/>


#### 예제를 통한 이해  
  
**Util.java**  
```java
public class Util {
	public static <T> Box<T> boxing(T t) {		// 스태틱하고 제네릭한 메소드를 정의함.
		Box<T> box = new Box<T>();
		box.set(t);
		return box;
	}
}
```
   
**BoxingMethodExample.java**   
```java
public class BoxingMethodExample {
	public static void main(String[] args) {
		Box<Integer> box1 = Util.<Integer>boxing(100);
		int intValue = box1.get();
		
		Box<String> box2 = Util.boxing("홍길동");
		String strValue = box2.get();
	}
}
```
   
 스태틱하게 정의한 boxing 메소드를 Example에서 사용할 때 따로 객체화 하지 않고 사용할 수 있다. T는 Box의 파라미터이자 boxing의 공통적인 파라미터이다. Box라는 클래스와 Util.boxing의 매개변수를 하나로 묶어 사용하고 싶을 때 같은 매개변수 <T>로 묶어 정의하고 사용한다. 위의 설명처럼 그렇게 되면 제네릭 타입의 객체에 메소드를 저장할 때 메소드의 파라미터를 보고 알아서 추정해준다.    


**제네릭 타입 변수 저장**   

```
int value = Util.boxing(100);	// 제네릭 타입은 객체(Wrapper 타입)에 담아야한다.    
```



-> 박스가 제네릭하고 getter, setter가 정의되어 있는 클래스이다.   

**Box.java**
```java
public class Box<T> {
	private T t;
	public T get() { return t; }
	public void set(T t) { this.t = t; }
}
```

#### 제네릭 클래스와 제네릭 메소드를 이용한 Compare    

**Box.java**
```java
public class Box<T> {
	private T t;
	public T get() { return t; }
	public void set(T t) { this.t = t; }
}
```



































































