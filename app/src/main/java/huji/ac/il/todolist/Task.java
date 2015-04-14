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

    public Task(String task, int day, int month, int year){
        setTask(task);
        setDay(day);
        setMonth(month);
        setYear(year);
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

    @Override
    public String toString(){
        return getTask() + " " + getDateAsString();
    }

    public String getDateAsString(){
        return getDay() + "/" + getMonth() + "/" + getYear();
    }

    private String getDateAsStringForTaskOverdue(){return getDay()+1 + "/" + getMonth() + "/" + getYear();}

    public boolean isTaskOverdue(){
        DateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            Date taskDate = dateFormat.parse(this.getDateAsStringForTaskOverdue());
            if(calendar.getTime().after(taskDate)){
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
