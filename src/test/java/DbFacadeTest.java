import java.sql.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class DbFacadeTest {
    /**
     * A test for the searchMoviePage method. Input would be a string(movie name).
     * Expected result is a result set containing three columns that are the actual
     * movie, US release date, and its average rating from all the reviews
     * @throws SQLException
     */
    @Test
    public void testSearchMoviePage() throws SQLException{
        //expected output
        List<String> movie = Arrays.asList("A Quiet Place");
        List<String> date = Arrays.asList("2018-04-06");
        List<Double> rate = Arrays.asList(3.5);

        try(DbFacade db = new DbFacade() ){
            //example input
            ResultSet rset = db.searchMoviePage("A Quiet Place");
            //have lists to store the result set
            ArrayList<String> actualMovie = new ArrayList<>();
            ArrayList<String> actualDate = new ArrayList<>();
            ArrayList<Double> actualRate = new ArrayList<>();
            //loop through to read all rows
            while(rset.next()){
                actualMovie.add(rset.getString(1));
                actualDate.add(rset.getString(2));
                actualRate.add(rset.getDouble(3));
            }
            // Compare the lists to the expected results
            assertEquals(movie, actualMovie);
            assertEquals(date, actualDate);
            assertEquals(rate, actualRate);
        }
    }
    /**
     * Test for the reportMoviePage method. Input would be two strings(movie name and
     * us release date). Expected result is a result set containing two columns that
     * are the actual movie name and US release date
     * @throws SQLException
     */
    @Test
    public void testReportMoviePage() throws SQLException{
        //expected output
        List<String> movie = Arrays.asList("The Sandlot");
        List<String> date = Arrays.asList("1993-04-07");

        try(DbFacade db = new DbFacade()){
            ResultSet rset = db.reportMoviePage("The Sandlot", "1993-04-07");
            //lists to store the result set
            ArrayList<String> actualMovie = new ArrayList<>();
            ArrayList<String> actualDate = new ArrayList<>();
            //loop through to read all the rows
            while(rset.next()) {
                actualMovie.add(rset.getString(1));
                actualDate.add(rset.getString(2));
            }
            // Compare the lists to the expected results
            assertEquals(movie, actualMovie);
            assertEquals(date, actualDate);
        }
    }

    /**
     * A test for the moviesAfter2000 method that returns a movie and all of its informetion
     * as long as the US release date is after 1-1-2000. There is no input for this method.
     * The output has 8 columns - all the movie information(name, release date, director, etc)
     * @throws SQLException
     */
    @Test
    public void testMoviesAfter2000() throws SQLException{
        //expected output
        List<String> movie = Arrays.asList("The Theory of Everything","The Dark Knight", "The Lord of the Rings",
                "A Quiet Place", "Cinderella Man", "Interstellar", "Ocean's Eleven",
                "Ocean's Thirteen", "Ocean's Twelve", "Transformers", "Transformers: Dark of the Moon",
                "Transformers: Revenge of the Fallen");
        List<String> date = Arrays.asList("2014-11-07","2007-08-18", "2003-12-17", "2018-04-06",
                "2005-05-23", "2014-10-26", "2001-12-07", "2007-06-05",
                "2004-12-08", "2007-07-27", "2011-06-28", "2009-06-24");

        try(DbFacade db = new DbFacade()){
            ResultSet rset = db.moviesAfter2000();
            //lists to store the result set
            ArrayList<String> actualMovie = new ArrayList<>();
            ArrayList<String> actualDate = new ArrayList<>();
            //loop through to read all rows
            while(rset.next()) {
                actualMovie.add(rset.getString(1));
                actualDate.add(rset.getString(2));
            }
            // Compare the lists to the expected results
            assertEquals(movie, actualMovie);
            assertEquals(date, actualDate);
        }
    }

    /**
     * A test for the genreSearch method that returns all the movies that are described
     * by a given genre. The input is the genre and the output is a result set that has two
     * columns that are the movie(s) and the genre.
     * @throws SQLException
     */
    @Test
    public void testGenreSearch() throws SQLException {
        //expected output
        List<String> movie = Arrays.asList("Cinderella Man");
        List<String> genre = Arrays.asList("Drama, Sports");

        try(DbFacade db = new DbFacade()){
            ResultSet rset = db.genreSearch("Drama, Sports");
            //lists to store the result set
            ArrayList<String> actualMovie = new ArrayList<>();
            ArrayList<String> actualGenre = new ArrayList<>();
            //loop through to store all the rows
            while(rset.next()) {
                actualMovie.add(rset.getString(1));
                actualGenre.add(rset.getString(2));
            }
            //compare the lists to expected results
            assertEquals(movie, actualMovie);
            assertEquals(genre, actualGenre);
        }
    }

    /**
     * A test for the actorSearch method. This input is a string(actor name) and the expected output
     * is a result set with two columns that are the actor and all the movies they act in.
     * @throws SQLException
     */
    @Test
    public void testActorSearch() throws SQLException{
        //expected output
        List<String> name = Arrays.asList("Brad Pitt","Brad Pitt", "Brad Pitt", "Brad Pitt" );
        List<String> movies = Arrays.asList("Fight Club", "Ocean's Eleven", "Ocean's Thirteen",
                "Ocean's Twelve");

        try(DbFacade db = new DbFacade()){
            ResultSet rset = db.actorSearch("Brad Pitt");
            //lists to store the result set
            ArrayList<String> actualActor = new ArrayList<>();
            ArrayList<String> actualMovies = new ArrayList<>();
            //loop through to read all the rows
            while(rset.next()){
                actualActor.add(rset.getString(1));
                actualMovies.add(rset.getString(2));
            }
            //compare the lists to expected results
            assertEquals(name, actualActor);
            assertEquals(movies, actualMovies);
        }
    }

    /**
     * A test for the moviesByAdmin method. The input is a string(the admin username) and the
     * expected output is a result set with a single column that is all the
     * movies that admin has added to the database.
     * @throws SQLException
     */
    @Test
    public void testMoviesByAdmin() throws SQLException{
        //expected output
        List<String> movies = Arrays.asList("A Quiet Place", "Cinderella Man",
                "Interstellar", "Ocean's Eleven", "Ocean's Thirteen", "Ocean's Twelve",
                "The Sandlot", "Transformers", "Transformers: Dark of the Moon",
                "Transformers: Revenge of the Fallen");

        try(DbFacade db = new DbFacade()){
            ResultSet rset = db.moviesByAdmin("Walker");
            //list to store result set
            ArrayList<String> actualMovies = new ArrayList<>();
            //loop through to read the row
            while (rset.next()){
                actualMovies.add(rset.getString(1));
            }
            //compare the list to expected output
            assertEquals(movies, actualMovies);
        }
    }
}
