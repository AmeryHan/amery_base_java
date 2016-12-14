package amery;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class Proxy {

	public static void main(String[] args) {

		String url = "http://gw.api.taobao.com/router/rest";

		String proxy = "web-proxy.rose.hp.com";

		String port = "8080";

		try {
			URL server = new URL(url);
			Properties systemProperties = System.getProperties();
			systemProperties.setProperty("http.proxyHost", proxy);
			systemProperties.setProperty("http.proxyPort", port);
			HttpURLConnection connection = (HttpURLConnection) server
					.openConnection();
			connection.connect();
			InputStream in = connection.getInputStream();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// readResponse(in);
	}
}
