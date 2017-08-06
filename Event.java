/*
 * Name: Edward Jose Fitzgerald
 * Last Modified: 08/06/2017 (MM/DD/YYYY)
 * Description: Class used to define the properties of an Event such as ID, X and Y axis, number of tickets
 * 				available, as well as the ticket price.
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Logger;

public class Event {
	// Used to provide log warnings and errors
	// URL: http://www.vogella.com/tutorials/Logging/article.html
	private final static Logger LOGGER = Logger.getLogger(Event.class.getName());
	
	// Global variables
	private int eventID;
	private int xAxis;
	private int yAxis;
	private int numOfTickets;
	private BigDecimal ticketPrice;
	
	// Constructor
	public Event(int eventID, int xAxis, int yAxis, int numOfTickets, BigDecimal ticketPrice){
		// Use setter methods to ensure data is validated
		setEventID(eventID);
		setXAxis(xAxis);
		setYAxis(yAxis);
		setNumOfTickets(numOfTickets);
		setTicketPrice(ticketPrice);
	}
	
	// Getter method for eventID
	public int getEventID() {
		return eventID;
	}

	// Setter method for eventID
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	
	// Getter method for xAxis
	public int getXAxis() {
		return xAxis;
	}

	// Setter method for xAxis
	public void setXAxis(int xAxis) {
		// xAxis must have a value between -10 to 10
		if(xAxis >= -10 && xAxis <= 10){
			this.xAxis = xAxis;
		} else {
			// Output warning to logs if value inputed is wrong
			LOGGER.warning("Event.setxAxis only accepts values from '-10' to '10'");
		}
	}

	// Getter method for yAxis
	public int getYAxis() {
		return yAxis;
	}

	// Setter method for yAxis
	public void setYAxis(int yAxis) {
		// yAxis must have a value between -10 to 10
		if(yAxis >= -10 && yAxis <= 10){
			this.yAxis = yAxis;
		} else {
			// Output warning to logs if value inputed is wrong
			LOGGER.warning("Event.yAxis only accepts values from '-10' to '10'");
		}
	}
	
	// Getter method for numOfTickets
	public int getNumOfTickets() {
		return numOfTickets;
	}

	// Setter method for numOfTickets
	public void setNumOfTickets(int numOfTickets) {
		this.numOfTickets = numOfTickets;
	}
	
	// Getter method for ticketPrice
	public BigDecimal getTicketPrice() {
		// Ensure to only return two decimal places and to round up all values
		return ticketPrice.setScale(2, RoundingMode.UP);
	}

	// Setter method for ticketPrice
	public void setTicketPrice(BigDecimal ticketPrice) {
		// ticketPrice can't be zero
		if(ticketPrice.compareTo(BigDecimal.ZERO) != 0){
			this.ticketPrice = ticketPrice;
		} else {
			// Output warning to logs if value inputed is wrong 
			LOGGER.warning("Ticket price can't be zero");
		}
	}
}
