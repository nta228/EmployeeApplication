package com.example.employeeapplication;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomEmployeeList extends BaseAdapter {
    private Activity context;
    ArrayList<com.example.employeeapplication.Employee> employees;
    private PopupWindow pwindo;
    com.example.employeeapplication.SqlLiteDatabaseHandler db;
    BaseAdapter ba;

    public CustomEmployeeList(Activity context, ArrayList employees, com.example.employeeapplication.SqlLiteDatabaseHandler db) {
        this.context = context;
        this.employees=employees;
        this.db=db;
    }

    public CustomEmployeeList(MainActivity mainActivity, ArrayList<Employee> employees, SqlLiteDatabaseHandler db) {
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}