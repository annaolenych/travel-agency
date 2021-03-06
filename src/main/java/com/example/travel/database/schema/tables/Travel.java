/*
 * This file is generated by jOOQ.
 */
package com.example.travel.database.schema.tables;


import com.example.travel.database.schema.Indexes;
import com.example.travel.database.schema.Keys;
import com.example.travel.database.schema.Sql4458432;
import com.example.travel.database.schema.tables.records.TravelRecord;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row10;
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
public class Travel extends TableImpl<TravelRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>sql4458432.travel</code>
     */
    public static final Travel TRAVEL = new Travel();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TravelRecord> getRecordType() {
        return TravelRecord.class;
    }

    /**
     * The column <code>sql4458432.travel.travel_id</code>.
     */
    public final TableField<TravelRecord, Integer> TRAVEL_ID = createField(DSL.name("travel_id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>sql4458432.travel.user_id</code>.
     */
    public final TableField<TravelRecord, Integer> USER_ID = createField(DSL.name("user_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>sql4458432.travel.customer_id</code>.
     */
    public final TableField<TravelRecord, Integer> CUSTOMER_ID = createField(DSL.name("customer_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>sql4458432.travel.type_id</code>.
     */
    public final TableField<TravelRecord, Integer> TYPE_ID = createField(DSL.name("type_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>sql4458432.travel.country_id</code>.
     */
    public final TableField<TravelRecord, Integer> COUNTRY_ID = createField(DSL.name("country_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>sql4458432.travel.hotel_id</code>.
     */
    public final TableField<TravelRecord, Integer> HOTEL_ID = createField(DSL.name("hotel_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>sql4458432.travel.transport_id</code>.
     */
    public final TableField<TravelRecord, Integer> TRANSPORT_ID = createField(DSL.name("transport_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>sql4458432.travel.nutrition_id</code>.
     */
    public final TableField<TravelRecord, Integer> NUTRITION_ID = createField(DSL.name("nutrition_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>sql4458432.travel.arrival</code>.
     */
    public final TableField<TravelRecord, LocalDate> ARRIVAL = createField(DSL.name("arrival"), SQLDataType.LOCALDATE.nullable(false), this, "");

    /**
     * The column <code>sql4458432.travel.departure</code>.
     */
    public final TableField<TravelRecord, LocalDate> DEPARTURE = createField(DSL.name("departure"), SQLDataType.LOCALDATE.nullable(false), this, "");

    private Travel(Name alias, Table<TravelRecord> aliased) {
        this(alias, aliased, null);
    }

    private Travel(Name alias, Table<TravelRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>sql4458432.travel</code> table reference
     */
    public Travel(String alias) {
        this(DSL.name(alias), TRAVEL);
    }

    /**
     * Create an aliased <code>sql4458432.travel</code> table reference
     */
    public Travel(Name alias) {
        this(alias, TRAVEL);
    }

    /**
     * Create a <code>sql4458432.travel</code> table reference
     */
    public Travel() {
        this(DSL.name("travel"), null);
    }

    public <O extends Record> Travel(Table<O> child, ForeignKey<O, TravelRecord> key) {
        super(child, key, TRAVEL);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Sql4458432.SQL4458432;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.TRAVEL_COUNTRYID_IDX, Indexes.TRAVEL_CUSTOMERID_IDX, Indexes.TRAVEL_HOTELID_IDX, Indexes.TRAVEL_NUTRITIONID_IDX, Indexes.TRAVEL_TRANSPORTID_IDX, Indexes.TRAVEL_TYPEID_IDX, Indexes.TRAVEL_USERID_IDX);
    }

    @Override
    public Identity<TravelRecord, Integer> getIdentity() {
        return (Identity<TravelRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<TravelRecord> getPrimaryKey() {
        return Keys.KEY_TRAVEL_PRIMARY;
    }

    @Override
    public List<ForeignKey<TravelRecord, ?>> getReferences() {
        return Arrays.asList(Keys.TRAVEL_IBFK_1, Keys.TRAVEL_IBFK_2, Keys.TRAVEL_IBFK_3, Keys.TRAVEL_IBFK_4, Keys.TRAVEL_IBFK_5, Keys.TRAVEL_IBFK_6, Keys.TRAVEL_IBFK_7);
    }

    private transient UserAccount _userAccount;
    private transient Customer _customer;
    private transient TravelType _travelType;
    private transient Country _country;
    private transient Hotel _hotel;
    private transient Transport _transport;
    private transient Nutrition _nutrition;

    public UserAccount userAccount() {
        if (_userAccount == null)
            _userAccount = new UserAccount(this, Keys.TRAVEL_IBFK_1);

        return _userAccount;
    }

    public Customer customer() {
        if (_customer == null)
            _customer = new Customer(this, Keys.TRAVEL_IBFK_2);

        return _customer;
    }

    public TravelType travelType() {
        if (_travelType == null)
            _travelType = new TravelType(this, Keys.TRAVEL_IBFK_3);

        return _travelType;
    }

    public Country country() {
        if (_country == null)
            _country = new Country(this, Keys.TRAVEL_IBFK_4);

        return _country;
    }

    public Hotel hotel() {
        if (_hotel == null)
            _hotel = new Hotel(this, Keys.TRAVEL_IBFK_5);

        return _hotel;
    }

    public Transport transport() {
        if (_transport == null)
            _transport = new Transport(this, Keys.TRAVEL_IBFK_6);

        return _transport;
    }

    public Nutrition nutrition() {
        if (_nutrition == null)
            _nutrition = new Nutrition(this, Keys.TRAVEL_IBFK_7);

        return _nutrition;
    }

    @Override
    public Travel as(String alias) {
        return new Travel(DSL.name(alias), this);
    }

    @Override
    public Travel as(Name alias) {
        return new Travel(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Travel rename(String name) {
        return new Travel(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Travel rename(Name name) {
        return new Travel(name, null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, LocalDate, LocalDate> fieldsRow() {
        return (Row10) super.fieldsRow();
    }
}
