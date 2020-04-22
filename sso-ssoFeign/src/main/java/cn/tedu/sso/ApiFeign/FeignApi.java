package cn.tedu.sso.ApiFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "sso-provider")
@RequestMapping("/user")
public interface FeignApi {
	@RequestMapping(path = "/query/{ticket}", method = RequestMethod.GET)
	public String getProviderServerMethod(@PathVariable(name = "ticket") String t); 
}
