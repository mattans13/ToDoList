package huji.ac.il.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;



public class TodolistManagerActivity extends ActionBarActivity {
    protected ArrayList<String> _tasks;
    protected MyArrayAdapter _adapter;
    protected ListView _listView;
    protected EditText _new_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist_manager);

        _tasks = new ArrayList<>();
        _adapter = new MyArrayAdapter(this, R.layout.even_row_view, _tasks);
        _listView = (ListView)findViewById(R.id.listView);
        _listView.setAdapter(_adapter);
        _listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TodolistManagerActivity.this);
                String msg = getResources().getString(R.string.delete_prefix) + " " +  _tasks.get(position) + "?";
                alertDialogBuilder.setMessage(msg);
                alertDialogBuilder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        _tasks.remove(position);
                        _adapter.notifyDataSetChanged();
                    }
                });
                alertDialogBuilder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialogBuilder.show();
                return true;
            }
        });
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
        _new_task = (EditText)findViewById(R.id.new_ask_EditText);
        String task = _new_task.getText().toString();
        _adapter.add(task);
        _new_task.setText("");
    }
}
