import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {

    @Test
        //søgning flere film
    void addMovie() {
        //Arrange
        MovieCollection nyFilmSamling = new MovieCollection();
        nyFilmSamling.addMovie("Burn after reading", "Coen", 2008, true, 120, "Komedie");
        nyFilmSamling.addMovie("Pulp Fiction", "Tarantino", 1994, true, 120, "Thriller");
        nyFilmSamling.addMovie("GoodFellas", "Scorsese", 1990, true, 146, "Drama");
        nyFilmSamling.addMovie("Livet er smukt", "Benigni", 1999, true, 120, "Drama");

        //Act
        int actualSizeOfList = nyFilmSamling.movieListe.size();

        //Assert
        int expectedSizeOfList = 4;
        Assertions.assertEquals(expectedSizeOfList, actualSizeOfList);
    }

    @Test
        //test af SearchMovie metoden, som gemmer i en ny Array kaldet searchMatch.
    void searchMovie() {
        //Arrange
        MovieCollection nyFilmSamling = new MovieCollection();
        nyFilmSamling.addMovie("Burn after reading", "Coen", 2008, true, 120, "Komedie");
        nyFilmSamling.addMovie("Pulp Fiction", "Tarantino", 1994, true, 120, "Thriller");
        nyFilmSamling.addMovie("Filmtitel1", "Tarantino", 1994, true, 120, "Thriller");
        nyFilmSamling.addMovie("Filmtitel2", "Tarantino", 1994, true, 120, "Thriller");

        //Act
        nyFilmSamling.searchMovie("Film");
        int actualValue = nyFilmSamling.searchMatch.size();
        //Assert

        int expectedValue = 2;
        Assertions.assertEquals(expectedValue,actualValue);
    }

}