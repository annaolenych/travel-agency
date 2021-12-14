/*
 * This file is generated by jOOQ.
 */
package com.example.travel.database.schema;


import com.example.travel.database.schema.tables.UserAccount;
import com.example.travel.database.schema.tables.records.UserAccountRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * travel_agency.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<UserAccountRecord> KEY_USER_ACCOUNT_ACCOUNT_ID_UNIQUE = Internal.createUniqueKey(UserAccount.USER_ACCOUNT, DSL.name("KEY_user_account_account_id_UNIQUE"), new TableField[] { UserAccount.USER_ACCOUNT.ACCOUNT_ID }, true);
    public static final UniqueKey<UserAccountRecord> KEY_USER_ACCOUNT_PRIMARY = Internal.createUniqueKey(UserAccount.USER_ACCOUNT, DSL.name("KEY_user_account_PRIMARY"), new TableField[] { UserAccount.USER_ACCOUNT.ACCOUNT_ID }, true);
    public static final UniqueKey<UserAccountRecord> KEY_USER_ACCOUNT_USERNAME_UNIQUE = Internal.createUniqueKey(UserAccount.USER_ACCOUNT, DSL.name("KEY_user_account_username_UNIQUE"), new TableField[] { UserAccount.USER_ACCOUNT.USERNAME }, true);
}
