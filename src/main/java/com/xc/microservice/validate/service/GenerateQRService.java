package com.xc.microservice.validate.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.xc.microservice.validate.dao.AppKeyRepository;
import com.xc.microservice.validate.model.entity.AppKey;
import com.xc.microservice.validate.model.entity.TFile;
import com.xc.microservice.validate.util.HttpUtils;

@Service
public class GenerateQRService {
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private AppKeyRepository appKeyDao;
	
	
	
	public String generateQR(String appid){
		AppKey appkey = appKeyDao.findByAppId(appid);
		String url = "https://api.weixin.qq.com/wxa/getwxacode?access_token="+appkey.getAccessToken();
		StringBuffer sb = new StringBuffer();
		sb.append("access_token=").append(appkey.getAccessToken());
		sb.append("&scene=").append("123");
		sb.append("&width=").append("344");
		sb.append("&auto_color=").append("true");
		sb.append("&line_color=").append("{\"r\":255,\"g\":0,\"b\":0}");
		HttpUtils.sendPostToWX(url, sb.toString());
		return "ok";
	}
	
	public String generateQR2(String appid,String scene){
		AppKey appkey = appKeyDao.findByAppId(appid);
		String url = "https://api.weixin.qq.com/wxa/getwxacode?access_token="+appkey.getAccessToken();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("access_token", appkey.getAccessToken());
		map.put("path", "pages/index/index");//你二维码中跳向的地
		map.put("scene", scene);//场景值
		map.put("width", "430");//图片大小
		JSONObject json = JSONObject.fromObject(map);
		try {
			String  res= httpPostWithJSON(url
			        + appkey.getAccessToken(), json.toString(),scene);
			return res;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public  String httpPostWithJSON(String url, String json,String senceid)
            throws Exception {
        String result = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
  
        StringEntity se = new StringEntity(json);
        se.setContentType("application/json");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
                        "UTF-8"));
        httpPost.setEntity(se);
        // httpClient.execute(httpPost);
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                    InputStream instreams = resEntity.getContent(); 
                    
                    result = saveToImgByInputStream(instreams, senceid);
            }
        }
        httpPost.abort();
        return result;
    } 
     
    /* @param instreams 二进制流
	* @param imgPath 图片的保存路径
	* @param imgName 图片的名称
	* @return
	*      1：保存正常
	*      0：保存失败
	*/
	public  String saveToImgByInputStream(InputStream instreams,String senceid) throws IOException{
		byte[] bytes = new byte[0];
		bytes = new byte[instreams.available()];
		instreams.read(bytes);
		String str = new String(bytes);
		logger.info(str);
		
		MultipartFile multipartFile = new MockMultipartFile("wx.jpg","temp.jpg","", instreams);
	     String random = RandomStringUtils.randomAlphabetic(64);
			try {
	            TFile mf = new TFile("wxapp-qr.jpg", "image/jpg", 430,
	                    new Binary(multipartFile.getBytes()),"zk");
	            mf.setImgId(random);
	            fileService.save(mf);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
		return random;
		
	}
}
