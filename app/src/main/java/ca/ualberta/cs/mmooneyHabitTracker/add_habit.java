package ca.ualberta.cs.mmooneyHabitTracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
/*
 adds the habits into a list by reading the values typed into the two text boxes
 and using them to create a habit. That habit is then added to habitList.
  */

public class add_habit extends Activity {
    private static final String FILENAME = "file.sav";
    private EditText bodyText;
    private EditText edit_due;
    private Button makeHabit;
    public ArrayList<Habit> habitList = new ArrayList<Habit>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit);
        makeHabit = (Button) findViewById(R.id.make_Habit);
        making();

    }
    //actually making the habit.
    public void making () {
        makeHabit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                bodyText = (EditText) findViewById(R.id.edit_Description);
                edit_due = (EditText) findViewById(R.id.edit_Due);
                String text = bodyText.getText().toString();
                String dueDate = edit_due.getText().toString();

                Date theDate = new Date(System.currentTimeMillis());
                Habit newHabit = new Habit(text, theDate, dueDate);

                habitList.add(newHabit);


                saveInFile();
                finish();

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void saveInFile() {
        try {

            FileOutputStream fos = openFileOutput(FILENAME,0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(habitList, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

}
