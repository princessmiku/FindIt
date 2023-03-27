package de.findit.filter;

public class RangeFilter extends Filter {


    /**
     * Add a filter for check if the record matching in a specific range
     * @param start Start number of the range
     * @param end ending number of the range
     * @param exclude if it should complete exclude the record from the search if it does not fit
     */
    public RangeFilter(double start, double end, boolean exclude) {
        super(exclude, true);
    }

    @Override
    public boolean check(String query) {
        return false;
    }
}
