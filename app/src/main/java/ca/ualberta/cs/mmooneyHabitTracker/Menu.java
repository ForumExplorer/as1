package ca.ualberta.cs.mmooneyHabitTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
/*
Menu creates the main screen of the app. This should be the screen you start on, and from here you go to different sceens
and call the different methods. The only thing here is two buttons, one to create a new habit, and one to view all current habits.
 */
public class Menu extends Activity {

    public Button NewH;
    public Button ViewH;

    public void Move(){
        NewH = (Button) findViewById(R.id.new_Habit);
        ViewH = (Button) findViewById(R.id.view_Habit);
        NewH.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent Nswitch = new Intent(Menu.this, add_habit.class);
                startActivity(Nswitch);
            }
        });
        ViewH.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Intent Vswitch = new Intent(Menu.this, edit_habit.class);
                startActivity(Vswitch);
            }

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Move();
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
