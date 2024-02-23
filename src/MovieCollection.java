import java.util.ArrayList;

public class MovieCollection {

    ArrayList<Movie> movieListe = new ArrayList<>();
    ArrayList<Movie> searchMatch = new ArrayList<>();


    //constructor
    public MovieCollection() {
    }


    //metoder
    public void addMovie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        movieListe.add(new Movie(title, director, yearCreated, isInColor, lengthInMinutes, genre));
        System.out.println("Du har tilføjet filmen: " + title);
        System.out.println("Filmsamlingen indeholder nu samlet: " + movieListe.size() + " film.");
    }

    public void printFullListe() {
        for (Movie film : movieListe) {
            System.out.println(film.toString());
        }
    }

    /*public String searchMovie(String title){ //user case 5

        for(Movie films : movieListe){
            if(films.getTitle().toLowerCase().contains(title.toLowerCase())){
                return "Ja vi har filmen: " +films.toString();
            }
        }

        return "Desværre vi har ikke filmen i samlingen.";
    }*/

    public ArrayList<Movie> searchMovie(String title) { //user case 6 jeg prøver at ændre denne til ren toString og ikke array senere
        for (Movie films : movieListe) {
            if (films.getTitle().toLowerCase().contains(title.toLowerCase())) {
                searchMatch.add(films);
            }
        }return  searchMatch;
    }


    public ArrayList<Movie> getMovieListe() { //getter for at få MovieListe af "typen" Movie
        return movieListe;
    }

    public ArrayList<Movie> getSearchMatch() {//getter for at kunne kalde searchMatch af typen Movie.
        return searchMatch;
    }
}
