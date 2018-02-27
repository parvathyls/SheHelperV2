package com.example.android.ruraldevelopmenthackathonv1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by psivapra on 2/24/2018.
 */

public final class BabyDBContract {
    private BabyDBContract() {
    }

    public static class BabyDB implements BaseColumns {
        public static final String BabyTbl = "rd_baby";

        public static final String baby_id = "baby_id";
        public static final String parent_id = "parent_id";
        public static final String baby_dob = "baby_dob";
        public static final String baby_gender = "baby_gender";
        public static final String baby_name = "baby_name";
        public static final String baby_birthwt = "baby_birthwt";
        public static final String baby_wtnow = "baby_wtnow";
        public static final String baby_birthht = "baby_birthht";
        public static final String baby_htnow ="baby_htnow";
        public static final String lowwt_yn ="lowwt_yn";
        public static final String stunt_growth_yn ="stunt_growth_yn";
        public static final String autism_yn ="autism_yn";
        public static final String downs_yn ="downs_yn";
        public static final String baby_other ="baby_other";
        public static final String baby_health_sts ="baby_health_sts";
        public static final String baby_doc_name ="baby_doc_name";
        public static final String baby_hosp_name ="baby_hosp_name";
        public static final String baby_health_ins ="baby_health_ins";
        public static final String baby_nxt_apptmt ="baby_nxt_apptmt";
        public static final String baby_added_dt="baby_added_dt";

        public static final String CREATE_BABYTABLE = "CREATE TABLE IF NOT EXISTS " +
                BabyTbl + " (" +
                baby_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                parent_id + " INTEGER, " +
                baby_dob + " TEXT, " +
                baby_gender + " TEXT, " +
                baby_name + " TEXT, " +
                baby_birthwt + " REAL, " +
                baby_wtnow + " REAL, " +
                baby_birthht + " REAL, " +
                baby_htnow + " REAL, " +
                lowwt_yn + " TEXT, " +
                stunt_growth_yn + " TEXT, " +
                autism_yn + " TEXT, " +
                downs_yn + " TEXT, " +
                baby_other + " TEXT, " +
                baby_health_sts + " TEXT, " +
                baby_doc_name + " TEXT, " +
                baby_hosp_name + " TEXT, " +
                baby_health_ins + " TEXT, " +
                baby_nxt_apptmt + " TEXT, " +
                baby_added_dt + " TEXT default CURRENT_DATE"+
                ")";
    }


    public static class BabyDBSQLiteHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 5;
        public static final String DATABASE_NAME = "sample_database";

        public BabyDBSQLiteHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(BabyDBContract.BabyDB.CREATE_BABYTABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BabyDBContract.BabyDB.BabyTbl);
            onCreate(sqLiteDatabase);
        }
    }
}
