package com.revature;

import java.util.List;
import java.util.*;
import com.revature.beans.Employee;
import com.revature.dao.EmployeeDAO;
/**
 * Hello world!
 *
 */
public class App 
{
				public static void main( String[] args )
				{
												EmployeeDAO dao = new EmployeeDAO();
												Scanner input = new Scanner(System.in);
												boolean done = false;
												boolean loggedin = false;
												Employee currentEmployee = null;
												while(!done) {
																if(!loggedin) {
																				System.out.print("Username: ");
																				String username = input.nextLine();
																				System.out.print("Password: ");
																				String password = input.nextLine();
																				Employee login = dao.getEmployee(username, password);
																				if(login != null) {
                                                loggedin = true;
																								currentEmployee = login;
																								System.out.println("Logged in as " + currentEmployee);
																				}
																				else {
																								System.out.println("No such user with those credentials");
																								continue;
																				}
																}

																System.out.println("Options: submit reimbursement , manager view , quit");
																String line = input.nextLine();
																switch(line) {
																				case "submit reimbursement":
                                                System.out.print("Amount: ");
                                                int amount = Integer.parseInt(input.nextLine());
                                                dao.createReimbursement(currentEmployee.getEmployeeId(), amount);
																								break;
																				case "manager view":
																								dao.getManagerView();
																								break;
                                        case "quit":
                                                input.close();
                                                done = true;
                                                break;
																				default:
																								System.out.println("No such matching command");
																}
												}
                        input.close();
												//system.out.println(dao.getEmployee(2));
												//dao.feedEmployee(2,1,10);
												//System.out.println(dao.getEmployee(2));
				}
}
