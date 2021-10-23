import java.util.*;

class NoFlightException  extends Exception  
{  
    public NoFlightException (String str)  
    {  
        super(str);  
    }  
}  

class NoReservationtException  extends Exception  
{  
    public NoReservationtException (String str)  
    {  
        super(str);  
    }  
} 


public class FlightReservationSystem {

	public static void main(String[] args) {
		BookingandReservationSystem bookingandReservationSystem = new BookingandReservationSystem();
		Customer customer = null;
		while (true) {
			Scanner in = new Scanner(System.in);
			System.out.println("Press c/C for Customer.");
			System.out.println("Press e/E to Exit.");
			char type = in.nextLine().charAt(0);		
			if(type == 'c' || type == 'C') {
				if(customer == null) {
					System.out.print("Enter your Name: ");
					String name = in.nextLine();
					System.out.print("Enter your Gender: ");
					String gender = in.nextLine();
					System.out.print("Enter your Address: ");
					String address = in.nextLine();
					System.out.print("Enter your PassportNo: ");
					String passno = in.nextLine();
					System.out.print("Enter your CardNo: ");
					String cardno = in.nextLine();
					System.out.print("Enter your Age: ");
					int age = in.nextInt();
					customer = new Customer(name, gender, address, passno, cardno, age);
					String str = customer.getName() + " " + customer.getGender() + " " + customer.getAddress() + " " + customer.getPassportNo() + " " + customer.getCardNo() + " " + customer.getAge() + "\r\n";
					if(bookingandReservationSystem.checkNewCustomer(customer))
						bookingandReservationSystem.writeToFile(str, "CustomerList.txt");
					System.out.println();
				}
				
				if(bookingandReservationSystem.Login(customer)) {
					System.out.println("\nPress 1 to search flights based on parameters.");
					System.out.println("Press 2 to reserve a flight.");
					System.out.println("Press 3 to modify a reservation (Seat Class).");
					System.out.println("Press 4 to modify a reservation (Flight).");
					System.out.println("Press 5 to cancel a reservation.");
					System.out.println("Press 6 to make booking.");
					System.out.println("Press 7 to print ticket (If booked).");
					System.out.println("Press 8 to Logout.");
					int choice = in.nextInt();
					if (choice == 1) {
						Scanner input = new Scanner(System.in);
						System.out.println("Enter search parameters. (Enter None for nothing)");
						System.out.print("Enter FlightNo: ");
						String fno = input.nextLine();
						if(fno.compareTo("None") == 0)
							fno = "";
						System.out.print("Enter Origin: ");
						String origin = input.nextLine();
						if(origin.compareTo("None") == 0)
							origin = "";
						System.out.print("Enter Destination: ");
						String destination = input.nextLine();
						if(destination.compareTo("None") == 0)
							destination = "";
						System.out.print("Enter Flight Date: ");
						String date = input.nextLine();
						if(date.compareTo("None") == 0)
							date = "";
						System.out.print("Enter Departure Time: ");
						String dtime = input.nextLine();
						if(dtime.compareTo("None") == 0)
							dtime = "";
						System.out.print("Enter Arrival Time: ");
						String atime = input.nextLine();
						if(atime.compareTo("None") == 0)
							atime = "";
						System.out.print("Enter Plane Model: ");
						String model = input.nextLine();
						if(model.compareTo("None") == 0)
							model = "";
						System.out.println("Press 1 for lowest fare and 0 for Not: ");
						boolean lfare;
						if(input.nextInt() == 1)
							lfare = true;
						else
							lfare = false;
						bookingandReservationSystem.flightCalendar.search(origin, destination, fno, date, dtime, atime, model, lfare);
						
					}
					else if (choice == 2) {
						Scanner input = new Scanner(System.in);
						System.out.print("Enter FlightNo to reserve: ");
						String fno = input.nextLine();
						System.out.print("Enter Flight Date: ");
						String date = input.nextLine();
						System.out.print("Enter Seat Class: ");
						String c = input.nextLine();
						try {
							bookingandReservationSystem.makeReservation(fno, date, c);
						} catch (NoFlightException e) {
							System.out.println(e);
						}
					}
					else if (choice == 3) {
						Scanner input = new Scanner(System.in);
						System.out.print("Enter Seat Class: ");
						String c = input.nextLine();
						System.out.print("Enter your Ticket PNR: ");
						int pnr = input.nextInt();
						try {
							bookingandReservationSystem.modifyReservationClass(c, pnr);
						} catch (NoReservationtException e) {
							System.out.println(e);
						}						
					}
					else if (choice == 4) {
						Scanner input = new Scanner(System.in);
						System.out.print("Enter FlightNo to reserve: ");
						String fno = input.nextLine();
						System.out.print("Enter Flight Date: ");
						String date = input.nextLine();
						System.out.print("Enter Seat Class: ");
						String c = input.nextLine();
						System.out.print("Enter your Ticket PNR: ");
						int pnr = input.nextInt();
						try {
							try {
								bookingandReservationSystem.modifyReservationFlight(fno, date, c, pnr);
							} catch (NoReservationtException e) {
								System.out.println(e);
							}
						} catch (NoFlightException e) {
							System.out.println(e);
						}
					}
					else if (choice == 5) {
						Scanner input = new Scanner(System.in);
						System.out.print("Enter your Ticket PNR: ");
						int pnr = input.nextInt();
						try {
							bookingandReservationSystem.cancelReservation(pnr);
						} catch (NoReservationtException e) {
							System.out.println(e);
						}
					}
					else if (choice == 6) {
						Scanner input = new Scanner(System.in);
						System.out.print("Enter FlightNo to reserve: ");
						String fno = input.nextLine();
						System.out.print("Enter Flight Date: ");
						String date = input.nextLine();
						System.out.print("Enter Seat Class: ");
						String c = input.nextLine();
						try {
							bookingandReservationSystem.makeBooking(fno, date, c);
						} catch (NoFlightException e) {
							System.out.println(e);
						}
					}
					else if (choice == 7) {
						Scanner input = new Scanner(System.in);
						System.out.print("Enter your Ticket PNR: ");
						int pnr = input.nextInt();
						bookingandReservationSystem.printTicket(pnr);
					}
					else if (choice == 8) {
						bookingandReservationSystem.Logout();
						customer = null;
					}
				}
				else {
					System.out.println();
					continue;
				}
				
			}
			else if(type == 'e' || type == 'E') {
				in.close();
				return;
			}
			else {
				System.out.println("Wrong Key!");
				System.out.println();
				continue;
			}
			System.out.println();
		}	
	}

}
