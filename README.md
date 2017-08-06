# ViagogoDeveloperTest

Developer: Edward Jose Fitzgerald <br />
Last Modified: 08/06/2017 (MM/DD/YYYY) <br />

The project was created inside of eclipse using Java 8 on Windows 10. <br />

# Compiling the program on Windows:<br />

Download the GitHub repository as a .zip and extract the contents of the archive anywhere.<br />

To compile the project you must compile the .java files using the Java Compiler. This can be done by downloading the Java SE Development Kit (JDK) from:
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html <br />

My Java compiler was found under: <br />

"C:\Program Files\Java\jdk1.8.0_111\bin\javac.exe" <br />

The program was compiled using the following command (ensure that you have navigated to a folder that contains both ClosestUserEvents.java and Events.java): <br />

"C:\Program Files\Java\jdk1.8.0_111\bin\javac.exe" ClosestUserEvents.java <br />

# Alternative to compiling:<br />

Download the GitHub repository as a .zip and extract the contents of the archive anywhere.<br />

Use the pre-compiled .class files inside of the GitHub repository. Ensure that your computer has Java installed:
https://www.java.com/en/

# Executing the Java program:<br />

It was then executed using (Note: the lack of .class at the end of the file): <br />

java ClosestUserEvents <br />

# Example output: <br />

Please Input Coordinates: <br />
5,-7 <br />
Closest Events to (5,-7): <br />
Event 98 - $198.72, Distance 0 <br />
Event 104 - $28.06, Distance 1 <br />
Event 105 - $38.55, Distance 3 <br />
Event 110 - $196.61, Distance 4 <br />
Event 114 - $154.53, Distance 4 <br />

# Answering Questions:<br />
- How might you change your program if you needed to support multiple events at the same location?<br />
I would prefer to use an SQL databse, such as MySQL or PostgreSQL, to store the events and their locations. This way you could store multiple events at the same coordinates and query the database for events in a specific region. The program would have to be changed to either connect to the database directly or use some kind of API to query the database. <br />
- How would you change your program if you were working with a much larger world size?<br />
If there were a lot more events that needed to be stored in the database, you could scale the databse using read replicas and sharding the database by region or country. Getting coordinates from the user would require a more sophisticated regular expression. The Manhattan distance algorithm may have to be replaced by another algorithm to calculate larger distances. You would want a more efficient algorithm in place to calculate the five closest events to the user that wouldn't require reading all the available information in the database or array. You would probably sort the data before inputting it into a database to make querying easier and more efficient.<br />
