// [문제2] 아래는 화씨(Fahrenhrit)를 섭써(Celcius)로 변환하는 코드이다. 
// 변환공식이 'C = 5/9 × (F - 32)'라고 할 때, ( )에 알맞은 코드를 넣으시오. 
// 단, 변환 결과값은 소수점 셋째 자리예서 반올림해야 한다.
// (Math.round)를 사용하지 않고 쳐리할 것.

public class Q2 {

	public static void main(String[] args) {
		int fahrenhrit = 100;
		float celcius = (float)(((fahrenhrit-32)*500+5)/9)/100;
		System.out.println("fahrenhrit:"+fahrenhrit);
		System.out.println("celcius:"+celcius);

	}

}

