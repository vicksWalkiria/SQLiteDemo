package com.walkiriaapps.sqlitedemo;

import android.provider.BaseColumns;

public final class CatContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private CatContract() {}

    /* Inner class that defines the table contents */
    public static class CatEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String STATUS = "status";
        public static final String AGE = "age";
    }
}
