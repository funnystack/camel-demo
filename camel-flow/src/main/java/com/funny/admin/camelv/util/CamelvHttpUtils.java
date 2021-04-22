package com.funny.admin.camelv.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoka.camelv.constant.Constant;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 封装http调用，使用okHttp
 * 
 * @author xiaoka
 *
 */
public class CamelvHttpUtils {

	private static Logger logger = LoggerFactory.getLogger(CamelvHttpUtils.class);

	private static SSLContext SSL_CONTEXT = null;

	public static final X509TrustManager X509_TRUST_MANAGER = new X509TrustManager() {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			X509Certificate[] x509Certificates = new X509Certificate[0];
			return x509Certificates;
		}
	};

	private static final HostnameVerifier HOSTNAME_VERIFIER = new HostnameVerifier() {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};

	static {
		try {
			SSL_CONTEXT = SSLContext.getInstance("SSL");
			SSL_CONTEXT.init(null, new TrustManager[] { X509_TRUST_MANAGER }, new SecureRandom());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用post方式请求Http
	 * 
	 * @param url
	 * @param contentType
	 * @param body
	 * @return
	 */
	public static String sendHttpPost(String url, String contentType, Map<String, String> header, String body) {
		OkHttpClient client = new OkHttpClient();
		try {
			Builder builder = new Request.Builder().url(url);
			// 添加header
			if (header != null && header.size() > 0) {
				Set<Entry<String, String>> entrySet = header.entrySet();
				for (Entry<String, String> entry : entrySet) {
					builder.addHeader(entry.getKey(), entry.getValue());
				}
			}
			MediaType type = MediaType.parse(contentType);
			Request request = builder.post(RequestBody.create(type, body)).build();
			Response response = client.newCall(request).execute();
			if (!response.isSuccessful()) {
				logger.error("发送post请求:url = " + url + ",mediaType=" + contentType + ",失败原因:" + response.toString());
				return response.code() + "";
			}
			return response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.ERROR;
	}

	/**
	 * 添加get请求
	 * 
	 * @param url
	 * @param header
	 * @return
	 */
	public static String sendHttpGet(String url, Map<String, String> header) {
		OkHttpClient client = new OkHttpClient();
		try {
			Builder builder = new Request.Builder().url(url);
			// 添加header
			if (header != null && header.size() > 0) {
				Set<Entry<String, String>> entrySet = header.entrySet();
				for (Entry<String, String> entry : entrySet) {
					builder.addHeader(entry.getKey(), entry.getValue());
				}
			}
			Request request = builder.build();
			Response response = client.newCall(request).execute();
			if (!response.isSuccessful()) {
				logger.error("发送get请求:url = " + url + ",失败原因:" + response.toString());
				return response.code() + "";
			}
			return response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.ERROR;
	}

	/**
	 * 使用HTTPS发送post请求
	 * 
	 * @param url
	 * @param contentType
	 * @param body
	 * @return
	 */
	public static String sendHttpsPost(String url, String contentType, Map<String, String> header, String body) {

		try {
			OkHttpClient client = new OkHttpClient.Builder()
					.sslSocketFactory(SSL_CONTEXT.getSocketFactory(), X509_TRUST_MANAGER)
					.hostnameVerifier(HOSTNAME_VERIFIER).build();
			Builder builder = new Request.Builder().url(url);
			// 添加header
			if (header != null && header.size() > 0) {
				Set<Entry<String, String>> entrySet = header.entrySet();
				for (Entry<String, String> entry : entrySet) {
					builder.addHeader(entry.getKey(), entry.getValue());
				}
			}
			MediaType type = MediaType.parse(contentType);
			Request request = builder.post(RequestBody.create(type, body)).build();
			Response response = client.newCall(request).execute();
			if (!response.isSuccessful()) {
				logger.error("发送post请求:url = " + url + ",mediaType=" + contentType + ",失败原因:" + response.toString());
				return response.code() + "";
			}
			return response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.ERROR;
	}

	/**
	 * 使用HTTPS发送post请求
	 * 
	 * @param url
	 * @param mediaType
	 * @param body
	 * @return
	 */
	public static String sendHttpsGet(String url, Map<String, String> header) {

		try {
			OkHttpClient client = new OkHttpClient.Builder()
					.sslSocketFactory(SSL_CONTEXT.getSocketFactory(), X509_TRUST_MANAGER)
					.hostnameVerifier(HOSTNAME_VERIFIER).build();
			Builder builder = new Request.Builder().url(url);
			// 添加header
			if (header != null && header.size() > 0) {
				Set<Entry<String, String>> entrySet = header.entrySet();
				for (Entry<String, String> entry : entrySet) {
					builder.addHeader(entry.getKey(), entry.getValue());
				}
			}
			Request request = builder.build();
			Response response = client.newCall(request).execute();
			if (!response.isSuccessful()) {
				logger.error("发送get请求:url = " + url + ",失败原因:" + response.toString());
				return response.code() + "";
			}
			return response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.ERROR;
	}

	public static void sendAsyncHttpPost(String url, String contentType, Map<String, String> header, String body) {
		OkHttpClient client = new OkHttpClient();
		try {
			Builder builder = new Request.Builder().url(url);
			// 添加header
			if (header != null && header.size() > 0) {
				Set<Entry<String, String>> entrySet = header.entrySet();
				for (Entry<String, String> entry : entrySet) {
					builder.addHeader(entry.getKey(), entry.getValue());
				}
			}
			MediaType type = MediaType.parse(contentType);
			Request request = builder.post(RequestBody.create(type, body)).build();
			// 异步请求
			client.newCall(request).enqueue(new Callback() {

				@Override
				public void onFailure(Call call, IOException e) {
					e.printStackTrace();
					logger.error("异步请求异常：" + e.getMessage());
				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					logger.info("异步请求成功:" + response.body().string());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
