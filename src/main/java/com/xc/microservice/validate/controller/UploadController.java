package com.xc.microservice.validate.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;  

import com.xc.microservice.validate.config.access.AccessLimit;
import com.xc.microservice.validate.model.entity.FansSession;
import com.xc.microservice.validate.model.entity.TFile;
import com.xc.microservice.validate.model.result.CodeMsg;
import com.xc.microservice.validate.model.result.Result;
import com.xc.microservice.validate.service.FileService;
import com.zyzs.microservice.validate.domain.user.ZyzsFans;

/**
 * 文件上传服务    -- 上传需登录
 * @author zk
 *
 */
@RestController
public class UploadController extends BaseController{
	
	@Autowired
	private FileService fileService;
	
	/**
	 * 上传文件--登录才可上传文件
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/upload/image", method = RequestMethod.POST, produces = "application/json")
	@AccessLimit(seconds=60, maxCount=5, needLogin=true)
	public Result<?> uploadImage(Model model,FansSession fans,@RequestParam(required=true,value="file")MultipartFile file){
		String random = RandomStringUtils.randomAlphabetic(64);
		try {
            TFile f = new TFile(file.getOriginalFilename(), file.getContentType(), file.getSize(),
                    new Binary(file.getBytes()),"123");
            f.setImgId(random);
            fileService.save(f);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		System.out.println("上传成功"+ random);
		return Result.msg(CodeMsg.SUCCESS.fillArgsToken(random));
	}
	/**
	 * 获取文件
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/api/file/get/image/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object>  uploadImage(HttpResponse response,@PathVariable String id)throws UnsupportedEncodingException {
		TFile file = fileService.getFile(id);
		if(file!=null){
			String fileName = new String(file.getName().getBytes("utf-8"),"ISO-8859-1");
			String extension = FilenameUtils.getExtension(fileName);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=" + new String(file.getName().getBytes("utf-8"),"ISO-8859-1"))
					.header(HttpHeaders.CONTENT_TYPE, "image/".concat(extension).concat(";charset=UTF-8"))
					.header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "").header("Connection", "close")
					.body(file.getContent().getData());
		}
		return new ResponseEntity<>(
		          "404", 
		          HttpStatus.BAD_REQUEST);
	}
	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/api/upload/test", method = RequestMethod.GET, produces = "application/json")
	public Result<?> uploadImageTest(){
		String picPath ="D://2.png";  
        MultipartFile file = getMulFileByPath(picPath); 
        String random = RandomStringUtils.randomAlphabetic(64);
		try {
            TFile mf = new TFile(file.getOriginalFilename(), file.getContentType(), file.getSize(),
                    new Binary(file.getBytes()),"zk");
            mf.setImgId(random);
            fileService.save(mf);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		return Result.msg(CodeMsg.SUCCESS.fillArgsToken(random));
	}
	
	
	private static MultipartFile getMulFileByPath(String picPath) {  
        FileItem fileItem = createFileItem(picPath);  
        MultipartFile mfile = new CommonsMultipartFile(fileItem);  
        return mfile;  
    }  
  
    private static FileItem createFileItem(String filePath)  
    {  
        FileItemFactory factory = new DiskFileItemFactory(16, null);  
        String textFieldName = "textField";  
        int num = filePath.lastIndexOf(".");  
        String extFile = filePath.substring(num);  
        FileItem item = factory.createItem(textFieldName, "text/plain", true,  
            "MyFileName" + extFile);  
        File newfile = new File(filePath);  
        int bytesRead = 0;  
        byte[] buffer = new byte[8192];  
        try  
        {  
            FileInputStream fis = new FileInputStream(newfile);  
            OutputStream os = item.getOutputStream();  
            while ((bytesRead = fis.read(buffer, 0, 8192))  
                != -1)  
            {  
                os.write(buffer, 0, bytesRead);  
            }  
            os.close();  
            fis.close();  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        return item;  
    }  
}
