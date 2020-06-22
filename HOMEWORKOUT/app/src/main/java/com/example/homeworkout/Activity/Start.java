package com.example.homeworkout.Activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeworkout.Activity.BaiTap;

import com.example.homeworkout.Model.Bai;
import com.example.homeworkout.R;

import java.util.ArrayList;
import java.util.Locale;

public class Start extends AppCompatActivity {
    private static final long START_TIME_IN_MILIS=30000;
    private static final long NGHI_TIME_IN_MILIS=15000;
    CountDownTimer cdtimer, nghitimer;
    boolean timerunning;
    long timeleft = START_TIME_IN_MILIS;
    long timetonghi = NGHI_TIME_IN_MILIS;
    long tmp=0;
    int flag;
    int pos = -1;
    int sobai;
    Intent intent;
    TextView viewCD, dem1;
    Button btnStart;
    TextView txReady,txStartwith,txTenBai,txDem;
    ImageView imgBai;
    ArrayList<Bai> baiArrayList1;
    String title,id;

    @Override
    protected void onPause() {
        super.onPause();
        if(pos < sobai){
            noti();
        }
        pauseTimer();

    }

    @Override
    protected void onStop() {
        super.onStop();
        pauseTimer();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createNotificationChannel();
        intit();
        intent = getIntent();
        title = intent.getStringExtra("title2");
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
        startTimerNghi();
        sobai = baiArrayList1.size();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timerunning == true) {
                    pauseTimer();
                }
                else {
                    if(flag == 1) {
                        startTimer();
                    }
                    else{
                        startTimerNghi();
                    }
                }
            }
        });
        updateCountDownText(timeleft);

    }

    private void intit() {
        txReady = findViewById(R.id.txtReady);
        txStartwith = findViewById(R.id.txtstartwith);
        txTenBai = findViewById(R.id.txtBaitap);
        viewCD = findViewById(R.id.txttime);
        imgBai = findViewById(R.id.imgHinhbaitap);
        btnStart = findViewById(R.id.btnStart);
        dem1 = findViewById(R.id.dem);
    }

    public void initArr() {
        Cursor cursor = MainActivity.database.getData("Select Id_mon,TenBai,HuongDan,hinh from TheDucBai where Id_mon = " + id);
        baiArrayList1 = new ArrayList<>();
        while (cursor.moveToNext()) {
            baiArrayList1.add(new Bai(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)
            ));
        }
    }
    public void setDl(){
        if(pos >= 0 && pos < sobai){
            txReady.setVisibility(View.GONE);
            txStartwith.setText("VÒNG 1/1");
            txTenBai.setText(pos + 1 + "/" + sobai + " " + baiArrayList1.get(pos).getTenBai());
            byte[] Anh = baiArrayList1.get(pos).getHinhBai();
            Bitmap bitmap = BitmapFactory.decodeByteArray(Anh, 0, Anh.length);
            imgBai.setImageBitmap(bitmap);
        }
        else{

        }
    }
    public void setDlnghi(){
        if(pos >= 0 && pos < sobai - 1){
            txReady.setText("Nghỉ ngơi nào!");
            txStartwith.setText("Tiếp theo");
            txTenBai.setText(pos + 2 + "/" + sobai + " " + baiArrayList1.get(pos + 1).getTenBai());
            byte[] Anh = baiArrayList1.get(pos + 1).getHinhBai();
            Bitmap bitmap = BitmapFactory.decodeByteArray(Anh, 0, Anh.length);
            imgBai.setImageBitmap(bitmap);
        }
    }


    // khu vực dành cho countdown time+++++++++++++++++++++++++++++++++++++++++++++++++++
    private void startTimer() {
        flag = 1;
        pos++;
        if(pos == sobai){
            Intent mh1 =  new Intent(getApplicationContext(),MainActivity.class);
            startActivity(mh1);
            finish();
        }
        setDl();
        cdtimer = new CountDownTimer(timeleft, 1000) {
            @Override
            public void onTick(long milisUntilFinshed1) {
                updateCountDownText(timeleft);
                timeleft = milisUntilFinshed1;
                tmp=tmp+1000;
                formatDem();
            }

            @Override
            public void onFinish() {
                timetonghi = NGHI_TIME_IN_MILIS;
                startTimerNghi();
                btnStart.setVisibility(View.VISIBLE);

            }
        }.start();
        timerunning = true;
        btnStart.setText("Pause");

    }

    private void startTimerNghi() {
        flag = 0;
        setDlnghi();
        nghitimer = new CountDownTimer(timetonghi, 1000) {
            @Override
            public void onTick(long milisUntilFinshed2) {
                updateCountDownText(timetonghi);
                timetonghi = milisUntilFinshed2;
                tmp=tmp+1000;
                formatDem();

            }

            @Override
            public void onFinish() {
                timeleft = START_TIME_IN_MILIS;
                startTimer();
                btnStart.setVisibility(View.VISIBLE);

            }
        }.start();
        timerunning = true;
        btnStart.setText("Pause");

    }

    private void pauseTimer() {
        if(flag == 1) {
            pos--;
            cdtimer.cancel();
        }
        else {
            nghitimer.cancel();
        }
        timerunning = false;
        btnStart.setText("Start");

    }

    private void updateCountDownText(long time) {
        int mins = (int) (time/1000)/60;
        int secs = (int) (time/1000)%60;
        String timerFormat = String.format(Locale.getDefault(),"%02d:%02d", mins, secs);
        viewCD.setText(timerFormat);
    }

    private void formatDem() {
        int mins = (int) (tmp/1000)/60;
        int secs = (int) (tmp/1000)%60;
        String timerFormat = String.format(Locale.getDefault(),"%02d:%02d", mins, secs);
        dem1.setText(timerFormat);
    }
    void noti(){
        String mess = "Chưa tập xong mà đã nghỉ.. đúng là đồ con lợn.";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"Vuongpro1")
                .setSmallIcon(R.drawable.ic_add_alert_black_24dp)
                .setContentTitle("Thông báo mới...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(mess))
                .setContentText(mess)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0,builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Test";
            String description = "Test noti";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("Vuongpro1", name, importance);
            channel.setVibrationPattern(new long[]{300, 300, 300});
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
