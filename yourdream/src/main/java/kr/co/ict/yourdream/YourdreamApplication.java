package kr.co.ict.yourdream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YourdreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(YourdreamApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer crosConfigurer() {
		return new WebMvcConfigurer() {
			// 마우스 우클릭 소스작업.. 클릭
			// 오버라이딩 있음
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				System.out.println("Test=======");
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedHeaders("*")
						.allowedMethods("*").maxAge(3600);
			}
		};
	}
}
