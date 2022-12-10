package Info;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Info {
    ArrayList<Content> contentList;
    File[] imageFileList;
    static final String movieDataFile = "src/data/film.txt";
    static final String seriesDataFile = "src/data/serier.txt";
    static final String moviePosterDirectory = "src/data/filmplakater";
    static final String seriesPosterDirectory = "src/data/serieforsider";

    File movieDirectory;    File[] movieFiles;
    File seriesDirectory;   File[] seriesFiles;

    public Info() {
        this.contentList = new ArrayList<>();
        filterAllInfo();
    }

    public void filterAllInfo() {

        this.movieDirectory = new File(moviePosterDirectory);
        this.movieFiles = movieDirectory.listFiles();
        this.seriesDirectory = new File(seriesPosterDirectory);
        this.seriesFiles = seriesDirectory.listFiles();

        //Filter data:
        filterData(contentList, movieDataFile);      //
        filterData(contentList, seriesDataFile);

        //Merge image files:
        mergeImageFiles();
        printListTest();
    }

    public void filterData(ArrayList<Content> contentList, String inputDataType) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputDataType)); // Initialise new filereader, and get path to data.
            String contentLine = reader.readLine(); // Read the first line.
            while(contentLine != null) { // Loop through data by looking at each line, and finish at null.
                String[] words = contentLine.split(";"); // Makes each line into an Array, and splits at ;.
                words[3] = words[3].replace(",", ""); // Indicates for which index we want to replace.
                words[1] = words[1].trim(); // Trim adjust all the whitespaces etc in the .txt
                Content filteredContent = (inputDataType.equals(movieDataFile)) ? filterMovieData(words) : filterSeriesData(words);
                contentList.add(filteredContent); // Adds movie to films(Arraylist).
                contentLine = reader.readLine(); // Reads through rest of the lines until null.
            }
            reader.close(); // Like 'Break' - stops the BufferedReader.
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    // Make an object that creates a movie with the corresponding index positions. (title, year, genre, rating).
    public Content filterMovieData(String[] words) {
        return new Movies(words[0], words[1], words[2], Double.parseDouble(words[3]));
    }

    public Content filterSeriesData(String[] words) {
        return new Series(words[0], words[1], words[2], Double.parseDouble(words[3]), words[4]);
    }
    // Run through ArrayList and make a "display" function.
    public void printMovies () {
        for (Content film : contentList) {
            System.out.println("Contents: " + film.title + " " + film.year + " " + film.genre + " " + film.rating);
        }
    }

    public void mergeImageFiles() {
        File[] concatenatedList = Stream.concat(Arrays.stream(this.movieFiles), Arrays.stream(this.seriesFiles)).toArray(contents -> (File[]) Array.newInstance(this.movieFiles.getClass().getComponentType(), contents));
        this.imageFileList = concatenatedList;
    }

    public void printListTest() {
        for(int i = 0; i < imageFileList.length; i++) {
            System.out.println("File names: " + imageFileList[i].getAbsoluteFile().getName());
        }
    }

    public ArrayList<Content> getContentList() {
        return this.contentList;
    }

    public File[] getFileList() {
        return this.imageFileList;
    }
}
