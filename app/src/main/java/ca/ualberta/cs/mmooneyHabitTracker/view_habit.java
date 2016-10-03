package ca.ualberta.cs.mmooneyHabitTracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
/*
Displays the description of the habit and the amount of completions it has done.
It also allows for you to complete habits, and clear habits. You can also delete habits.
 */
public class view_habit extends edit_habit {
    private static final String FILENAME = "file.sav";
    private Button completeHabit = (Button) findViewById(R.id.complete_Habit);
    private Button clearCompleations = (Button) findViewById(R.id.clear_Completions);
    private Button deleteHabit = (Button) findViewById(R.id.delete_Habit);
    private TextView hDescribe;
    private String describe;
    private ArrayList<Habit> habitList;
    private String when;
    private Habit target;
    public ArrayList<Date> Completes;

//adds one completion
    public void Completion (){
        completeHabit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Date theDate = new Date(System.currentTimeMillis());
                Completes.add(theDate);
            }
        });
    }
//removes all completions
    public void Clear (){
        clearCompleations.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Completes.clear();

            }
        });

    }
    //supposed to delete the habit selected.
    public void Delete (){
        deleteHabit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                habitList.remove(habit_number);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        habitList = loadFromFile();
        target = habitList.get(habit_number - 1);
        describe = target.getDescription();
        when = target.getDue();
        hDescribe = (TextView) findViewById(R.id.description_Text);
        hDescribe.setText(describe);

        setContentView(R.layout.view_habit);
        ListView Comp =(ListView) findViewById(R.id.completions_List);
        //supposed to display all completions.
        ArrayAdapter<Date> adapter = new ArrayAdapter<Date>(this,
                R.layout.simple_list_item_1, Completes);
        Comp.setAdapter(adapter);
        Completion();
        Delete();
        Clear();
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
    private ArrayList<Habit> loadFromFile() {
        ArrayList<Habit> habitList = new ArrayList<Habit>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt on September 22nd 2016
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            habitList = gson.fromJson(in, listType);
            return habitList;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
