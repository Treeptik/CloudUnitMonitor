package fr.treeptik.cloudunitmonitor.docker;

import java.io.IOException;
import java.net.URI;

import fr.treeptik.cloudunitmonitor.json.ui.JsonResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class JSONClient {

	private Logger logger = Logger.getLogger(JSONClient.class);

	public JsonResponse sendGet(URI uri) throws IOException {
		StringBuilder builder = new StringBuilder();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse response = httpclient.execute(httpGet);
		LineIterator iterator = IOUtils.lineIterator(response.getEntity()
				.getContent(), "UTF-8");
		while (iterator.hasNext()) {
			builder.append(iterator.nextLine());
		}
		JsonResponse jsonResponse = new JsonResponse(response.getStatusLine()
				.getStatusCode(), builder.toString(), null);
		return jsonResponse;
	}

	public int sendPost(URI uri, String body, String contentType)
			throws ClientProtocolException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("POST : uri  " + uri + " - body  :  " + body
					+ " - contentype : " + contentType);
		}

		/**
		 * TODO
		 */
		logger.info("POST : uri " + uri + " - body  :  " + body
				+ " - contentype : " + contentType);

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(uri);
		httpPost.addHeader("content-type", contentType);
		httpPost.setEntity(new StringEntity(body));

		StatusLine statusLine = httpclient.execute(httpPost).getStatusLine();
		if (logger.isDebugEnabled()) {
			logger.debug("POST : uri " + uri + " returns "
					+ statusLine.getStatusCode());
		}

		logger.info("POST : uri " + uri + " returns "
				+ statusLine.getStatusCode());

		return statusLine.getStatusCode();
	}

	public int sendDelete(URI uri) throws ClientProtocolException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("DELETE : uri " + uri);
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete(uri);
		StatusLine statusLine = httpClient.execute(httpDelete).getStatusLine();
		if (logger.isDebugEnabled()) {
			logger.debug("DELETE : uri " + uri + " returns "
					+ statusLine.getStatusCode());
		}
		logger.info("POST : uri " + uri + " returns "
				+ statusLine.getStatusCode());

		return statusLine.getStatusCode();
	}
}
