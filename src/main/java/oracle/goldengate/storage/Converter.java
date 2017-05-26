package oracle.goldengate.storage;

import oracle.goldengate.common.data.Schema;
import oracle.goldengate.common.data.SchemaAndValue;

import java.util.Map;

/**
 * The Converter interface provides support for translating between Kafka Connect's runtime data format
 * and byte[]. Internally, this likely includes an intermediate step to the format used by the serialization
 * layer (e.g. JsonNode, GenericRecord, Message).
 */
public interface Converter {

    /**
     * Configure this class.
     * @param configs configs in key/value pairs
     * @param isKey whether is for key or value
     */
    void configure(Map<String, ?> configs, boolean isKey);

    /**
     * Convert a Kafka Connect data object to a native object for serialization.
     * @param topic the topic associated with the data
     * @param schema the schema for the value
     * @param value the value to convert
     * @return the serialized value
     */
    byte[] fromConnectData(String topic, Schema schema, Object value);

    /**
     * Convert a native object to a Kafka Connect data object.
     * @param topic the topic associated with the data
     * @param value the value to convert
     * @return an object containing the {@link Schema} and the converted value
     */
    SchemaAndValue toConnectData(String topic, byte[] value);
}