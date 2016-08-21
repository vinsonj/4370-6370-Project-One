/****************************************************************************************
 *@file TableTest.java
 *
 *@author Nick Klepp, Trent Walls, Jason Vinson, Theresa Miller
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TableTest{

    

    @Test
    public void testMinus(){

	Table movie = new Table ("movie", "title year length genre studioName producerNo",
				 "String Integer Integer String String Integer", "title year");

	Table movie2 = new Table ("movie2", "title year length genre studioName producerNo",
				  "String Integer Integer String String Integer", "title year");

	Table movie3 = new Table ("movie3", "title year length genre studioName producerNo",
				  "String Integer Integer String String Integer", "title year");
    
	Comparable [] film0 = { "Star_Wars", 1977, 124, "sciFi", "Fox", 12345 };
	Comparable [] film1 = { "Star_Wars_2", 1980, 124, "sciFi", "Fox", 12345 };
	Comparable [] film2 = { "Rocky", 1985, 200, "action", "Universal", 12125 };
	Comparable [] film3 = { "Rambo", 1978, 100, "action", "Universal", 32355 };

	movie.insert(film0);
	movie.insert(film1);
	movie.insert(film2);
	movie.insert(film3);
	movie.print();

	movie2.insert(film0);
	movie2.insert(film1);   
	movie2.print();

	movie3.insert(film2);
	movie3.insert(film3);
	movie3.print();

	Table newMovie=movie.minus(movie2);
	newMovie.print();

	System.out.println("movie3 tuples: " + movie3.getTuples().toString());
	System.out.println("newMovie tuples: " + newMovie.getTuples().toString());
	assertTrue(movie3.equals(newMovie));
    }
    
}
