package com.xc.microservice.validate.controller;

import java.math.BigDecimal;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.xc.microservice.validate.config.access.AccessLimit;
import com.xc.microservice.validate.model.entity.FansSession;
import com.xc.microservice.validate.model.result.Result;
import com.xc.microservice.validate.service.SmokerShareService;
import com.zyzs.microservice.validate.domain.nearby.ZyzsSmokerShareSellers;
/**
 * 烟友分享店铺
 * @author dsk_zyzs@aliyun.com
 * 2019年4月17日
 */
@RestController
public class SmokerShareController extends BaseController{
	/*日志*/
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private SmokerShareService shareService;
	/**
	 * 烟友上传店铺
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/smoker/share",method = RequestMethod.POST, produces = "application/json")
	@AccessLimit(seconds=60, maxCount=5, needLogin=true)
	public Result<?> uploadSeller(FansSession fans,HttpServletRequest request,
			HttpServletResponse response){
		String shopName = request.getParameter("shopName"); //店铺名称
		String pcd = request.getParameter("pcd");  //省市区/县
		String address = request.getParameter("address"); //详细地址
		String latitude = request.getParameter("latitude"); //纬度
		String longitude = request.getParameter("longitude"); //经度
		String imgs = request.getParameter("imgs");  //图片名称
		ZyzsSmokerShareSellers shareSeller = new ZyzsSmokerShareSellers();
		shareSeller.setCreateDate(new Date());
		shareSeller.setIsDelete("0");
		shareSeller.setLatitude(new BigDecimal(latitude));
		shareSeller.setLongitude(new BigDecimal(longitude));
		shareSeller.setShop_imgs(imgs);
		shareSeller.setShopAddress(address);
		shareSeller.setShopName(shopName);
		shareSeller.setStatus("0");
		shareSeller.setThumbs(0);
		shareSeller.setUpdateDate(new Date());
		shareSeller.setZyzsId(fans.getZyzsId());
		logger.info(fans.getOpenid()+"======================分享的店铺保存");
		return shareService.saveShareShop(shareSeller);
	}
	/**
	 * 审核网友分享店铺
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/smoker/examine",method = RequestMethod.GET, produces = "application/json")
	public Result<?> examineShop(HttpServletRequest request,
			HttpServletResponse response){
		String shopId = request.getParameter("shopId");
		String stauts = request.getParameter("stauts");
		return shareService.examineShop(shopId, stauts);
	}
}
