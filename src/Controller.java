public class Controller {

    //oprettelse af instance af MovieCollection
    MovieCollection instanceMovieCollection = new MovieCollection();

    //constructor
    public Controller() {
    }
    public void addMovie1(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre){
        instanceMovieCollection.addMovie(title,director,yearCreated,isInColor,lengthInMinutes,genre);
    }

}
