package domain_model;

import java.util.Comparator;

public class colorComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return Boolean.compare(o1.getIsInColor(), o2.getIsInColor());
    }
}
