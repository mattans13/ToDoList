package huji.ac.il.todolist;


import android.graphics.Color;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Mattan.Shpaier on 29-Mar-15.
 * Represents a ToDoLinst task
 */
public class Task {
    private String task;
    private int day,month,year;
    private long id;

    public Task(String task, int day, int month, int year){
        setTask(task);
        setDay(day);
        setMonth(month);
        setYear(year);
        setId(0);
    }

    public void setId(long id){
        this.id = id;
    }

    public void setDay(int day){ this.day = day;  }

    public void setMonth(int month){ this.month = month; }

    public void setYear(int year){ this.year = year; }

    public void setTask(String task){
        this.task = task;
    }

    public String getTask(){
        return this.task;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public long getId(){
        return this.id;
    }

    @Override
    public String toString(){
        return getTask() + " " + getDateAsString();
    }

    public String getDateAsString(){
        return getDay() + "/" + getMonth() + "/" + getYear();
    }

    private String getDateAsStringForTaskOverdue(){return getDay()+1 + "/" + getMonth() + "/" + getYear();}

    public Date getDateAsDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy");
        Date taskDate = null;
        try {
            taskDate = dateFormat.parse(this.getDateAsStringForTaskOverdue());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return taskDate;
    }

    public boolean isTaskOverdue(){
        Date taskDate = getDateAsDate();
        Calendar calendar = Calendar.getInstance();

        if(calendar.getTime().after(taskDate)){
            return true;
        }
        return false;
    }
}
