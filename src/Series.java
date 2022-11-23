public class Series extends Content {

    int season;

    public Series(String title, String genre, int year, double rating, int season) {
        super(title, year, genre, rating);
        this.season = season;
    }
}
