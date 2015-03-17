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

import java.util.ArrayList;

/**
 * Created by Mattan.Shpaier on 15-Mar-15.
 */
public class MyArrayAdapter extends ArrayAdapter {
    Context context;
    int layoutResourceId;
    ArrayList<String> tasks;
    static int location;

    public MyArrayAdapter(Context context, int resource, ArrayList tasks){
        super(context,resource,tasks);
        this.layoutResourceId = resource;
        this.context = context;
        this.tasks = tasks;
        this.location = 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        TextHolder holder;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new TextHolder();
            holder.textView = (TextView) row.findViewById(R.id.evenRowTextView);
            row.setTag(holder);
        }
        else{
            holder = (TextHolder)row.getTag();

        }
        if(position % 2 == 0){
            holder.textView.setTextColor(Color.RED);
        }
        else{
            holder.textView.setTextColor(Color.BLUE);
        }
        holder.textView.setText(this.tasks.get(position));
        return row;
    }
    static  class TextHolder{
        TextView textView;
    }
}
