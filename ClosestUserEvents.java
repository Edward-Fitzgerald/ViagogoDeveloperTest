/*
 * Name: Edward Jose Fitzgerald
 * Last Modified: 08/06/2017 (MM/DD/YYYY)
 * Description: Generates a series of events, accepts user input for coordinates and finds the five closest
 * 				events to the user in a 20 by 20 grid (-10 to 10 for X and Y axis).
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Class used to generate and identify the closest events to the user's location.
public class ClosestUserEvents {
	// Main method (code starts executing from here)
	public static void main(String[] args){
		// Generate an array of events in a 20 by 20 grid
		Event[] events = generateEvents();
		// Get the user's location as coordinates
		String userCoordinates = getUserCoordinates();
		
		// Find the five closest events to the user
		findClosestEvents(events, userCoordinates);
	}
	
	// Generates events on the X and Y axis
	public static Event[] generateEvents(){
		// Create an array list that stores all the events
		List<Event> eventsList = new ArrayList<Event>();
		// Keeps track of the event IDs for events being created
		int currentEventID = 1;
		
		// Loop through the Y axis
		for(int y = 10; y >= -10; y--){
			// Loop through the X axis
			for(int x = -10; x <= 10; x++){
				// Generating a pseudo-random number from 1-100
				Random randomGenerator = new Random();
				int eventCreated = randomGenerator.nextInt(99) + 1;
				// Assuming 30% chance of creating an event at each coordinate
				if(eventCreated <= 30){
					// Assuming max. number of tickets available is 100,000
					int numOftickets = randomGenerator.nextInt(100000);
					// Assuming only one ticket price per event
					// Assuming $200 max. ticket price and $10 min. ticket price ($10/$200=0.05)
					// Since ticket price can't be zero, we add a minimum ticket price of $10. ($200/1.05 = 190.47)
					double ticketPriceDouble = 190.47 * (randomGenerator.nextDouble() + 0.05);				
					BigDecimal ticketPrice = new BigDecimal(ticketPriceDouble);
					// Use the constructor for the Event class to create a new Event and add it to the array list
					Event event = new Event(currentEventID++, x, y, numOftickets, ticketPrice);
					eventsList.add(event);
				}
			}	
		}
		
		// Create an array from the array list and return the array created
		// URL: https://stackoverflow.com/questions/7969023/from-arraylist-to-array
		Event[] events = eventsList.toArray(new Event[eventsList.size()]);
		return events;
	}
	
	// Prompt the user for their coordinates
	public static String getUserCoordinates(){
		// Variable used to store user coordinates. E.g: -10,-10 OR 10,10.
		String userCoordinates;
		
		// Loop until the user inputs coordinates in the correct range and with a comma delimiter and no spaces
		while(true){
			// Try/catch to catch any InputMismatchExceptions generated by the user
			try {
				// Create a scanner with the keyboard as its input
				Scanner sc = new Scanner(System.in);
				
				// Prompt user for coordinates
				System.out.println("Please Input Coordinates: ");
				// Use RegEx to look for a number between 0-10 followed by a command and another number between 0-10
				userCoordinates = sc.next("^-?(10|\\d),-?(10|\\d)$");
				// Close the scanner to prevent leaking the resource
				sc.close();
				
				// Break out of the loop if successful
				break;
			// Catch any InputMismatchExceptions generated by the user by inputting data in wrong
			} catch(InputMismatchException iMEx){
				// Warn the user of the error
				System.out.println("Invalid Coordinates. Coordinates must be between '-10' and '10' and "
						+ "separated by a comma ',' without spaces. Examples: \n-10,-7\n3,10\n-5,7");
			}
		}
		
		// Return the successful coordinates
		return userCoordinates;
	}
	
	// Output the five closest events to the user
	public static void findClosestEvents(Event events[], String userCoordinates){
		// Convert the X and Y axis coordinates from the String into integer variables stored separately
		int userXAxis = Integer.parseInt(userCoordinates.substring(0, userCoordinates.indexOf(",")));
		int userYAxis = Integer.parseInt(userCoordinates.substring(userCoordinates.indexOf(",") + 1, 
											userCoordinates.length()));
		
		// Create a new array that matches the number of events that will store the distance from the user
		int[] distanceToEvent = new int[events.length];
		// Loop through the events array
		for(int i = 0; i < events.length; i++){
			// Get the current event being evaluated
			Event currentEvent = events[i];
			// Find the Manhattan distance from the user's location to the event's location
			distanceToEvent[i] = manhattanDistance(userXAxis, userYAxis, currentEvent.getXAxis(), currentEvent.getYAxis());
		}
		
		// Create an array to track the index location of the five closest events in the larger array
		int[] closestEventsIndex = new int[5];
		// Create an array to store the distance to the user of the five closest events.
		// Populate the array with a distance greater than is possible when traversing the whole grid (this ensures that any 
		// distance that is closer will be found.)
		int[] closestEventsDistance = {41, 41, 41, 41, 41};
		// Loop through all the Manhattan distances calculated above
		for(int i = 0; i < distanceToEvent.length; i++){
			// Loop through the closest events array
			for(int j = 0; j < closestEventsDistance.length; j++){
				// If the current distance is smaller than any distance in the closest events array, replace it
				if(distanceToEvent[i] < closestEventsDistance[j]){
					// Replace the index stored and distance from the event to the user
					closestEventsIndex[j] = i;
					closestEventsDistance[j] = distanceToEvent[i];
					
					// Break out of the closest events loop
					break;
				}
			}
		}
		
		// Print out a message to the user
		System.out.println("Closest Events to (" + userXAxis + "," + userYAxis + "):");
		
		// Loop through the five closest events
		for(int i = 0; i < 5; i++){
			// Get the current event being printed out
			Event currentEvent = events[closestEventsIndex[i]];
			// Output its information to the screen
			System.out.println("Event " + currentEvent.getEventID() + " - $" + currentEvent.getTicketPrice() + 
					", Distance " + distanceToEvent[closestEventsIndex[i]]);
		}
	}
	
	// Calculate the Manhattan distance from the user to the event location in the X and Y axis
	public static int manhattanDistance(int userX, int userY, int eventX, int eventY){
		// Find the distance to be traveled in the X axis
		int distanceInXAxis;
		if(userX > eventX){
			distanceInXAxis = userX - eventX;
		} else {
			distanceInXAxis = eventX - userX;
		}
		
		// Find the distance to be traveled in the Y axis
		int distanceInYAxis;
		if(userY > eventY){
			distanceInYAxis = userY - eventY;
		} else {
			distanceInYAxis = eventY - userY;
		}
		
		// Return the Manhattan distance that has to be traveled by user to get to the event location
		return distanceInXAxis + distanceInYAxis;
	}
}