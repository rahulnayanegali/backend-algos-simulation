package com.java.cs505.project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class TestController {

	@RequestMapping("/test")
	public String testFunction() {
		return "Java project!";
	}
	
	
	
	
	@PostMapping("/test")
	public String testFunction(@RequestBody String abc)
	{
		return "post method";
	}
}



