package data_source;

import domain_model.Genre;
import domain_model.MovieCollection;
import mock_klasser.FileHandlerInterface;
import mock_klasser.FileHandlerMock;
import org.junit.jupiter.api.Test;

class FileHandlerTest {

    @Test
    void saveListOfMovies() {
        //Arrange
       FileHandlerInterface fh = new FileHandlerMock();
        MovieCollection movc = new MovieCollection(fh);

        //Act


        //Assert


        //Movie m1 = new Movie("The Thing", "John Carpenter", 1972, true, 109, Genre.HORROR);
        movc.addMovie("The Thing", "John Carpenter", 1972, true, 109, Genre.HORROR);



    }
}