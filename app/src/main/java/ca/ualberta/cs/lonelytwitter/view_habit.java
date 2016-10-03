package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
/*
Displays the description of the habit and the amount of completions it has done.
It also allows for you to complete habits, and clear habits. You can also delete habits.
 */
public class view_habit extends edit_habit {
    private Button completeHabit = (Button) findViewById(R.id.complete_Habit);
    private Button clearCompleations = (Button) findViewById(R.id.clear_Completions);
    private Button deleteHabit = (Button) findViewById(R.id.delete_Habit);
    private TextView hDescribe;
    private String describe;
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
}
