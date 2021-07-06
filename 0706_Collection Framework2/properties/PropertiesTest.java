package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

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
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
