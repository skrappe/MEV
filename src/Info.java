import java.io.*;
import java.util.*;
import java.io.IOException;
public class Info {
    private ArrayList<Movie> films;

    public ArrayList<Movie> getFilms() {
        return films;
    }

    public void Films() {

        films = new ArrayList<>();
        // Install bufferReader, & run through data.
        BufferedReader reader; // Initialise Bufferredder.
        try {
            reader = new BufferedReader(new FileReader("./Data/film.txt")); // Initialise new filereader, and get path to data.
            String filmContent = reader.readLine(); // Read the first line.
            while (filmContent != null) { // Loop through data by looking at each line, and finish at null.
                String[] words = filmContent.split(";"); // Makes each line into an Array, and splits at ;.
                words[3] = words[3].replace(",", ""); // Indicates for which index we want to replace.
                words[1] = words[1].trim(); // Trim adjust all the whitespaces etc in the .txt
                Movie movie = createMovie(words); // initiates the object
                films.add(movie); // Adds movie to films(Arraylist).
                filmContent = reader.readLine(); // Reads through rest of the lines untill null.
            }
            reader.close(); // Like 'Break' - stops the BufferedReader.
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        printMovies();
    }
    // Make an object that creates a movie with the corresponding index positions. (title, year, genre, rating).
    public Movie createMovie (String[]words){
        return new Movie(words[0], words[1], words[2], Double.parseDouble(words[3]));
    }
    // Run through ArrayList and make a "display" function.
    public void printMovies () {
        for (Movie film : films) {
            System.out.println(film.title + " " + film.year + " " + film.genre + " " + film.rating);
        }
    }

}



