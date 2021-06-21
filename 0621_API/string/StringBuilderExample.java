package string;

public class StringBuilderExample {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();	// ��Ƽ ������ ȯ���� �ƴϸ� ���� ���۸� �� ������ ����.
		// ���۷� �ٲپ �Ʒ� ����� �Ȱ���.
		
		sb.append("Java ");
		sb.append("Program Study");		
		// ���� String�� ���� �޸𸮸� �����ϰ� ���ο� �޸𸮸� �Ҵ������� append�� ���� �޸𸮿� �߰��Ѵ�.
		// ���� �޸� ȿ������ �����Ѵ�.
		System.out.println(sb.toString());	// StringBuilder�� String Ÿ���� �ƴϹǷ� �ݵ�� ����� toString()���� Ÿ���� �ٲپ���� �Ѵ�.
		
		sb.insert(4, "2");
		System.out.println(sb.toString());

		sb.setCharAt(4, '6');		// 5��°�� 6���� �ٲپ��(2�� 6����)
		System.out.println(sb.toString());
		
		sb.replace(6, 13, "Book");	// ������ ĭ��(7ĭ)�� ������� book���� �ٲ۴�.
		System.out.println(sb.toString());
		
		sb.delete(4, 5);			// ������ ������ ���� �����ϴ�.
		System.out.println(sb.toString());
		
		int length = sb.length();
		System.out.println("�ѹ��ڼ�: " + length);
		
		String result = sb.toString();
		System.out.println(result);		
	}
}
