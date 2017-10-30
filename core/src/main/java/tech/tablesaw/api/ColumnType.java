package tech.tablesaw.api;


import tech.tablesaw.columns.Column;

/**
 * Defines the type of data held by a {@link Column}
 */
public enum ColumnType {

    BOOLEAN(Byte.MIN_VALUE),
    CATEGORY(""),
    FLOAT(Float.NaN),
    DOUBLE(Double.NaN),
    SHORT_INT(Short.MIN_VALUE),
    INTEGER(Integer.MIN_VALUE),
    LONG_INT(Long.MIN_VALUE),
    LOCAL_DATE(Integer.MIN_VALUE),
    LOCAL_DATE_TIME(Long.MIN_VALUE),
    LOCAL_TIME(-1),
    SKIP(null);

    private final Comparable<?> missingValue;

    ColumnType(Comparable<?> missingValue) {
        this.missingValue = missingValue;
    }

    public Comparable<?> getMissingValue() {
        return missingValue;
    }

    public static ColumnType getByType(String type) {
        for (ColumnType columnType : values()) {
            if (columnType.name().equalsIgnoreCase(type)) {
                return columnType;
            }
        }
        return null;
    }
    
}
