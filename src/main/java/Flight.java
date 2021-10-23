import java.util.*;

public class Flight{
	private String FlightNo, Origin, Destination, Stop;
	private FlightDateTime dateTime;
	private Vector<Ticket> tickets;
	private Plane plane;
	private double Fare;
	
	public Flight(String flightno, String origin, String destination, String stop, double fare, FlightDateTime fdt, Plane p) {
		tickets = new Vector<Ticket>();
		FlightNo = flightno;
		Origin = origin;
		Destination = destination;
		Stop = stop;
		Fare = fare;
		dateTime = fdt;
		plane = p;
	}
	
	public FlightDateTime getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(FlightDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public Vector<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Vector<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Plane getPlane() {
		return plane;
	}
	
	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public String getFlightNo() {
		return FlightNo;
	}

	public void setFlightNo(String flightNo) {
		FlightNo = flightNo;
	}

	public String getOrigin() {
		return Origin;
	}

	public void setOrigin(String origin) {
		Origin = origin;
	}

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}
	
	public String getStop() {
		return Stop;
	}
	
	public void setStop(String stop) {
		Stop = stop;
	}

	public double getFare() {
		return Fare;
	}

	public void setFare(double fare) {
		Fare = fare;
	}
	
	public void printFlight() {
		System.out.println("Flight No: " + FlightNo);
		System.out.println("Origin: " + Origin);
		if(Stop.compareTo("-") != 0)			
			System.out.println("Stop: " + Stop);
		System.out.println("Destination: " + Destination);
		System.out.println("Fare: " + Fare);
		System.out.println("Depart Date: " + dateTime.getDeptDate());
		System.out.println("Depart Time: " + dateTime.getDeptTime());
		System.out.println("Arrival Time: " + dateTime.getArrTime());
		System.out.println("Plane Model: " + plane.getModel());
		System.out.println("Plane TotalSeats: " + plane.getTotalSeats());
		System.out.println("Avaliable Seats: " + plane.getAvaliableSeats());
	}
	
}