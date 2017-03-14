package demoSpringBoot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

public class HttpClientTest {

	@Test
	public void testGetToken() {
		try {
			URL url = new URL("http://localhost:8081/token?username=gusi&password=123");
			URLConnection connection = url.openConnection();// 得到一个连接对象
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);// 用于读取返回的数据流
			StringBuffer sb = new StringBuffer();// 用于接收返回的数据
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb.toString());
			br.close();// 关闭各种连接
			isr.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUseToken() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKZXJzZXktU2VjdXJpdHktQmFzaWMiLCJzdWIiOiJndXNpIiwiYXVkIjoidXNlciIsImV4cCI6MTQ4OTQ1Njg1NCwiaWF0IjoxNDg5NDU2NDk0LCJqdGkiOiIxIn0.9vJhfwSMhHDbFYbP0USIcY_ydz2NtfBhpf5PHxx9P38";
		try {
			URL url = new URL("http://localhost:8081/user/dosomething");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 得到一个http的连接对象
			connection.addRequestProperty("encoding", "UTF-8");
			connection.setRequestMethod("POST");// 设置请求方式
			connection.setDoInput(true);// 设置可写入
			connection.setDoOutput(true);// 设置可读取
			connection.addRequestProperty("auth_token", token);
			OutputStream os = connection.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);// 定义写入流对象
			bw.write("parame=haha");
			bw.flush();// 通过写入流对象写入请求参数
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);// 定义读取流对象
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}// 通过读取流读取返回数据
				// 关闭各种连接对象
			br.close();
			isr.close();
			is.close();
			bw.close();
			osw.close();
			os.close();
			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
