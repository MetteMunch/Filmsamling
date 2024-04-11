package mock_klasser;

import domain_model.Movie;

import java.util.ArrayList;


public class FileHandlerMock implements FileHandlerInterface {
    // Class til at gemme alle metoder vedr. filhåndtering
    //eventuelle attributter
    ArrayList<String> film = new ArrayList<>();
            //= loadSavedMovieList();


    public ArrayList<Movie> loadSavedMovieList() {
        ArrayList<Movie> movies = new ArrayList<>();
        /*

        while (scannerInput.hasNext()) {
            String line = scannerInput.nextLine();
            String[] values = line.split(";");
            String title = values[0]; //String title
            String director = values[1]; //String director
            int yearCreated = Integer.parseInt(values[2]); //int yearCreated
            boolean isInColor = Boolean.parseBoolean(values[3]); //Boolean IsInColor
            int length = Integer.parseInt(values[4]); //int length

            Genre genre = null;
            try {
                genre = Genre.valueOf(values[5].toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid genre: " + values[5]);
            } // Handle the error appropriately, such as skipping this entry or asking for correct input
            //continue;

            Movie movie = new Movie(title, director, yearCreated, isInColor, length, genre);
            // Laver en ny movie baseret på scannerinput fra fil

            movies.add(movie);
            // Tilføjer filmene til vores filmsamling
        }
        scannerInput.close();
        return movies;
*/
        return null;
    }


    public void saveListOfMovies(ArrayList<Movie> movieListe) {

            for (Movie movie : movieListe) {
                String movieString = String.format("%s;%s;%d;%b;%d;%s",
                        movie.getTitle(), movie.getDirector(), movie.getYearCreated(),
                        movie.getIsInColor(), movie.getLengthInMinutes(), movie.getGenre());
                film.add(movieString);
            }
        }


    public ArrayList<String> getFilm() {
        return film;
    }
    public void setFilm(ArrayList<String> film) {
        this.film = film;
    }




    /*public void saveListOfMovies() {
        PrintStream output = null;
        try {
            System.out.println("Nu skulle listen blive kørt igennem try i metoden save");
            output = new PrintStream(new File("movieDatabase.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("Der er noget i vejen");
            throw new RuntimeException(e);
        }
        for (Movie movie : movieListe) {
            System.out.println("Nu skulle listen blive gemt?");
            output.println(movie);
        }
    }*/  //denne save metode giver problemer, da den gemmer objekterne som toString og ikke csv...så kan der ikke loades igen!


}

