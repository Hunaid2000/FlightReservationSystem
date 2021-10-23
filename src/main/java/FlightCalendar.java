import java.io.*;
import java.util.*;

public class FlightCalendar{
	Vector<Flight> flights;
	
	public FlightCalendar() {
		flights = new Vector<Flight>();
	}
	
	public void readFile() {
		try{  
	        FileInputStream in = new FileInputStream("FlightSchedule.txt");       
	        Scanner input = new Scanner(in); 
            while (input.hasNextLine()) {
            	String fno = input.next();
                String origin = input.next();
                String stop = input.next();
                String dest = input.next();
                String dtime = input.next();
                String atime = input.next();
                String date = input.next();
                String fare = input.next();
                String model = input.next();
                String tseats = input.next();
                flights.add(new Flight(fno, origin, dest, stop, Double.parseDouble(fare),new FlightDateTime(date, dtime, atime), new Plane(model, Integer.parseInt(tseats), Integer.parseInt(tseats))));                
            } 
	        input.close();    
	        in.close();
	    }  
	    catch(IOException e)  
	    {  
	        e.printStackTrace();  
	    } 
	}
	
	public void search(String origin, String dest, String fno, String date, String dtime, String atime, String model, Boolean lowestfare) {
		Vector<Flight> temp = new Vector<Flight>();
		
		for(int i=0;i<flights.size();i++) {
			if(flights.elementAt(i).getOrigin().compareTo(origin) == 0 && flights.elementAt(i).getDestination().compareTo(dest) == 0)
				temp.add(flights.elementAt(i));
		} 
		
		if(fno.compareTo("") != 0)
			temp.removeIf(n -> !(n.getFlightNo().compareTo(fno) == 0));
		if(date.compareTo("") != 0)
			temp.removeIf(n -> !(n.getDateTime().getDeptDate().compareTo(date) == 0));
		if(dtime.compareTo("") != 0)
			temp.removeIf(n -> !(n.getDateTime().getDeptTime().compareTo(dtime) == 0));
		if(atime.compareTo("") != 0)
			temp.removeIf(n -> !(n.getDateTime().getArrTime().compareTo(atime) == 0));
		if(model.compareTo("") != 0)
			temp.removeIf(n -> !(n.getPlane().getModel().compareTo(model) == 0));
		if(lowestfare) {
			double fare = temp.elementAt(0).getFare();
			for(int i=0;i<temp.size();i++) {
				if(temp.elementAt(i).getFare() < fare)
					fare = temp.elementAt(i).getFare(); 
			}
			double f = fare;
			temp.removeIf(n -> !(n.getFare() == f));
		}

		if(temp.size() != 0) {
			for(int i=0;i<temp.size();i++) {
				temp.elementAt(i).printFlight();
				System.out.println();
			}
		}			
		else {
			System.out.println("No flights avaliable according to your search.");
		}
	}
	
	
}