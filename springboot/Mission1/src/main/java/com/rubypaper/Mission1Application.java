package com.rubypaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan({"edu.pnu.controller"}) 다른 패키지에서찾을때 @ComponentScan({"패키지경로"})
@SpringBootApplication
public class Mission1Application {

	public static void main(String[] args) {
		//SpringApplication.run(Chapter01Application.class, args);
		SpringApplication application =
				new SpringApplication( Mission1Application.class);
		//application.setWebApplicationType(WebApplicationType.NONE);  //NONE 일반자바로 구동하겟다
		application.setWebApplicationType(WebApplicationType.SERVLET); //SERVLET 내부톰캣으로 구동하겟다
		application.run(args);
		
		System.out.println("done");
	}

}
