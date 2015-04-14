package huji.ac.il.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mattan.Shpaier on 19-Mar-15.
 */
public class AddNewTodoItemActivity extends Activity {
    final int RESULT_OK = 1;
    final int RESULT_NOT_OK = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button addButton = (Button)findViewById(R.id.btnOK);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                EditText task = (EditText)findViewById(R.id.edtNewItem);
                DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker);
                String dateInString = datePicker.getDayOfMonth() + "/" + (datePicker.getMonth()+1) + "/" + datePicker.getYear();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = null;
                try {
                    date = dateFormat.parse(dateInString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                intent.putExtra("title", task.getText().toString());
                if(null != date){
                    intent.putExtra("dueDate", date);
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Button cancelButton = (Button)findViewById(R.id.btnCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_NOT_OK);
                finish();
            }
        });
    }

}
