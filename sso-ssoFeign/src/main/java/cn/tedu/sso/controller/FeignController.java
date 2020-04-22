package cn.tedu.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.sso.ApiFeign.FeignApi;

@RestController
public class FeignController {
	@Autowired
	private FeignApi feignApi;
	
	@RequestMapping(path = "/init/{ticket}", method = RequestMethod.GET)
	public String _query(@PathVariable(name = "ticket") String t) {
		return feignApi.getProviderServerMethod(t);
	}
}
