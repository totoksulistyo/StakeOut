package totok.sulistyo.s0;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "stakeoutb.db";
    public static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table project(no INTEGER PRIMARY KEY AUTOINCREMENT null, nama_pro text null, nama_surveyor text null, tgl DATETIME DEFAULT CURRENT_TIMESTAMP);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO project (nama_pro, nama_surveyor) VALUES ('Poltekba', 'Totok');";
        db.execSQL(sql);
        sql = "create table data(id INTEGER PRIMARY KEY AUTOINCREMENT null, id_pro INTEGER null, code TEXT null, " +
                "azbs TEXT null," +
                "xa TEXT null, " +
                "ya TEXT null, " +
                "za TEXT null, " +
                "xso TEXT null, " +
                "yso TEXT null, " +
                "zso TEXT null, " +
                "hi TEXT null, " +
                "vd TEXT null, " +
                "vm TEXT null, " +
                "vs TEXT null, " +
                "ba TEXT null, " +
                "bt TEXT null, " +
                "bb TEXT null, " +
                "az TEXT null, " +
                "hd TEXT null, " +
                "fb TEXT null, " +
                "cf TEXT null )";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub


    }

}
