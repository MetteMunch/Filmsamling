import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //create instance of MovieCollection
        MovieCollection samling1 = new MovieCollection();

        Scanner input = new Scanner(System.in);

        System.out.println("Angiv titel på filmen, som du vil tilføje:");
        String title = input.nextLine();

        System.out.println("Angiv director på filmen:");
        String director = input.nextLine();

        System.out.println("Angiv genre på filmen, vælg mellem action, komedie, gyser, romantik, krimi eller sci-fi:");
        String genre = input.nextLine();

        System.out.println("Hvilket år havde den premiere");
        int yearCreated = input.nextInt();

        System.out.println("Skriv true, hvis filmen er i farver:");
        boolean isInColor = input.nextBoolean();

        System.out.println("Hvor mange minutter varer filmen?");
        int lengthInMinutes  = input.nextInt();

        samling1.addMovie(title,director,yearCreated,isInColor, lengthInMinutes,genre);

    }
}