import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Info1 {
    private ArrayList<Series> serieListe;

    public ArrayList<Series> getSerieListe() {
        return serieListe;
    }

    public void Serier() {
        serieListe = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("./Data/serier.txt"));
            String seriesContent = br.readLine();
            while (seriesContent != null) {
                String[] words = seriesContent.split(";");
                words[3] = words[3].replace(",", ".");
                words[1] = words[1].trim();
                Series serie = createSerie(words);
                serieListe.add(serie);
                seriesContent = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Series createSerie(String[] words) {
        return new Series(words[0], words[1], words[2], Double.parseDouble(words[3]), words[4]);

        // Make an object that creates a movie with the corresponding index positions. (title, year, genre, rating).
    }

    public void printSeries() {
        for (Series series : serieListe) {
            System.out.println(series.title + " " + series.year + " " + series.genre + " " + series.rating + " " + series.season);
        }
    }
}