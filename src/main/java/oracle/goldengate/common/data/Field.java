package oracle.goldengate.common.data;

import java.util.Objects;

/**
 * <p>
 *     A field in a {@link Struct}, consisting of a field name, index, and {@link Schema} for the field value.
 * </p>
 */
public class Field {
    private final String name;
    private final int index;
    private final Schema schema;

    public Field(String name, int index, Schema schema) {
        this.name = name;
        this.index = index;
        this.schema = schema;
    }

    /**
     * Get the name of this field.
     * @return the name of this field
     */
    public String name() {
        return name;
    }


    /**
     * Get the index of this field within the struct.
     * @return the index of this field
     */
    public int index() {
        return index;
    }

    /**
     * Get the schema of this field
     * @return the schema of values of this field
     */
    public Schema schema() {
        return schema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return Objects.equals(index, field.index) &&
                Objects.equals(name, field.name) &&
                Objects.equals(schema, field.schema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, index, schema);
    }
}