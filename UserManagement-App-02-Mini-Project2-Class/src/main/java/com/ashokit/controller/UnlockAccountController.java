package com.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.constants.AppConstants;
import com.ashokit.domain.UnlockAccount;
import com.ashokit.service.UserService;

@Controller
public class UnlockAccountController {

	@Autowired
	private UserService userService;
	/**
	 * This method is used load unlock-account form
	 * 
	 * @param email
	 * @param model
	 * @return String
	 */
	@GetMapping("/loadUnlockAccForm")
	public String loadUnlockAccForm(@RequestParam(AppConstants.EMAIL) String email, Model model) {
		UnlockAccount unlockAcc = new UnlockAccount();
		unlockAcc.setEmail(email);
		model.addAttribute(AppConstants.UNLOCKACC, unlockAcc);
		return AppConstants.UNLOCKACC_VIEW_NAME;
	}

	/**
	 * This method is used to handle unlock-account form submission
	 * 
	 * @param unlockAcc
	 * @param model
	 * @return String
	 */
	@PostMapping("/unlockAccount")
	public String handleSubmitBtn(@ModelAttribute(AppConstants.UNLOCKACC) UnlockAccount unlockAcc, Model model) {
		boolean isTempPwdValid = userService.isTempPwdValid(unlockAcc.getEmail(), unlockAcc.getTempPwd());
		if(isTempPwdValid == true)
		{
			userService.unlockAccount(unlockAcc.getEmail(), unlockAcc.getConfirmPwd());
			model.addAttribute(AppConstants.SUCCESSMSG, AppConstants.UNLOCKACC_SUCCESS_MSG);
		}
		else 
		{
			model.addAttribute(AppConstants.FAILUREMSG, AppConstants.UNLOCKACC_FAILURE_MSG);
		}
		
		return AppConstants.UNLOCKACC_VIEW_NAME;
	}

}