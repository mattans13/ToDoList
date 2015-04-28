package huji.ac.il.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

    public void open() {
        this.database = this.dbHelper.getWritableDatabase();
    }

    public void close(){
        this.database.close();
    }

    public void addTaskToDB(Task task) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLightHelper.COLUMN_TITLE, task.getTask());
        contentValues.put(MySQLightHelper.COLUMN_DUE, task.getDateAsDate().getTime());
        task.setId(database.insert(MySQLightHelper.TABLE_TASKS, null, contentValues));
        close();
    }

    public void deleteTask(Task task){
        open();
        database.delete(MySQLightHelper.TABLE_TASKS, MySQLightHelper.COLUMN_ID + " = ?",
                new String[] {String.valueOf(task.getId())});
        close();
    }

    public ArrayList<Task> getAllTasks(){
        ArrayList<Task> tasks = new ArrayList<>();
        String selectAllQuery = "SELECT * FROM " + MySQLightHelper.TABLE_TASKS;
        open();

        Cursor cursor = database.rawQuery(selectAllQuery, null);
        //loop through query results
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            do {
                String taskTitle = cursor.getString(1);
                long taskEpoch = cursor.getLong(2);
                Date taskDate = new Date(taskEpoch);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(taskDate);

                Task newTask = new Task(taskTitle, calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.YEAR));
                newTask.setId(cursor.getLong(0));
                tasks.add(newTask);
            } while (cursor.moveToNext());
        }
        return tasks;
    }
}
