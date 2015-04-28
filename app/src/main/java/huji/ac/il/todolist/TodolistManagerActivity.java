package huji.ac.il.todolist;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class TodolistManagerActivity extends Activity {
    protected ArrayList<Task> tasks;
    protected MyArrayAdapter adapter;
    protected ListView listView;
    protected TasksDB tasksDB;
    private final String TASK_KEY = "title";
    private final String DATE_KEY = "dueDate";
    private final String CALL = "call";
    private final String CALL_CAP = "Call";
    private final int RESULT_FROM_ADD_ITEM = 1;

    final int RESULT_OK = 1;
    final int RESULT_NOT_OK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist_manager);

        this.tasksDB = new TasksDB(this);
        this.tasks = tasksDB.getAllTasks();
        this.adapter = new MyArrayAdapter(this, this.tasks);
        this.listView = (ListView)findViewById(R.id.list);
        this.listView.setAdapter(this.adapter);

        registerForContextMenu(this.listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        this.getMenuInflater().inflate(R.menu.costume_context_menu, menu);
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final int itemPosition = acmi.position;
        final String curTaskString = this.tasks.get(itemPosition).getTask();

        menu.setHeaderTitle(curTaskString);

        MenuItem itemDelete = menu.findItem(R.id.menuItemDelete);
        itemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                getTasks().remove(itemPosition);
                tasksDB.deleteTask(getTasks().get(itemPosition));
                getAdapter().notifyDataSetChanged();
                return true;
            }
        });
        if (curTaskString.startsWith(CALL)|| curTaskString.startsWith(CALL_CAP)){
            MenuItem callItem = menu.findItem(R.id.menuItemCall);
            callItem.setTitle(curTaskString);
            callItem.setVisible(true);

            MenuItem itemCall = menu.findItem(R.id.menuItemCall);
            itemCall.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    String numberToDial = curTaskString.split("\\s")[1];
                    callIntent.setData(Uri.parse("tel:" +numberToDial.trim()));
                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(callIntent);
                    return true;
                }
            });

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todolist_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.add_button:
                onAddItem();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onAddItem() {
        Intent intent = new Intent(getApplicationContext(), AddNewTodoItemActivity.class);
        startActivityForResult(intent, RESULT_FROM_ADD_ITEM);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case RESULT_FROM_ADD_ITEM:
                    Date date = (Date)data.getSerializableExtra(DATE_KEY);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    int day, month, year;
                    day = calendar.get(Calendar.DAY_OF_MONTH);
                    month = calendar.get(Calendar.MONTH)+1;
                    year = calendar.get(Calendar.YEAR);
                    Task task = new Task(data.getStringExtra(TASK_KEY), day, month, year);
                    this.tasksDB.addTaskToDB(task);
                    this.adapter.add(task);
                    this.adapter.notifyDataSetChanged();
            }
        }
    }
    private ArrayList<Task> getTasks(){ return this.tasks; }

    private MyArrayAdapter getAdapter(){ return this.adapter; }
}
/*
 * TODO
 * 1. change main activity view so it has 2 text views instead of one
 * 2. set new activity window according to ex description
 *  2.1 create dialog activity
 *  2.2 create date picker
 *  2.3 write 2 extras: task and date
 *  2.4 set the result
 * 3. in adapter - check date for each task and mark past due date tasks with red color
 */