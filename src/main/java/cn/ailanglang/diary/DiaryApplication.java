package cn.ailanglang.diary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author smileyan
 */
@SpringBootApplication
@EnableSwagger2
public class DiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiaryApplication.class, args);
	}

}
