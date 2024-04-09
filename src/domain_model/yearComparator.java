package domain_model;

import java.util.Comparator;

public class yearComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        if(o1.getYearCreated() == o2.getYearCreated()) {
            return 0;
        } else if(o1.getYearCreated()>o2.getYearCreated()) {
            return 1;
        } else {
            return -1;
        }

    }
}
