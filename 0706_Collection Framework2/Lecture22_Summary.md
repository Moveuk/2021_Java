# Lecture21 : 컬렉션 프레임워크(Collection Framework)
Key Word : 컬렉션 프레임워크, Set, HashSet, Map, HashMap, HashTable, Properties, Tree, TreeSet, TreeMap, Comparable, Comparator

<hr/>

 ### 교재 736p : 15.3.1 HashSet   
     
 HashSet은 Set 인터페이스의 구현 클래스이다.   
    
 HashSet은 객체들을 순서 없이 저장하고 동일한 객체는 중복 저장하지 않는다. HashSet이 판단하는 동일한 객체란 꼭 같은 인스턴스를 뜻하지 않는다. HashSet은 객체를 저장하기 전에 먼저 객체의 hashCode() 메소드를 호출해서 해시코드를 얻어낸다. 그리고 이미 저장되어 있는 객체들의 해시코드와 비교한다. 만약 동일한 해시코드가 있다면 다시 equals() 메소드로 두 객체를 비교해서 true가 나오면 동일한 객체로 판단하고 중복 저장을 하지 않는다.

**HashSet 흐름도**

![image](https://user-images.githubusercontent.com/84966961/124412399-633afb80-dd89-11eb-9213-1e35b73caa9e.png)   
   
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

![image](https://user-images.githubusercontent.com/84966961/124412399-633afb80-dd89-11eb-9213-1e35b73caa9e.png)

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


<br><br>
<hr/>

 ## 교재 750p : 15.5 검색 기능을 강화시킨 컬렉션
 
 컬렉션 프레임 워크는 검색 기능을 강화시킨 TreeSet과 TreeMap을 제공하고 있다. 이름에서 알 수 있듯이 TreeSet은 Set 컬렉션이고, TreeMap은 Map 컬렉션이다. 이 컬렉션들은 이진 트리(binary tree)를 이용해서 계층적 구조(Tree 구조)를 가지면서 객체를 저장한다.   
    
 TreeSet은 값만 가지고 있으며 TreeMap은 트리 구조에 Key-Value 구조를 더한 컬렉션이다.   
 
 
<br><br>
<hr/>

 ### 교재 750p : 15.5.1 이진 트리 구조
 
 이진 트리(binary tree)는 여러 개의 노드(node)가 트리 형태로 연결된 구조로, 루트 노드(root node)라고 불리는 하나의 노드에서부터 시작해서 각 노드에 최대 2개의 노드를 연결 할 수 있는 구조를 가지고 있다. 위아래로 연결된 두 노드를 부모-자식관계에 있다고 하며 위의 노드를 부모 노드, 아래의 노드를 자식 노드라고 한다. 하나의 부모 노드는 최대 두개의 자식노드와 연결될 수 있다. 그림에서 보면 A 노드는 B, C 노드의 부모 노드이고 B, C 노드는 A 노드의 자식 노드이다.
 
 
 
 
 
 
<br><br>
<hr/>

 ### 교재 752p : 15.5.2 TreeSet   
     



 
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
<hr/>

### 교재 763p : Comparable, Comparator   
     

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
   


































