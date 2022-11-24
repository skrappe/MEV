public class Content {
     String title;
     String genre;
     String year;
     double rating;


    public Content(String title, String year, String genre, double rating) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }

        public String getTitle(){
            return title;
        }
        public String getGenre(){
            return genre;
        }
        public String getYear(){
            return year;
        }
        public double getRating(){
            return rating;
        }
    }

