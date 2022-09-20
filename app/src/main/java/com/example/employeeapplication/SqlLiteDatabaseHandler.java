package com.example.employeeapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SqlLiteDatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "EmployeeData";
    // Country table name
    private static final String TABLE_Employee= "Employee";
    // Country Table Columns names
    private static final String KEY_ID = "id";
    private static final String EMPLOYEE_NAME = "employeeName";
    private static final String DESIGNATION = "designation";
    private static final String SALARY = "salary";

    public SqlLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COUNTRY_TABLE = "CREATE TABLE " + TABLE_Employee
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + EMPLOYEE_NAME + " TEXT,"
                + DESIGNATION + " TEXT,"
                + SALARY + "TEXT" + ")";
        db.execSQL(CREATE_COUNTRY_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Employee);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new country
    void addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EMPLOYEE_NAME, employee.getEmployeeName()); // Country Name
        values.put(DESIGNATION, employee.getDesignation()); // Country Population
        values.put(SALARY, employee.getSalary()); // Country Population

        // Inserting Row
        db.insert(TABLE_Employee, null, values);
        db.close(); // Closing database connection
    }

    // Getting single country
    Employee getEmployee(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_Employee, new String[] { KEY_ID,

                        EMPLOYEE_NAME, DESIGNATION,SALARY }, KEY_ID + "=?",
                new String[] { String.valueOf( id ) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Employee employee = new Employee(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return employee;
    }

    // Getting All Countries
    public List getAllEmployees() {
        List employeeList = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Employee;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setEmployeeName(cursor.getString(1));
                employee.setDesignation(cursor.getString(2));
                employee.setDesignation(cursor.getString(3));
                // Adding country to list
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        // return country list
        return employeeList;
    }

    // Updating single country
    public int updateEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EMPLOYEE_NAME, employee.getEmployeeName());
        values.put(DESIGNATION, employee.getDesignation());
        values.put(SALARY, employee.getSalary());

        // updating row
        return db.update(TABLE_Employee, values, KEY_ID + " = ?",
                new String[] { String.valueOf(employee.getId()) });
    }

    // Deleting single country
    public void deleteEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Employee, KEY_ID + " = ?",
                new String[] { String.valueOf(employee.getId()) });
        db.close();
    }

    // Deleting all countries
    public void deleteAllEmployees() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Employee,null,null);
        db.close();
    }

    // Getting countries Count
    public int getEmployeesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_Employee;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}