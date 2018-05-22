package com.example.pc.savingdata;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class showActivity extends AppCompatActivity {
    EditText inputid;
    TextView tvTitle, tvYear, tvCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        inputid=findViewById(R.id.inputid);
        tvTitle=findViewById(R.id.txtTitle);
        tvYear=findViewById(R.id.txtYear);
        tvCategory=findViewById(R.id.txtCategory);
    }

    public void search(View view) {
        String id=inputid.getText().toString().trim();
        if (id.isEmpty())
        {
            return;
        }
        Database db=new Database(this);
        Cursor movie=db.getOneItem(id);
        if (movie.moveToFirst())
        {
            //display
            //id, title, year, category
            tvTitle.setText(movie.getString(1));
            tvYear.setText(movie.getString(2));
            tvCategory.setText(movie.getString(3));
        }else
        {
            tvTitle.setText("");
            tvYear.setText("");
            tvCategory.setText("");
            Toast.makeText(this, "No Record Found", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View view) {
        String id=inputid.getText().toString().trim();
        if (id.isEmpty()) {
            return;
        }
        Database db=new Database(this);
        db.deleteData(id);
        tvTitle.setText("");
        tvYear.setText("");
        tvCategory.setText("");
    }
}
