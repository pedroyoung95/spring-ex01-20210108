package org.zerock.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.Book;
import org.zerock.domain.CustomBookEditor;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/paramex/*")
@Log4j
public class ParameterController {

	@RequestMapping("/ex1")
	public void method1(HttpServletRequest request) {
		log.info(request);
		log.info(request.getParameter("name"));
		log.info("method1");
		//request객체를 파라미터로 받아서 그대로 사용하는 방식으로 파라미터 수집
	}
	
	@RequestMapping("/ex2")
	public void method2(@RequestParam("name") String n) {
		//메소드의 파라미터에 @RequestParam어노테이션을 붙여서 파라미터 수집 가능
		log.info("method2");
		log.info(n);
	}
	
	@RequestMapping("/ex3")
	public void method3(@RequestParam String name) {
		//받을 파라미터와 메소드의 변수 이름이 같으면 파라미터 이름 생략 가능
		log.info("method3");
		log.info(name);
	}
	
	@RequestMapping("/ex4")
	public void method4(String name) {
		//받을 파라미터와 메소드의 변수 이름이 같으면 어노테이션도 생략 가능
		log.info("method4");
		log.info(name);
	}
	
	@RequestMapping("/ex5")
	public void method5(HttpServletRequest request) {
		log.info("method5");
		log.info(request.getParameterValues("check"));
		String[] list = request.getParameterValues("check");
		for(String s : list) {
			log.info(s);
		}
	}
	
	@RequestMapping("/ex6")
	public void method6(String[] check) {
		log.info("method6");
		for(String s : check) {
			log.info(s);
		}
	}
	
	@RequestMapping("/ex7")
	public void method7(@RequestParam("check") ArrayList<String> check) {
		//리스트는 파라미터와 이름이 같아도 @RequestParam 생략 불가
		log.info("method7");
		log.info(check);
		for(String c : check) {
			log.info(c);
		}
	}
	
	@RequestMapping("/ex8")
	public void method8(HttpServletRequest request) {
		String name = request.getParameter("name");
		String ageStr = request.getParameter("age");
		int age = Integer.parseInt(ageStr);
		
		Member member = new Member();
		member.setName(name);
		member.setAge(age);
		
		log.info("method8");
		log.info(member);
	}
	
	@RequestMapping("/ex9")
	public void method9(Member member) {
		//파라미터이름과 필드이름이 같은 걸 매칭해서 객체에 적절히 값을 자동으로 넣어줌
		//파라미터값을 받아서 객체에 set하는 코드가 필요X
		log.info("method9");
		log.info(member);
	}
	
	@InitBinder
	//@InitBinder어노테이션이 붙은 메소드가 다른 메소드보다 먼저 실행하게 됨
	//파라미터에 대한 사전 작업이 필요한 경우 이 어노테이션이 붙은 메소드에서 작업하면 됨
	public void initBinder1(WebDataBinder binder) {
		log.info("initbinder1");
		//requiredType은 propertyEditor를 사용
//		binder.registerCustomEditor(requiredType, propertyEditor);
		//파라미터 값을 Book클래스에 맞게 할당하기 위해 CustomBookEditor를 거치게 됨
		binder.registerCustomEditor(Book.class, new CustomBookEditor());
	}
	
	@RequestMapping("/ex10")
	public void method10(@RequestParam("book") Book book) {
		log.info("method10");
		log.info(book);
	}
	
}
