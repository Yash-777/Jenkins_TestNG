package in.jenkins.test;

import java.io.IOException;

public class Utils {
	public static void getUserAction() {
		try {
			System.in.read();
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
