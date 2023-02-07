package com.mysiteforme.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.base.MySysUser;
import com.mysiteforme.admin.entity.*;
import com.mysiteforme.admin.entity.Menu;
import com.mysiteforme.admin.realm.AuthRealm;
import com.mysiteforme.admin.service.*;
import com.mysiteforme.admin.util.Constants;
import com.mysiteforme.admin.util.RestResponse;
import com.mysiteforme.admin.util.ToolUtil;
import com.mysiteforme.admin.util.VerifyCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Controller
public class LoginController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Value("${server.port}")
	private String port;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategorySecService categorySecService;

	@Autowired
	private VegetableService vegetableService;

	@Autowired
	private VegetableSellService vegetableSellService;

	@Autowired
	private BlogService blogService;

	@Autowired
	private CartService cartService;
	@GetMapping("login")
	public String login(HttpServletRequest request,String error,Model model) {
		model.addAttribute("error",error);
		return "/shop/login";
	}
	
	@PostMapping("doLogin")
	@SysLog("用户登录")
	public String doLogin(HttpServletRequest request,Model model,RedirectAttributes attr) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		if(StringUtils.isEmpty(rememberMe)){
			rememberMe="false";
		}else {
			rememberMe="true";
		}
		String error = null;
//		String code = request.getParameter("code");
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			error = "用户名或者密码不能为空";
			model.addAttribute("error",error);
			return "shop/login";

		}
		Map<String,Object> map = Maps.newHashMap();
		HttpSession session = request.getSession();
		if(session == null){
			error = "session超时";
			model.addAttribute("error",error);
			return "shop/login";
		}


		/*就是代表当前的用户。*/
		Subject user = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,password,Boolean.valueOf(rememberMe));
		try {
			user.login(token);
			if (user.isAuthenticated()) {
				map.put("url","index");
			}
		}catch (IncorrectCredentialsException e) {
			error = "登录密码错误.";
		} catch (ExcessiveAttemptsException e) {
			error = "登录失败次数过多";
		} catch (LockedAccountException e) {
			error = "帐号已被锁定.";
		} catch (DisabledAccountException e) {
			error = "帐号已被禁用.";
		} catch (ExpiredCredentialsException e) {
			error = "帐号已过期.";
		} catch (UnknownAccountException e) {
			error = "帐号不存在";
		} catch (UnauthorizedException e) {
			error = "您没有得到相应的授权！";
		}
		model.addAttribute("error",error);
		if(StringUtils.isNotEmpty(error)){
			attr.addAttribute("error",error);
//			return "redirect:shop/login?error="+error;
			return "shop/login";
		}else{
			return "redirect:/index";
		}
	}
	
	@GetMapping("index")
	public String showView(Model model,HttpServletRequest request){
		JSONArray array = new JSONArray();
		List<Category> categoryList = categoryService.selectList(new EntityWrapper<>());
		for (int i = 0; i < categoryList.size(); i++) {
			Category category = categoryList.get(i);
			JSONObject object = new JSONObject();
			object.put("name",category.getName());
			object.put("id",category.getId());

			Wrapper<CategorySec> wrapper = new EntityWrapper<>();
			wrapper.eq("cid",category.getId());
			List<CategorySec> categorySecs = categorySecService.selectList(wrapper);
			if(CollectionUtils.isEmpty(categoryList)){
				object.put("categorySecs",null);
			}else {
				object.put("categorySecs",categorySecs);
			}
			array.add(object);
		}
		model.addAttribute("categoryList",array);
		LOGGER.info("array:{}", JSON.toJSONString(array));

		//购物车
		Object principal = SecurityUtils.getSubject().getPrincipal();
		EntityWrapper<Cart> w = new EntityWrapper<>();
		if(null != principal){
			w.eq("customer_id",String.valueOf(MySysUser.id()));
		}else {
			w.eq("customer_id","####");
		}
		List<Cart> carts = cartService.selectList(w);
		int sum = carts.stream().mapToInt(x -> (Integer.valueOf(x.getVcount())) * x.getVprice().intValue()).sum();
		model.addAttribute("carts",carts);
		model.addAttribute("sum",sum);

		AuthRealm.ShiroUser user1 = (AuthRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if(null != user1){
			User user = userService.findUserById(user1.id);
			if(user != null){
				model.addAttribute("currentUser",user);
			}
		}

		EntityWrapper<Vegetable> wrapper = new EntityWrapper<>();
		//人气菜品
		List<String> rvids = vegetableSellService.getViewList();
		wrapper.in("id",rvids);
		List<Vegetable> rvegetables = vegetableService.selectList(wrapper);
		model.addAttribute("hotVegetables",rvegetables);

		//畅销蔬菜
		List<String> svid = vegetableSellService.getSellList();
		EntityWrapper<Vegetable> ws = new EntityWrapper<>();
		ws.in("id",svid);
		List<Vegetable> svegetables = vegetableService.selectList(ws);
		model.addAttribute("sellVegetables",svegetables);

		//文章
		EntityWrapper<Blog> wrapper1 = new EntityWrapper<>();
		List<Blog> blogs = blogService.selectList(wrapper1);
		model.addAttribute("blogs",blogs);

		return "/shop/index";
	}

	@GetMapping("home")
	private String home(){
		return "shop/index";
	}

	/**
	 * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
	 */
	@GetMapping("/genCaptcha")
	public void genCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 4, null);
		//将验证码放到HttpSession里面
		request.getSession().setAttribute(Constants.VALIDATE_CODE, verifyCode);
		LOGGER.info("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
		//设置输出的内容的类型为JPEG图像
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 116, 36, 5, true, new Color(249,205,173), null, null);
		//写给浏览器
		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
	}

	@GetMapping("main")
	public String main(Model model){
		Map map = userService.selectUserMenuCount();
		User user = userService.findUserById(MySysUser.id());
		Set<Menu> menus = user.getMenus();
		java.util.List<Menu> showMenus = Lists.newArrayList();
		if(menus != null && menus.size()>0){
			for (Menu menu : menus){
				if(StringUtils.isNotBlank(menu.getHref())){
					Long result = (Long)map.get(menu.getPermission());
					if(result != null){
						menu.setDataCount(result.intValue());
						showMenus.add(menu);
					}
				}
			}
		}
		showMenus.sort(new MenuComparator());
		model.addAttribute("userMenu",showMenus);
		return "main";
	}

	/**
	 *  空地址请求
	 * @return
	 */
	@GetMapping(value = "")
	public String index() {
		LOGGER.info("这事空地址在请求路径");
		Subject s = SecurityUtils.getSubject();
		return s.isAuthenticated() ? "redirect:index" : "login";
	}

	@GetMapping("systemLogout")
	@SysLog("退出系统")
	public String logOut(){
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}

	/**
	 * 注册
	 * @param user
	 * @return
	 */
	@PostMapping("doRegister")
	@ResponseBody
	@Transactional(readOnly = false)
	public RestResponse doRegister(User user){
		if(StringUtils.isNotBlank(user.getEmail())){
			if(userService.userCount(user.getEmail())>0){
				return RestResponse.failure("该邮箱已被使用");
			}
		}
		if(StringUtils.isNoneBlank(user.getTel())){
			if(userService.userCount(user.getTel())>0){
				return RestResponse.failure("该手机号已被绑定");
			}
		}
		try {
			ToolUtil.entryptPassword(user);
			userService.insert(user);
			User userByLoginName = userService.findUserByLoginName(user.getLoginName());
			// 这里需要设置一个默认角色id TODO
			roleService.addRole(user.getId());
		}catch (Exception e){
			LOGGER.error("e:{}",e);
		}
		return RestResponse.success();
	}
}



class MenuComparator implements Comparator<Menu>{

	@Override
	public int compare(Menu o1, Menu o2) {
		if(o1.getSort()>o2.getSort()){
			return -1;
		}else {
			return 0;
		}

	}
}