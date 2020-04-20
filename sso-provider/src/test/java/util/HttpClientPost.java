package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientPost {
	//核心方法
	public static String httpPostWithForm(String url, Map<String, String> paraMap) throws Exception{
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		
		//遍历参数Map，设置表单参数
		List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
		for(Map.Entry<String, String> entry : paraMap.entrySet()) {
			pairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
		
		HttpResponse resp = client.execute(httpPost);
		if(200 == resp.getStatusLine().getStatusCode()) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he, "utf-8");
		}
		
		return respContent;
	}
	
	@Test
	public void Query() throws Exception{
		String url = "http://127.0.0.1:8001/user/query";
		Map<String, String> paraMap = new HashMap<String, String>();
		System.out.println("");
	}
}
