package ui;

import domain_model.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    //create instance of domain_model.Controller class
    Controller samling1 = new Controller();
    Scanner input;

    int SENTINEL = 6;
    int inputKey = 0;
    int filmIndexNo = 9;


    //Constructor
    public UserInterface() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");//denne gør sådan, at jeg ikke får scanner bugs, men skal huske at bruge next og ikke nextLine
        startProgram();
    }

    public void startProgram() {

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


            } else if (inputKey == 5) {
                sortMenu();
            }
            repeatMenu();
        }
        System.out.println("Thank you and see you soon.");
    }

    ////-------MENUES-------////

    public void userCommunication() {//usercase 2 og tilrettet i de følgende
        System.out.println("Welcome to the movie collection!\n" +
                "Type 1, if you want to add a movie.\n" +
                "Type 2, if you want to search for a title.\n" +
                "Type 3, if you want to edit or delete a movie in the collection.\n" +
                "Type 4, if you want to see the full movie collection.\n" +
                "Type 5, if you want to see the sorting menu.\n" +
                "Type 6, if you want to exit the program.");
        inputKey = input.nextInt();
    }

    public void repeatMenu() {
        System.out.println("\nIf you want to add another movie, type 1.\n" +
                "If you want to search for a title, type 2.\n" +
                "If you want to edit or delete a title, type 3.\n" +
                "If you want to see a full list of the movie collection, type 4.\n" +
                "If you want to see the sorting menu, type 5.\n" +
                "If you want to exit the program, type 6.");
        inputKey = input.nextInt();
    }

    /////-------ADD, EDIT, SEARCH-------////

    public void opretFilm() {
        samling1.getInstanceMovieCollection().getSearchMatch().clear();
        boolean cond = true;
        boolean titleFlag = true;

        while (cond) {
            System.out.println("Type the title of the movie you want to add:");
            String title = input.next();// Her benyttes kun next selvom det kan være input med flere ord...men dette er fixet med useDelimeter

            samling1.findTitle(title);
            if (!samling1.getInstanceMovieCollection().getSearchMatch().isEmpty() && titleFlag) {
                int count = 0;
                System.out.println("\n The following movie(s) were found: ");
                for (Movie movie : samling1.getInstanceMovieCollection().getSearchMatch()) {
                    System.out.println(count + ". " + movie);
                    count++;
                }
                System.out.println("You entered a title that returned " + count + " results. Would you like to continue adding your movie? Type yes/no.");

                String choice = input.next().toLowerCase();
                samling1.getInstanceMovieCollection().getSearchMatch().clear();//Cleare listen for at holde den clean

                if (choice.equals("yes")) {
                    titleFlag = false;
                    cond = false;
                } else if (choice.equals("no")) {
                    System.out.println("You chose no and will now return to the menu.");
                    break;
                } else {
                    System.out.println("Please input only yes or no in order to continue.");
                }

            }

            System.out.println("Type the director of the movie:");
            String director = input.next();

            Genre genre = null;

            System.out.println("Type genre of the movie - choose between Action, Thriller, Drama, Comedy, Romance, Crime, Horror and Scifi:");
            do {

                String genreStr = input.next().toUpperCase();
                try {
                    genre = Genre.valueOf(genreStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid genre - Please choose from the provided genres");
                }
            } while (genre == null);

            System.out.println("What year is the movie from?");
            int yearCreated = ScanIntSafely();

            System.out.println("Type true if the movie is in color or false if it is black/white.");
            String color = input.next();
            boolean isInColor = color.equals("yes");

            System.out.println("Type the duration of the movie in minutes.");
            int lengthInMinutes = ScanIntSafely();

            addMovie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
        }
    }

    public void addMovie(String title, String director, int yearCreated, boolean isInColor, int lengthInMinutes, Genre genre) {
        String result = samling1.addMovie(title, director, yearCreated, isInColor, lengthInMinutes, genre);
        if (result.equals("true")) {
            System.out.println("You have added the movie: " + title);
            System.out.println("The collection now contains: " + samling1.getInstanceMovieCollection().getMovieListe().size() + " film.");
            //samling1.getInstanceMovieCollection().getFh().saveListOfMovies();
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
            return;
        }
        System.out.println("We have this movie in the collection: \n" + "" + samling1.findTitleMedToString(inputTitle));
        while (true) {
            System.out.println("Is it the correct movie, which you want to edit? yes/no");
            String answer = input.next().toLowerCase();

            if (answer.equals("yes")) {
                System.out.println("What would you like to change? Type number.\n" +
                        "1.Title\n" +
                        "2.Director\n" +
                        "3.Genre\n" +
                        "4.Premiere\n" +
                        "5.Color or black/white\n" +
                        "6.Duration in minutes\n" +
                        "7.Delete movie from collection\n" +
                        "8.I have changed my mind..take me back to the main menu.\n");

                inputKey = ScanIntSafely();

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
                    case 8 -> {
                        repeatMenu();
                    }
                }
                break;

            } else if (answer.equals("no")) {
                System.out.println("Which movie do you want to edit then?");
                inputTitle = input.next();
                result = samling1.findTitleMedToString(inputTitle);
                filmIndexNo = samling1.getIndexToBeChanged();
                if (result.equals("noMovie")) {
                    System.out.println("Sorry it seems like we do not have a movie with that title in the collection.");
                    return;
                }
                System.out.println("We have this movie in the collection: \n" + samling1.findTitleMedToString(inputTitle));
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }


    public void movieSearchWithNewArray() {
        samling1.getInstanceMovieCollection().getSearchMatch().clear();//denne sørger for at listen er tom ved start på søgning
        System.out.println("Which movie are you looking for?");
        int count = 1;
        String inputTitle = input.next();
        samling1.findTitle(inputTitle);
        if (!samling1.getInstanceMovieCollection().getSearchMatch().isEmpty()) {
            System.out.println("A search in the collection gave this result: ");
            for (Movie movie : samling1.getInstanceMovieCollection().getSearchMatch()) {
                System.out.print(count + ". " + movie.toString2() + "\n");
                count++;
            }
        } else {
            System.out.println("I am sorry we do not have this movie in the collection.");
        }


    }

    public void changeTitle() {
        System.out.println("What do you want to change the title to?");
        String inputNewTitle = input.next();
        String result = samling1.setTitle(filmIndexNo, inputNewTitle);
        if (result.equals("titleChanged")) {
            System.out.println("You have now succesfully changed the information on the title to: ");
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
            samling1.saveListOfMoviesToFile();
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
            samling1.saveListOfMoviesToFile();
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
            samling1.saveListOfMoviesToFile();
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
            samling1.saveListOfMoviesToFile();
        } else {
            System.out.println("Sorry some information were incorrect!");
        }

    }

    public void changeGenre() {
        System.out.println("What do you want to change the genre to?");
        System.out.println("Choose between Action, Thriller, Drama, Comedy, Romance, Crime, Horror and Scifi:");
        String inputNewGenreStr = input.next().toUpperCase();

        Genre inputNewGenre;
        try {
            inputNewGenre = Genre.valueOf(inputNewGenreStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid genre. Please choose from the provided genres.\n");
            changeGenre(); // Kalder metoden igen hvis vi rammer en exception for at køre igen
            return;
        }

        String result = samling1.setGenre(filmIndexNo, inputNewGenre);
        if (result.equals("genreChanged")) {
            System.out.println("You have now succesfully changed the information on the movie to: " + inputNewGenre);
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
            samling1.saveListOfMoviesToFile();
        }
    }

    public void changeDuration() {
        System.out.println("What would you like to change the duration to?");
        int newDuration = ScanIntSafely();
        String result = samling1.setDuration(filmIndexNo, newDuration);
        if (result.equals("durationChanged")) {
            System.out.println("You have now succesfully changed the duration of the movie to: ");
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
            samling1.saveListOfMoviesToFile();
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
            samling1.saveListOfMoviesToFile();
        } else if (colorInput.equals("no")) {
            boolean newColor = false;
            samling1.setIsInColor(filmIndexNo, newColor);
            System.out.println("You have succesfully changed the color aspect of the movie to: ");
            System.out.println(samling1.getInstanceMovieCollection().getMovieListe().get(filmIndexNo));
            samling1.saveListOfMoviesToFile();
        } else {
            System.out.println("No changes were made.");
        }
    }


    ////-------SORTERING-------////
    public void displaySortMenu() {
        System.out.println("Select a criteria by which you want your movielist to be sorted.");
        System.out.println("1. by Title");
        System.out.println("2. by Director");
        System.out.println("3. by Premiere Year");
        System.out.println("4. by Colourised or not");
        System.out.println("5. by Duration");
        System.out.println("6. by Genre");
        System.out.println("7. by Multiple Criteria");
    }

    public void sortMenu() {
        displaySortMenu();
        inputKey = input.nextInt();

        if (inputKey == 1) { //TITEL
            sortTitle();
        } else if (inputKey == 2) { //Director
            sortDirector();
        } else if (inputKey == 3) {//Premiere Year
            sortYearCreated();
        } else if (inputKey == 4) { //Colourised
            sortColorised();
        } else if (inputKey == 5) { //Duration
            sortDuration();
        } else if (inputKey == 6) { //Genre
            sortGenre();
        } else if (inputKey == 7) { //Multiply sort
            sortMultipleCriteria();
        }  //Ny else if med repeatMenu()?
    }

    public void sortTitle() {
        //Kalder sortering på liste via comparator
        Collections.sort(samling1.getInstanceMovieCollection().getMovieListe(), new titleComparator());
        System.out.println("You have successfully sorted your list by title:");
        for (Movie film : samling1.getInstanceMovieCollection().getMovieListe()) {
            System.out.println(film.toString2());
        }
    }

    public void sortDirector() {
        Collections.sort(samling1.getInstanceMovieCollection().getMovieListe(), new directorComparator());
        System.out.println("You have successfully sorted your list by director:");
        for (Movie film : samling1.getInstanceMovieCollection().getMovieListe()) {
            System.out.println(film.toStringDirector());
        }
    }

    public void sortYearCreated() {
        Collections.sort(samling1.getInstanceMovieCollection().getMovieListe(), new yearComparator());
        System.out.println("You have successfully sorted your list by premiere year:");
        for (Movie film : samling1.getInstanceMovieCollection().getMovieListe()) {
            System.out.println(film.toStringYear());
        }
    }

    public void sortColorised() {
        Collections.sort(samling1.getInstanceMovieCollection().getMovieListe(), new colorComparator());
        System.out.println("You have successfully sorted your list by whether or not the movie is colorised:");
        for (Movie film : samling1.getInstanceMovieCollection().getMovieListe()) {
            System.out.println(film.toStringIsInColor());
        }
    }

    public void sortDuration() {
        Collections.sort(samling1.getInstanceMovieCollection().getMovieListe(), new durationComparator());
        System.out.println("You have successfully sorted your list by duration:");
        for (Movie film : samling1.getInstanceMovieCollection().getMovieListe()) {
            System.out.println(film.toStringDuration());
        }
    }

    public void sortGenre() {
        Collections.sort(samling1.getInstanceMovieCollection().getMovieListe(), new genreComparator());
        System.out.println("You have successfully sorted your list by genre:");
        for (Movie film : samling1.getInstanceMovieCollection().getMovieListe()) {
            System.out.println(film.toStringGenre());
        }
    }


    public void sortMultipleCriteria() {
        System.out.println("Select the first criteria you wish to sort on.");
        displaySortMenu();
        String searchCriteria1 = "";
        String searchCriteria2 = "";
        int firstCriteria = ScanIntSafely();

        System.out.println("Select the second criteria you wish to sort on.");
        displaySortMenu();
        int secondCriteria = ScanIntSafely();

        Comparator<Movie> comparator = null;
        switch (firstCriteria) {
            case 1:
                comparator = new titleComparator();
                searchCriteria1 = "title";
                break;
            case 2:
                comparator = new directorComparator();
                searchCriteria1 = "director";
                break;
            case 3:
                comparator = new yearComparator();
                searchCriteria1 = "premiere year";
                break;
            case 4:
                comparator = new colorComparator();
                searchCriteria1 = "colorised";
                break;
            case 5:
                comparator = new durationComparator();
                searchCriteria1 = "duration";
                break;
            case 6:
                comparator = new genreComparator();
                searchCriteria1 = "genre";
                break;
            default:
                System.out.println("Only digits from 1-6 are accepted.");
                break;
        }

        switch (secondCriteria) {
            case 1:
                comparator = comparator.thenComparing(new titleComparator());
                searchCriteria2 = "title";
                break;
            case 2:
                comparator = comparator.thenComparing(new directorComparator());
                searchCriteria2 = "director";
                break;
            case 3:
                comparator = comparator.thenComparing(new yearComparator());
                searchCriteria2 = "premiere year";
                break;
            case 4:
                comparator = comparator.thenComparing(new colorComparator());
                searchCriteria2 = "colorised";
                break;
            case 5:
                comparator = comparator.thenComparing(new durationComparator());
                searchCriteria2 = "duration";
                break;
            case 6:
                comparator = comparator.thenComparing(new genreComparator());
                searchCriteria2 = "genre";
                break;
            default:
                System.out.println("Only digits from 1-6 are accepted.");
                break;
        }

        if (comparator != null) {
                        Collections.sort(samling1.getInstanceMovieCollection().getMovieListe(), comparator);
            System.out.println("You have successfully sorted your list by " + searchCriteria1 + " and " + searchCriteria2 + ":");
            for (Movie film : samling1.getInstanceMovieCollection().getMovieListe()){
                if(firstCriteria == 1) {
                    System.out.println(film.toString2());
                }
                if(firstCriteria == 2) {
                    System.out.println(film.toStringDirector());
                }
                if(firstCriteria == 3) {
                    System.out.println(film.toStringYear());
                }
                if(firstCriteria == 4) {
                    System.out.println(film.toStringIsInColor());
                }
                if(firstCriteria == 5) {
                    System.out.println(film.toStringDuration());
                }
                if(firstCriteria == 6) {
                    System.out.println(film.toStringGenre());
                }
            }

        }
    }
    ////-------HJÆLPEMETODE-------////


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










