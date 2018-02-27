
        package com.example.android.ruraldevelopmenthackathonv1;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.provider.BaseColumns;
        import android.util.Log;

        /**
 * Created by psivapra on 2/16/2018.
 */

public final class SampleDBContract {
    private SampleDBContract() {
    }

    public static class SSPerson implements BaseColumns {
        public static final String TABLE_NAME = "rd_ss_person";

        public static final String SSID = "ss_id";
        public static final String SSName = "ss_name";
        public static final String SSEmail = "ss_email";
        public static final String SSPhone = "ss_phone";
        public static final String SSPassword = "ss_password";
        public static final String SSLang = "ss_lang";
        public static final String SSVillage = "ss_village";
        public static final String SSPostal = "ss_postalcode";
        public static final String SSAddedDate="ss_added_dt";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                SSID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SSName + " TEXT, " +
                SSEmail + " TEXT, " +
                SSPhone + " INTEGER, " +
                SSPassword + " TEXT, " +
                SSLang + " TEXT, " +
                SSVillage + " TEXT, " +
                SSPostal + " INTEGER, " +
                SSAddedDate + " INTEGER" + ")";
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
    public static class MotherApptmt implements BaseColumns {
        public static final String MotherApptmtTbl = "rd_mother_apptmt";

        public static final String mother_apptmt_id = "apptmt_id";
        public static final String apptmt_parent_id = "parent_id";
        public static final String mother_apptmt_dt = "apptmt_dt";
        public static final String mother_apptmt_done="done_flag";
        public static final String CREATE_MOTHER_APPTMT = "CREATE TABLE IF NOT EXISTS " +
                MotherApptmtTbl + " (" +
                mother_apptmt_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                apptmt_parent_id + " INTEGER, " +
                mother_apptmt_dt + " TEXT, " +
                mother_apptmt_done + " TEXT " +
                ")";
    }
    public static class BabyApptmt implements BaseColumns {
        public static final String BabyApptmtTbl = "rd_baby_apptmt";

        public static final String apptmt_id = "apptmt_id";
        public static final String apptmt_baby_id = "baby_id";
        public static final String baby_apptmt_dt = "apptmt_dt";
        public static final String baby_apptmt_done="done_flag";
        public static final String CREATE_BABY_APPTMT = "CREATE TABLE IF NOT EXISTS " +
                BabyApptmtTbl + " (" +
                apptmt_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                apptmt_baby_id + " INTEGER, " +
                baby_apptmt_dt + " TEXT, " +
                baby_apptmt_done + " TEXT " +
                ")";

    }

    public static class SampleDBSQLiteHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 5;
        public static final String DATABASE_NAME = "sample_database";

        public SampleDBSQLiteHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(SampleDBContract.SSPerson.CREATE_TABLE);
            sqLiteDatabase.execSQL(ParentDB.CREATE_PARENTTABLE);
            sqLiteDatabase.execSQL(BabyDB.CREATE_BABYTABLE);
            sqLiteDatabase.execSQL(MotherApptmt.CREATE_MOTHER_APPTMT);
            sqLiteDatabase.execSQL(BabyApptmt.CREATE_BABY_APPTMT);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SampleDBContract.SSPerson.TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ParentDB.ParentTbl);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BabyDB.BabyTbl);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MotherApptmt.MotherApptmtTbl);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BabyApptmt.BabyApptmtTbl);
            onCreate(sqLiteDatabase);
        }
    }
}
