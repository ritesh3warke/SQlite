package com.riteshwarke.sqlite.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.riteshwarke.sqlite.Helpers.DatabaseController;
import com.riteshwarke.sqlite.Models.Student;
import com.riteshwarke.sqlite.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DatabaseController db = new DatabaseController(this);


         // CRUD Operations

        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addStudent(new Student("Ritesh", "Andheri"));
        db.addStudent(new Student("Virat", "Virar"));
        db.addStudent(new Student("ABD", "Sakinaka"));
        db.addStudent(new Student("Dhoni", "Dahisar"));




        // Reading all contacts
        Log.i("Reading: ", "Reading all students..");
        List<Student> student = db.getAllStudents();

        for (Student s : student) {
            String log = "Roll No: " + s.getRoll_no() + " ,Name: " + s.getName() + " ,Address: " + s.getAddress();
            // Writing Contacts to log
            Log.i("STUDENTS", log);

        }

        db.deleteStudent("ROLL_NO","2");

        Log.i("Reading 1: ", "Reading all students..");
        List<Student> student1 = db.getAllStudents();

        for (Student s : student1) {
            String log = "Roll No: " + s.getRoll_no() + " ,Name: " + s.getName() + " ,Address: " + s.getAddress();
            // Writing Contacts to log
            Log.i("STUDENTS 1s", log);

        }

        Student s = new Student(1,"Ritesh", "Bandra");
        db.updateStudent(s);

        Log.i("Reading 2: ", "Reading all students..");
        List<Student> student2 = db.getAllStudents();

        for (Student s2 : student2) {
            String log = "Roll No: " + s2.getRoll_no() + " ,Name: " + s2.getName() + " ,Address: " + s2.getAddress();
            // Writing Contacts to log
            Log.i("STUDENTS 1s", log);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
