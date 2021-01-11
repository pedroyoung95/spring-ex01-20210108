package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/return/*")
@Log4j
public class ReturnController {

	@RequestMapping("/ex1")
	public String method1() {
		//컨트롤러 리턴 타입이 String이면 뷰 jsp파일 명을 리턴해서 해당 뷰를 바로 띄움
		log.info("method1");
		return "returnView1";
	}
	
	@RequestMapping("/ex2")
	public String method2() {
		log.info("method2");
		//'redirect:' 으로 시작되는 String을 리턴하면 해당 이름의 RequestMapping되어있는
		//컨트롤러로 redirect됨(context root가 필요없음)
		return "redirect:/sample/";
	}
	
	@RequestMapping("/ex3")
	public @ResponseBody String method3() {
		//@ResponseBody -> 리턴 string값 자체가 응답 자체가 됨
		log.info("method3");
		return "returnValue3 hello world";
	}
	
	@RequestMapping("/ex4")
	public void method4() {
		//리턴 타입이 void인 경우, 요청경로가 그대로 jsp파일이름이 됨
		log.info("method4");
		
	}
	
	@RequestMapping("/ex5")
	public @ResponseBody Member method5() {
		log.info("method5");
		
		Member member = new Member();
		member.setName("donald");
		member.setAge(33);
		
//{"name":"donald", "age":33} -> JavaScript Object Notation(JSON)에 따른 객체 표기
//브라우저와 서버는 text형태로 데이터를 주고받음
//JSON이 text형식이기 때문에, 브라우저와 서버간 데이터를 주고받도록 저장하고 변환하는 역할
//java 객체 -> json 표기로 변환 -> javascript로 변환 가능 -> html에서 활용 가능
//위 과정의 반대도 가능
//즉, java객체와 javascript 간 변환을 가능하게 하는 것이 json
		
		return member;
	}
}
