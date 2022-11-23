public class Content {
     String title;
     String genre;
     int year;
     double rating;


    public Content(String title, int year, String genre, double rating) {
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
        public int getYear(){
            return year;
        }
        public double getRating(){
            return rating;
        }
    }

