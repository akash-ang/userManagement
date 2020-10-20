package com.ashokit.domain;

import java.util.Date;

import lombok.Data;

@Data
public class UserAccount {
	private Integer userId;
	private String accountStatus;
	private Integer cityId;
	private Integer countryId;
	private Date createdDate;
	private Date dob;
	private String firstName;
	private Character gender;
	private Integer stateId;
	private Date updatedDate;
	private String userEmail;
	private String lastName;
	private String userPassword;
	private String userMobile;
}
