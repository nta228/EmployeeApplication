package com.example.practicalapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnSave, btnUpdate, btnDelete, btnCancel;
    private EmployeeAdapter employeeAdapter;
    private ListView lvEmployee;
    private List<Employee> employees;
    private SQLiteHelper sqLiteHelper;
    private EditText editName, editDesignation, editSalary;
    private Employee employeeSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnSave);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        lvEmployee = findViewById(R.id.lvEmployee);
        editName = findViewById(R.id.editName);
        editDesignation = findViewById(R.id.editDesignation);
        editSalary = findViewById(R.id.editSalary);
        sqLiteHelper = new SQLiteHelper(this);
        employees = sqLiteHelper.findAllEmployee();
        employeeAdapter = new EmployeeAdapter(this, employees, sqLiteHelper);
        lvEmployee.setAdapter(employeeAdapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isValidForm()) {
                    Toast.makeText(MainActivity.this, "Invalid form, please try again !",
                            Toast.LENGTH_SHORT).show();
                } else {
                    String name = editName.getText().toString();
                    String designation = editDesignation.getText().toString();
                    Double salary = Double.parseDouble(editSalary.getText().toString());
                    Employee employee = new Employee(name, designation, salary);
                    sqLiteHelper.createEmployee(employee);
                    employees.add(employee);
                    employeeAdapter.notifyDataSetChanged();
                    resetForm();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isValidForm()) {
                    Toast.makeText(MainActivity.this, "Invalid form, please try again !",
                            Toast.LENGTH_SHORT).show();
                }else {
                    String name = editName.getText().toString();
                    String designation = editDesignation.getText().toString();
                    Double salary = Double.parseDouble(editSalary.getText().toString());
                    Employee employee = new Employee(employeeSelected.getId(), name, designation, salary);
                    sqLiteHelper.updateEmployee(employee);
                    employees = sqLiteHelper.findAllEmployee();
                    employeeAdapter.updateData(employees);
                    cancelForm();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteHelper.deleteEmployee(employeeSelected);
                employees.remove(employeeSelected);
                employeeAdapter.notifyDataSetChanged();
                cancelForm();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelForm();
            }
        });

        lvEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee employee = employees.get(position);
                editName.setText(employee.getName());
                editDesignation.setText(employee.getDesignation());
                editSalary.setText(String.valueOf(employee.getSalary()));
                employee.setId(id);
                employeeSelected = employee;
                btnUpdate.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.VISIBLE);
                btnDelete.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.GONE);
            }
        });
    }

    public boolean isValidForm() {
        String name = editName.getText().toString();
        String designation = editDesignation.getText().toString();
        Double salary = editSalary.getText().toString().length() > 0 ? Double.parseDouble(editSalary.getText().toString()) : 0;
        boolean isValid = true;
        if(name.length() == 0) {
            isValid = false;
        }
        if(designation.length() == 0) {
            isValid = false;
        }
        if(salary == 0) {
            isValid = false;
        }
        return isValid;
    }

    public void resetForm() {
        editName.setText("");
        editDesignation.setText("");
        editSalary.setText("");
    }

    public void cancelForm() {
        resetForm();
        btnUpdate.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        btnSave.setVisibility(View.VISIBLE);
    }
}