package zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

@EnableZuulProxy
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		DruidDataSourceAutoConfigure.class,
		DataSourceTransactionManagerAutoConfiguration.class
})
public class RunZuulApplication {
	public static void main(String[] args) {
		SpringApplication.run(RunZuulApplication.class, args);
	}
}