# Lecture21 : 컬렉션 프레임워크(Collection Framework)
Key Word : 컬렉션 프레임워크, List, ArrayList, Vector, LinkedList, Set, HashSet

<hr/>

 ## 교재 722p : 15.1 컬렉션 프레임워크 소개   
     
 애플리케이션을 개발하다 보면 **다수의 객체를 저자애 두고 필요할 때마다 꺼내서 사용하는 경우**가 많다. 만약 10개의 Product 객체를 저장해 두고, 필요할 때마다 하나씩 꺼내서 이용한다고 가정해보자. 어떻게 Product 객체를 효율적으로 추가, 검색, 삭제할지 고민해야 되는데, 가장 간단한 방법은 배열을 이용하는 것이다.  

```

```

배열은 쉽게 생성하고 사용할 수 있지만, 저장할 수 있는 객체 수가 배열을 생성할 때 결정되기 때문에 불특정 다수의 객체를 저장하기에는 문제가 있다. 그리고 객체를 삭제했을 때 해당 인덱스가 비게 되어 낱알이 듬성듬성 빠진 옥수수가 될 수 있다. 그렇기 때문에 새로운 객체를 저장하려면 어디가 비어 있는지 확인하는 코드도 필요하다.

![image](https://user-images.githubusercontent.com/84966961/124217803-f7ac2080-db33-11eb-833a-164f872078e9.png)


자바의 이러한 문제점을 해결하고, 널리 알려져 있는 자료구조 (Data Structure)를 바탕으로 객체들을 효율적으로 추가, 삭제, 검색할 수 있도록 java.util 패키지에 컬렉션과 관련된 인터페이스와 클래스들을 포함시켜 놓았다. 이들을 총칭해서 컬렉션 프레임워크(Collection_객체를 수집해서 저장하는 것 FrameWork_사용방법을 미리 정해 놓은 라이브러리)라고 한다. 
   
| 인터페이스 | 특징 | 구현클래스 |
|---|---|---|
| List | 순서가 있는 데이터의 집합. 데이터의 중복을 허용한다. 예) 대기자 명단 | ArrayList, LinkedList, Stack, Vector 등 |
| Set | 순서를 유지하지 않는 데이터의 집합. 데이터의 중복을 허용하지 않는다. 예) 양의 정수집합, 소수의 집합 | HashSet, TreeSet 등 |
| Map | 키(Key)와 값(Value)의 쌍(Pair)으로 이루어진 데이터의 집합 순서는 유지되지 않으며, 키는 중복을 허용하지 않고, 값은 중복을 허용한다. 예) 우편번호, 지역번호(전화번호) | HashMap, TreeMap, Hashtable, Properties 등 |

<br/><br/>
---
 
 ## 교재 724p : 15.2 List 컬렉션   
    
 List 컬렉션은 객체를 일렬로 늘어놓은 구조를 가지고 있다. 객체를 인덱스로 관리하기 때문에 객체를 저장하면 자동 인덱스가 부여되고 인덱스로 객체를 검색, 삭제할 수 있는 기능을 제공한다. List 컬렉션은 객체 자체를 저장하는 것이 아니라 다음 그림과 같이 객체의 번지를 참조한다. **동일한 객체를 중복 저장**할 수 있는데, 이 경우 동일한 번지가 참조된다. null도 저장이 ㄷ가능한데, 이 경우 해당 인덱스는 객체를 참조하지 않는다.
    
![image](https://user-images.githubusercontent.com/84966961/124404246-1816ed80-dd75-11eb-9bfb-31b0061a4615.png)
    
    
 List 컬렉션에는 ArrayList, Vector, LinkedList 등이 있는데, 다음은 List 컬렉션에서 공통적으로 사용 가능한 List 인터페이스의 메소드들이다.   

![image](https://user-images.githubusercontent.com/84966961/124403838-3e3b8e00-dd73-11eb-8328-661505ebd701.png)

```java
List<String> list = ...;  // 객체생성
list.add("홍길동");        // String 객체 저장 
list.add(1, "신용권");     // 지정한 인덱스에 객체 저장 
boolean isFindValue = list.contains("Hello World"); // 동일한 String 객체를 갖고 있는지 검색 
list.remove(0);                 // 인덱스 값을 이용하여 객체 삭제 
for(String value : list)        // List에 저장된 모든 객체를 얻어서 콘솔 창에 출력     
{ System.out.println(value); }
```



<br/><br/>
---
 
 ### 교재 726p : 15.2.1 ArrayList   
    
 일반 배열과 ArrayList는 인덱스로 객체를 관리한다는 점에서 유사하지만, 큰차이점을 가지고 있다. 배열은 생성할 때 크기가 고정되고 사용 중에 크기를 변경할 수 없지만, ArrayList는 **저장 용량(capacity)을 초과한 객체들이 들어오면 자동적으로 저장 용량(capacity)이 늘어난다**는 것이다.    
   
 -> ArrayList는 저장 용량이 늘어나는 데 내부는 배열 구조로 되어 있으며 내부적으로 배열이 다 찼을 때 더 큰 새로운 배열을 만들어 정보들을 옴기는 방식으로 처리하게 된다.    
   
![image](https://user-images.githubusercontent.com/84966961/124404182-c706f980-dd74-11eb-884d-6788846d70ed.png)










 일반적으로 컬렉션에는 단일 종류의 객체들만 저장되므로 자바 5부터 제네릭을 도입하여 ArrayList 객체를 생성할 때 타입 파라미터로 저장할 객체의 타입을 지정함으로써 불필요한 타입 변환을 하지 않도록 했다.

```
 // 자바 4이전
 
 List list = new ArrayList();                   // 컬렉션 생성
 list.add("깃허브");                             // 컬렉션에 객체를 추가
 Object obj = list.get(0);                      // 컬렉션에서 객체 검색 
 String blog = (String) obj;                    // 타입 변환 후 홍길동을 얻을 수 있음
 
 // 자바5 이후
 List<String> list = new ArrayList<String>();   // 컬렉션 생성
 list.add("깃허브");                             // 컬렉션에 객체를 추가
 String blog = list.get(0);                     // 컬렉션에서 객체 검색, 홍길동을 바로 얻음
```
 
 ArrayList에 객체를 추가하면 인덱스 0부터 차례때로 저장된다. ArrayList에서 특정 인덱스의 객체를 제거하면 바로 뒤 인덱스부터 마지막 인덱스까지 모두 앞으로 1씩 당겨진다. 마찬가지로 특정 이넥스에 객체를 삽입하면 해당 인덱스부터 마지막 인덱스까지 모두 1씩 밀려난다.   
   

![image](https://user-images.githubusercontent.com/84966961/124404190-cd957100-dd74-11eb-9ebd-c56430182d72.png)
   
<br/><br/>
---
   
#### 예제

```java
import java.util.*;

public class ArrayListExample {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();    // 리스트 생성
		
		list.add("Java");                               // String 객체 저장
		list.add("JDBC");
		list.add("Servlet/JSP");
		list.add(2, "Database");
		list.add("iBATIS");

		int size = list.size();                         // 저장한 총 객체 갯수 : 5
		System.out.println("총 객체수: " + size);		
		System.out.println();
		
		String skill = list.get(2);                     // 2번 데이터베이스 호출
		System.out.println("2: " + skill);
		System.out.println();

		for(int i=0; i<list.size(); i++) {
			String str = list.get(i);
			System.out.println(i + ":" + str);
		}
		System.out.println();
				
		list.remove(2);
		list.remove(2);
		list.remove("iBATIS");		
		
		for(int i=0; i<list.size(); i++) {
			String str = list.get(i);
			System.out.println(i + ":" + str);
		}
	}
}
```



<br/><br/>
---

#### 기본형 타입의 배열

 기본형 타입은 제네릭 타입에 들어갈 수 없으므로 배열 생성시 Wrapper 타입을 사용하면 된다.   

```java
public class Test {

	public static void main(String[] args) {

		List<String> list = new ArrayList();
		
		list.add("1");
		list.add("3");
		list.add("5");
		list.add("2");
		list.add("4");
		list.add("4");
		
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		
		List<Integer> list2 = new ArrayList();

		list2.add(new Integer(10));     // 굳이 이렇게 캐스팅 해줄 필요 없음. 오토박싱 되기 때문
		list2.add(20);
		list2.add(30);
		list2.add(40);
		list2.add(50);
		
		
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(list2.get(i));
		}
	}

}
```




<br/><br/>
---

#### Quiz

 위 예제의 합계 계산

```
	public static void main(String[] args) {

		List<String> list = new ArrayList();
		
		list.add("1");
		list.add("3");
		list.add("5");
		list.add("2");
		list.add("4");
		list.add("4");
		
		int sum = 0; 
		for (int i = 0; i < list.size(); i++) {
			sum += Integer.parseInt(list.get(i));
		}
		System.out.println(sum);
		
		List<Integer> list2 = new ArrayList();

		list2.add(new Integer(10));     // 굳이 이렇게 캐스팅 해줄 필요 없음. 오토박싱 되기 때문
		list2.add(20);
		list2.add(30);
		list2.add(40);
		list2.add(50);
		
		
		int sum2 = 0; 
		for (int i = 0; i < list2.size(); i++) {
			sum2 += list2.get(i);
		}
		System.out.println(sum2);
	}
}
```

**결과 화면**   
   
![image](https://user-images.githubusercontent.com/84966961/124406109-5ca58780-dd7b-11eb-9759-e2c314687938.png)

 


<br/><br/>
---

#### 크기를 고정하는 ArrayList

 배열의 크기를 정하여 사용하고 싶다면 `Arrays.asList`를 사용하여 객체를 생성하면 된다.   


```java
public class ArraysAsListExample {
	public static void main(String[] args) {
		List<String> list1 = Arrays.asList("홍길동", "신용권", "감자바");
		for(String name: list1) {
			System.out.println(name);
		}
		
		List<Integer> list2 = Arrays.asList(1, 2, 3);
		for(int value : list2) {
			System.out.println(value);
		}
	}
}
```

<br/><br/>
---

### 교재 729p : 15.2.2 Vector

 Vector는 ArrayList와 동일한 내부 구조를 가지고 있다. 하지만 Vector에는 다른 리스트에는 없는 동기화 기능이 있기 때문에 조금은 무거울 수 있다. 그러므로 동기화가 필요하지 않은 배열에는 사용할 필요가 없다.   
   
![image](https://user-images.githubusercontent.com/84966961/124408617-8a8dca80-dd81-11eb-80e3-e773f5856109.png)


```java
public class VectorExample {
	public static void main(String[] args) {
		List<Board> list = new Vector<Board>();
	
		list.add(new Board("제목1", "내용1", "글쓴이1"));
		list.add(new Board("제목2", "내용2", "글쓴이2"));
		list.add(new Board("제목3", "내용3", "글쓴이3"));
		list.add(new Board("제목4", "내용4", "글쓴이4"));
		list.add(new Board("제목5", "내용5", "글쓴이5"));
		
		list.remove(2);
		list.remove(3);
		
		for(int i=0; i<list.size(); i++) {
			Board board = list.get(i);
			System.out.println(board.subject + "\t" + board.content + "\t" + board.writer);
		}
	}
}
```

결과적으로는 3, 5를 제거했으므로 1, 2, 4만 남아있다.



<br/><br/>
---

### 교재 731p : 15.2.3 LinkedList

 LinkedList는 List 구현 클래스이므로 ArrayList와 사용 방법은 똑같지만 내부 구조는 완전 다르다. ArrayList는 내부 배열에 객체를 저장해서 인덱스로 관리하지만, LinkedList는 인접 참조를 링크해서 체인처럼 관리한다.

**힙 영역 내부**
![image](https://user-images.githubusercontent.com/84966961/124408638-97122300-dd81-11eb-8ee0-a3fc29ed75be.png)   
   
 LinkedList에서 특정 인덱스의 객체를 제거하면 앞뒤 링크만 변경되고 나머지 링크는 변경되지 않는다. 특정 인덱스에 객체를 삽입할 때에도 마찬가지다. ArrayList는 중간 인덱스의 객체를 제거하면 뒤의 객체는 인덱스가 1씩 앞으로 당겨진다고 했다. 그렇기 때문에 빈번한 객체 삭제와 삽입이 일어나는 곳에서는 ArrayList보다 LinkedList가 좋은 성능을 발휘한다. 다음 이미지는 중간에 객체를 제거할 경우 앞뒤 링크의 수정이 일어나는 모습을 보여주고 있다.   

![image](https://user-images.githubusercontent.com/84966961/124408804-fc661400-dd81-11eb-996b-a91def32bf50.png)   
   














끝에서부터(순차적으로) 추가/삭제하는 경우는 ArrayList가 빠르지만, 중간에 추가 또는 삭제할 경우는 앞뒤 링크 정보만 변경하면 되는 LinkedList가 더 빠르다. ArrayList는 뒤쪽 인덱스들을 모두 1씩 증가 또는 감소시키는 시간이 필요하므로 처리 속도가 빠르다. LinkedList의 배열에서 빠져나온 객체들(링크가 풀린 객체들)은 가비지에 들어가 처분된다.


 구분 | 순차적으로 추가/삭제 | 중간에 추가/삭제 | 검색
----|-------------|-----------|---
ArrayList | 빠르다 | 느리다 | 빠르다
LinkedList | 느리다 | 빠르다 | 느리다

    
-> 보통의 경우에는 속도 차이가 크지 않으므로 그냥 `ArrayList`를 사용해도 될 것이다.



<br/><br/>
---

## 교재 733p : 15.3 Set 컬렉션

 List 컬렉션은 저장 순서를 유지하지만, **Set 컬렉션은 저장 순서가 유지되지 않는다.** 또한 **객체를 중복해서 저장할 수 없고, 하나의 null만 저장할 수 있다.** Set 컬렉션은 수학의 집합에 비유될 수 있다. 집합은 순서와 상관없고 중복이 허용되지 않기 때문이다.


Set 컬렉션에는 HashSet,LinkedHashSet,TreeSet 등이 있는데 다음은 Set 컬렉션에서 공통적으로 사용 가능한 Set 인터페이스의 메소드들이다. 인덱스로 관리하지 않기 때문에 인덱스를 매개값으로 갖는 메소드가 없다.   

**Set 컬렉션 메소드**    

![image](https://user-images.githubusercontent.com/84966961/124409171-c4130580-dd82-11eb-8979-bf3fddab25aa.png)

<br><br>

#### Iterator 인터페이스

 List 컬렉션과는 달리 Set 컬렉션에는 인덱스가 없기 때문에 get() 메소드가 없다. 따라서 Set 컬렉션에서 값을 삭제하고 싶다면 인덱스가 아닌 값을 넣어 삭제하고 저장된 객체를 가져오고 싶다면 Iterator를 사용하여 반복자를 받아오면 된다.   


<br><br>

**Iterator 메소드**

리턴타입 | 메소드 | 설명
---|---|---
boolean | hasNext() | 해당 이터레이션(iteration)이 다음 요소를 가지고 있으면 true를 반환하고, 더 이상 다음 요소를 가지고 있지 않으면 false를 반환함.
E | next() | 이터레이션(iteration)의 다음 요소를 반환함.
default | void remove() | 해당 반복자로 반환되는 마지막 요소를 현재 컬렉션에서 제거함. (선택적 기능)




 Iterator를 사용하여 String 객체들을 반복해서 하나씩 가져오는 코드이다.

```
Set<String> set = ...;
Iterator<String> iterator = set.iterator();

// 저장 객체의 size만큼 반복함.
while(iterator.hasNext()) {                 // hasNext로 값이 있는지 확인함.(null인지를 확인하고 null일시 false를 리턴해서 반복문에서 빠져나감.)
    String atr = iterator.next();	        // String 객체를 하나 가져옴
}
```

 Iterator를 사용하지 않더라도 향상된 for문을 이용해서 전체 객체를 대상으로 반복할 수 있다.   

```java
Set<String> set = ...;
for(String str : set) {

}
```

 Set 컬렉션에서 Iterator의 next() 메소드로 가져온 객체를 제거하고 싶다면 remove() 메소드를 호출하면 된다. Iterator의 메소드이지만, 실제 Set 컬렉션에서 객체가 제거됨을 알아야 한다. 다음은 Set 컬렉션에서 "홍길동"을 제거한다.   
 
```java
while(iterator.hasNext()) {
    String str = iterator.next();;
    it(str.equals("홍길동") {
        iterator.remove();
    }
}
```

<br/><br/>
---

#### 예제

 Set은 중복불가 및 랜덤적인 객체 저장을 한다.

```java
public class SetTest {

	public static void main(String[] args) {

		Set<String> set = new HashSet();
		
		set.add("3");
		for (int i = 1; i < 3; i++) {       // 3이 중복
			set.add(String.valueOf(i));
		}
		set.add("7");                       // 7을 먼저 넣어지만 7이 이후에 나옴.
		set.add("4");
		
		Iterator<String> iterator = set.iterator();

		while (iterator.hasNext()) {
			String s = iterator.next();
			System.out.println(s);
		}
	}
}
```
   
**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/124411010-9760ed00-dd86-11eb-92cc-af87e90e4117.png)

나중에 저장된 4가 7보다 먼저 나옴.

#### iterator의 특징

 한번 사용한 iterator로 다시 사용해도 결과가 나오지 않는다. 배열에서 next로 넘어가기만 하기 때문에 초기화를 위해 iterator를 사용할 때마다 다시 재정의하여 초기화를 시켜줘야한다.

```java
public class SetTest {

	public static void main(String[] args) {

		Set<String> set = new HashSet();
		
		set.add("3");
		for (int i = 1; i < 3; i++) {
			set.add(String.valueOf(i));
		}
		set.add("7");
		set.add("4");
		
		Iterator<String> iterator = set.iterator();

		while (iterator.hasNext()) {
			String s = iterator.next();
			System.out.println(s);
		}
		
		System.out.println("--------------");
		
		while (iterator.hasNext()) {
			String s = iterator.next();
			System.out.println(s);
		}
	}
}
```

![image](https://user-images.githubusercontent.com/84966961/124412617-e2c8ca80-dd89-11eb-9658-4902f43fe3b8.png)   
   
**iterator 초기화**   

```java
...
		Iterator<String> iterator = set.iterator();

		while (iterator.hasNext()) {
			String s = iterator.next();
			System.out.println(s);
		}
		
		System.out.println("--------------");
		
		iterator = set.iterator();
		
		while (iterator.hasNext()) {
			String s = iterator.next();
			System.out.println(s);
		}
	...
```

<br/><br/>
---

### quiz : 1 ~ 10 까지의 Set 컬렉션을 홀수만 남겨 출력해라.

```java
public class SetQuiz2 {
	public static void main(String[] args) {

		Set<String> set = new HashSet();
		
		set.add("1");
		set.add("2");
		set.add("3");
		set.add("4");
		set.add("5");
		set.add("6");
		set.add("7");
		set.add("8");
		set.add("9");
		set.add("10");
    }
}
```

**답안**
```java
public class SetQuiz2 {

	public static void main(String[] args) {

		Set<String> set = new HashSet();
		
		set.add("1");
		set.add("2");
		set.add("3");
		set.add("4");
		set.add("5");
		set.add("6");
		set.add("7");
		set.add("8");
		set.add("9");
		set.add("10");
		
		Iterator<String> iterator = set.iterator();

		while (iterator.hasNext()) {
			String s = iterator.next();
			if (Integer.parseInt(s)%2==0) {
				iterator.remove();
			}
		}
		
		System.out.println("----결과 화면----");
		
		iterator = set.iterator();
		
		while (iterator.hasNext()) {
			String s = iterator.next();
			System.out.println(s);
		}
	}
}
```
   
**결과 화면**   
   
![image](https://user-images.githubusercontent.com/84966961/124413239-2bcd4e80-dd8b-11eb-89e5-2d8def8f499f.png)   
    
<br/><br/>
---

#### 예제 : 로또 번호 뽑기

 제공 코드를 Set을 이용하여 6개의 숫자가 나오도록 해보자.   
    
**제공 코드**
```java
public class Lotto {

	public static void main(String[] args) {

		for (int i = 0; i < 6; i++) {
			int lotto = (int)(Math.random() * 45)+1;
			System.out.println(lotto);
		}
	}
}
```

**나의 답안**
```java
public class Lotto {

	public static void main(String[] args) {

		Set<Integer> lottoNums = new HashSet<>(6);
		
		Iterator<Integer> it = lottoNums.iterator();
		
		while (true) {
			int lotto = (int)(Math.random() * 45)+1;
			lottoNums.add(lotto);
			System.out.println(lotto);
			if (lottoNums.size() == 6) {
				break;
			}
		}
		System.out.println(lottoNums);
	}

}
```

**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/124414851-7603ff00-dd8e-11eb-893a-97109d22bbe0.png)


<br/><br/>
---

### 교재 736p : 15.3.1 HashSet

 HashSet은 Set 인터페이스의 구현 클래스이다.   
    
 HashSet은 객체들을 순서 없이 저장하고 동일한 객체는 중복 저장하지 않는다. HashSet이 판단하는 동일한 객체란 꼭 같은 인스턴스를 뜻하지 않는다. HashSet은 객체를 저장하기 전에 먼저 객체의 hashCode() 메소드를 호출해서 해시코드를 얻어낸다. 그리고 이미 저장되어 있는 객체들의 해시코드와 비교한다. 만약 동일한 해시코드가 있다면 다시 equals() 메소드로 두 객체를 비교해서 true가 나오면 동일한 객체로 판단하고 중복 저장을 하지 않는다.

**HashSet 흐름도**

![image](https://user-images.githubusercontent.com/84966961/124412399-633afb80-dd89-11eb-9213-1e35b73caa9e.png)   
   
 문자열을 HashSet에 저장할 경우, 같은 문자열을 갖는 String 객체는 동등한 객체로 간주되고 다른 문자열을 갖는 String 객체는 다른 객체로 간주되는데, 그 이유는 String 클래스가 hashCode()와 equals() 메소드를 재정의해서 같은 문자열일 경우 hashCode()의 리턴값을 같게, equals()의 리턴값은 true가 나오도록 했기 때문이다.   


















