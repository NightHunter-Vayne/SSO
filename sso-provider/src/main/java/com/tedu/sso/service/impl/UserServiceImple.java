package com.tedu.sso.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.sso.mapper.UserMapper;
import com.tedu.sso.pojo.User;
import com.tedu.sso.result.SysResult;
import com.tedu.sso.service.UserService;

import redis.clients.jedis.Jedis;

@Service
public class UserServiceImple implements UserService {
	//存放查询类型
	private static final Map<Integer, String> PARAM_TYPE = 
			new HashMap<Integer, String>();
	
	//初始化
	static {
		PARAM_TYPE.put(1, "username");
		PARAM_TYPE.put(2, "phone");
		PARAM_TYPE.put(3, "email");
	}
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private Jedis jedis;
	
	//1、根据type=1:username,2:phone,3:email
	@Override
	public SysResult check(String param, Integer type) {
		int i = userMapper.check(PARAM_TYPE.get(type), param);
		if(0 == i) {
			return SysResult.ok("false");//数据库没有，可用
		}else {
			return SysResult.build(201, "OK", "true");//201:数据库
			//值已经存在 不可用
		}
	}

	//2、添加用户信息
	@Override
	@Transactional
	public void InsertUser(User user) {
		int i1 = userMapper.check(PARAM_TYPE.get(1), user.getUserName());
		int i2 = userMapper.check(PARAM_TYPE.get(2), user.getPhone());
		int i3 = userMapper.check(PARAM_TYPE.get(3), user.getEmail());
		
		if((i1 == 0)&&(i2 == 0)&&(i3 == 0)) {
			userMapper.InsertUser(user);
		}
	}

	@Override
	public String Login(String username, String password) {
		String ticket;
		int i = userMapper.Login(username, password);
		if(1 == i) {
			ticket = username + System.currentTimeMillis();
			saveInfoToRedis(ticket, username);
			return ticket;
		}else {
			return null;
		}
	}

	private void saveInfoToRedis(String key, String userName) {
		User u = userMapper.Query(userName);
		
		jedis.hset(key, "id", String.valueOf(u.getId()));
		jedis.hset(key, "username", u.getUserName());
		jedis.hset(key, "password", u.getPassword());
		jedis.hset(key, "phone", u.getPhone());
		jedis.hset(key, "email", u.getEmail());
	}

	@Override
	public boolean Query(String ticket) {
		String username = jedis.hget(ticket, "username");
		String password = jedis.hget(ticket, "password");
		
		User u = userMapper.Query(username);
		String passwd = u.getPassword();
		
		if(passwd.equals(password)) {
			return true;
		}else
			return false;
	}
}