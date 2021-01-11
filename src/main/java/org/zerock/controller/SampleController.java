package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller 
//@Controller어노테이션 안에 @Component어노테이션이 포함되어있어서 bean객체 생성됨
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	@RequestMapping( method= {RequestMethod.GET, RequestMethod.POST})
	public void basic() {
		log.info("basic................");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get.................");
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name: " + name);
		log.info("age: " + age);
		return "ex02";
	}
	
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		log.info("array ids: " + Arrays.toString(ids));
		for(String s : ids) {
			log.info(s);
		}
		return "ex02Array";
	}
	
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids: " + ids);
		return "ex02List";
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("" + dto);
		return "ex01";
	}
	
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list);		
		return "ex02Bean";
	}
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//	}
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		//TodoDTO클래스의 필드에 @DateTimeFormat이 붙어있기 때문에 메소드에선 추가 작성X
		log.info("todo: " + todo);
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		return "/sample/ex04";
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06...............");
		SampleDTO dto = new SampleDTO();
		dto.setName("홍길동");
		dto.setAge(10);
		
		return dto;
	}
}
