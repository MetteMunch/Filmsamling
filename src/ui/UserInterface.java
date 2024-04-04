package ui;

import domain_model.Movie;
import domain_model.Controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    //create instance of domain_model.Controller class
    Controller samling1 = new Controller();
    Scanner input;

    int SENTINEL = 5;
    int inputKey = 0;
    int filmIndexNo = 9;


    //Constructor
    public UserInterface() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");//denne gør sådan, at jeg ikke får scanner bugs, men skal huske at bruge next og ikke nextLine
        startProgram();
    }

    public void startProgram() {
        //Jeg tilføjer lige nogle titler, så jeg har lidt at lege med...
        samling1.addMovie2("Burn After Reading", "Coen", 2008, true, 120, "Komedie");
        samling1.addMovie2("Godfather", "Coppola", 1972, true, 175, "Thriller");
        samling1.addMovie2("La vita e bella", "Benigni", 1997, true, 120, "Drama");

        userCommunication();//Her kalder jeg metoden, hvor user oplyses om valgmuligheder

        while (inputKey != SENTINEL) {

            if (inputKey == 4) {  //i denne printer den en liste med samtlige film
                samling1.findAllMovies();


            } else if (inputKey == 2) {  //denne søger efter en specifik titel
                movieSearchWithNewArray();


            } else if (inputKey == 1) {
                opretFilm();


            } else if (inputKey == 3) {
                findAndEditMovie();


            } else if (inputKey == 6) {
                userCommunication();

            }
            repeatMenu();
        }
        System.out.println("Thank you and see you soon.");
    }

    public void userCommunication() {//usercase 2 og tilrettet i de følgende
        System.out.println("Welcome to the movie collection!\n" +
                "Type 1, if you want to add a movie.\n" +
                "Type 2, if you want to search for a title.\n" +
                "Type 3, if you want to edit or delete a movie in the collection.\n" +
                "Type 4, if you want to see the full movie collection.\n" +
                "Type 5, if you want to exit the program.");
        inputKey = input.nextInt();
    }

    public void opretFilm() {
        System.out.println("Type the title of the movie you want to add:");
        String title = input.next();// Her benyttes kun next selvom det kan være input med flere ord...men dette er fixet med useDelimeter

        System.out.println("Type the director of the movie:");
        String director = input.next();

        System.out.println("Type genre of the movie, choose between action, thriller, drama, comedy, romance, crime, horror and sci-fi:");
        String genre = input.next();

        System.out.println("What year is the movie from?");
        int yearCreated = ScanIntSafely();

        System.out.println("Type true if the movie is in color or false if it is black/white.");
        boolean isInColor = input.nextBoolean();

        System.out.println("Type the duration of the movie in minutes.");
        int lengthInMinutes = ScanIntSafely();

        addMovie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
    }

    public void repeatMenu() {
        System.out.println("\nIf you want to add another movie, type 1.\n" +
                "If you want to search for a title, type 2.\n" +
                "If you want to edit or delete a title, type 3.\n" +
                "If you want to see a full list of the movie collection, type 4.\n" +
                "If you want to exit the program, type 5.");
        inputKey = input.nextInt();
    }

    public void addMovie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, String genre) {
        String result = samling1.addMovie2(title, director, yearCreated, isInColor, lengthInMinutes, genre);
        if (result.equals("true")) {
            System.out.println("You have added the movie: " + title);
            System.out.println("The collection now contains: " + samling1.getInstanceMovieCollection().getMovieListe().size() + " film.");
        }
    }

    public void findAndEditMovie() {//her vises kun første match
        //user case 5, hvis der skal søges og udskrives med toString
        System.out.println("Which movie do you like to edit?");
        String inputTitle = input.next();
        String result = samling1.findTitleMedToString(inputTitle);
        filmIndexNo = samling1.getIndexToBeChanged();
        //System.out.println("Dette er filmIndexNo: " +filmIndexNo + " Dette er filmtitel: " +result);
        if (result.equals("noMovie")) {
            System.out.println("Sorry it seems like we do not have a movie with that title in the collection.");
        } else {
            System.out.println("We have this movie in the collection: \n" + "" + samling1.findTitleMedToString(inputTitle));
            System.out.println("Is it the correct movie, which you want to edit? yes/no");
            String answer = input.next();
            if (answer.toLowerCase().equals("yes")) {
                System.out.println("What would you like to change? Type number.\n" +
                        "1.Title\n" +
                        "2.Director\n" +
                        "3.Genre\n" +
                        "4.Premiere\n" +
                        "5.Color or black/white\n" +
                        "6.Duration in minutes\n" +
                        "7.Delete movie from collection\n");
                inputKey = input.nextInt();

                switch (inputKey) {
                    case 1 -> {
                        changeTitle();
                    }
                    case 2 -> {
                        changeDirector();
                    }
                    case 3 -> {
                        changeGenre();
                    }
                    case 4 -> {
                        changeYear();
                    }
                    case 5 -> {
                        changeColor();
                    }
                    case 6 -> {
                        changeDuration();
                    }
                    case 7 -> {
                        deleteMovie();
                    }
                }

            } else if (answer.toLowerCase().equals("no")) {
                System.out.println("Then try to search again with a bit more information");
                findAndEditMovie();
            }
        }
    }


    public void movieSearchWithNewArray() {
        samling1.getInstanceMovieCollection().getSearchMatch().clear();//denne sørger for at listen er tom ved start på søgning
        System.out.println("Which movie are you looking for?");
        int count = 1;
        String inputTitle = input.next();
        for (Movie movie : samling1.findTitle(inputTitle)) {
            if (!samling1.getInstanceMovieCollection().getSearchMatch().isEmpty()) {
                System.out.println("\n We have this movie in the collection: ");
                System.out.print(count + ". " + movie);
                count++;
            }
        }
        System.out.println("I am sorry we do not have this movie in the collection.");
    }

    public void changeTitle() {
        System.out.println("What do you want to change the title to?");
        String inputNewTitle = input.next();
        String result = samling1.setTitle(filmIndexNo, inputNewTitle);
        if (result.equals("titleChanged")) {
            System.out.println("You have now succesfully changed the information on the title to: ");
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
        } else {
            System.out.println("Sorry, some information were incorrect!");
        }
    }

    public void changeDirector() {
        System.out.println("What do you want to change the director to?");
        String inputNewDirector = input.next();
        String result = samling1.setDirector(filmIndexNo, inputNewDirector);
        if (result.equals("directorChanged")) {
            System.out.println("You have now succesfully changed the information on the director to: ");
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
        } else {
            System.out.println("Sorry, some information were incorrect!");
        }
    }

    public void deleteMovie() {
        System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
        System.out.println("Are you sure you want to delete this movie from the collection, yes/no?");
        String answer = input.next();
        if (answer.toLowerCase().equals("yes")) {
            samling1.deleteMovie();
        } else {
            System.out.println("Okay then lets move on");
        }
    }

    public void changeYear() {
        System.out.println("What do you want to change the year of premiere to?");
        int inputNewYear = ScanIntSafely();
        String result = samling1.setYear(filmIndexNo, inputNewYear);
        if (result.equals("yearChanged")) {
            System.out.println("You have now succesfully changed the information on the movie to: ");
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
        } else {
            System.out.println("Sorry some information were incorrect!");
        }

    }

    public void changeGenre() {
        System.out.println("What do you want to change the genre to?");
        String inputNewGenre = input.next();
        String result = samling1.setGenre(filmIndexNo, inputNewGenre);
        if (result.equals("genreChanged")) {
            System.out.println("You have now succesfully changed the information on the movie to: ");
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
        }
    }

    public void changeDuration() {
        System.out.println("What would you like to change the duration to?");
        int newDuration = ScanIntSafely();
        String result = samling1.setDuration(filmIndexNo, newDuration);
        if (result.equals("durationChanged")) {
            System.out.println("You have now succesfully changed the duration of the movie to: ");
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
        }
    }

    public void changeColor() {
        System.out.println("Is this movie in color? Type yes/no.");
        String colorInput = input.next().toLowerCase();

        if (colorInput.equals("yes")) {
            boolean newColor = true;
            samling1.setIsInColor(filmIndexNo, newColor);
            System.out.println("You have succesfully changed the color aspect of the movie to: ");
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
        } else if (colorInput.equals("no")) {
            boolean newColor = false;
            samling1.setIsInColor(filmIndexNo, newColor);
            System.out.println("You have succesfully changed the color aspect of the movie to: ");
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
        } else {
            System.out.println("No changes were made.");
        }
    }

    private int ScanIntSafely() { //Metode til at fange hvis man skriver et bogstav i en int scanner, der ellers vil melde en fejl
        try {
            return input.nextInt(); // Her tester den om der bliver tastet en int ind i scanneren
        } catch (InputMismatchException ime) {
            input.nextLine(); // Scanneren skal lige forstå, at den nu skal være klar til at læse på en ny linje
            System.out.println("You didn't type in a number - please try again:");
            return ScanIntSafely(); // Rekursion: Metoden kalder sig selv, og starter dermed forfra med et nyt try!
        }
    }
}










