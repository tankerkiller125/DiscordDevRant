package ml.rhodes.libs.devrant;

public enum Range {
    DAY, WEEK, MONTH, ALL;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
