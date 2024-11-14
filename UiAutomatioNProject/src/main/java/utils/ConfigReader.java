package utils;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	private static Properties props = new Properties();
	
	static {
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/config.properties");
			props.load(fis);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	public static String getProps(String strValue) {
		System.out.println(props.getProperty(strValue));
		return props.getProperty(strValue);
		
		
	}
}
