package com.bluebus.main;

import java.time.LocalDate;
//import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.bluebus.exception.BusNotFoundException;

import com.bluebus.model.Bluebus;
import com.bluebus.model.Category;
import com.bluebus.model.Type;
import com.bluebus.model.User;
import com.bluebus.service.BluebusServiceImpl;
import com.bluebus.service.IBluebusService;
import com.bluebus.service.IUserService;
import com.bluebus.service.UserServiceImpl;
import com.bluebus.util.PasswordGenerator;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("*****Welcome to Bluebus*****");
		int choise = 0;
		do {
			System.out.println("Press(1) if Admin\n(2) if user");
			int choose = sc.nextInt();
			sc.nextLine();
			IUserService userservice = new UserServiceImpl();
			
			if (choose == 1) {
				
				System.out.println("Enter Username: ");
				String username = sc.nextLine();
				System.out.println("Enter Password: ");
				String password = sc.nextLine();
				
				User user = userservice.login(username, password);
				
				if(user != null) {
					System.out.println("Welcome Admin!");
					System.out.println("");
					System.out.println("Admin Name: " + user.getUsername() + "\nName: " + user.getName() + "\nMobile Number: " + user.getMobile() + "\nEmail: "+user.getEmail()+"\nCity: "+user.getCity()+"\nUserId: "+user.getUserId());
					System.out.println("");
					System.out.println(
							"Press(1) to add bus\n(2) to update bus cost\n(3) to get bus by Bus Number\n(4) to delete bus by bus number");
					int select = sc.nextInt();
					IBluebusService service = new BluebusServiceImpl();
					switch (select) {
					case 1:

						LocalDateTime localstarttime;
						LocalDate localstartdate;

						System.out.println("Enter Bus Number:");
						int busnumber = sc.nextInt();
						sc.nextLine();

						System.out.println("Enter Bus Name:");
						String busName = sc.nextLine();

						System.out.println("Enter start Date in format(yyyy-MM-dd)");
						String startDate = sc.nextLine();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						localstartdate = LocalDate.parse(startDate, formatter);

						System.out.println("Press(0) if Sleeper\n(1) if Semi-Sleeper\n(2) if Seater");
						int categorychoice = sc.nextInt();
						sc.nextLine();
						String category = Category.values()[categorychoice].category;
						System.out.println("Press(0) if AC\n(1) if Non-AC");
						int typechoise = sc.nextInt();
						sc.nextLine();
						String type = Type.values()[typechoise].type;
						System.out.println("Enter cost:");
						double cost = sc.nextDouble();
						sc.nextLine();
						System.out.println("Enter Source:");
						String source = sc.nextLine();
						System.out.println("Enter Destination:");
						String destination = sc.nextLine();
						System.out.println("Enter Seats Available:");
						int seatsAvailable = sc.nextInt();
						sc.nextLine();

						System.out.println("Enter start time in format(yyyy-MM-dd HH:mm)");
						String startTime = sc.nextLine();
						DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
						localstarttime = LocalDateTime.parse(startTime, format);

						Bluebus bus = new Bluebus(busnumber, busName, localstartdate, category, type, cost, source,
								destination, seatsAvailable, localstarttime);
						service.addBus(bus);

						break;

					case 2:

						System.out.println("Enter Bus Number: ");
						int busNumber = sc.nextInt();
						sc.nextLine();
						System.out.println("Enter Cost to be updated: ");
						double costUpdate = sc.nextDouble();
						sc.nextLine();
						service.updateBus(busNumber, costUpdate);

						break;

					case 3:
						bus = null;
						System.out.println("Enter Bus Number: ");
						busNumber = sc.nextInt();
						sc.nextLine();
						try {
							bus = service.getByNumber(busNumber);
						} catch (BusNotFoundException e) {
							System.out.println(e.getMessage());
							;
						}
						if (bus != null) {
							System.out.println(
									"Bus Name: " + bus.getBusName() + "\nStart Date: " + bus.getStartDate() + "\nCategory: "
											+ bus.getCategory() + "\nType: " + bus.getType() + "\nCost: " + bus.getCost()
											+ "\nSource: " + bus.getSource() + "\nDestination: " + bus.getDestination()
											+ "\nSeats Available: " + bus.getSeatsAvailable() + "\nStart Time: "
											+ bus.getStartTime().getHour() + ":" + bus.getStartTime().getMinute());
						}

						break;

					case 4:
						System.out.println("Enter Bus Number: ");
						busNumber = sc.nextInt();
						sc.nextLine();
						service.deleteBus(busNumber);
						break;
					}
				} 
				
				else if(user == null) {
					System.out.println("Not an Admin\nPlease Try Again");
				}
				}

			
			
			else if (choose == 2) {
					
					System.out.println("Press(1) to register(if new user)\n(2) to login");
					int option =  sc.nextInt();
					sc.nextLine();
					if(option == 1) {
						User user = new User();
						PasswordGenerator password = new PasswordGenerator(); 
						System.out.println("Enter UserName: ");
						user.setUsername(sc.nextLine());
						System.out.println("Enter Name: ");
						user.setName(sc.nextLine());
						System.out.println("Enter Mobile Number: ");
						user.setMobile(Long.parseLong(sc.nextLine()));
						System.out.println("Enter Email: ");
						user.setEmail(sc.nextLine());
						System.out.println("Enter City: ");
						user.setCity(sc.nextLine());
						user.setPassword(password.generatePassword(8));
						
						String generatedpassword = userservice.register(user);
						if(generatedpassword != null) {
							System.out.println("Successfully Registered!");
							System.out.println("Your generated password is: "+generatedpassword);
							System.out.println("Press(1) to change password");
							choise = sc.nextInt();
							sc.nextLine();
							if(choise == 1) {
								System.out.println("Enter New Password: ");
								String newPassword = sc.nextLine();
								String oldPassword = generatedpassword;
								int change = userservice.changePassword(oldPassword, newPassword);
								if(change == 1) {
									System.out.println("Password Changed");
								}
							}
							
						}
					}
					
					else if(option == 2) {
						System.out.println("Enter Username: ");
						String username = sc.nextLine();
						System.out.println("Enter Password: ");
						String password = sc.nextLine();
						
						User user = userservice.login(username, password);
						
						if(user != null) {
							System.out.println("Welcome User!");
							System.out.println("");
							System.out.println("User Name: " + user.getUsername() + "\nName: " + user.getName() + "\nMobile Number: " + user.getMobile() + "\nEmail: "+user.getEmail()+"\nCity: "+user.getCity()+"\nUserId: "+user.getUserId());
							System.out.println("");
							
							System.out.println(
									"Press(1) to get all buses from source to destination\n(2) to get buses based on cost\n(3) to get buses based on category\n(4) to get buses based on type\n(5) to get buses based on start date");
							int select = sc.nextInt();
							sc.nextLine();
							IBluebusService service = new BluebusServiceImpl();
							switch (select) {
							case 1:
								System.out.println("Enter Source: ");
								String sourceName = sc.nextLine();
								System.out.println("Enter Destination: ");
								String destinationName = sc.nextLine();
								List<Bluebus> buses = service.getAll(sourceName, destinationName);
								for (Bluebus bluebus : buses) {
									System.out.println(bluebus);
								}
								break;

							case 2:
								System.out.println("Enter Source: ");
								sourceName = sc.nextLine();
								System.out.println("Enter Destination: ");
								destinationName = sc.nextLine();
								System.out.println("Enter Maximum cost:");
								double maxCost = sc.nextDouble();
								List<Bluebus> busesByCost = null;
								try {
									busesByCost = service.getByLessFare(sourceName, destinationName, maxCost);
								} catch (BusNotFoundException e) {
									System.out.println(e.getMessage());
								}
								if (busesByCost != null) {
									for (Bluebus bluebus : busesByCost) {
										System.out.println(bluebus);
									}
								}
								break;

							case 3:
								System.out.println("Enter Source: ");
								sourceName = sc.nextLine();
								System.out.println("Enter Destination: ");
								destinationName = sc.nextLine();
								System.out.println("Press(0) if Sleeper\n(1) if Semi-Sleeper\n(2) if Seater");
								int categorychoice = sc.nextInt();
								sc.nextLine();
								String category = Category.values()[categorychoice].category;
								List<Bluebus> busesByCategory = null;
								try {
									busesByCategory = service.getbyCategory(sourceName, destinationName, category);
								} catch (BusNotFoundException e) {

									System.out.println(e.getMessage());
								}
								if (busesByCategory != null)
									for (Bluebus bluebus : busesByCategory) {
										System.out.println(bluebus);
									}
								break;

							case 4:
								System.out.println("Enter Source: ");
								sourceName = sc.nextLine();
								System.out.println("Enter Destination: ");
								destinationName = sc.nextLine();
								System.out.println("Press(0) if AC\n(1) if Non-AC");
								int typechoise = sc.nextInt();
								sc.nextLine();
								String type = Type.values()[typechoise].type;
								List<Bluebus> busesByType = null;
								try {
									busesByType = service.getbyType(sourceName, destinationName, type);
								} catch (BusNotFoundException e) {

									System.out.println(e.getMessage());
								}
								if (busesByType != null) {
									for (Bluebus bluebus : busesByType) {
										System.out.println(bluebus);
									}
								}
								break;

							case 5:

								System.out.println("Enter Source: ");
								sourceName = sc.nextLine();
								System.out.println("Enter Destination: ");
								destinationName = sc.nextLine();

								LocalDateTime localstarttime;
								System.out.println("Enter start time in format(yyyy-MM-dd HH:mm)");
								String startTime = sc.nextLine();
								DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
								localstarttime = LocalDateTime.parse(startTime, format);
								List<Bluebus> busesByTime = null;
								try {
									busesByTime = service.getbyStartTime(sourceName, destinationName, localstarttime);
								} catch (BusNotFoundException e) {

									System.out.println(e.getMessage());
								}
								if (busesByTime != null) {
									for (Bluebus bluebus : busesByTime) {
										System.out.println(bluebus);
									}
								}
								break;

							}
						}
						}
					}

						System.out.println("Press(1) to continue:");
						choise = sc.nextInt();
						sc.nextLine();

					} while (choise == 1);
					sc.close();
					System.out.println("Thank You");

				

							
						
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
		}	
}
