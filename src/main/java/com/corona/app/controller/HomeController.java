package com.corona.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.corona.app.Services;
import com.corona.model.LocationStat;

@Controller
public class HomeController {
	
	private Services services;
	
	@Autowired
	public void setServices(Services services) {
		this.services = services;
	}


	@GetMapping("/")
    public String showHomePage(Model model) {
		List<LocationStat> allStats = services.getAllStats();	
		int presentCount = allStats.stream().mapToInt(p-> p.getInfectedCount()).sum();
		int prevCount = allStats.stream().mapToInt(p-> p.getPreviousInfectedCount()).sum();
		model.addAttribute("allStats",  allStats);
		model.addAttribute("presentCount", presentCount);
		model.addAttribute("deltaCount", presentCount - prevCount);
    	return "home";
	}
}
