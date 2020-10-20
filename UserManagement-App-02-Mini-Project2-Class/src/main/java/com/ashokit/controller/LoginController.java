package com.ashokit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashokit.constants.AppConstants;
import com.ashokit.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	/**
	 * This method is used to load login page
	 * 
	 * @return String
	 */
	@GetMapping({ "/", "/index" })
	public String index() {
		return AppConstants.INDEX_VIEW_NAME;
	}

	/**
	 * This method is used handle sign-in btn request
	 * 
	 * @param req
	 * @param model
	 * @return String
	 */
	@PostMapping("/signIn")
	public String handleSignInBtn(HttpServletRequest req, Model model) {

		String status = userService.loginCheck(req.getParameter(AppConstants.USEREMAIL),
				req.getParameter(AppConstants.USERPAZZWORD));

		if (status == AppConstants.VALID) {
			return AppConstants.DASHBOARD_VIEW_NAME;
		} else {
			model.addAttribute(AppConstants.FAILUREMSG, status);
			return AppConstants.INDEX_VIEW_NAME;
		}

	}
}
