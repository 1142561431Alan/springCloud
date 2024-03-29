package com.jt.dubbo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.dubbo.pojo.User;
import com.jt.dubbo.service.UserService;

@RestController
public class UserController {
	
	//springboot利用dubbo注解实例化接口对象
	//timeout 定义超时时间 
	//check = true 当服务器启动时检测是否有提供者
	//如果没有提供者.则程序启动报错
	/**
	 * randomLoad 默认随机发送
	 * roundRobin 在权重相同的条件
	 * 
	 * leastActive 挑选当前负载最少的服务器访问
	 * 
	 */
	
	@Reference(timeout=3000,check=false,loadbalance="leastActive")
	private UserService userService;
	
	@RequestMapping("/findAll")
	public List<User> findAll(){
		
		return userService.findAll();
	}
	
	@RequestMapping("/saveUser/{name}/{age}/{sex}")
	public String saveUser(User user) {
		
		userService.saveUser(user);
		return "用户入库成功!!!";
	}
}
