package domain_model;

// ENUM er en special klasse der repræsenter en gruppe af konstante variabler
public enum Genre {
    ACTION ("Action"),
    COMEDY ("Comedy"),
    ROMANCE ("Romance"),
    DRAMA ("Drama"),
    HORROR ("Horror"),
    THRILLER ("Thriller"),
    SCIFI ("Scifi"),
    CRIME ("Crime");

   //Attribute
    private String displayName;

    // Metode
    Genre(String displayName) {
        this.displayName = displayName;
    }

    // To-string der returner displayName
    @Override
    public String toString() {
        return displayName;
    }
}
