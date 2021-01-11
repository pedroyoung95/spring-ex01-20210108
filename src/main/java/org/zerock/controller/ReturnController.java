package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
