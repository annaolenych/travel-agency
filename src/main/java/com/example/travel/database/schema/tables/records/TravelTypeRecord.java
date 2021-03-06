/*
 * This file is generated by jOOQ.
 */
package com.example.travel.database.schema.tables.records;


import com.example.travel.database.schema.tables.TravelType;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TravelTypeRecord extends UpdatableRecordImpl<TravelTypeRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>sql4458432.travel_type.type_id</code>.
     */
    public void setTypeId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>sql4458432.travel_type.type_id</code>.
     */
    public Integer getTypeId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>sql4458432.travel_type.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>sql4458432.travel_type.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return TravelType.TRAVEL_TYPE.TYPE_ID;
    }

    @Override
    public Field<String> field2() {
        return TravelType.TRAVEL_TYPE.NAME;
    }

    @Override
    public Integer component1() {
        return getTypeId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Integer value1() {
        return getTypeId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public TravelTypeRecord value1(Integer value) {
        setTypeId(value);
        return this;
    }

    @Override
    public TravelTypeRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public TravelTypeRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TravelTypeRecord
     */
    public TravelTypeRecord() {
        super(TravelType.TRAVEL_TYPE);
    }

    /**
     * Create a detached, initialised TravelTypeRecord
     */
    public TravelTypeRecord(Integer typeId, String name) {
        super(TravelType.TRAVEL_TYPE);

        setTypeId(typeId);
        setName(name);
    }
}
