package com.cg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	@NotNull(message="UserId is Mandatory")
	private Long userId;
	
	@Column(name="user_name")
	@NotNull(message="Password is Mandatory")
	private String userName;
	
	@Column(name="full_name")
	@NotNull(message="Password is Mandatory")
	private String fullName;
	
	@Column(name="mobile_no")
	@NotNull(message="Mobile Number is Mandatory")
	private Long mobileNo;
	
	@Column(name="mail_id")
	@NotNull(message="Email is Mandatory")
	@Email
	private String mailId;
	
	@Column(name="gender")
	@NotNull(message="Gender is Mandatory")
	private String gender;
	
	@Column(name="age")
	@Positive(message="Age should be positive")
	private Integer age;
	
	@Column(name="password")
	@Size(min=8,max=15,message="Password should have atleast and maxium 15 characters")
	@NotNull(message="Password is Mandatory")
	private String password;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long userId, String userName, String fullName, Long mobileNo, String mailId, String gender, Integer age,
			String password) {
		super();
		this.userId = userId;
		this.userName=userName;
		this.fullName = fullName;
		this.mobileNo = mobileNo;
		this.mailId = mailId;
		this.gender = gender;
		this.age = age;
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", fullName=" + fullName + ", mobileNo=" + mobileNo
				+ ", mailId=" + mailId + ", gender=" + gender + ", age=" + age + ", password=" + password + "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	

}
