# Lecture21 : 컬렉션 프레임워크(Collection Framework)
Key Word : 컬렉션 프레임워크, Set, HashSet, Map, HashMap, HashTable, Properties, Tree, TreeSet, TreeMap, Comparable, Comparator, FIFO, LIFO

<hr/>

 ### 교재 736p : 15.3.1 HashSet   
     
 HashSet은 Set 인터페이스의 구현 클래스이다.   
    
 HashSet은 객체들을 순서 없이 저장하고 동일한 객체는 중복 저장하지 않는다. HashSet이 판단하는 동일한 객체란 꼭 같은 인스턴스를 뜻하지 않는다. HashSet은 객체를 저장하기 전에 먼저 객체의 hashCode() 메소드를 호출해서 해시코드를 얻어낸다. 그리고 이미 저장되어 있는 객체들의 해시코드와 비교한다. 만약 동일한 해시코드가 있다면 다시 equals() 메소드로 두 객체를 비교해서 true가 나오면 동일한 객체로 판단하고 중복 저장을 하지 않는다.

**HashSet 흐름도**

![image](https://user-images.githubusercontent.com/84966961/124692906-6a910f00-df19-11eb-98cf-428c65757e1b.png)   
   
 문자열을 HashSet에 저장할 경우, 같은 문자열을 갖는 String 객체는 동등한 객체로 간주되고 다른 문자열을 갖는 String 객체는 다른 객체로 간주되는데, 그 이유는 String 클래스가 hashCode()와 equals() 메소드를 재정의해서 같은 문자열일 경우 hashCode()의 리턴값을 같게, equals()의 리턴값은 true가 나오도록 했기 때문이다.   

#### 같은 객체 구분을 위한 hashCode()와 equals() 오버라이드


**MemberTest.java**
```java
public class MemberTest {

	public static void main(String[] args) {

		Set<Member> set = new HashSet<>();
		
		Member m1 = new Member("홍길동", 10);
		Member m2 = new Member("홍길동", 10);
		
		set.add(m1);
		set.add(m2);
		
		Iterator<Member> it = set.iterator();
		
		while (it.hasNext()) {
			Member m = it.next();
			System.out.println(m.name);
			System.out.println(m.age);
		}
	}

}
```

**Member.java**
```java
public class Member {

	public String name;
	public int age;
	
	public Member(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public int hashCode() {
		// 만약 같은 이름과 나이라면 해쉬코드가 같을 거고 그러면 HashSet 흐름도에서 equals에게 보내서 같은지 확인한다.
		return (name+age).hashCode(); 
	}

	@Override
	public boolean equals(Object obj) {
		Member member = (Member)obj;
		
		boolean result = (this.name == member.name) && (this.age == member.age);
		
		return result;
	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + age;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Member other = (Member) obj;
//		if (age != other.age)
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}
}
```


<br><br>
<hr/>

 ## 교재 736p : 15.4 Map 컬렉션   
     
 Map 컬렉션은 키(Key)와 값(Value)으로 구성된 Entry 객체를 저장하는 구조를 가지고 있다. 여기서 키와 값은 모두 객체이다. 키는 중복 저장될 수 없지만 값은 중복 저장될 수 있다. 만약 기존에 저장된 키와 동일한 키로 값을 저장하면 기존의 값은 없어지고 새로운 값으로 대치된다.    
   
![image](https://user-images.githubusercontent.com/84966961/124526963-3cd79780-de3f-11eb-8d95-73bda85e4f94.png)   
    
 Map 컬렉션에는 HashMap, Hashtable, LinkedHashMap, Properties, TreeMap 등이 있다. 다음은 Map 컬렉션에서 공통적으로 사용 가능한 Map 인터페이스들의 메소드들이다.   

![image](https://user-images.githubusercontent.com/84966961/124526959-334e2f80-de3f-11eb-9932-045f988dd410.png)
   
 객체를 추가 할 때는 `put(K key,V value)`을 이용하여 추가한다. 
   
 키를 리턴할 때는 Set을 값을 리턴할 때는 get을 사용하여 객체 검색을 한다.   
    
 삭제할 때는 `remove(Object Key)`를 사용한다.

```
Map<String, Integer> map = ~;
map.put("move",25);
int score = map.get("move");
map.remove("move");
```

**키 값만 먼저 알아오는 방법**

 키를 알고 있다면 get() 메소드로 간단하게 객체를 찾아오면 되지만, 저장된 전체 객체를 대상으로 하나씩 얻고 싶을 경우에는 두 가지 방법을 사용할 수 있다. 첫 번째는 keySet() 메소드로 모든 키를 Set 컬렉션으로 얻은 다음, 반복자를 통해 키를 하나씩 얻고 get() 메소드를 통해 값을 얻으면 된다.   
    
 Key 값을 Set한 타입의 형태로 받아 올 수 있다.   
 
```java 
Map<K, V> map = ~;
Set<K> keySet = map.keySet();					// Set 형태로 Iterator를 사용하기 위하여
Iterator<K> keyIterator = keySet.iterator();
while(keyIterator.hasNext()){
      K key = keyIterator.next();
      V val = map.get(key);
 }
```

**키 밸류 값 모두 알아오는 방법**

 두 번째 방법은 entrySet() 메소드로 모든 Map.Entry를 Set 컬렉션으로 얻은 다음, 반복자를 통해 Map.Entry를 하나씩 얻고 getKey()와 getValue() 메소드를 이용해 키와 값을 얻으면 된다.

```java
Set<Map.Entry<K, V>> entrySet = map.entrySet();
Iterator<Map.entry<K, V>> entryIterator = enterySet.iterator();
while(entryIterator.hasNext(0) {
      Map.Entry<K, V> entry = entryIterator.next();
      K key = entry.getKey();
      V value = entry.getValue();
}
```



<br><br>
<hr/>

 ### 교재 736p : 15.4.1 HashMap   
     
 HashMap은 Map 인터페이스를 구현한 대표적인 Map 컬렉션이다. HashMap의 키로 사용할 객체는 hashCode()와 equals() 메소드를 재정의해서 동등 객체가 될 조건을 정해야 한다. 동등 객체, 즉 동일한 키가 될 조건은 hashCode()의 리턴값이 같아야 하고, equals() 메소드가 true를 리턴해야 한다.   

![image](https://user-images.githubusercontent.com/84966961/124692871-551be500-df19-11eb-8068-d37910e888df.png)

 주로 키 타입은 String 을 많이 사용하는데, String은 문자열이 같을 경우 동등 객체가 될 수 있도록 hashCode()와 equals() 메소드가 재정의되어 있다. HashMap을 생성하기 위해서는 키 타입과 값 타입을 파라미터로 주고 기본 생성자를 호출하면 된다.   
 
#### 예제

**MapTest.java**
```java
package map_collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
		// 맵 컬렉션 생성
		Map<String, Integer> map = new HashMap<>();

		// 객체 저장
		map.put("신용권", 85);
		map.put("홍길동", 90);
		map.put("동장군", 80);
		map.put("홍길동", 95);
		System.out.println("총 Entry 수 : " + map.size());

		// 객체 찾기
		System.out.println("\t홍길동 : " + map.get("홍길동"));
		System.out.println();

		// 객체를 하나씩 처리
		Set<String> keySet = map.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			Integer value = map.get(key);
			System.out.println("\t" + key + " : " + value);
		}
		System.out.println();

		// 객체 삭제
		map.remove("홍길동");
		System.out.println("총 Entry 수 : " + map.size());

		// 객체를 하나씩 처리
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
		Iterator<Map.Entry<String, Integer>> entryIterator = entrySet.iterator();

		while (entryIterator.hasNext()) {
			Map.Entry<String, Integer> entry = entryIterator.next();
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println("\t" + key + " : " + value);
		}
		System.out.println();

		// 객체 전체 삭제
		map.clear();
		System.out.println("총 Entry 수 : " + map.size());
	}
}
```

 Map 컬렉션 역시 동등 객체인지 확인하기 위하여 hashCode()와 equals() 메소드를 재정의하여 사용해야 한다.

**Student.java**
```java
public class Student {
	public int sno;
	public String name;
	
	public Student(int sno, String name) {
		this.sno = sno;
		this.name = name;
	}

	public boolean equals(Object obj) {
		if(obj instanceof Student) {
			Student student = (Student) obj;
			return (sno==student.sno) && (name.equals(student.name)) ;
		} else {
			return false;
		}
	}

	public int hashCode() {
		return sno + name.hashCode();
	}
}
```

**HashMapTest2**
```java
public class HashMapTest2 {
	public static void main(String[] args) {
		Map<Student, Integer> map = new HashMap<Student, Integer>();
		
		map.put(new Student(1, "홍길동"), 95);
		map.put(new Student(1, "홍길동"), 95);
		
		System.out.println("총 Entry 수: " + map.size());
	}
}
```

**결과 화면**    
![image](https://user-images.githubusercontent.com/84966961/124530820-2f72db00-de48-11eb-9162-8736371f9268.png)
    
 
<br><br>
<hr/>

 ### 교재 746p : 15.4.2 HashTable 

생략



<br><br>
<hr/>

 ### 교재 748p : 15.4.3 Properties   
     
 Properties는 Hashtable의 하위 클래스이기 때문에 Hashtable의 모든 특징을 그대로 가지고 있다. 차이점은 Hastable은 키와 값을 다양한 타입으로 지정이 가능한데 비해 Properties는 키와 값을 String 타입으로 제한한 컬렉션이다. Properties는 애플리케이션의 옵션 정보, 데이터베이스 연결 정보 그리고 국제화(다국어) 정보가 저장된 프로퍼티(~.properties) 파일을 읽을 때 주로 사용한다.   

 프로퍼티 파일은 키와 값이 = 기호로 연결되어 있는 테스트 파일로 ISO 8859-1 문자셋으로 저장된다. 이 문자셋으로 직접 표현할 수 없는 한글은 유니코드(Unicode)로 변환되어 저장된다. 예를 들어 다음과 같이 country와 language 키로 각각 "대한민국", "한글"을 입력하면 자동으로 유니코드로 변환되어 저장된다. 이클립스에서 유니코드로 변환된 내용을 다시 한글로 보려면 마우스를 유니코드 위에 올려놓으면 된다.

**PropertiesTest.java**
```java
public class PropertiesTest {

	public static void main(String[] args) {

		Properties prop = new Properties();
		
		prop.setProperty("timeout", "30");
		prop.setProperty("language", "kr");
		prop.setProperty("size", "10");
		prop.setProperty("capacity", "10");
		
		
		prop.propertyNames(); // Enumeration 타입으로 반환 해준다.
		
		Enumeration e = prop.propertyNames();
		
		while (e.hasMoreElements()) {
			String key = (String)e.nextElement();
			System.out.println(key + " : "+ prop.getProperty(key));
		}

		Properties prop2 = new Properties();

		try {
//			prop2.load(new PropertiesTest().getClass().getResourceAsStream("src/properties/input.txt"));
			prop2.load(new FileInputStream("src/properties/input"));
//				prop.load(new ReadProperties().getClass().getResourceAsStream(filePath))
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(prop2.getProperty("name"));
		System.out.println(prop2.getProperty("data"));

		String[] data = prop2.getProperty("data").split(",");

		// 최대값
		// 최소값
		// 평균
		// 총합
		
		int max = 0;
		int min = Integer.parseInt(data[0]);
		double avg = 0;
		int total = 0;
		
		for (String i : data) {
			if (max < Integer.parseInt(i)) {
				max = Integer.parseInt(i);
			} 
			if (min > Integer.parseInt(i)) {
				min = Integer.parseInt(i);
			}
			total += Integer.parseInt(i);
			avg = total/data.length;
		}
		
		System.out.println(max);
		System.out.println(min);
		System.out.println(total);
		System.out.println(avg);
		
	}

}
```

 properties 를 이용해 data를 받아 max, min, 평균, 총합을 구하는 코드이다.

<br><br>
<hr/>

 ## 교재 750p : 15.5 검색 기능을 강화시킨 컬렉션
 
 컬렉션 프레임 워크는 검색 기능을 강화시킨 TreeSet과 TreeMap을 제공하고 있다. 이름에서 알 수 있듯이 TreeSet은 Set 컬렉션이고, TreeMap은 Map 컬렉션이다. 이 컬렉션들은 이진 트리(binary tree)를 이용해서 계층적 구조(Tree 구조)를 가지면서 객체를 저장한다.   
    
 TreeSet은 값만 가지고 있으며 TreeMap은 트리 구조에 Key-Value 구조를 더한 컬렉션이다.   
 
 
<br><br>
<hr/>

 ### 교재 750p : 15.5.1 이진 트리 구조
 
 이진 트리(binary tree)는 여러 개의 노드(node)가 트리 형태로 연결된 구조로, 루트 노드(root node)라고 불리는 하나의 노드에서부터 시작해서 각 노드에 최대 2개의 노드를 연결 할 수 있는 구조를 가지고 있다. 위아래로 연결된 두 노드를 부모-자식관계에 있다고 하며 위의 노드를 부모 노드, 아래의 노드를 자식 노드라고 한다. 하나의 부모 노드는 최대 두개의 자식노드와 연결될 수 있다. 그림에서 보면 A 노드는 B, C 노드의 부모 노드이고 B, C 노드는 A 노드의 자식 노드이다.
 
 
 ![image](https://user-images.githubusercontent.com/84966961/124546447-9d79cb00-de65-11eb-8807-fe2eff9723e1.png)

 ![image](https://user-images.githubusercontent.com/84966961/124546190-3bb96100-de65-11eb-8541-43c0ead41b61.png)

 
 
<br><br>
<hr/>

 ### 교재 752p : 15.5.2 TreeSet   
     
 TreeSet은 이진 트리(binary tree)를 기반으로한 Set 컬렉션이다. 하나의 노드는 노드값인 value와 왼족과 오른쪽 자식 노드를 참조하기 위한 두 개의 변수로 구성된다. TreeSet에 객체를 저장하면 자동으로 정렬되는데 부모값과 비교해서 낮은 것은 왼족 자식 노드에, 높은 것은 오른쪽 자식 노드에 저장한다.

![image](https://user-images.githubusercontent.com/84966961/124546500-af5b6e00-de65-11eb-9028-25734af9fef6.png)

<br><br>

#### TreeSet의 메소드들

리턴 타입 | 메소드 | 설명
------|-----|---
E | first() | 제일 낮은 객체를 리턴
E | last() | 제일 높은 객체를 리턴
E | lower(E e) | 주어진 객체보다 바로 아래 객체를 리턴
E | higer(E e) | 주어진 객체보다 바로 위 객체를 리턴
E | floor(E e) | 주어진 객체와 동등한 객체가 있으면 리턴, 만약 없다면 주어진 객체의 바로 아래의 객체를 리턴
E | ceiling(E e) | 주어진 객체와 동등한 객체가 있으면 리턴, 만약 없다면 주어진 객체의 바로 위의 객체를 리턴
E | pollFirst() | 제일 낮은 객체를 꺼내오고 컬렉션에서 제거함
E | pollLast() | 제일 높은 객체를 꺼내오고 컬렉션에서 제거함

<br><br>

#### 예제
 
**TreeSetExample.java**
 ```java
public class TreeSetExample1 {
	public static void main(String[] args) {
		TreeSet<Integer> scores = new TreeSet<Integer>();
		scores.add(new Integer(87));
		scores.add(new Integer(98));
		scores.add(new Integer(75));
		scores.add(new Integer(95));
		scores.add(new Integer(80));
		
		Integer score = null;
		
		score = scores.first();
		System.out.println("가장 낮은 점수: " + score);
		
		score = scores.last();
		System.out.println("가장 높은 점수: " + score + "\n");
		
		score = scores.lower(new Integer(95));
		System.out.println("95점 아래 점수: " + score);
		
		score = scores.higher(new Integer(95));
		System.out.println("95점 위의 점수: " + score + "\n");		
		
		score = scores.floor(new Integer(95));
		System.out.println("95점 이거나 바로 아래 점수: " + score);
		
		score = scores.ceiling(new Integer(85));
		System.out.println("85점 이거나 바로 위의 점수: " + score + "\n");
		
		while(!scores.isEmpty()) {
			score = scores.pollFirst();
			System.out.println(score + "(남은 객체 수: " + scores.size() + ")");
		}
	}
}
```
   
 **결과 화면**    
 ![image](https://user-images.githubusercontent.com/84966961/124546916-4c1e0b80-de66-11eb-86c3-a49471c6d840.png)
     
 
<br><br>
    
 #### TreeSet 객체 정렬 : descendingIterator(), descendingSet()
 
 TreeSet은 다음과 같은 정렬과 관련된 메서드를 제공한다.    


| 리턴 타입 | 메소드 | 설명 |
|---|---|---|
| `Iterator<E>` | descendingIterator() | 내림차순으로 정렬된 Iterator를 리턴 |
| `NavigableSet<E>` | descendingSet() | 내림차순으로 정렬된 NavigableSet을 반환 |

	
 NavigableSet은 TreeSet 처럼 first(), last(), lower(), higher(), floor(), ceiling() 메서드를 제공한다. 오름차순으로 정렬하고 싶다면 다음과 같이 descendingSet() 메소드를 두 번 호출 하면 된다.    


```java
NavigableSet<E> descendingSet = treeSet.descendingSet();
NavigableSet<E> descendingSet = descendingSet.descendingSet();
```

**TreeSetExample2.java**
 ```java
public class TreeSetExample2 {
	public static void main(String[] args) {
		TreeSet<Integer> scores = new TreeSet<Integer>();
		scores.add(new Integer(87));
		scores.add(new Integer(98));
		scores.add(new Integer(75));
		scores.add(new Integer(95));
		scores.add(new Integer(80));
		
		NavigableSet<Integer> descendingSet = scores.descendingSet();
		for(Integer score : descendingSet) {
			System.out.print(score + " ");
		}
		//  내림차순 : 98 95 87 80 75
		System.out.println();
		
		NavigableSet<Integer> ascendingSet = descendingSet.descendingSet();
		
		for(Integer score : ascendingSet) {
			System.out.print(score + " ");
		}
		//  오름차순 : 75 80 87 95 98
	}
}
```

**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/124550215-4d9e0280-de6b-11eb-954e-50053e1a7964.png)
  
 
<br><br>
    
 #### TreeSet 객체 정렬 : headSet, tailSet, subSet
 
다음은 TreeSet이 가지고 있는 범위 검색과 관련된 메소드들이다.

| 리턴 타입 | 메소드 | 설명 |
|---|---|---|
| `NavigableSet<E>` | headSet(<br>  E toElement, <br>  boolean inclusive<br>) | 주어진 객체보다 낮은 객체들을 NaviableSet으로 리턴.<br> 주어진 객체 포함 여부는 두 번째 파라미터에 다라 달라짐. |
| `NavigableSet<E>` | tailSet(<br>  E fromElement, <br>  boolean Inclusive <br>) | 주어진 객체보다 높은 객체들을 NavigableSet으로 리턴.<br> 주어진 객체 포함 여부는 두 번째 파라미터에 따라 달라짐. |
| `NavigableSet<E>` | subSet(<br>  E toElement, <br>  boolean inclusive, <br>  E fromElement, <br>  boolean Inclusive ) | 시작과 끝으로 주어진 객체 사이의 객체들을 NavigableSet으로 리턴.<br> 시작과 끝 객체의 포함 여부는 두 번째, 네 번째 파라미터에 따라 달라짐. |


**TreeSetExample3.java**
 ```java
public class TreeSetExample3 {
	public static void main(String[] args) {
		TreeSet<String> treeSet = new TreeSet<String>();
		treeSet.add("apple");
		treeSet.add("forever");		
		treeSet.add("description");
		treeSet.add("ever");
		treeSet.add("zoo");
		treeSet.add("base");
		treeSet.add("guess");
		treeSet.add("cherry");
		
		System.out.println("[c~f 사이의 단어 검색]");
		NavigableSet<String> rangeSet = treeSet.subSet("c", true, "f", true);
		for(String word : rangeSet) {
			System.out.println(word);
		}

	}
}
```

**결과 화면**     
     
 
<br><br>
<hr/>

 ### 교재 757p : 15.5.3 TreeMap   
     
 자동으로 String 또한 자동으로 가나다 순 오름차 순 으로 표시되게 된다.
	 
**TreeMapTest.java**
```java
public class TreeMapTest {

	public static void main(String[] args) {
		// 맵 컬렉션 생성
		Map<String, Integer> map = new TreeMap<String, Integer>();

		// 객체 저장
		map.put("신용권", 85);
		map.put("홍길동", 90);
		map.put("동장군", 80);
		map.put("홍길동", 95);
		
		System.out.println(map);

		Set<String> keySet = map.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			Integer value = map.get(key);
			System.out.println("\t" + key + " : " + value);
		}
		System.out.println();
	}
}
```

**오름차순된 결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/124534455-4668fb80-de4f-11eb-80bf-2f21c68db849.png)

<br><br>

#### TreeMap 메소드

리턴 타입 | 메소드 | 설명
------|-----|---
Map.Entry<K, V> | firstEntry() | 제일 낮은 Map.Entry 리턴
Map.Entry<K, V> | lastEntry() | 제일 높은 Map.Entry 리턴
Map.Entry<K, V> | lowerEntry() | 주어진 키보다 바로 아래 Map.Entry 리턴
Map.Entry<K, V> | higherEntry() | 주어진 키보다 바로 위 Map.Entry 리턴
Map.Entry<K, V> | floorEntry() | 키와 같은 키가 있으면 Map.Entry 리턴, 없다면 바로 아래의 Map.Entry 리턴
Map.Entry<K, V> | ceilingEntry() | 키와 같은 키가 있으면 Map.Entry 리턴, 없다면 바로 위의 Map.Entry 리턴
Map.Entry<K, V> | pollFirstEntry() | 제일 낮은 Map.Entry를 꺼내오고 컬렉션에서 제거
Map.Entry<K, V> | pollLastEntry() | 제일 높은 Map.Entry를 꺼내오고 컬렉션에서 제거

 
<br><br>

#### TreeMap 예제

**TreeMapExample1.java**
```java
public class TreeMapExample1 {
	public static void main(String[] args) {
		TreeMap<Integer,String> scores = new TreeMap<Integer,String>();
		scores.put(new Integer(87), "홍길동");
		scores.put(new Integer(98), "이동수");
		scores.put(new Integer(75), "박길순");
		scores.put(new Integer(95), "신용권");
		scores.put(new Integer(80), "김자바");
		
		Map.Entry<Integer, String> entry = null;
		
		entry = scores.firstEntry();
		System.out.println("가장 낮은 점수: " + entry.getKey() + "-" + entry.getValue());
		
		entry = scores.lastEntry();
		System.out.println("가장 높은 점수: " + entry.getKey() + "-" + entry.getValue() + "\n");
		
		entry = scores.lowerEntry(new Integer(95));
		System.out.println("95점 아래 점수: " + entry.getKey() + "-" + entry.getValue());
		
		entry = scores.higherEntry(new Integer(95));
		System.out.println("95점 위의 점수: " + entry.getKey() + "-" + entry.getValue() + "\n");
		
		entry = scores.floorEntry(new Integer(95));
		System.out.println("95점 이거나 바로 아래 점수: " + entry.getKey() + "-" + entry.getValue());
		
		entry = scores.ceilingEntry(new Integer(85));
		System.out.println("85점 이거나 바로 위의 점수: " + entry.getKey() + "-" + entry.getValue() + "\n");
		
		while(!scores.isEmpty()) {
			entry = scores.pollFirstEntry();
			System.out.println(entry.getKey() + "-" + entry.getValue() + "(남은 객체 수: " + scores.size() + ")");
		}
	}
}

```

**결과 화면**    
![image](https://user-images.githubusercontent.com/84966961/124552840-00bc2b00-de6f-11eb-89ea-dbd04e7fac59.png)
   
<br><br>

#### TreeMap NavigableSet 정렬 메소드

리턴타입 | 메소드 | 설명
-----|-----|---
`NavigableSet<K>` | descendingKeySet() 내림차순으로 정렬된 키의 NavigableSet 리턴
`NavigableMap<K,V>` | descendingMap() | 내림차순으로 정렬된 Map.Entry의 NavigableSet 리턴

 NavigableSet 경우에 TreeSet 과 유사하다.

**TreeMapExample2.java**
```java
public class TreeMapExample2 {
	public static void main(String[] args) {
		TreeMap<Integer, String> scores = new TreeMap<Integer, String>();
		scores.put(new Integer(87), "홍길동");
		scores.put(new Integer(98), "이동수");
		scores.put(new Integer(75), "박길순");
		scores.put(new Integer(95), "신용권");
		scores.put(new Integer(80), "김자바");

		NavigableMap<Integer, String> descendingMap = scores.descendingMap();
		Set<Map.Entry<Integer, String>> descendingEntrySet = descendingMap.entrySet();
		for (Map.Entry<Integer, String> entry : descendingEntrySet) {
			System.out.print(entry.getKey() + "-" + entry.getValue() + " ");
		}
		System.out.println();

		NavigableMap<Integer, String> ascendingMap = descendingMap.descendingMap();
		Set<Map.Entry<Integer, String>> ascendingEntrySet = ascendingMap.entrySet();
		// for문
		System.out.println("for 문");
		for (Map.Entry<Integer, String> entry : ascendingEntrySet) {
			System.out.print(entry.getKey() + "-" + entry.getValue() + " ");
		}
		System.out.println();

		Iterator<Map.Entry<Integer, String>> entryIterator = ascendingEntrySet.iterator();
		
		// while문
		System.out.println("while 문");
		while (entryIterator.hasNext()) {
			Map.Entry<Integer, String> entry = entryIterator.next();
			Integer key = entry.getKey();
			String value = entry.getValue();
			System.out.print(entry.getKey() + "-" + entry.getValue() + " ");

		}
	}
}
```

**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/124554340-d23f4f80-de70-11eb-83b5-fc6838622616.png)
   


<br><br>

#### TreeMap NavigableSet 범위 검색 메소드


리턴타입  |  메소드 | 설명
---|---|---
NavigableMap   | headMap(<br>  K toKey,<br>  boolean inclusive<br>)   | 주어진 키보다 낮은 Map.Entry를 NavigableMap으로 리턴.<br> 주어진 객체 포함 여부는 두 번째 boolean값에 따라 달라진다.
NavigableMap   | tailMap(<br>  K fromKey,<br>  boolean inclusive<br>)   | 주어진 키보다 높은 Map.Entry를 NavigableMap으로 리턴.<br>주어진 객체 포함 여부는 두 번째 boolean값에 따라 달라진다.
NavigableMap   | subMap(<br>  K fromKey,<br>  boolean fromInclusive,<br>  K toKey,<br>  boolean toInclusive<br>) | 시작과 끝 사이의 Map.Entry를 NavigableMap으로 리턴 


**TreeMapExample3.java**
```java
public class TreeMapExample3 {
	public static void main(String[] args) {
		TreeMap<String,Integer> treeMap = new TreeMap<String,Integer>();
		treeMap.put("apple", new Integer(10));
		treeMap.put("forever", new Integer(60));		
		treeMap.put("description", new Integer(40));
		treeMap.put("ever", new Integer(50));
		treeMap.put("zoo", new Integer(10));
		treeMap.put("base", new Integer(20));
		treeMap.put("guess", new Integer(70));
		treeMap.put("cherry", new Integer(30));
		
		System.out.println("[c~f 사이의 단어 검색]");
		NavigableMap<String,Integer> rangeMap = treeMap.subMap("c", true, "f", true);
		for(Map.Entry<String, Integer> entry : rangeMap.entrySet()) {
			System.out.println(entry.getKey() + "-" + entry.getValue() + "페이지");
		}

	}
}
```




<br><br>
<hr/>

### 교재 763p : 15.5.4 Comparable과 Comparator      
   
1. Comparable 인터페이스를 구현하여 하는 방법(compareTo() 메소드)   
    
2. Comparator 인터페이스를 구현하여 정렬자로 제공하는 방법(compare() 메소드)


#### 정렬의 방식을 바꾸기 위한 방법 : Comparable, Comparator
   
 기본 방식인 오름차순 정렬을 다른 방식으로 정렬을 하고 싶다면 Comparable, Comparator를 사용할 수 있다.   

**TreeMapTest.java**
```java
public class TreeMapTest {

	public static void main(String[] args) {
		// 맵 컬렉션 생성
		Map<String, Integer> map = new TreeMap<String, Integer>();

		// 객체 저장
		map.put("신용권", 85);
		map.put("홍길동", 90);
		map.put("동장군", 80);
		map.put("홍길동", 95);
		
		System.out.println(map);

		Set<String> keySet = map.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			Integer value = map.get(key);
			System.out.println("\t" + key + " : " + value);
		}
		System.out.println();
	}
	
	static class ValueComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			
			return 0;
			
			// 오름차순 : o1 - o2 
			// 내림차순 : o2 - o1
		}
	}
}
```
    
 정렬의 방식에서 비교 대상 o1과 o2를 빼는 순서 변경을 통해 오름차순과 내림차순을 정할 수 있다. 양수일 경우 비교 대상이 큰 것, 음수일 경우 비교 대상이 더 작은 것, 0일 경우에는 둘의 차순이 같으므로 위치 변경이 일어나지 않는다.   
   
<br><br>
<hr/>

### 정렬을 구현하는 방법 : Comparable

 일반적으로 int형은 자동으로 트리셋에서 정수 값의 크고 작음에 따라 자동으로 정렬되게 된다. 하지만 String 타입의 키값은 기본 정렬이 되지 않으므로 비교하기 위해서 정렬을 원하는 클래스에 Comparable을 구현하여 CompareTo 메소드를 원하는 기준으로 정의해주면 된다.


**Person.java**
```java
public class Person {
	   public String name;
	   public int age;
	   public Person(String name, int age) {
	      super();
	      this.name = name;
	      this.age = age;
	   }
	   
	}
```

**PersonTest.java**
```java
public class PersonTest {

	public static void main(String[] args) {

		TreeSet<Person> treeSet = new TreeSet<>();
		
		treeSet.add(new Person("홍길동",10));
		treeSet.add(new Person("김자동",12));
		treeSet.add(new Person("고길동",14));

		System.out.println(treeSet);
	}

}
```

**정렬 기준이 없어 생기는 예외 화면**

![image](https://user-images.githubusercontent.com/84966961/124559779-0b7abe00-de77-11eb-914f-eb251972af31.png)


**Compareble을 구현한 Person.java**   
```java
public class Person implements Comparable<Person> {
	public String name;
	public int age;

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public int compareTo(Person o) {

		return age - o.age; // 오름차순
//		return o.age - age;	// 내림차순
	}
}
```

**PersonTest.java**   
```java
public class PersonTest {

	public static void main(String[] args) {

		TreeSet<Person> treeSet = new TreeSet<>();
		
		treeSet.add(new Person("홍길동",10));
		treeSet.add(new Person("김자동",12));
		treeSet.add(new Person("고길동",14));

		Iterator<Person> it = treeSet.iterator();
		
		while (it.hasNext()) {
			Person p = it.next();
			System.out.println(p.name+"  " + p.age);
		}
	}

}
```

**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/124560482-cefb9200-de77-11eb-8221-809aa90ed112.png)

   
<br><br>
<hr/>

### String 정렬 구현 : Comparable
   
 주소 기준으로도 해보았다.
 
 

**주소로 정렬한 Person.java**   
```java
public class Person implements Comparable<Person> {
	public String name;
	public int age;
	public String address;

//	public Person(String name, int age) {
//		super();
//		this.name = name;
//		this.age = age;
//	}

	
	public Person(String name, int age, String address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}

	@Override
	public int compareTo(Person o) {
		// String 정렬
		return address.compareTo(o.address); // 오름차순
//		return o.address.compareTo(address); // 오름차순
		// 숫자 정렬
//		return age - o.age; // 오름차순
//		return o.age - age;	// 내림차순
	}
}
```

**PersonTest.java**   
```java
public class PersonTest {

	public static void main(String[] args) {

		TreeSet<Person> treeSet = new TreeSet<>();
		
		treeSet.add(new Person("홍길동",10,"서울"));
		treeSet.add(new Person("김자동",12,"대전"));
		treeSet.add(new Person("고길동",14,"부산"));

		Iterator<Person> it = treeSet.iterator();
		
		while (it.hasNext()) {
			Person p = it.next();
			System.out.println(p.name+"  " + p.age+"  "+p.address);
		}
		
		// 아이디어 노트 : String 구분 방법
//		String aString = "a";
//		String bString = "b";
//		
//		System.out.println(aString.compareTo(bString));

	}

}
```

<br><br>
<hr/>

## 교재 767p : 15.6 LIFO와 FIFO 컬렉션      
    
 후입선출(LIFO : Last In First Out)은 나중에 넣은 객체가 먼저 빠져나가는 자료구조를 말한다. 반대로 선입선출(FIFO : First In First Out)은 먼저 넣은 객체가 먼저 빠져나가는 구조를 말한다. 컬렉션 프레임워크에는 LIFO 자료구조를 제공하는 스택(Stack) 클래스와 FIFO 자료구조를 제공하는 큐(Queue) 인터페이스를 제공하고 있다. 다음은 스택과 큐의 구조를 보여준다.

![image](https://user-images.githubusercontent.com/84966961/124562410-f4899b00-de79-11eb-8e42-99e6953c4b26.png)   
출처 : https://gohighbrow.com/stacks-and-queues/

![image](https://user-images.githubusercontent.com/84966961/124683299-b20e9f80-df07-11eb-80c0-883d363c5b25.png)

   
리턴 타입 | 메소드 | 설명 
---|---|---
E | push(E item) | 데이터를 스택에 입력
E | pop() | 데이터 사용 후 삭제
E | empty() | 스택 비어있는지 확인 (if 문에 `!stack.empty()` 이런 식으로 사용)
E | peek() | 데이터 사용 후 삭제하지 않음.

<br/><br>
<hr>

### 코드를 통한 스택 생성

 웹 브라우저의 뒤로가기 앞으로가기 기능의 로직을 한 번 짜보자.

**BackForwardStack.java**
```java
package stacklifo;

import java.util.EmptyStackException;
import java.util.Stack;

public class BackForwardStack {
	
	public static Stack back = new Stack<>();
	public static Stack forward = new Stack<>();
	
	public static void main(String[] args) {
		
		goURL("1.네이버");
		goURL("2.구글");
		goURL("3.다음");
		goURL("4.네이트");
		
		printURL();
		
		System.out.println("뒤로가기 forward 생김");
		toBack();
		printURL();
		
		System.out.println("네이버로 이동 : forward가 초기화.");
		goURL("5.네이버");
		printURL();
		
		System.out.println("뒤로가기 forward 생김");
		toBack();
		
		printURL();
	}
	
	public static void goURL(String url) {
		back.push(url);
		if (!forward.empty()) {
			forward.clear();
		}
	}
	
	public static void goForward() {
		if (!forward.empty()) {
			back.push(forward.pop());
		}
		
	}
	
	public static void toBack() {
		if (!back.empty()) {
			back.peek();
			forward.push(back.pop());
			// back.pop()의 리턴은 지우는 url인가봄.
		    /**
		     * Removes the object at the top of this stack and returns that
		     * object as the value of this function.
		     *
		     * @return  The object at the top of this stack (the last item
		     *          of the {@code Vector} object).
		     * @throws  EmptyStackException  if this stack is empty.
		     */
//		    public synchronized E pop() {
//		        E       obj;
//		        int     len = size();
//
//		        obj = peek();
//		        removeElementAt(len - 1);
//
//		        return obj;  // 지우는 obj를 리턴함.
//		    }
		}
	}
	
	public static void printURL() {
		System.out.println("--------------------");
		System.out.println();
		
		System.out.println("현재 위치 : "+back.lastElement());
		System.out.println("현재 위치 : "+back.peek());
		
		System.out.println("back"+back);
		System.out.println("forward"+forward);
		
		System.out.println();
		System.out.println("--------------------");
	}
}
```

**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/124687357-b0e17080-df0f-11eb-84bd-bea93bd1f82b.png)



