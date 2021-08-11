package com.yakir.springboot.advanced.justgifit;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yakir.springboot.advanced.justgifit.utils.JustGifItProperties;

@SuppressWarnings("deprecation")
@SpringBootApplication
/*
(
		exclude = {JacksonAutoConfiguration.class, // we can exclude Jackson from loading using Spring as we don't use it in our app
				   JmxAutoConfiguration.class, // same for this
				   WebSocketServletAutoConfiguration.class} // same for this
)
*/
@EnableConfigurationProperties(JustGifItProperties.class)
public class JustGifItApplication {

//	@Value("${spring.servlet.multipart.location}/gif/")
//	private String gifLocation;
	
	@Inject
	private JustGifItProperties justGifItProperties;
	
	public static void main(String[] args) {
		SpringApplication.run(JustGifItApplication.class, args);
	}
	
	@PostConstruct
	private void init() {
//		File gifFolder = new File(gifLocation);
		if(!justGifItProperties.getGifLocation().exists()) {
			justGifItProperties.getGifLocation().mkdir();
		}
	}

	/* disabled due to newer version of Spring (the course is using an old Spring version with deprecated features)
	
	// disable other HTTP methods that Spring provides (not POST and not GET) as we don't use them
	@Bean
	public FilterRegistrationBean<HiddenHttpMethodFilter> deRegisterHiddenHttpMethodFilter(HiddenHttpMethodFilter filter) {
		FilterRegistrationBean<HiddenHttpMethodFilter> bean = new FilterRegistrationBean<>(filter);
		bean.setEnabled(false);
		return bean;
	}
	
	// disable HTTP PUT method as we don't use it
	@Bean
	public FilterRegistrationBean<HttpPutFormContentFilter> deRegisterHiddenHttpMethodFilter(HttpPutFormContentFilter filter) {
		FilterRegistrationBean<HttpPutFormContentFilter> bean = new FilterRegistrationBean<>(filter);
		bean.setEnabled(false);
		return bean;
	}
	
	// disable request/session beans as we don't use it
	@Bean
	public FilterRegistrationBean<RequestContextFilter> deRegisterRequestContextFilter(RequestContextFilter filter) {
		FilterRegistrationBean<RequestContextFilter> bean = new FilterRegistrationBean<>(filter);
		bean.setEnabled(false);
		return bean;
	}
	
	*/
	
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/gif/**").addResourceLocations("file:" + justGifItProperties.getGifLocation().getPath());
				super.addResourceHandlers(registry);
			}
		};
	}
}
