package demoSpringBoot;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class Base64Test {
	private static String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKZXJzZXktU2VjdXJpdHktQmFzaWMiLCJzdWIiOiJndXNpIiwiYXVkIjoidXNlciIsImV4cCI6MTQ4OTQwNDY5MywiaWF0IjoxNDg5NDA0MzM2LCJqdGkiOiIxIn0.4K-IjzoyIk35TeEe51k-siVxzRYkV5v4qk4j0DVEIDU";

	@Test
	public void testHeader() {
		String header = token.split("\\.")[0];

		String result = new String(Base64.decodeBase64(header));
		System.out.println(result);
	}

	@Test
	public void testPayload() {
		String payload = token.split("\\.")[1];
		String result = new String(Base64.decodeBase64(payload));

		System.out.println(result);
	}

	@Test
	public void testSign() {
		String sign = token.split("\\.")[2];
		String result = new String(Base64.decodeBase64(sign));

		System.out.println(result);
	}

}
