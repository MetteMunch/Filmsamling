import java.util.Scanner;

public class UserInterface {
    //create instance of Controller class
    Controller samling1 = new Controller();
    Scanner input = new Scanner(System.in);

    int SENTINEL = 5;
    int inputKey = 0;

    //Constructor
    public UserInterface() {
    }

    public void startProgram() {
//Jeg tilføjer lige nogle titler, så jeg har lidt at lege med...
        samling1.addMovie1("Burn After Reading", "Coen", 2008, true, 120, "Komedie");
        samling1.addMovie1("Godfather", "Coppola", 1972, true, 175, "Thriller");
        samling1.addMovie1("La vita e bella", "Benigni", 1997, true, 120, "Drama");
        userCommunication();

        while (inputKey != SENTINEL) {  //ændret til at bruge SENTINEL og ikke break. variabel menuChoice
            input.nextLine();//nødvendig for at kunne indsætte titel (scanner bug)

            if (inputKey == 4) {//i denne printer den en liste med samtlige film
                samling1.findAllMovies();

            } else if (inputKey == 2) {
                findFilm();
                //findFilmMedNyArray();

            } else if (inputKey == 1) {
                opretFilm();

            } else if (inputKey == 3) {
                redigerFilm();
            }
            gentagMenu();
        }
        System.out.println("Tak for denne gang");

    }

    public void userCommunication() {
        System.out.println("Velkommen til min filmsamling\n" +
                "Tryk 1, hvis du vil oprette en film\n" +
                "Tryk 2, hvis du vil søge på en filmtitel\n" +
                "Tryk 3, hvis du redigere en filmtitel\n" +
                "Tryk 4 hvis du vil se en fuld liste\n" +
                "tryk 5 hvis du vil afslutte");
        inputKey = input.nextInt();
    }

    public void opretFilm() {
        System.out.println("Angiv titel på filmen, som du vil tilføje:");
        String title = input.nextLine();// skal bruge nextLine når der kan være flere ord ellers er next ok

        System.out.println("Angiv director på filmen:");
        String director = input.nextLine();

        System.out.println("Angiv genre på filmen, vælg mellem action, thriller, drama, komedie, gyser, romantik, krimi eller sci-fi:");
        String genre = input.nextLine();

        System.out.println("Hvilket år havde den premiere");
        int yearCreated = input.nextInt();

        System.out.println("Skriv true, hvis filmen er i farver og false, hvis den s/h:");
        boolean isInColor = input.nextBoolean();

        System.out.println("Hvor mange minutter varer filmen?");
        int lengthInMinutes = input.nextInt();

        samling1.addMovie1(title, director, yearCreated, isInColor, lengthInMinutes, genre);
    }

    public void gentagMenu() {
        System.out.println("\nVil du tilføje en film mere så tryk 1\n" +
                "Vil du søge på en filmtitel så tryk 2\n" +
                "Vil du redigere en filmtitel så tryk 3\n" +
                "Vil du se en fuld liste så tryk 4\n" +
                "Vil du slutte programmet så tryk 5");
        inputKey = input.nextInt();
    }

    public void findFilm() {
        //user case 5, hvis der skal søges og udskrives med toString
        System.out.println("Skriv lidt af titlen på filmen, som du søger");
        String inputTitle = input.nextLine();
        System.out.println(samling1.findTitleMedToString(inputTitle));
    }

    public void redigerFilm() {
        //her kommer kode
    }

    public void findFilmMedNyArray() {
        System.out.println("Hvilken film søger du?");
        String inputTitle = input.nextLine();
        for (Movie movie : samling1.findTitle(inputTitle)) {
            if (!samling1.getInstanceMovieCollection().getSearchMatch().isEmpty()) {
                System.out.println(" Ja vi har filmen i samlingen: ");
                System.out.print(movie);
            } else {
                System.out.println("Desværre vi har ikke filmen");
            }
        }
    }
}










