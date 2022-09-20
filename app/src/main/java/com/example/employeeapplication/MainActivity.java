package com.example.employeeapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Employee> employees;
    com.example.employeeapplication.SqlLiteDatabaseHandler db;
    Button save;
    PopupWindow pwindo;
    Activity activity;
    ListView listView;
    CustomEmployeeList customEmployeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity=this;
        db= new com.example.employeeapplication.SqlLiteDatabaseHandler(this);
        listView = (ListView) findViewById(R.id.lsContact);
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        Log.d("MainActivity: ", "Before reading mainactivity");
        employees = (ArrayList) db.getAllEmployees();

        for (Employee employee : employees) {
            String log = "Id: " + employee.getId() + " ,Name: " + employee.getEmployeeName() + " ,designation: " + employee.getDesignation()+ " ,Salary: " + employee.getSalary();
            // Writing Countries to log
            Log.d("Name: ", log);
        }

        CustomEmployeeList customEmployeeList = new CustomEmployeeList(this, employees, db);
        listView.setAdapter(customEmployeeList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(), "You Selected " + employees.get(position).getEmployeeName() + " as Employee", Toast.LENGTH_SHORT).show();
            }
        });
    }
}