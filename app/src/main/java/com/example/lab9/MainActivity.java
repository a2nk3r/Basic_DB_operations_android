package com.example.lab9;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editRoll, editName, editAge;
    private Button loadBtn, addBtn, findBtn, updateBtn, deleteBtn;
    private TextView resultView;

    private void clearFields(){
        editRoll.setText("");
        editName.setText("");
        editAge.setText("");
        hideVirtualKeyboard();
    }
    private void hideVirtualKeyboard(){
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editRoll = findViewById(R.id.editRoll);
        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        loadBtn = findViewById(R.id.loadBtn);
        addBtn = findViewById(R.id.addBtn);
        findBtn = findViewById(R.id.findBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        resultView = findViewById(R.id.resultView);

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHandler dbHandler = new MyDBHandler(getApplicationContext(), null, null, 1);
                if(dbHandler.loadHandler() == ""){
                    resultView.setText("No data!");
                }
                else{
                    resultView.setText(dbHandler.loadHandler());
                }
                clearFields();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHandler dbHandler = new MyDBHandler(getApplicationContext(), null, null, 1);
                int roll = Integer.parseInt(editRoll.getText().toString());
                String name = editName.getText().toString();
                int age = Integer.parseInt(editAge.getText().toString());
                Student student = new Student(roll, name, age);
                dbHandler.addHandler(student);
                clearFields();
                Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();
            }
        });

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHandler dbHandler = new MyDBHandler(getApplicationContext(), null, null, 1);
                Student student = dbHandler.findHandler(editName.getText().toString());
                if(student != null){
                    resultView.setText(String.valueOf(student.getRollNumber()) + " " + student.getName() + " " + student.getAge() + System.getProperty("line.separator"));
                }
                else{
                    resultView.setText("No match found!");
                }
                clearFields();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHandler dbHandler = new MyDBHandler(getApplicationContext(), null, null, 1);
                boolean result = dbHandler.deleteHandler(Integer.parseInt(editRoll.getText().toString()));
                if(result){
                    resultView.setText("Record deleted!");
                }
                else{
                    resultView.setText("No match found!");
                }
                clearFields();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHandler dbHandler = new MyDBHandler(getApplicationContext(), null, null, 1);
                boolean result = dbHandler.updateHandler(Integer.parseInt(editRoll.getText().toString()), editName.getText().toString(), Integer.parseInt(editAge.getText().toString()));
                if(result){
                    resultView.setText("Record updated!");
                }
                else{
                    resultView.setText("No match found!");
                }
                clearFields();
            }
        });
    }

}
