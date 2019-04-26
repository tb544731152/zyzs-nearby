package com.xc.microservice.validate.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.xc.microservice.validate.util.wx.TemplateData;
import com.xc.microservice.validate.util.wx.TemplateVM;
import com.xc.microservice.validate.util.wx.WxTemplate;

import net.sf.json.JSONObject;

/**
 * 和微信交互工具类
 * @author zk
 *
 */
public class WxUtil {
	/**
     * 
     * @param appId   公账号appId
     * @param appSecret
     * @param url    当前网页的URL，不包含#及其后面部分
     * @return
     * @throws Exception 
     */
    public static JSONObject getParam(String appId , String access_token,String jsTicket , HttpServletRequest request) throws Exception{
     
		JSONObject json = new JSONObject();
    	String openurl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
        String url = getUrl(request);
        Map<String, String> params = sign(jsTicket, url);
        params.put("appid", appId);
        JSONObject jsonObject = JSONObject.fromObject(params);  
        return jsonObject;
    }
    private static String getUrl(HttpServletRequest request){
        
        StringBuffer requestUrl = request.getRequestURL();
         
        String queryString = request.getQueryString();
        String url = "";
        if (queryString==null || "null".equals(queryString)) {
        	url = requestUrl.toString();
		}else {
			url = requestUrl +"?"+queryString;
		}
        return url;
    }
    
    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String str;
        String signature = "";
 
        //注意这里参数名必须全部小写，且必须有序
        str = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
 
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
 
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
 
        return ret;
    }
    
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }
 
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
	/**
	 * 解析code 换取openId
	 * @param t1
	 * @return
	 * @throws Exception
	 */
	public static JSONObject analysisAuthor(String appid,String secret ,String code) throws Exception{
		JSONObject json = new JSONObject();
		if(!"".equals(appid)&&
				!"".equals(secret)&&
					!"".equals(code)){
			
			String result = HttpUtils.postJsonRequest("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code", json.toString());
			JSONObject data = JSONObject.fromObject(result);
			return data;
		}else{
			throw new RuntimeException("解析code 换取openId code 为空 "+code);
		}
	}
	/**
	 * 获取带参数临时二维码
	 */
	public  static String getTicketTEMP(String token,String scene_str) throws Exception {
		JSONObject json = new JSONObject();
		Map<String,Object> scene = new HashMap<String,Object>();
		scene.put("scene_str",scene_str);
		
		Map<String,Object> action_info = new HashMap<String,Object>();
		action_info.put("scene", scene);
		
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("expire_seconds", 1800);
		res.put("action_name", "QR_STR_SCENE");
		res.put("action_info", action_info);
		
		String result = HttpUtils.postJsonRequest("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token, json.fromObject(res).toString());
		JSONObject ticket = JSONObject.fromObject(result);
		return ticket.get("ticket").toString();
	}
	
	/**
	 * 获取带参数永久二维码
	 */
	public  static String getTicketOLD(String token,String scene_key) throws Exception {
		JSONObject json = new JSONObject();
		Map<String,Object> scene = new HashMap<String,Object>();
		scene.put("scene_str",scene_key);
		
		Map<String,Object> action_info = new HashMap<String,Object>();
		action_info.put("scene", scene);
		
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("action_name", "QR_LIMIT_STR_SCENE");
		res.put("action_info", action_info);
		
		String result = HttpUtils.postJsonRequest("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token, json.fromObject(res).toString());
		JSONObject ticket = JSONObject.fromObject(result);
		return ticket.get("ticket").toString();
	}
	
	
	/**
	 * 推送模板
	*/
	public static JSONObject sendTemplate(String accessToken,String msg){
        String res = HttpUtils.postJsonRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken,msg);
		JSONObject results = JSONObject.fromObject(res);
        return results;
	}
	
	public static JSONObject sendMsg(String accessToken,String openId,String templateId,TemplateVM vm){
		WxTemplate t = new WxTemplate();
		t.setTouser(openId);
        t.setTemplate_id(templateId);
        Map<String,TemplateData> key = new HashMap<String,TemplateData>();  
        
        TemplateData first = new TemplateData();  
        first.setColor("#EAC100");  
        first.setValue(vm.getTitle());  
        key.put("first", first);  
       
        TemplateData keyword1 = new TemplateData();  
        keyword1.setColor("#000000");  
        keyword1.setValue(vm.getKeyword1()+"\n"); 
        key.put("keyword1", keyword1);  
      
        TemplateData keyword2 = new TemplateData();  
        keyword2.setColor("#000000");  
        keyword2.setValue(vm.getKeyword2()+"\n");  
        key.put("keyword2", keyword2);  
        
        if(vm.getKeyword3()!=null){
        	TemplateData keyword3 = new TemplateData();  
        	keyword3.setColor("#000000");  
        	keyword3.setValue(vm.getKeyword3()+"\n");  
        	key.put("keyword3", keyword3);  
        }
        if(vm.getKeyword4()!=null){
        	TemplateData keyword4 = new TemplateData();  
        	keyword4.setColor("#000000");  
        	keyword4.setValue(vm.getKeyword4()+"\n");  
        	key.put("keyword4", keyword4);  
        }
        
        if(vm.getRemark()!=null){
        	TemplateData remark = new TemplateData();  
        	remark.setColor("#EAC100");  
        	remark.setValue("\n"+vm.getRemark());  
        	key.put("remark", remark);  
        }
        t.setData(key);
        if(vm.getUrl()!=null){
        	t.setUrl(vm.getUrl());
        }
        return sendTemplate(accessToken, JSONObject.fromObject(t).toString());
	}
	
	
	
}
