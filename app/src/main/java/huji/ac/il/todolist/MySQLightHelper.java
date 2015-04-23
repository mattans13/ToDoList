package huji.ac.il.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mattan on 21/04/2015.
 */
public class MySQLightHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "todo_db";
    public static final String TABLE_TASKS = "tasks";
    public static String COLUMN_ID = "_id";
    public static String COLUMN_TITLE = "title";
    public static String COLUMN_DUE = "due";

    public static int DB_VERSION = 1;

    private static String CREATE_DB = "create table" + TABLE_TASKS
                                                     + "(" + COLUMN_ID
                                                           + " integer primary key autoincrement, "
                                                           + COLUMN_TITLE + " text not null "
                                                           + COLUMN_DUE + " long not null);";

    public MySQLightHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
