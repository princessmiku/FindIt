package de.findit.fileds;

public class CustomField {

    private final String value;
    private float percentageInclusion = 0.2f, importantPercent = 0.7f, importantPercentMultiple = 1.5f;


    public CustomField(String value) {
        this.value = value;
    }
    public CustomField(String value, float percentageInclusion, float importantPercent, float importantPercentMultiple) {
        this.value = value;
        this.percentageInclusion = percentageInclusion;
        this.importantPercent = importantPercent;
        this.importantPercentMultiple = importantPercentMultiple;
    }

    public String getValue() {
        return value;
    }

    public float getPercentageInclusion() {
        return percentageInclusion;
    }

    public float getImportantPercent() {
        return importantPercent;
    }

    public float getImportantPercentMultiple() {
        return importantPercentMultiple;
    }
}
