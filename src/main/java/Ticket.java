public class Ticket{
	private static int count = 1;
	private int PNR;	
	private Customer customer;
	private Seat seat;
	private FlightDateTime dateTime;
	private String FlightNo, Origin, Destination, Stop;
	
	public Ticket(Customer cust, String c, Boolean booked, Boolean reserved, Flight f) {
		PNR = count;
		count++;
		setCustomer(cust);
		setSeat(new Seat(c, booked, reserved, f));
		dateTime = f.getDateTime();
		FlightNo = f.getFlightNo();
		Origin = f.getOrigin();
		Destination = f.getDestination();
		Stop = f.getStop();		
	}
	
	public int getPNR() {
		return PNR;
	}

	public void setPNR(int pNR) {
		PNR = pNR;
	}

	public FlightDateTime getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(FlightDateTime dateTime) {
		this.dateTime = dateTime;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}	
	
	public void printTicket(Flight f) {
		System.out.println("----------Your Ticket----------");
		customer.printCustomer();
		f.printFlight();
		System.out.println("SeatNo: " + getSeat().getSeatNo());		
	}
	
}