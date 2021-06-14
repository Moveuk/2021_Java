# Interface 활용
Key Word : -able, 
<hr/>
   
### Starcraft의 건물(Building)으로 구조 익히기   
**만들고자 하는 원하는 스타크래프트 빌딩의 구조**   
   
![image](https://user-images.githubusercontent.com/84966961/121826382-5e3add00-ccf2-11eb-80ea-85108303e8f9.png)
   
<br/><br/>
<hr/>
    
 1. 먼저 각 요소들의 클래스, 인터페이스 들을 만들어 준다.   
   
![image](https://user-images.githubusercontent.com/84966961/121826560-431c9d00-ccf3-11eb-84f5-a95208a40ff4.png)   
   
<br/><br/>
<hr/>
    
 2. Liftable interface에 각 건물의 기능에 대한 추상 메소드들을 정의해준다. (liftoff, move, stop, land)   
    
 ```java
 public interface Liftable {
	void liftoff();
	void move(int x,int y);
	void stop();
	void land();
}
```
   
 `Liftable Implements` 라는 배럭과 팩토리와의 포함 관계인 클래스를 만들어 줄 것이다. 왜 굳이 만들어서 사용하는 걸까?   

 => 만약 같은 기능을 가진 건물들(클래스)들이 무수히 많고 기능을 공통으로 한번에 정의해주는 객체가 없다면 일일히 수백개가 되는 클래스들을 다시 정의해주는 곤란한 상황이 생길 수 있다. 그런 상황을 방지하기 위해서 `Liftable Implements`를 만들어주고 한 곳에 기능을 모두 정의한 다음 기능이 필요한 건물들이 포함관계로 참조함으로써 구조의 단순화와 효율성을 높여 줄 수 있다.   
   
```java
public class LiftableImpl implements Liftable {
	@Override
	public void liftoff() {
		System.out.println("이륙한다.");
	}
	@Override
	public void move(int x, int y) {
		System.out.println("움직인다.");
	}
	@Override
	public void stop() {
		System.out.println("멈춘다.");
	}
	@Override
	public void land() {
		System.out.println("착륙한다.");
	}
}
```

<br/><br/>
<hr/>
    
3. ㅁ
 4. ㅁ
 5. ㅁ
 6. ㅁ
 7. ㅁㅁ
 8. ㅁ
 9. ㅁ
 10. ㅁ
 11. ㅁ
 12. ㅁ
 13. ㅁ
 14. ㅁ
 15. ㅁ
 16. 
