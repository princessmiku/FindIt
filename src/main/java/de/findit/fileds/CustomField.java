package de.findit.fileds;

public class CustomField {

    private final String fieldName, value;
    private float percentageInclusion = 0.2f, importantPercent = 0.7f, importantPercentMultiple = 1.5f;


    public CustomField(String fieldName, String value) {
        this.fieldName = fieldName.strip().toLowerCase();
        this.value = value.strip().toLowerCase();
    }
    public CustomField(String fieldName, String value, float percentageInclusion, float importantPercent, float importantPercentMultiple) {
        this.fieldName = fieldName.strip().toLowerCase();
        this.value = value.strip().toLowerCase();
        this.percentageInclusion = percentageInclusion;
        this.importantPercent = importantPercent;
        this.importantPercentMultiple = importantPercentMultiple;
    }

    public String getFieldName() {
        return fieldName;
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
