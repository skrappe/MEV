import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Info1 {
    static ArrayList<Series> liste;

    public void Serier() {
        liste = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Presentation/Data/serier.txt"));
            String seriesContent = br.readLine();
            while (seriesContent != null) {
                String[] words = seriesContent.split(";");
                words[3] = words[3].replace(",", "");
                words[1] = words[1].replace(" ", "");
                System.out.println(br);
                seriesContent = br.readLine();
            }
            br.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    // Make an object that creates a movie with the corresponding index positions. (title, year, genre, rating).

}