package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDAO implements EmployeeDAOInterface{
	
	public List<Employee> getAllEmployees() {
		PreparedStatement ps = null;
		Employee e = null;
		List<Employee> employees = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE";
			ps = conn.prepareStatement(sql);
			//Add any variables to PS
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int employeeId = rs.getInt("Employee_Id");
				String login = rs.getString("login");
				String credential = rs.getString("credential");
				e = new Employee(employeeId, login, credential);
				employees.add(e);
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return employees;
	}
	
	public Employee getEmployee(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee e = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				int employeeId = rs.getInt("EMPLOYEE_ID");
				String login = rs.getString("login");
				String credential = rs.getString("credential");
				
				e = new Employee(employeeId, login, credential);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		}
		return e;
	}
				public Employee getEmployee(String login, String credential) {

								PreparedStatement ps = null;
								ResultSet rs = null;
								Employee e = null;
								try(Connection conn = ConnectionUtil.getConnection()) {
								String sql = "SELECT * FROM EMPLOYEE WHERE login = ? AND credential = ?";
								ps = conn.prepareStatement(sql);
								ps.setString(1, login);
								ps.setString(2, credential);
			
								rs = ps.executeQuery();
								while (rs.next()) {
												int employeeId = rs.getInt("EMPLOYEE_ID");
				
												e = new Employee(employeeId, login, credential);
								}
								} catch (Exception ex) {
								ex.printStackTrace();
								} finally {
												if (ps != null) {
																try {
																ps.close();
																} catch (SQLException ex) {
																// TODO Auto-generated catch block
																ex.printStackTrace();
																}
												}
												if (rs != null) {
																try {
																rs.close();
																} catch (SQLException ex) {
																// TODO Auto-generated catch block
																ex.printStackTrace();
																}
												}
								}
								return e;
				}
	
				public void getManagerView() {
								PreparedStatement ps = null;
								ResultSet rs = null;
								try(Connection conn = ConnectionUtil.getConnection()) {
												String sql = "SELECT * FROM VW_MANAGER_VIEW";
												ps = conn.prepareStatement(sql);
								
												rs = ps.executeQuery();
												while(rs.next()) {
																int employeeId = rs.getInt("E_ID");
																int reimbursementId = rs.getInt("R_ID");
																int amount = rs.getInt("amount");
																System.out.println("E_ID: " + employeeId + ", R_ID: " + reimbursementId + ", amount: " + amount);
												}
								} catch (Exception ex) {
												ex.printStackTrace();
								} finally {
												if(ps != null) {
																try {
																				ps.close();
																} catch (SQLException ex) {
																				ex.printStackTrace();
																}
												}
								}
				}

												
	public void createReimbursement(int employeeId, int amount) {
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "{CALL SP_CREATE_REIMBURSEMENT(?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, employeeId);
			cs.setInt(2, amount);
			
			Boolean result = cs.execute();
			if (!result)
				System.out.println("Reimbursement created for current employee with amount " + amount);
			else
				System.out.println("Failed");
			
			cs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
