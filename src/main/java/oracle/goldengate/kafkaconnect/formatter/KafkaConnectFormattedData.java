/*
 *
 * Copyright (c) 2016, Oracle and/or its affiliates. All rights reserved.
 *
 */
package oracle.goldengate.kafkaconnect.formatter;

import oracle.goldengate.common.data.Struct;
import oracle.goldengate.format.NgFormattedData;


public class KafkaConnectFormattedData implements NgFormattedData {
    private static final int numRecords=2;
    private Struct records[];
    private Struct keys[];
    
    public KafkaConnectFormattedData(){
        records = new Struct[this.numRecords];
        keys = new Struct[this.numRecords];
    }

    @Override
    public int size() {
        int ctr = 0;
        for (int i = 0; i < this.numRecords; i++) {
            if (records[i] != null) {
                ctr++;
            }
        }
        return ctr;
    }

    @Override
    public byte[] getBytes(int i) {
        throw new UnsupportedOperationException("Cannot get message as bytes.");
    }

    @Override
    public String getString(int i) {
        throw new UnsupportedOperationException("Cannot get message as String."); 
    }

    @Override
    public String getSchemaAsString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] getSchemaAsBytes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getWrapperSchemaAsString() {
        throw new UnsupportedOperationException("Wrapper Schema is unsupported.");
    }

    @Override
    public byte[] getWrapperSchemaAsBytes() {
        throw new UnsupportedOperationException("Wrapper Schema is unsupported.");
    }

    @Override
    public int getPayloadSchemaHashCode() {
        throw new UnsupportedOperationException("no");
    }

    @Override
    public String getSchemaSuffix() {
        return ".avsc";
    }

    /**
     * Method to set a payload record struct
     * @param record A payload record struct
     */
    public void setRecord(Struct record){
        int i=0;

	while (i < this.numRecords) {
	    if (records[i] == null) {
		records[i] = record;
	        break;
	    }
            i++;
        }
    }
    
    /**
     * Method to set a key record struct
     * @param key A key record struct
     */
    public void setKey(Struct key){
        int i = 0;
        while (i < this.numRecords){
            if (keys[i] == null){
                keys[i] = key;
                break;
            }
            i++;
        }
    }
    
    /**
     * Method to get the payload record struct at the index.
     * @param index The index
     * @return The payload record struct at the index or null if no record.
     */
    public Struct getRecord(int index){
        if (index < this.numRecords) {
            return (records[index]);
        }
        return null;
    }
    
    /**
     * Methdod to get the key record struct at the index.
     * @param index The index
     * @return The key record struct at the index or null if no record.
     */
    public Struct getKey(int index){
        if (index < this.numRecords){
            return keys[index];
        }
        return null;
    }
    
}
