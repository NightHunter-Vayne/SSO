package com.tedu.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.sso.pojo.User;
import com.tedu.sso.result.SysResult;
import com.tedu.sso.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * d 检测数据是否可用
	 * 
	 * @param param用户输入的参数
	 * @param type type为类型，可选参数1、2、3分别代表username、phone、email
	 * @return
	 */
	@RequestMapping(value="/check/{param}/{type}",method=RequestMethod.GET)
	public SysResult check(@PathVariable("param") String param,
			@PathVariable("type") Integer type) {
		return userService.check(param, type);
	}
	
	/**
	 * 2、用户注册
	 * 
	 * @param user
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/register/{userName}/{password}/{phone}/{email}/{createDate}/{updateDate}",
			method=RequestMethod.GET)
	public String InsertUser(User user) {
		try {
			userService.InsertUser(user);
			return "恭喜你，注册成功！";
		} catch (Exception e) {
			e.printStackTrace();
			return "对不起，注册失败！";
		}
		
	}
	
	/**
	 * 3、登录
	 * 
	 * @param userName
	 * @param passwd
	 * @return
	 */
	@RequestMapping(value="/login/{username}/{password}")
	public String Login(@PathVariable("username") String username, @PathVariable("password")String password) {
		String ticket = userService.Login(username, password);
		
		if(ticket != null) {
			return "恭喜你，登录成功！你的票是"+ticket;
		}else {
			return "对不起，登录失败";
		}
	}
	
	/**
	 * ticket查询
	 * 
	 * @param ticket
	 * @return
	 */
	@RequestMapping(value="/query/{ticket}")
	public String Query(@PathVariable("ticket") String ticket) {
		if(userService.Query(ticket)) {
			return "您的ticket有效，欢迎您！！";
		}else {
			return "您的ticket无效或已过期，请重新登录！！";
		}
	}
}
