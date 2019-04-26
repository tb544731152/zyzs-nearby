package com.xc.microservice.validate.util.wx;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSONObject;
import com.xc.microservice.validate.model.entity.AppKey;
import com.xc.microservice.validate.model.result.WxSendMsg;
import com.xc.microservice.validate.util.HttpUtils;



public class WxMessageUtils {
	
	public static JSONObject sendMsg(WxSendMsg msg,AppKey appkey){
		JSONObject json = new JSONObject();
		WxTemplate t = new WxTemplate();
		t.setTouser(msg.getOpenId());
		t.setPage("index");
		t.setForm_id(msg.getForm_id());
        t.setTemplate_id(msg.templateId);
        
        Map<String,TemplateData> m = new HashMap<String,TemplateData>();  
        TemplateData keyword1 = new TemplateData();  
        keyword1.setColor("#000000");  
        keyword1.setValue(msg.getResult()+"\n");  
        m.put("keyword1", keyword1);  
        
        TemplateData keyword2 = new TemplateData();  
        keyword2.setColor("#000000");  
        keyword2.setValue(msg.getDate()+"\n");  
        m.put("keyword2", keyword2);  
        t.setData(m);
        String result = HttpUtils.postJsonRequest("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+appkey.getAccessToken(), json.toJSONString(t).toString());
		JSONObject results = JSONObject.parseObject(result);
		return results;
	}
	
	
	/** 
     * 解析微信发来的请求（XML） 
     *  
     * @param request 
     * @return 
     * @throws Exception 
     */ 
    @SuppressWarnings("unchecked") 
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception { 
        // 将解析结果存储在HashMap中 
        Map<String, String> map = new HashMap<String, String>(); 
        // 从request中取得输入流 
        InputStream inputStream = request.getInputStream(); 
        // 读取输入流 
        SAXReader reader = new SAXReader(); 
        Document document = reader.read(inputStream); 
        // 得到xml根元素 
        Element root = document.getRootElement(); 
        // 得到根元素的所有子节点 
        List<Element> elementList = root.elements(); 
        // 遍历所有子节点 
        for (Element e : elementList) 
            map.put(e.getName(), e.getText()); 
        // 释放资源 
        inputStream.close(); 
        inputStream = null; 
        return map; 
    } 
 

}
