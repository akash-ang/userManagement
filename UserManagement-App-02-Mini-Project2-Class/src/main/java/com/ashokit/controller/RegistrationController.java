package com.ashokit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ashokit.constants.AppConstants;
import com.ashokit.domain.UserAccount;
import com.ashokit.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;

	@ModelAttribute
	public void loadFormData(Model model) {

		model.addAttribute(AppConstants.USERACC, new UserAccount());

		model.addAttribute(AppConstants.COUNTRIES, userService.loadCountries());
	}

	@GetMapping("/register")
	public String loadRegForm(Model model) {
		return AppConstants.REGISTRATION_VIEW_NAME;
	}

	@GetMapping("/uniqueMailCheck")
	public @ResponseBody String isEmailUnique(@RequestParam(AppConstants.EMAIL) String email) {

		System.out.println(email);
		return userService.isUniqueEmail(email) ? AppConstants.UNIQUE : AppConstants.DUPLICATE;

	}

	@GetMapping("/countryChange")
	public @ResponseBody Map<Integer, String> handleCountryChangeEvnt(@RequestParam(AppConstants.COUNTRYID) Integer countryId) {
		return userService.loadStatesByCountryId(countryId);
	}

	@GetMapping("/stateChange")
	public @ResponseBody Map<Integer, String> handleStateChangeEvnt(@RequestParam(AppConstants.STATEID) Integer stateId) {
		return userService.loadCitiesByStateId(stateId);
	}

	@PostMapping("/userRegistration")
	public String handleRegisterBtn(UserAccount userAcc, Model model) {
		if (userService.saveUserAccount(userAcc)) {
			model.addAttribute(AppConstants.SUCCESSMSG, AppConstants.REGISTRATION_SUCCESS_MSG);
		} else {
			model.addAttribute(AppConstants.FAILUREMSG, AppConstants.REGISTRATION_FAILED_MSG);
		}
		return AppConstants.REGISTRATION_VIEW_NAME;
	}
}
