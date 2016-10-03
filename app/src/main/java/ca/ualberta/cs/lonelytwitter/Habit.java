package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mmooney on 9/28/16.
 * This class is valled in order to create a habit. Each habit has description, 140 letters at max
 * a date it was created on. And a list of days that it is due to be completed on.
 */


    public class Habit {
        private String description;
        private Date date;
        private String due;


        public Habit(String description, Date date, String due){
            this.description = description;
            this.date = date;
            this.due = (due);
        }

        public void setDescription(String description) throws TooLongException {
            if (description.length() > 140){
                throw new TooLongException();
            }
            this.description = description;
        }

        public void setDue(String due) {
            this.due = due;
        }


        public String getDescription() {
            return description;
        }
        public String getDue(){
            return due;
    }

}
