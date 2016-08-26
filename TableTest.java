/****************************************************************************************
 *@file TableTest.java
 *
 *@author Nick Klepp, Trent Walls, Jason Vinson, Theresa Miller
 */

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

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
    
    @Test
    public void testEquiJoin(){
    	System.out.println("\n\nBEGINNING TEST OF EQUI-JOIN\n\n");
    	
    	//ARRANGE
    	Table movie = new Table ("movie", "title year length genre studioName producerNo",
                                          "String Integer Integer String String Integer", "title year");

    	Table movie2 = new Table ("movie2", "title year actor1 actor2 actor3 actor4",
                                            "String Integer String String String String", "title year");
    	
    	Comparable [] film0 = { "Star_Wars", 1977, 124, "sciFi", "Fox", 12345 };
    	Comparable [] film1 = { "Star_Wars_2", 1980, 124, "sciFi", "Fox", 12345 };
    	Comparable [] film2 = { "Rocky", 1985, 200, "action", "Universal", 12125 };
    	Comparable [] film3 = { "Rambo", 1978, 100, "action", "Universal", 32355 };

    	movie.insert(film0);
    	movie.insert(film1);
    	movie.insert(film2);
    	movie.insert(film3);
    	movie.print();

    	Comparable [] joinFilm0 = {"Star_Wars", 1977, "Han", "Luke", "Yoda", "Bob"};
    	Comparable [] joinFilm1 = {"Star_Wars_2", 1980, "Padme", "Jango", "Zam", "Joe"};
    	movie2.insert(joinFilm1); 
    	movie2.insert(joinFilm0);
     
    	movie2.print();
    	
    	//ACT
    	Table joinTable = movie.join("title year", "title year", movie2);	//EquiJoin
    	Table equiJoin_correct = new Table (
    			"equiJoin_correct", "title year length genre studioName producerNo title2 year2 actor1 actor2 actor3 actor4",
                		   "String Integer Integer String String Integer String Integer String String String String", "title year");
    	
    	Comparable [] equiJoin_correct_film1 = { "Star_Wars", 1977, 124, "sciFi", "Fox", 12345, "Star_Wars", 1977, "Han", "Luke", "Yoda", "Bob"};
    	Comparable [] equiJoin_correct_film2 = { "Star_Wars_2", 1980, 124, "sciFi", "Fox", 12345, "Star_Wars_2", 1980, "Padme", "Jango", "Zam", "Joe"};
    	equiJoin_correct.insert(equiJoin_correct_film1);
    	equiJoin_correct.insert(equiJoin_correct_film2);
    	equiJoin_correct.print();
    	joinTable.print();
//    	assertTrue(equiJoin_correct.getTuples().size() == joinTable.getTuples().size());
    	
//    	for(Comparable[] equiJoin_correct_tuple : equiJoin_correct.getTuples()){
//    	    for(Comparable [] joinTable_tuple : joinTable.getTuples()){
//    	    	for( int i = 0; i < joinTable_tuple.length; i++){
//    	    		System.out.print(joinTable_tuple[i] + " ");
//    	    	}
//    	    	System.out.println("");
//    	    	for( int i = 0; i < equiJoin_correct_tuple.length; i++){
//    	    		System.out.print(equiJoin_correct_tuple[i] + " ");
//    	    	}
//    	    	System.out.println(Arrays.equals(equiJoin_correct_tuple, joinTable_tuple));
//    	    	
//	    		if(equiJoin_correct_tuple.equals(joinTable_tuple))
//	    			System.out.println("\n\nFOUND MATCHING TUPLE\n\n");
//	    		else
//	    			System.out.println("\n\nMISMATCHED TUPLES\n"+equiJoin_correct_tuple.toString()+"\n\n");
//    	    }
//    	}
//    	equiJoin_correct.printIndex();
//    	joinTable.printIndex(); 
//    	List<Comparable []> tuples1 = equiJoin_correct.getTuples();
//    	List<Comparable []> tuples2 = joinTable.getTuples();
    	
    
    	Table naturalJoin = movie.join(movie2); 
    	naturalJoin.print();
    	
    	//ASSERT
    	assertTrue(equiJoin_correct.getTuples().size() == naturalJoin.getTuples().size());
//    	Table selectTest = joinTable.select(new KeyType(new Comparable[]{"Star_Wars",1977}));
//    	selectTest.print();
    	
    	assertTrue(joinTable.equals(equiJoin_correct));
    	
//    	for( int i = 0; i < tuples1.size(); i++){
//    		System.out.println(i);
//    		assertTrue(tuples1.get(i).equals(tuples2.get(i)));
//    	}
//    	assertTrue(tuples1 == tuples2);
//    	assertTrue(joinTable.compatible(equiJoin_correct));

    }
    
    @Test
    public void testNaturalJoin(){
    	
    	System.out.println("\n\nBEGINNING TEST OF NATURAL JOIN\n\n");
    	
    	//ARRANGE
    	Table movie = new Table ("movie", "title year length genre studioName producerNo",
                                          "String Integer Integer String String Integer", "title year");

    	Table movie2 = new Table ("movie2", "title year actor1 actor2 actor3 actor4",
                                            "String Integer String String String String", "title year");
    	
    	Comparable [] film0 = { "Star_Wars", 1977, 124, "sciFi", "Fox", 12345 };
    	Comparable [] film1 = { "Star_Wars_2", 1980, 124, "sciFi", "Fox", 12345 };
    	Comparable [] film2 = { "Rocky", 1985, 200, "action", "Universal", 12125 };
    	Comparable [] film3 = { "Rambo", 1978, 100, "action", "Universal", 32355 };

    	movie.insert(film0);
    	movie.insert(film1);
    	movie.insert(film2);
    	movie.insert(film3);
    	movie.print();

    	Comparable [] joinFilm0 = {"Star_Wars", 1977, "Han", "Luke", "Yoda", "Bob"};
    	Comparable [] joinFilm1 = {"Star_Wars_2", 1980, "Padme", "Jango", "Zam", "Joe"};
    	movie2.insert(joinFilm1); 
    	movie2.insert(joinFilm0);
    	movie2.print();
    	
    	Table naturalJoin_correct = new Table (
    			"naturalJoin_correct", "title year length genre studioName producerNo actor1 actor2 actor3 actor4",
                		   "String Integer Integer String String Integer String String String String", "title year");
    	
    	Comparable [] naturalJoin_correct_film1 = { "Star_Wars", 1977, 124, "sciFi", "Fox", 12345, "Han", "Luke", "Yoda", "Bob"};
    	Comparable [] naturalJoin_correct_film2 = { "Star_Wars_2", 1980, 124, "sciFi", "Fox", 12345, "Padme", "Jango", "Zam", "Joe"};
    	naturalJoin_correct.insert(naturalJoin_correct_film1);
    	naturalJoin_correct.insert(naturalJoin_correct_film2);
    	naturalJoin_correct.print();
    	
    	Table joinTable = movie.join(movie2);	//Natural Join
    	joinTable.print();
    	
    	assertTrue(naturalJoin_correct.getTuples().size() == joinTable.getTuples().size());
    	assertTrue(naturalJoin_correct.equals(joinTable));
    	
    }
    
}
