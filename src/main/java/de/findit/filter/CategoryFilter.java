package de.findit.filter;

public class CategoryFilter extends Filter{

    private final String name;
    /**
     * @param name    name of the category
     * @param exclude if it should complete exclude the record from the search if it does not fit
     * @param exactly must it fit completely into the scheme or may there be some deviation
     */
    public CategoryFilter(String name, boolean exclude, boolean exactly, String name1) {
        super(exclude, exactly);
        this.name = name;
    }

    @Override
    public boolean check(String query) {
        return false;
    }
}
