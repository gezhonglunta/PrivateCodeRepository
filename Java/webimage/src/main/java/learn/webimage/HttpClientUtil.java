package learn.webimage;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.StatusLine;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

public class HttpClientUtil {
	public static int Sleep = 100;

	public static byte[] getResStream(String url) {
		if (Sleep > 00) {
			try {
				Thread.sleep(Sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		HttpClient httpClient = new HttpClient();
		try {
			HttpMethod method = new GetMethod(url);
			HttpConnectionManager connectionManger = httpClient.getHttpConnectionManager();
			if (connectionManger != null) {
				HttpConnectionManagerParams params = connectionManger.getParams();
				params.setConnectionTimeout(30000);
				//params.setSoTimeout(60000);
			}
			httpClient.executeMethod(method);
			StatusLine statusLine = method.getStatusLine();
			byte[] result = null;
			if (statusLine.getStatusCode() == 200) {
				InputStream stream = method.getResponseBodyAsStream();
				result = FileUtil.readFromInputStream(stream);
				stream.close();
			}
			method.releaseConnection();
			return result;
		} catch (HttpException e) {
		} catch (IOException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalStateException e) {
		} catch (Exception e) {
		}
		return null;
	}

	public static boolean verifyUrl(String url) {
		try {
			URI uri = new URI(url, false);
			if (uri != null) {
				return true;
			}

		} catch (URIException e) {
			System.out.println(url);
		}
		return false;
	}
}
