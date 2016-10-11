package com.eritechno.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eritechno.web.model.ViewNames;

@Controller
public class DefaultController {

	@RequestMapping("/")
	public String home(Model model) {
		return ViewNames.HOME_PAGE;
	}

	@RequestMapping("/error")
	public String error() {
		return ViewNames.NOT_FOUND;
	}
}
