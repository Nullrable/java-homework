package com.lsd.modulework05;

import com.lsd.modulework05.work01.Clazz;
import com.lsd.modulework05.work01.School;
import com.lsd.modulework05.work01.Student;
import com.lsd.modulework05.work03.jdbc.HikariService;
import com.lsd.modulework05.work03.jdbc.JdbcService;
import com.lsd.modulework05.work03.jdbc.User;
import com.lsd.modulework05starter.service.HelloService;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lsd.modulework05.work01","com.lsd.modulework05.work03" })
public class ModuleWork05Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ModuleWork05Application.class, args);



		//=====work01 写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）========

		Student student = context.getBean(Student.class);
		System.out.println(context.getBean(Student.class));
		System.out.println("student name: " + student.getName());
		System.out.println("student old: " + student.getOld());

		School school = context.getBean(School.class);
		System.out.println("school name: " + school.getName());

		System.out.println(context.getBean(Clazz.class));

		//===================================================================================================




		//=====work02 给前面课程提供的 Student/Klass/School 实现自动配置和 Starter================================

		HelloService helloService = context.getBean(HelloService.class);
		System.out.println("helloService sayHello: " + helloService.sayHello());

		//===================================================================================================





		//=====work03 ======================================================================================
		System.out.println("work03 使用 JDBC 原生接口，实现数据库的增删改查操作。");
		//work03-01 使用 JDBC 原生接口，实现数据库的增删改查操作。
		JdbcService jdbcService = context.getBean(JdbcService.class);

		jdbcService.save();
		List<User> userList = jdbcService.find();
		if(userList != null  || userList.size() == 0){
			userList.forEach( user -> System.out.println("=====>" + user.getName()));
		}else{
			System.out.println("无数据");
		}

		jdbcService.update();
		userList = jdbcService.find();
		if(userList != null || userList.size() == 0 ){
			userList.forEach( user -> System.out.println("=====>" + user.getName()));
		}else{
			System.out.println("无数据");
		}


		jdbcService.delete();
		userList = jdbcService.find();
		if(userList != null || userList.size() == 0){
			userList.forEach( user -> System.out.println("=====>" + user.getName()));
		}else{
			System.out.println("无数据");
		}


		//work03-02 使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
		System.out.println("work03-02 使用事务，PrepareStatement 方式，批处理方式，改进上述操作。");
		jdbcService.testTx();


		//work03-03 配置 Hikari 连接池，改进上述操作。
		//===================================================================================================
		System.out.println("work03-03 配置 Hikari 连接池，改进上述操作。");
		HikariService hikariService = context.getBean(HikariService.class);
		hikariService.save();


	}





}
