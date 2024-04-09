package data_source;

import domain_model.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    // Class til at gemme alle metoder vedr. filhåndtering
    //eventuelle attributter
    private final File file = new File("movieDatabase.csv");
    private ArrayList<Movie> movieListe = loadSavedMovieList();

    //Constructor
    public FileHandler() {
        loadSavedMovieList();
    }

    ArrayList<Movie> loadSavedMovieList() {
        ArrayList<Movie> movies = new ArrayList<>();
        Scanner scannerInput = null;
        try {
            scannerInput = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Sorry something went wrong with loading the MovieCollection.");
            throw new RuntimeException(e);
        }

        Movie posterFraListen = null;

        while (scannerInput.hasNext()) {
            String line = scannerInput.nextLine();
            String[] values = line.split(";");
            posterFraListen = new Movie(
                    (values[0]),//String title
                    (values[1]),//String director
                    (Integer.parseInt(values[2])),//int yearCreated
                    (Boolean.parseBoolean(values[3])),//Boolean IsInColor
                    (Integer.parseInt(values[4])),//int length
                    (values[5])//String genre
            );
            movies.add(posterFraListen);
        }
        scannerInput.close();
        return movies;

    }
    public void saveListOfMovies() {
        try (PrintWriter output = new PrintWriter(new FileWriter("movieDatabase.csv"))){            ;
            for (Movie movie : movieListe) {
                String movieString = String.format("%s;%s;%d;%b;%d;%s",
                        movie.getTitle(), movie.getDirector(), movie.getYearCreated(),
                        movie.getIsInColor(), movie.getLengthInMinutes(), movie.getGenre());
                output.println(movieString);
            }
            System.out.println("MovieList is saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving movies: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public ArrayList<Movie> getMovieListe() {
        return movieListe;
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
