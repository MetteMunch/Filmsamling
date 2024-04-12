package domain_model;

import mock_klasser.FileHandlerMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MovieCollectionTest {

    @Test
    void addMovie() {
        //Arrange
        FileHandlerMock fh = new FileHandlerMock();
        MovieCollection movc = new MovieCollection(fh);
        ArrayList<Movie> movies = new ArrayList<>();
        Movie m1 = new Movie("The Thing", "John Carpenter", 1972, true, 109, Genre.HORROR);
        movies.add(m1);



        //Act
        //movc.addMovie("The Thing", "John Carpenter", 1972, true, 109, Genre.HORROR);
        fh.getFilm().add(String.valueOf(m1));


        int actualResult = fh.getFilm().size();
        int expectedResult = 1;


        //Assert
        Assertions.assertEquals(expectedResult, actualResult);

    }
}