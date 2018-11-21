package kr.geun.o.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Mvc 관련 설정
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
				.allowedOrigins("*")
				.allowedMethods("*")
			.allowCredentials(false)
			.maxAge(3600);
		//@formatter:on
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

	}
}
