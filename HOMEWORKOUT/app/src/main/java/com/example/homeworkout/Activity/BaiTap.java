package com.example.homeworkout.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.homeworkout.Activity.MainActivity;
import com.example.homeworkout.Adapter.ListBaiAdapter;
import com.example.homeworkout.Model.Bai;
import com.example.homeworkout.R;

import java.util.ArrayList;

public class BaiTap extends AppCompatActivity {
    ViewPager viewPager;
    ListView listViewbai;
    ArrayList<Bai> baiArrayList;
    Button btStartBai;
    Dialog popupDialog;
    String title;
    String id;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_tap);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent1 = getIntent();
        btStartBai = (Button) findViewById(R.id.buttonStartbai);
        btStartBai.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setIntent();
                finish();
            }
        });
        title = intent1.getStringExtra("test1");
        setTitle(title);
        if(title.equals("Cổ Điển")){
            id = "1";
        }
        else if(title.equals("Bụng")){
            id = "2";
        }
        else if(title.equals("Mông")){
            id = "4";
        }
        else if(title.equals("Tay")){
            id = "3";
        }
        else if(title.equals("Chân")){
            id = "5";
        }

        initArr();
        listViewbai = findViewById(R.id.lis_bai);
        listViewbai.setAdapter(new ListBaiAdapter(this,baiArrayList));
        popupDialog = new Dialog(this);
        listViewbai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setPopup();
            }

            private void setPopup() {
                popupDialog.setContentView(R.layout.popup_tutorial_bai);
                popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        });
    }

    public void initArr() {
        Cursor cursore = MainActivity.database.getData("Select Id_mon,TenBai,HuongDan,hinh from TheDucBai where Id_mon = " + id);
        baiArrayList = new ArrayList<>();
        while (cursore.moveToNext()) {
            baiArrayList.add(new Bai(
                    cursore.getInt(0),
                    cursore.getString(1),
                    cursore.getString(2),
                    cursore.getBlob(3)
            ));
        }
    }
    public void setIntent(){
        Intent intent = new Intent(getApplicationContext(),Start.class);
        intent.putExtra("title2",title);
        startActivity(intent);
    }
}
