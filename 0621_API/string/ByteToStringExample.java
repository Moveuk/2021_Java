package string;

public class ByteToStringExample {
	public static void main(String[] args) {
		byte[] bytes = { 72, 101, 108, 108, 111, 32, 74, 97, 118, 97  };
		
		String str1 = new String(bytes);	// bytes로 초기화
		System.out.println(str1);	// Hello Java
		
		String str2 = new String(bytes, 6, 4);	// 0부터시작이므로 7번째부터 4열까지 초기화
		System.out.println(str2);	// Java
	}
}
