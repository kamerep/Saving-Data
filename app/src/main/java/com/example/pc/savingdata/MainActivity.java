package com.example.pc.savingdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText inputTitle, inputYear, inputCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputTitle=findViewById(R.id.inputTitle);
        inputYear=findViewById(R.id.inputYear);
        inputCategory=findViewById(R.id.inputCategory);
    }

    public void save(View view) {
        String title = inputTitle.getText().toString();
        String year = inputYear.getText().toString();
        String category = inputCategory.getText().toString();
        if (title.isEmpty()||year.isEmpty()||category.isEmpty()) {
            Toast.makeText(this, "Empty Fields", Toast.LENGTH_SHORT).show();
            return;
        }
        Database db = new Database(this);
        boolean status = db.insertData(title, year, category);
        if (status == true) {
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this, "Failed To Save", Toast.LENGTH_SHORT).show();
        }

    }

    public void show(View view) {
        Intent x=new Intent(this, showActivity.class);
        startActivity(x);
    }
}
