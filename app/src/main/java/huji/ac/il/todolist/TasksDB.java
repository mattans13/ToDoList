package huji.ac.il.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Mattan on 21/04/2015.
 */
public class TasksDB {
    private SQLiteDatabase database;
    private MySQLightHelper dbHelper;
    private String[] columns = {MySQLightHelper.COLUMN_ID,
                                MySQLightHelper.COLUMN_TITLE,
                                MySQLightHelper.COLUMN_DUE};
    public TasksDB(Context context){
        this.dbHelper = new MySQLightHelper(context);
    }

    public void open() throws SQLException{
        this.database = this.dbHelper.getWritableDatabase();
    }

    public void close(){
        this.database.close();
    }

    public Task createTask(String title, int year, int month, int day){
        Task task = null;

        return task;
    }


}
