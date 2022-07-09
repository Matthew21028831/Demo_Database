package sg.edu.rp.c36.id21028831.demodatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    ListView lvResults;
    EditText inputDate, inputTask;
    TextView tvResult;

    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert=findViewById(R.id.btnInsert);
        btnGetTasks=findViewById(R.id.btnGetTasks);
        inputDate=findViewById(R.id.inputDate);
        inputTask=findViewById(R.id.inputTask);
        lvResults=findViewById(R.id.lvResults);
        tvResult=findViewById(R.id.tvResult);

        alTasks=new ArrayList<>();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db=new DBHelper(MainActivity.this);
                String task=inputTask.getText().toString();
                String date=inputDate.getText().toString();
                db.insertTask(task, date);
                db.close();

            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db=new DBHelper(MainActivity.this);
                ArrayList<String> data=db.getTaskContent();
                db.close();

                String txt="";
                for (int i=0; i<data.size(); i++){
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResult.setText(txt);
                DBHelper db2 = new DBHelper(MainActivity.this);
                ArrayList<DBHelper.Task> alTasks = db2.getTasks();
                db2.close();
                aaTasks=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, alTasks);
                lvResults.setAdapter(aaTasks);

            }
        });
    }

}

