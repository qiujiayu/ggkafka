package oracle.goldengate.source;

import oracle.goldengate.connector.ConnectRecord;
import oracle.goldengate.common.data.Schema;

import java.util.Map;

/**
 * <p>
 * SourceRecords are generated by SourceTasks and passed to Kafka Connect for storage in
 * Kafka. In addition to the standard fields in {@link ConnectRecord} which specify where data is stored
 * in Kafka, they also include a sourcePartition and sourceOffset.
 * </p>
 * <p>
 * The sourcePartition represents a single input sourcePartition that the record came from (e.g. a filename, table
 * name, or topic-partition). The sourceOffset represents a position in that sourcePartition which can be used
 * to resume consumption of data.
 * </p>
 * <p>
 * These values can have arbitrary structure and should be represented using
 * org.apache.kafka.connect.data objects (or primitive values). For example, a database connector
 * might specify the sourcePartition as a record containing { "db": "database_name", "table":
 * "table_name"} and the sourceOffset as a Long containing the timestamp of the row.
 * </p>
 */
public class SourceRecord extends ConnectRecord<SourceRecord> {
    private final Map<String, ?> sourcePartition;
    private final Map<String, ?> sourceOffset;

    public SourceRecord(Map<String, ?> sourcePartition, Map<String, ?> sourceOffset,
                        String topic, Integer partition, Schema valueSchema, Object value) {
        this(sourcePartition, sourceOffset, topic, partition, null, null, valueSchema, value);
    }

    public SourceRecord(Map<String, ?> sourcePartition, Map<String, ?> sourceOffset,
                        String topic, Schema valueSchema, Object value) {
        this(sourcePartition, sourceOffset, topic, null, null, null, valueSchema, value);
    }

    public SourceRecord(Map<String, ?> sourcePartition, Map<String, ?> sourceOffset,
                        String topic, Schema keySchema, Object key, Schema valueSchema, Object value) {
        this(sourcePartition, sourceOffset, topic, null, keySchema, key, valueSchema, value);
    }

    public SourceRecord(Map<String, ?> sourcePartition, Map<String, ?> sourceOffset,
                        String topic, Integer partition,
                        Schema keySchema, Object key, Schema valueSchema, Object value) {
        this(sourcePartition, sourceOffset, topic, partition, keySchema, key, valueSchema, value, null);
    }

    public SourceRecord(Map<String, ?> sourcePartition, Map<String, ?> sourceOffset,
                        String topic, Integer partition,
                        Schema keySchema, Object key,
                        Schema valueSchema, Object value,
                        Long timestamp) {
        super(topic, partition, keySchema, key, valueSchema, value, timestamp);
        this.sourcePartition = sourcePartition;
        this.sourceOffset = sourceOffset;
    }

    public Map<String, ?> sourcePartition() {
        return sourcePartition;
    }

    public Map<String, ?> sourceOffset() {
        return sourceOffset;
    }

    @Override
    public SourceRecord newRecord(String topic, Integer kafkaPartition, Schema keySchema, Object key, Schema valueSchema, Object value, Long timestamp) {
        return new SourceRecord(sourcePartition, sourceOffset, topic, kafkaPartition, keySchema, key, valueSchema, value, timestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        SourceRecord that = (SourceRecord) o;

        if (sourcePartition != null ? !sourcePartition.equals(that.sourcePartition) : that.sourcePartition != null)
            return false;
        if (sourceOffset != null ? !sourceOffset.equals(that.sourceOffset) : that.sourceOffset != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (sourcePartition != null ? sourcePartition.hashCode() : 0);
        result = 31 * result + (sourceOffset != null ? sourceOffset.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SourceRecord{" +
                "sourcePartition=" + sourcePartition +
                ", sourceOffset=" + sourceOffset +
                "} " + super.toString();
    }
}
