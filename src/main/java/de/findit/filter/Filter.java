package de.findit.filter;

public abstract class Filter {

    private final boolean exclude, exactly;

    /**
     * @param exclude if it should complete exclude the record from the search if it does not fit
     * @param exactly must it fit completely into the scheme or may there be some deviation
     */
    public Filter(boolean exclude, boolean exactly) {
        this.exclude = exclude;
        this.exactly = exactly;
    }

    public abstract boolean check(String query);

}
