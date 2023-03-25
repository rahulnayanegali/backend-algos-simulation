package com.java.cs505.project.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.cs505.project.Service.NumberSortingImpl;

@Controller
@ResponseBody
public class NumberSortingController {
	
	@Autowired
	private NumberSortingImpl numberSorting;

	@RequestMapping("/numberSorting")
	public String testFunction() {
		return "Java project!";
	}
	
	
	
	
	@PostMapping("/numberSorting")
	public ArrayList<String> sortingOperation(@RequestBody String sortingType, ArrayList<String> list)
	{		
		return numberSorting.sortingOperation(sortingType, list);
	}
}



