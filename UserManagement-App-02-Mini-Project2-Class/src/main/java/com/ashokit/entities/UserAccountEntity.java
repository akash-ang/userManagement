package com.ashokit.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_ACCOUNTS")
public class UserAccountEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID", length = 10)
	private Integer userId;
	@Column(name = "ACC_STATUS")
	private String accountStatus;
	@Column(name = "CITY_ID", length = 10)
	private Integer cityId;
	@Column(name = "COUNTRY_ID", length = 10)
	private Integer countryId;
	@Column(name = "CREATED_DATE", length = 10)
	@CreationTimestamp
	private Date createdDate;
	@Column(name = "DOB", length = 10)
	private Date dob;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "GENDER", length = 255)
	private Character gender;
	@Column(name = "STATE_ID", length = 10)
	private Integer stateId;
	@UpdateTimestamp
	@Column(name = "UPDATED_DATE", length = 10)
	private Date updatedDate;
	@Column(name = "USER_EMAIL")
	private String userEmail;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "USER_PASSWORD")
	private String userPassword;
	@Column(name = "USER_MOBILE")
	private String userMobile;
}
