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
public class ForgotPasswordController {

	@Autowired
	UserService userService;
	/**
	 * This method is used to load forgot password form
	 * 
	 * @return String
	 */
	@GetMapping("/forgotPwd")
	public String loadForm() {
		return AppConstants.FORGOTPWD_VIEW_NAME;
	}

	/**
	 * This is method is used to handle forgot password screen submit btn
	 * 
	 * @param req
	 * @param model
	 * @return String
	 */
	@PostMapping("/forgotPwd")
	public String handleForgotPwdSubmtBtn(HttpServletRequest req, Model model) {
		String status = userService.recoverPassword(req.getParameter(AppConstants.EMAIL));
		if(status.equals(AppConstants.SUCCESS))
		{
			model.addAttribute(AppConstants.SUCCESSMSG, AppConstants.FORGOTPWD_SUCCESS_MSG);
		}
		else
		{
			model.addAttribute(AppConstants.FAILUREMSG, AppConstants.FORGOTPWD_FAILURE_MSG );
		}
		return AppConstants.FORGOTPWD_VIEW_NAME;
		
	}
}
