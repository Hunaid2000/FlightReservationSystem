import java.io.*;
import java.util.*;

public class BookingandReservationSystem{
	FlightCalendar flightCalendar;
	Customer loginedCustomer;
	
	public BookingandReservationSystem() {
		flightCalendar = new FlightCalendar();
		flightCalendar.readFile();
	}
	
	public boolean Login(Customer cust) {
		loginedCustomer = cust;
		if(loginedCustomer != null) {
			System.out.println("Login Successful");
			return true;
		}
		else {
			System.out.println("Login Unsuccessful");
			return false;
		}			
	}
	
	public void Logout() {
		if(loginedCustomer != null) {
			loginedCustomer = null;
			System.out.println("You are now Logout.");
		}			
		else
			System.out.println("Already Logout.");
	}
	
	public void removeFromFile(String rem, String fname) {
		try {
			File tempFile = new File("temp.txt");
			BufferedReader r = new BufferedReader(new FileReader(new File(fname)));
			BufferedWriter w = new BufferedWriter(new FileWriter(tempFile));
			String line;
			while((line = r.readLine()) != null) {
			    if(line.trim().equals(rem)) 
			    	continue;
			    w.write(line + System.getProperty("line.separator"));
			}
			w.close(); 
			r.close();
			FileInputStream in = new FileInputStream("temp.txt");
			FileWriter outFile = new FileWriter(new File(fname));
	        Scanner input = new Scanner(in); 
	        while (input.hasNextLine()) {
	        	String str = input.nextLine();
	        	outFile.write(str + "\r\n");
	        } 
	        input.close();
	        in.close();
	        outFile.close();
	        tempFile.delete();			
		} catch (Exception e) {}		
	}
	
	public void writeToFile(String towrite, String fname) {
		try {
			FileWriter outFile = new FileWriter(new File(fname), true);
			outFile.write(towrite);
			outFile.close();
		} catch (IOException e) {}
	}
	
	public int findFlight(int pnr) {
		for(int i=0;i<flightCalendar.flights.size();i++) {
			for(int j=0;j<flightCalendar.flights.elementAt(i).getTickets().size();j++)
				if(flightCalendar.flights.elementAt(i).getTickets().elementAt(j).getCustomer().getPassportNo().compareTo(loginedCustomer.getPassportNo()) == 0 && flightCalendar.flights.elementAt(i).getTickets().elementAt(j).getPNR() == pnr)
					return i;
		}
		return -1;
	}
	
	public int findFlight(String fno, String date) {
		for(int i=0;i<flightCalendar.flights.size();i++) {
			if(flightCalendar.flights.elementAt(i).getFlightNo().compareTo(fno) == 0 && flightCalendar.flights.elementAt(i).getDateTime().getDeptDate().compareTo(date) == 0)
				return i;
		}
		return -1;
	}
	
	public boolean checkNewCustomer(Customer customer) {
		try{  
	        FileInputStream in = new FileInputStream("CustomerList.txt");       
	        Scanner input = new Scanner(in);
	        String str = customer.getName() + " " + customer.getGender() + " " + customer.getAddress() + " " + customer.getPassportNo() + " " + customer.getCardNo() + " " + customer.getAge();
            while (input.hasNextLine()) {
            	if(str.compareTo(input.nextLine()) == 0) {
            		input.close();    
        	        in.close();
            		return false;
            	}
            } 
	        input.close();    
	        in.close();
	    }  
	    catch(IOException e)  
	    {  
	        e.printStackTrace();  
	    }
		return true;
	}
	
	public void makeReservation(String fno, String date, String c) throws NoFlightException {
		int flightindex = findFlight(fno, date);
		if(flightindex != -1) {
			Ticket ticket = new Ticket(loginedCustomer, c, false, true, flightCalendar.flights.elementAt(flightindex));
			flightCalendar.flights.elementAt(flightindex).getTickets().add(ticket);
			String str = loginedCustomer.getName() + " " + loginedCustomer.getPassportNo() + " " + ticket.getPNR() + "\r\n";
			writeToFile(str, "ReservationList.txt");
			flightCalendar.flights.elementAt(flightindex).getPlane().setAvaliableSeats(flightCalendar.flights.elementAt(flightindex).getPlane().getAvaliableSeats() - 1);
			System.out.println("Your ticket is now Reserved. PNR is: " + ticket.getPNR());
		}		
		else
			throw new NoFlightException("Flight not found.");
	}
	
	public void modifyReservationClass(String c, int pnr) throws NoReservationtException {
		int flightindex = findFlight(pnr);
		if(flightindex != -1) {
			for(int i=0;i<flightCalendar.flights.elementAt(flightindex).getTickets().size();i++) {
				if(flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getCustomer().getPassportNo().compareTo(loginedCustomer.getPassportNo()) == 0 && flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getSeat().getIsReserved() == true && flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getPNR() == pnr) {
					flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getSeat().setClass(c);
					System.out.println("Your Seat is now changed to " + c + ". PNR is: " + flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getPNR());
					return;
				}			
			}
		}
		else
			throw new NoReservationtException("You have no Reservation.");
	}
	
	public void modifyReservationFlight(String fno, String date, String c, int pnr) throws NoFlightException, NoReservationtException {
		int flightindex1 = findFlight(fno, date);
		int flightindex2 = findFlight(pnr);
		if(flightindex1 == -1) {
			throw new NoFlightException("Flight not found.");
		}
		if(flightindex2 == -1) {
			throw new NoReservationtException("You have no Reservation.");
		}
		else {
			for(int i=0;i<flightCalendar.flights.elementAt(flightindex2).getTickets().size();i++) {
				if(flightCalendar.flights.elementAt(flightindex2).getTickets().elementAt(i).getCustomer().getPassportNo().compareTo(loginedCustomer.getPassportNo()) == 0 && flightCalendar.flights.elementAt(flightindex2).getTickets().elementAt(i).getSeat().getIsReserved() == true && flightCalendar.flights.elementAt(flightindex2).getTickets().elementAt(i).getPNR() == pnr) {
					Ticket ticket = flightCalendar.flights.elementAt(flightindex2).getTickets().elementAt(i);
					flightCalendar.flights.elementAt(flightindex1).getTickets().add(ticket);
					flightCalendar.flights.elementAt(flightindex1).getTickets().elementAt(i).getSeat().setClass(c);
					flightCalendar.flights.elementAt(flightindex1).getPlane().setAvaliableSeats(flightCalendar.flights.elementAt(flightindex1).getPlane().getAvaliableSeats() - 1);					
					flightCalendar.flights.elementAt(flightindex2).getPlane().setAvaliableSeats(flightCalendar.flights.elementAt(flightindex2).getPlane().getAvaliableSeats() + 1);
					flightCalendar.flights.elementAt(flightindex2).getTickets().remove(i);
					System.out.println("Your Flight is now changed. PNR is: " + flightCalendar.flights.elementAt(flightindex1).getTickets().elementAt(i).getPNR());
					break;
				}			
			}
		}		
	}
	
	public void cancelReservation(int pnr) throws NoReservationtException {
		int flightindex = findFlight(pnr);
		if(flightindex != -1) {
			for(int i=0;i<flightCalendar.flights.elementAt(flightindex).getTickets().size();i++) {
				if(flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getCustomer().getPassportNo().compareTo(loginedCustomer.getPassportNo()) == 0 && flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getSeat().getIsReserved() == true && flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getPNR() == pnr) {
					flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getSeat().setIsReserved(false);
					String rem = loginedCustomer.getName() + " " + loginedCustomer.getPassportNo() + " " + flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getPNR();
					removeFromFile(rem, "ReservationList.txt");
					flightCalendar.flights.elementAt(flightindex).getPlane().setAvaliableSeats(flightCalendar.flights.elementAt(flightindex).getPlane().getAvaliableSeats() + 1);
					flightCalendar.flights.elementAt(flightindex).getTickets().remove(i);
					System.out.println("Your Reservation is now Cancelled.");
					return;
				}
			}
		}
		else
			throw new NoReservationtException("You have no Reservation.");
	}
	
	public void makeBooking(String fno, String date, String c) throws NoFlightException {
		int flightindex = findFlight(fno, date);
		if(flightindex != -1) {
			for(int i=0;i<flightCalendar.flights.elementAt(flightindex).getTickets().size();i++) {
				if(flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getCustomer().getPassportNo().compareTo(loginedCustomer.getPassportNo()) == 0 && flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getSeat().getIsReserved() == true) {
					flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getSeat().setIsReserved(false);
					flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getSeat().setIsBooked(true);
					String rem = loginedCustomer.getName() + " " + loginedCustomer.getPassportNo() + " " + flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getPNR();
					removeFromFile(rem, "ReservationList.txt");
			        String str = loginedCustomer.getName() + " " + loginedCustomer.getPassportNo() + " " + flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getPNR() + "\r\n";
					writeToFile(str, "BookingList.txt");
					System.out.println("Your Reserved ticket is now Booked. PNR is: " + flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getPNR());
					return;
				}
			}		
			Ticket ticket = new Ticket(loginedCustomer, c, true, false, flightCalendar.flights.elementAt(flightindex));
			flightCalendar.flights.elementAt(flightindex).getTickets().add(ticket);
			String str = loginedCustomer.getName() + " " + loginedCustomer.getPassportNo() + " " + ticket.getPNR() + "\r\n";
			writeToFile(str, "BookingList.txt");
			flightCalendar.flights.elementAt(flightindex).getPlane().setAvaliableSeats(flightCalendar.flights.elementAt(flightindex).getPlane().getAvaliableSeats() - 1);
			System.out.println("Your ticket is now Booked. PNR is: " + ticket.getPNR());
		}
		else
			throw new NoFlightException("Flight not found.");
	}
	
	public void printTicket(int pnr) {
		int flightindex = findFlight(pnr);
		if(flightindex != -1) {
			for(int i=0;i<flightCalendar.flights.elementAt(flightindex).getTickets().size();i++) {
				if(flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getCustomer().getPassportNo().compareTo(loginedCustomer.getPassportNo()) == 0 && flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getSeat().getIsBooked() == true && flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).getPNR() == pnr) {
					flightCalendar.flights.elementAt(flightindex).getTickets().elementAt(i).printTicket(flightCalendar.flights.elementAt(flightindex));
					System.out.println();
					return;
				}			
			}
		}
		System.out.println("Flight not found/ Ticket not booked.");
	}
	
}