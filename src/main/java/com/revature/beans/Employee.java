package com.revature.beans;

public class Employee {
	private int employeeId;
	private String login;
	private String credential;


	@Override
	public String toString() {
		return "Employee [id=" + employeeId + ", login=" + login + ", credential=" + credential + "]"; 
	}

	public Employee(int employeeId, String login, String credential){
		super();
		this.employeeId = employeeId;
		this.login = login;
		this.credential = credential;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPassword() {
					return credential;
	}
	public void setPassword() {
					this.credential = credential;
	}
	public void setEmployeeId() {
					this.employeeId = employeeId;
	}
	public int getEmployeeId() {
					return employeeId;
	}
	public String getLogin() {
					return login;
	}
	public void setLogin() {
					this.login = login;
	}
}
