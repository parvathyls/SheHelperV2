package com.example.android.ruraldevelopmenthackathonv1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

/**
 * Created by psivapra on 2/21/2018.
 */

public final class ParentDBContract {
    private ParentDBContract() {
    }

    public static class ParentDB implements BaseColumns {
        public static final String ParentTbl = "rd_parent";

        public static final String parent_id = "parent_id";
        public static final String father_name = "father_name";
        public static final String father_age = "father_age";
        public static final String mother_name = "mother_name";
        public static final String mother_age = "mother_age";
        public static final String par_phone = "par_phone";
        public static final String par_email = "par_email";
        public static final String par_password = "par_password";
        public static final String par_em_contact_name = "par_em_contact_name";
        public static final String par_em_contact_phone="par_em_contact_phone";
        public static final String par_lang="par_lang";
        public static final String child_num="child_num";
        public static final String par_occupation="par_occupation";
        public static final String par_annual_income="par_annual_income";
        public static final String par_village="par_village";
        public static final String par_postalcode="par_postalcode";
        public static final String preg_months="preg_months";
        public static final String due_dt="due_dt";
        public static final String mat_blood_group="mat_blood_group";
        public static final String diabetes_yn="diabetes_yn";
        public static final String hypertension_yn="hypertension_yn";
        public static final String fibroid_yn="fibroid_yn";
        public static final String obesity_yn="obesity_yn";
        public static final String hormonal_yn="hormonal_yn";
        public static final String prior_miscarriage_yn="prior_miscarriage_yn";
        public static final String par_other="par_other";
        public static final String gyn_name="gyn_name";
        public static final String hosp_name="hosp_name";
        public static final String insurance_provider="insurance_provider";
        public static final String risk_status="risk_status";
        public static final String next_apptmt_dt="next_apptmt_dt";
        public static final String par_added_dt="par_added_dt";

        public static final String CREATE_PARENTTABLE = "CREATE TABLE IF NOT EXISTS " +
                ParentTbl + " (" +
                parent_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                father_name + " TEXT, " +
                father_age + " INTEGER, " +
                mother_name + " TEXT, " +
                mother_age + " INTEGER, " +
                par_phone + " INTEGER, " +
                par_email + " TEXT, " +
                par_password + "TEXT, " +
                par_em_contact_name + " TEXT, " +
                par_em_contact_phone + " INTEGER, " +
                par_lang + " TEXT, " +
                child_num + " INTEGER, " +
                par_occupation + " TEXT, " +
                par_annual_income + " INTEGER, " +
                par_village + " TEXT, " +
                par_postalcode + " INTEGER, " +
                preg_months + " INTEGER, " +
                due_dt + " TEXT, " +
                mat_blood_group + " TEXT, " +
                diabetes_yn + " TEXT, " +
                hypertension_yn + " TEXT, " +
                fibroid_yn + " TEXT, " +
                obesity_yn + " TEXT, " +
                hormonal_yn + " TEXT, " +
                prior_miscarriage_yn + " TEXT, " +
                par_other + " TEXT, " +
                gyn_name + " TEXT, " +
                hosp_name + " TEXT, " +
                insurance_provider + " TEXT, " +
                risk_status + " TEXT, " +
                next_apptmt_dt + " TEXT, " +
                par_added_dt + " TEXT default CURRENT_DATE"+
                ")";
    }


    public static class ParentDBSQLiteHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 4;
        public static final String DATABASE_NAME = "sample_database";

        public ParentDBSQLiteHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(ParentDBContract.ParentDB.CREATE_PARENTTABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ParentDBContract.ParentDB.ParentTbl);
            onCreate(sqLiteDatabase);
        }
    }
}
