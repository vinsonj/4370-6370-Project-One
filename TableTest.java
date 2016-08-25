/****************************************************************************************
 *@file TableTest.java
 *
 *@author Nick Klepp, Trent Walls, Jason Vinson, Theresa Miller
 */

import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.*;

public class TableTest extends TestCase{

    private Table movie;
    private Table movie2;
    private Table movie3;
    private Table movie4;
    
    protected void setUp(){
	movie = new Table ("movie", "title year length genre studioName producerNo",
				 "String Integer Integer String String Integer", "title year");

	movie2 = new Table ("movie2", "title year length genre studioName producerNo",
				  "String Integer Integer String String Integer", "title year");

	movie3 = new Table ("movie3", "title year length genre studioName producerNo",
				  "String Integer Integer String String Integer", "title year");

	movie4 = new Table ("movie4", "title year length genre studioName producerNo",
				  "String Integer Integer String String Integer", "title year");
	
	Comparable [] film0 = { "Star_Wars", 1977, 124, "sciFi", "Fox", 12345 };
	Comparable [] film1 = { "Star_Wars_2", 1980, 124, "sciFi", "Fox", 12345 };
	Comparable [] film2 = { "Rocky", 1985, 200, "action", "Universal", 12125 };
	Comparable [] film3 = { "Rambo", 1978, 100, "action", "Universal", 32355 };

	movie.insert(film0);
	movie.insert(film1);
	movie.insert(film2);
	movie.insert(film3);
	//movie.print();

	movie2.insert(film0);
	movie2.insert(film1);   
	//movie2.print();

	movie3.insert(film2);
	movie3.insert(film3);
	//movie3.print();

	movie4.insert(film0);
    }
    
    
    //@Test
    public void testProject(){
        Table t_project1;
        Table t_project2;
        Table t_project3;
        Table t_project4;
        
        Table t_project1_correct;
        Table t_project2_correct;
        Table t_project3_correct;
        Table t_project4_correct;
        
        Comparable [] project1_film0 = { "Star_Wars", 1977 };
	Comparable [] project1_film1 = { "Star_Wars_2", 1980 };
	Comparable [] project1_film2 = { "Rocky", 1985 };
	Comparable [] project1_film3 = { "Rambo", 1978 };
        
        System.out.println("\n\n Testing projection....");
        
        //Projection 1
        t_project1_correct = new Table ("movie", 
                                        "title year",
                                        "String Integer",
                                        "title year");
        t_project1_correct.insert(project1_film0);
        t_project1_correct.insert(project1_film1);
        t_project1_correct.insert(project1_film2);
        t_project1_correct.insert(project1_film3);
        
        System.out.print("\n\n Test 1: Regular projection");
        t_project1 = movie.project ("title year");
        assertTrue(t_project1.equals(t_project1_correct));
        
        System.out.print("Test 2: Order reversed");
        t_project2 = movie.project ("year table");

        System.out.print("Test 3: No columns");
        t_project3 = movie.project ("year table");
        
        System.out.print("Test 4: All columns");
        t_project4 = movie.project ("year table");
        
        //assertTrue()''
        assertTrue(false);
    }
    

    @Test
    public void testMinus(){

	/*
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
	movie3.print();*/

	Table newMovie=movie.minus(movie2);
	//newMovie.print();

	//System.out.println("movie3 tuples: " + movie3.getTuples().toString());
	//System.out.println("newMovie tuples: " + newMovie.getTuples().toString());
	assertTrue(movie3.equals(newMovie));
    }

    @Test
    public void testIndexSelect(){
	KeyType key1=new KeyType(new Comparable[]{"Star_Wars",1977});
	Table newMovie=movie.select(key1);
	//System.out.println("newMovie: ");
	//newMovie.print();
	assertTrue(newMovie.equals(movie4));
    } 
    
}
