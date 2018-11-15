package kr.geun.o.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 *
 * @author akageun
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//@formatter:off
		registry
			.addMapping("/api/**")
				.allowedOrigins("http://localhost:9907")
				.allowedMethods("*")
			.allowCredentials(false)
			.maxAge(3600);
		//@formatter:on
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

	}
}
