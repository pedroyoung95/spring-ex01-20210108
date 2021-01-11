package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
