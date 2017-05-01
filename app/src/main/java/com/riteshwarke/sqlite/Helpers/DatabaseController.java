package com.riteshwarke.sqlite.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.riteshwarke.sqlite.Models.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ritesh Warke on 30/04/17.
 */

public class DatabaseController extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "myDatabase";

    private static final String TABLE_STUDENTS = "students";

    // Contacts Table Columns names
    private static final String ROLL_NO = "roll_no";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";

    public DatabaseController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_STUDENTS + "("
                + ROLL_NO + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
                + ADDRESS + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {  //i is old version    //i1 is neew version
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    // CRUD OPERATIONS

    // CREATE
    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, student.getName());
        values.put(ADDRESS, student.getAddress());

        // Inserting Row
        db.insert(TABLE_STUDENTS, null, values);
        db.close(); // Closing database connection
    }


    //READ SINGLE
    // Getting single contact
    Student getStudent(int rollno) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENTS, new String[] { ROLL_NO,
                        NAME, ADDRESS }, ROLL_NO + "=?",
                new String[] { String.valueOf(rollno) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Student student = new Student(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return student;
    }

    //READ ALL

    // Getting All Contacts
    public List<Student> getAllStudents() {
        List<Student> contactList = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setRoll_no(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setAddress(cursor.getString(2));
                // Adding contact to list
                contactList.add(student);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // UPDATE
    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, student.getName());
        values.put(ADDRESS, student.getAddress());

        // updating row
        return db.update(TABLE_STUDENTS, values, ROLL_NO + " = ?",
                new String[] { String.valueOf(student.getRoll_no()) });
    }

    // DELETE
    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, ROLL_NO + " = ?",
                new String[] { String.valueOf(student.getRoll_no()) });
        db.close();
    }

    public void deleteStudent(String key, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, key + " = ?",
                new String[] { String.valueOf(value) });
        db.close();
    }

    public int getStudentsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
