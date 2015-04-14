package huji.ac.il.todolist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Mattan.Shpaier on 15-Mar-15.
 */
public class MyArrayAdapter extends ArrayAdapter {

    ArrayList<Task> tasks;

    public MyArrayAdapter(Context context, ArrayList<Task> tasks){
        super(context, R.layout.row_view,tasks);
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View rowView = convertView;

        if(rowView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            rowView = inflater.inflate(R.layout.row_view, null);
        }

        Task task = tasks.get(position);

        if(null != task){
            TextView taskName = (TextView)rowView.findViewById(R.id.txtTodoTitle);
            TextView taskDueDate = (TextView)rowView.findViewById(R.id.txtTodoDueDate);

            if(null != taskName)
                taskName.setText(task.getTask());

            if(null != taskDueDate){
                taskDueDate.setText(task.getDateAsString());
                if( task.isTaskOverdue()) {
                    taskDueDate.setTextColor(Color.RED);
                }
            }
        }

        return rowView;
    }
}
