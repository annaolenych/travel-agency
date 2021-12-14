/*
 * This file is generated by jOOQ.
 */
package com.example.travel.database.schema.tables;


import com.example.travel.database.schema.Keys;
import com.example.travel.database.schema.Sql4458432;
import com.example.travel.database.schema.tables.records.TransportRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Transport extends TableImpl<TransportRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>sql4458432.transport</code>
     */
    public static final Transport TRANSPORT = new Transport();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TransportRecord> getRecordType() {
        return TransportRecord.class;
    }

    /**
     * The column <code>sql4458432.transport.transport_id</code>.
     */
    public final TableField<TransportRecord, Integer> TRANSPORT_ID = createField(DSL.name("transport_id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>sql4458432.transport.name</code>.
     */
    public final TableField<TransportRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(45).nullable(false), this, "");

    private Transport(Name alias, Table<TransportRecord> aliased) {
        this(alias, aliased, null);
    }

    private Transport(Name alias, Table<TransportRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>sql4458432.transport</code> table reference
     */
    public Transport(String alias) {
        this(DSL.name(alias), TRANSPORT);
    }

    /**
     * Create an aliased <code>sql4458432.transport</code> table reference
     */
    public Transport(Name alias) {
        this(alias, TRANSPORT);
    }

    /**
     * Create a <code>sql4458432.transport</code> table reference
     */
    public Transport() {
        this(DSL.name("transport"), null);
    }

    public <O extends Record> Transport(Table<O> child, ForeignKey<O, TransportRecord> key) {
        super(child, key, TRANSPORT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Sql4458432.SQL4458432;
    }

    @Override
    public Identity<TransportRecord, Integer> getIdentity() {
        return (Identity<TransportRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<TransportRecord> getPrimaryKey() {
        return Keys.KEY_TRANSPORT_PRIMARY;
    }

    @Override
    public Transport as(String alias) {
        return new Transport(DSL.name(alias), this);
    }

    @Override
    public Transport as(Name alias) {
        return new Transport(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Transport rename(String name) {
        return new Transport(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Transport rename(Name name) {
        return new Transport(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
