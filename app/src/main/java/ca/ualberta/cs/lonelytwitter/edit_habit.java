package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
displays all of the habits in a single list.
You select the number of the habit in order to view more details.
 */
public class edit_habit extends add_habit {
    private static final String FILENAME = "file.sav";
    private EditText Hnumber;
    private String Hdescription;
    public int habit_number;
    private Button habit_View;

    public void move() throws NoInputException{
        habit_View = (Button) findViewById(R.id.view_Button);
        if (habit_number == 0){
            throw  new NoInputException();
        }
        habit_View.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Intent Eswitch = new Intent(edit_habit.this, view_habit.class);
            }

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        loadFromFile();
        setContentView(R.layout.edit_habit);
        final ListView my_habits =(ListView) findViewById(R.id.myHabits);
        ArrayAdapter<Habit> adapter = new ArrayAdapter<Habit>(this,
                R.layout.simple_list_item_1, habitList);
        my_habits.setAdapter(adapter);
        try {
            move();
        }catch(NoInputException e){

        }
        //Disabled because I couldn't figure out how to move by pressing buttons on a list.
       // my_habits.setOnClickListener(new AdapterView.OnItemClickListener(){
         //   public void OnItemClickListener(AdapterView<?> parent, View view, int position, long id){
           //     Toast.makeText(edit_habit.this, habitList[position] )
            //}
        }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        habit_number = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (habit_number == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void loadFromFile() {
        ArrayList<String> tweets = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt on September 22nd 2016
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            habitList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
