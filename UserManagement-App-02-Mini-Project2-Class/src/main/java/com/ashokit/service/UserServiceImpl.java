package com.ashokit.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.constants.AppConstants;
import com.ashokit.domain.UserAccount;
import com.ashokit.entities.CountryMasterEntity;
import com.ashokit.entities.UserAccountEntity;
import com.ashokit.persistencelayer.CityMasterRepo;
import com.ashokit.persistencelayer.CountryMasterRepo;
import com.ashokit.persistencelayer.StateMasterRepo;
import com.ashokit.persistencelayer.UserAccountRepo;
import com.ashokit.util.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private UserAccountRepo userRepo;

	@Autowired
	private CountryMasterRepo countryRepo;

	@Autowired
	private StateMasterRepo stateRepo;

	@Autowired
	private CityMasterRepo cityRepo;

	@Override
	public String loginCheck(String email, String pwd) {
		UserAccountEntity entity = userRepo.findByUserEmailAndUserPassword(email, pwd);
		if (entity == null) {
			return AppConstants.INVALID_CREDENTIALS;
		} else if (entity.getAccountStatus().equals(AppConstants.LOCKED)) {
			return AppConstants.ACCOUNT_LOCKED;
		} else {
			return AppConstants.VALID;
		}

	}

	@Override
	public Map<Integer, String> loadCountries() {
		Map<Integer, String> countriesMap = new HashMap<Integer, String>();
		List<CountryMasterEntity> countries = countryRepo.findAll();
		countries.forEach(entity -> {
			countriesMap.put(entity.getCountryId(), entity.getCountryName());
		});
		return countriesMap;
	}

	@Override
	public Map<Integer, String> loadStatesByCountryId(Integer countryId) {
		Map<Integer, String> statesMap = new HashMap<Integer, String>();
		stateRepo.findByCountryId(countryId).forEach(entity -> {
			statesMap.put(entity.getStateId(), entity.getStateName());
		});
		return statesMap;
	}

	@Override
	public Map<Integer, String> loadCitiesByStateId(Integer stateId) {
		Map<Integer, String> citiesMap = new HashMap<Integer, String>();
		cityRepo.findByStateId(stateId).forEach(entity -> {
			citiesMap.put(entity.getCityId(), entity.getCityName());
		});
		return citiesMap;
	}

	@Override
	public boolean isUniqueEmail(String email) {

		return userRepo.findByUserEmail(email) != null ? false : true;
	}

	@Override
	public String generateTempPwd() {

		String AlphaNumericString = AppConstants.ALPHA_NUMERIC_STRING;

		StringBuilder sb = new StringBuilder(6);

		for (int i = 0; i < 6; i++) {

			int index = (int) (AlphaNumericString.length() * Math.random());

			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();

	}

	@Override
	public boolean saveUserAccount(UserAccount userAccount) {
		userAccount.setAccountStatus(AppConstants.LOCKED);
		userAccount.setUserPassword(generateTempPwd());

		UserAccountEntity userEntity = new UserAccountEntity();
		BeanUtils.copyProperties(userAccount, userEntity);

		UserAccountEntity savedEntity = userRepo.save(userEntity);
		if (savedEntity != null) {

			return sendRegSuccEmail(userAccount.getUserEmail(), AppConstants.REG_SUCCES_EMAIL_SUBJECT,
					getRegSuccMailBody(userAccount));
		}
		return false;

	}

	@Override
	public String getRegSuccMailBody(UserAccount userAccount) {
		String mailBody = "";
		try {
			Path path = Paths.get("UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt");
			Stream<String> lines = Files.lines(path);
			List<String> replacedLines = lines.map(line -> line.replace(AppConstants.FNAME_EMAIL_BODY, userAccount.getFirstName())
					.replace(AppConstants.LNAME_EMAIL_BODY, userAccount.getLastName()).replace(AppConstants.TEMP_PWD_EMAIL_BODY, userAccount.getUserPassword())
					.replace(AppConstants.EMAIL_EMAIL_BODY, userAccount.getUserEmail())).collect(Collectors.toList());

			mailBody = String.join("", replacedLines);
			lines.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return mailBody;
	}

	@Override
	public boolean sendRegSuccEmail(String to, String subject, String body) {
		return emailUtils.sendEmail(to, subject, body);
	}

	@Override
	public boolean isTempPwdValid(String email, String tempPwd) {
		UserAccountEntity entity = userRepo.findByUserEmailAndUserPassword(email, tempPwd);

		return entity != null ? true : false;
	}

	@Override
	public boolean unlockAccount(String email, String password) {
		UserAccountEntity entity = userRepo.findByUserEmail(email);
		entity.setAccountStatus(AppConstants.UNLOCKED);
		entity.setUserPassword(password);

		UserAccountEntity savedEntity = userRepo.save(entity);
		return savedEntity.getUserId() != null ? true : false;
	}

	@Override
	public String recoverPassword(String email) {
		UserAccountEntity entity = userRepo.findByUserEmail(email);
		if (entity != null) {
			UserAccount userAccount = new UserAccount();
			BeanUtils.copyProperties(entity, userAccount);
			sendPwdToEmail(userAccount.getUserEmail(), AppConstants.PAZZWORD_RECOVERY_EMAIL_SUBJECT,
					getRecoverPwdEmailBody(userAccount));
			return AppConstants.SUCCESS;
		} else {
			return AppConstants.FAILED;
		}
	}

	@Override
	public String getRecoverPwdEmailBody(UserAccount userAccount) {

		String mailBody = "";
		try {
			Path path = Paths.get("FORGOT-PASSWORD-EMAIL-BODY-TEMPLATE.txt");
			Stream<String> lines = Files.lines(path);
			List<String> replacedLines = lines.map(line -> line.replace(AppConstants.FNAME_EMAIL_BODY, userAccount.getFirstName())
					.replace(AppConstants.LNAME_EMAIL_BODY, userAccount.getLastName()).replace(AppConstants.TEMP_PWD_EMAIL_BODY, userAccount.getUserPassword()))
					.collect(Collectors.toList());

			mailBody = String.join("", replacedLines);
			lines.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return mailBody;

	}

	@Override
	public String sendPwdToEmail(String to, String subject, String body) {
		emailUtils.sendEmail(to, subject, body);
		return null;
	}

}
