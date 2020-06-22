package com.example.homeworkout.Activity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.homeworkout.Adapter.MainAdapter;
import com.example.homeworkout.Database.ListDatabase;
import com.example.homeworkout.Fragment.Fragment_Calendar;
import com.example.homeworkout.Fragment.Fragment_Work;
import com.example.homeworkout.Model.Bai;
import com.example.homeworkout.Model.List;
import com.example.homeworkout.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    String ten;
    Cursor cursor1,cursor2;
    public static ListDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();

        database = new ListDatabase(this, "workout02.db", null,1);
        database.QueryData("CREATE TABLE IF NOT EXISTS TheDuc(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(50), MoTa VARCHAR(250), hinh BLOB)");
        database.QueryData("CREATE TABLE IF NOT EXISTS TheDucBai(Id_bai INTEGER PRIMARY KEY AUTOINCREMENT,Id_mon INTEGER(5), Tenbai VARCHAR(50), HuongDan VARCHAR(250), hinh BLOB)");
        //lich
        database.QueryData("CREATE TABLE IF NOT EXISTS Lich(Id INTEGER PRIMARY KEY AUTOINCREMENT, Weight INTEGER , Height INTEGER, Bmi DOUBLE)");

       /* database.QueryData("drop table TheDuc");
        database.QueryData("drop table TheDucBai");*/
        cursor1 = MainActivity.database.getData("Select * from TheDuc");
        if(cursor1.getCount() == 0) {
            taodlMon("Cổ Điển", "Được khoa học chứng minh giúp giảm cân và nâng cao chức năng tim mạch.", R.drawable.banner_codien);
            taodlMon("Bụng", "Có được cơ bắp lôi cuốn, bóng nhẵn và săn chắc bằng các phương pháp tập cơ bụng hiệu quả.", R.drawable.banner_bung);
            taodlMon("Tay", "Dành vài phút mỗi ngày để có được cánh tay đẹp và săn chắc nhanh chóng.", R.drawable.banner_tay);
            taodlMon("Mông", "Chỉ trong 7 phút, 12 bài tập điển hình này sẽ giúp bạn có cơ thể săn chắc mà bạn hằng mong muốn.", R.drawable.banner_mong);
            taodlMon("Chân", "Muốn có đôi chân thanh thoát và hấp dẫn? duỗi thẳng và căng thân ngay bây giờ.", R.drawable.banner_chan);
        }



        cursor2 = MainActivity.database.getData("Select * from TheDucBai");
        if(cursor2.getCount() == 0) {
            //**************CÁC BÀI TẬP BỤNG***********************************
           taodlBai(R.drawable.abs_1,"GÁNH TẠ NHẢY","Bắt đầu ở tư thế gánh tạ, rồi dùng cơ bụng bật nhảy lên để tạo sức mạnh. Bài tập này tập cho bụng.",2);
            taodlBai(R.drawable.abs_2,"GẬP BỤNG NGƯỢC","Nằm ngửa giữ đầu gối bạn vuông góc 90 độ và tay để phía sau đầu. Nâng đùi và thân trên lên, và sau đó duỗi căng ra. Lặp lại động tác này.",2);
            taodlBai(R.drawable.abs_3,"TẤM VÁN THẲNG TAY","Bắt đầu ở tư thế chống đẩy, nhưng giữ thẳng cánh tay của bạn. Bài tập này giúp tập bụng và cơ lưng.",2);
            taodlBai(R.drawable.abs_4,"GẬP BỤNG CHÉO KIỂU NGA","Ngồi trên sàn với đầu gối cong, bàn chân đặt xuống mặt sàn và lưng nghiêng về sau. Sau đó nắm hai tay với nhau và xoay vặn từ bên này sang bên kia. Bài tập này tập cho cơ liên sườn.",2);
            taodlBai(R.drawable.abs_5,"CHÓ SĂN CHIM","Bắt đầu với việc để đầu gối dưới mông và tay để dưới vai. Sau đó duỗi chân phải và tay trái của cùng một lúc. Giữ trong năm giây, sau đó quay trở lại và lặp lại với bên kia.",2);
            taodlBai(R.drawable.abs_6,"GIẢM MỠ TOÀN THÂN","Đứng chân rộng bằng vai, sau đó đặt tay trên mặt đất và đá chân về phía sau.Chống đẩy nhanh rồi nhảy lên.",2);
            taodlBai(R.drawable.abs_7,"NÂNG TAY DÀI","Nằm ngửa với đầu gối cong và chân để sát mặt sàn. Đặt cánh tay thẳng trên đỉnh đầu của bạn. Nâng thân trên của bạn khỏi sàn nhà, sau đó từ từ trở lại vị trí bắt đầu. Các bài tập tăng sức chịu đựng bụng",2);
            taodlBai(R.drawable.abs_8,"CẦU MỘT CHÂN","Nằm trên sàn, cong một chân và nhấc chân kia. Sau đó nhấc hông lên khỏi sàn. Giữ tư thế này trong năm giây. Bài tập này tập cho bụng và hông.",2);
            taodlBai(R.drawable.abs_9,"CHỐNG ĐẨY MỘT CHÂN","Bắt đầu ở tư thế chống đẩy cổ điển nhưng nhấc một chân lên. Sau đó thực hiện một vài động tác chống đẩy và chuyển sang chân kia. Động tác này tốt cho cơ bụng dưới của bạn.",2);
            taodlBai(R.drawable.abs_10,"ĐO SÀN","Nằm trên sàn, chạm sàn bằng ngón chân và cẳng tay. Giữ thẳng cơ thể và giữ tư thế càng lâu càng tốt. Bài tập này làm khỏe cơ bụng, lưng và vai.",2);
            taodlBai(R.drawable.abs_11,"GẬP BỤNG NGANG THÂN","Nằm và cong gối, để chân trên sàn. Để tay chéo trước ngực. Sau đó nhấc đầu và vai để tạo góc 30 độ với mặt đất. Bài tập này chủ yếu có tác dụng với cơ bụng thẳng và cơ liên sườn.",2);
            taodlBai(R.drawable.abs_12,"LEO NÚI","Bắt đầu ở tư thế chống đẩy. Co gối phải về phía ngực và giữ chân trái thẳng, sau đó nhanh chóng chuyển sang chân khác. Bài tập này tăng cường nhiều nhóm cơ.",2);
            taodlBai(R.drawable.abs_13,"CHIẾC CẦU","Nằm sát trên sàn, nhấc hông khỏi sàn trong khi giữ lưng thẳng. Giữ tư thế này càng lâu càng tốt. Bài tập chiếc cầu giúp làm khỏe toàn phần bụng, lưng dưới và cơ mông.",2);
            //**************CÁC BÀI TẬP MÔNG***********************************
            taodlBai(R.drawable.ass_2,"NÂNG CƠ MÔNG KIỂU ẾCH","Nằm úp bụng, cong gối và gót chân ép sát vào nhau. Sau đó nâng chân lên và xuống từ 10-15 lần. Bài tập này rất hiệu quả để làm gọn mông.",4);
            taodlBai(R.drawable.ass_3,"BƯỚC GẬP GỐI","Đứng chân rộng bằng vai, tay để hông. Bước chân phải về trước và hạ thấp cơ thể cho tới khi đùi phải song song với sàn. Trở lại tư thế và lặp lại với chân kia. Bài tập này giúp làm khỏe cơ bốn đầu đùi, cơ mông lớn và gân kheo.",4);
            taodlBai(R.drawable.ass_4,"CÂY CẦU MÔNG","Nằm ngửa với đầu gối cong và chân để sát mặt sàn. Đặt cánh tay sát với hai bên thân. Sau đó nhấc mông lên rồi hạ xuống. Lặp lại 10-15 lần cho bài tập này.",4);
            taodlBai(R.drawable.ass_5,"LỪA ĐÁ CHÂN TRÁI","Bắt đầu với tư thế quỳ gối chống hai tay. Sau đó nhấc chân trái và ép mông đến hết mức có thể. Trở lại tư thế bắt đầu và lặp lại từ 10-15 lần cho bài tập này.",4);
            taodlBai(R.drawable.ass_6,"ĐỨNG TẤN CHÂN PHẢI","Bước một bước dài về trước bằng chân phải và giữ thân trên thẳng. Sau đó hạ thân xuống rồi lại nâng lên. Lặp lại từ 10-15 lần cho bài tập này.",4);
            taodlBai(R.drawable.ass_7,"VÒI CỨU HỎA BÊN TRÁI","Bắt đầu với tư thế quỳ gối chống hai tay. Sau đó nhấc chân trái sang bên một góc 90 độ. Lặp lại từ 10-15 lần cho bài tập này.",4);
            taodlBai(R.drawable.ass_8,"VÒI CỨU HỎA BÊN PHẢI","Bắt đầu với tư thế quỳ gối chống hai tay. Sau đó nhấc chân phải sang bên một góc 90 độ. Lặp lại từ 10-15 lần cho bài tập này.",4);
            taodlBai(R.drawable.ass_9,"ĐỨNG TẤN","Đứng để tay lên hông và chân đứng hơi rộng hơn vai. Sau đó hạ cơ thể xuống cho đến khi đùi song song với sàn. Đầu gối mở ra cùng hướng với ngón chân. Lặp lại từ 10-15 lần cho bài tập này.",4);
            taodlBai(R.drawable.ass_10,"LỪA ĐÁ CHÂN PHẢI","Bắt đầu với tư thế quỳ gối chống hai tay. Sau đó nhấc chân phải và ép mông đến hết mức có thể. Trở lại tư thế bắt đầu và lặp lại từ 10-15 lần cho bài tập này",4);
            taodlBai(R.drawable.ass_11,"TẤN SUMO NÂNG BẮP CHÂN","Đứng tay để hông và chân hơi rộng hơn vai. Sau đó hạ cơ thể xuống cho tới khi đùi song song với sàn. Nhấc gót chân khi bạn nhô người lên. Lặp lại bài này từ 10-15 lần.",4);
            taodlBai(R.drawable.ass_12,"ĐỨNG TẤN CHÂN TRÁI","Bước một bước dài về trước bằng chân trái và giữ thân trên thẳng. Sau đó hạ thân xuống rồi lại nâng lên. Lặp lại từ 10-15 lần cho bài tập này.",4);
            //**************CÁC BÀI TẬP CỔ ĐIỂN***********************************
            taodlBai(R.drawable.img_1,"BẬT NHẢY","Bắt đầu đứng hai chân để gần nhau, tay buông xuống để hai bên, sau đó nhảy lên chân dang ra và để tay lên cao trên đầu. Trở lại vị trí bắt đầu rồi lặp lại. Bài tập này tập luyện cho toàn thân và có tác dụng với tất cả các nhóm cơ lớn.",1);
            taodlBai(R.drawable.img_2,"NGỒI DỰA TƯỜNG","Bắt đầu bằng việc dựa lưng vào tường, sau đó trượt xuống cho tới khi đầu gối vuông góc 90 độ. Dựa lưng vào tường, để tay và cánh tay phía trên chân. Giữ tư thế. Bài tập này nhằm làm tăng các cơ bốn đầu đùi",1);
            taodlBai(R.drawable.img_3,"CHỐNG ĐẨY","Nằm áp xuống sàn lấy tay đỡ thân. Giữ thẳng thân trong khi nâng và hạ thân bằng tay. Bài tập này giúp tập ngực, vai, cơ tay sau, lưng và chân.",1);
            taodlBai(R.drawable.img_4,"TẬP CƠ BỤNG","Nằm ngửa cong gối và tay duỗi thẳng. Sau đó nhấc thân trên khỏi sàn. Giữ một vài giây rồi từ từ trở lại tư thế ban đầu. Bài tập này chủ yếu cho các cơ bụng thẳng và cơ liên sườn..",1);
            taodlBai(R.drawable.img_5,"BƯỚC LÊN GHẾ","Đứng trước ghế. Sau đó bước lên ghế rồi bước xuống. Bài tập này để tập khỏe chân và mông đùi.",1);
            taodlBai(R.drawable.img_6,"GÁNH ĐÙI","Chân đứng rộng bằng vai, tay để thẳng về trước, hạ thấp cơ thể cho tới khi đùi song song với sàn. Đầu gối cùng hướng với ngón chân. Quay lại tư thế ban đầu và lặp lại. Bài tập này giúp tập đùi, hông, cơ đùi trước, gân kheo và thân dưới.",1);
            taodlBai(R.drawable.img_7,"TẬP CƠ TAY SAU TRÊN GHẾ","Từ vị trí bắt đầu, ngồi trên ghế. Rồi di chuyển mông khỏi ghế, tay giữ vào mép ghế. Từ từ cong và duỗi thẳng tay để nâng và hạ cơ thể. Bài tập này có tác dụng tốt cho cơ tay sau.",1);
            taodlBai(R.drawable.img_8,"ĐO SÀN","Nằm trên sàn, chạm sàn bằng ngón chân và cẳng tay. Giữ thẳng cơ thể và giữ tư thế càng lâu càng tốt. Bài tập này làm khỏe cơ bụng, lưng và vai.",1);
            taodlBai(R.drawable.img_9,"BƯỚC CAO","Chạy tại chỗ, nhấc cao gối càng cao càng tốt trong mỗi bước. Giữ thẳng thân trên trong bài tập này.",1);
            taodlBai(R.drawable.img_10,"BƯỚC GẬP GỐI","Đứng chân rộng bằng vai, tay để hông. Bước chân phải về trước và hạ thấp cơ thể cho tới khi đùi phải song song với sàn. Trở lại tư thế và lặp lại với chân kia. Bài tập này giúp làm khỏe cơ bốn đầu đùi, cơ mông lớn và gân kheo.",1);
            taodlBai(R.drawable.img_11,"CHỐNG ĐẨY VÀ XOAY NGƯỜI","Bắt đầu ở tư thế chống đẩy. Hạ xuống để chống đẩy và khi nâng lên, xoay thân trên và giơ tay phải lên trên. Lặp lại bài tập này với tay kia. Đây là bài tập tuyệt vời cho cơ ngực, vai, tay và phần trung tâm.",1);
            taodlBai(R.drawable.img_12,"VÁN NGHIÊNG - PHẢI","Nằm nghiêng dùng phải cẳng tay đỡ thân mình. Giữ cơ thể theo đường thẳng. Bài tập này giúp tập các cơ bụng thẳng và cơ liên sườn.",1);
            //**************CÁC BÀI TẬP CHÂN***********************************
            taodlBai(R.drawable.leg_1,"NÂNG BẮP CHÂN","Đứng thẳng hai chân để gần nhau. Nhấc gót chân, đứng bằng các đầu ngón chân. Rồi hạ gót chân xuống. Lặp lại bài tập này từ 10-15 lần cho một lượt.",5);
            taodlBai(R.drawable.leg_2,"CHÙNG CHÂN CHÉO","Đứng thẳng. Rồi lùi ra sau với chân trái để sang phải, đồng thời cong gối. Trở lại tư thế bắt đầu và đổi bên. Lặp lại bài tập này từ 10-15 lần cho một lượt",5);
            taodlBai(R.drawable.leg_3,".NÂNG BẮP CHÂN TRÁI","Đứng để tay vào tường. Để bàn chân phải vào gót trái, sau đó nhấc gối trái lên và hạ xuống. Lặp lại bài tập này từ 10-15 lần cho một lượt.",5);
            taodlBai(R.drawable.leg_4,"CHÙNG CHÂN NGANG","Đứng thẳng hai chân để gần nhau. Đưa chân phải sang bên rồi hạ thấp thân xuống trong khi giữ thẳng chân trái. Trở lại tư thế bắt đầu và đổi bên. Lặp lại bài tập này từ 10-15 lần cho một lượt..",5);
            taodlBai(R.drawable.leg_5,".BƯỚC CHÙNG GỐI TRÁI","Đứng thẳng người. Sau đó chân trái bước ra sau và hạ thân người xuống. Khi đứng lên, nâng gối trái càng cao càng tốt. Trở lại tư thế bắt đầu, lặp lại bài tập này từ 10-15 lần cho một lượt.",5);
            taodlBai(R.drawable.leg_6,"NÂNG BẮP CHÂN PHẢI","Đứng để tay vào tường. Để bàn chân trái vào gót phải, sau đó nhấc gối phải lên và hạ xuống. Lặp lại bài tập này từ 10-15 lần cho một lượt.",5);
            taodlBai(R.drawable.leg_7,"NÂNG CHÂN TRÁI Ở DƯỚI","Nằm nghiêng bên trái, đầu tựa vào tay trái. Sau đó để chân phải thẳng trên sàn. Nhấc chân trái lên rồi hạ xuống. Lặp lại bài tập này từ 10-15 lần cho một lượt.",5);
            taodlBai(R.drawable.leg_8,"NÂNG CHÂN PHẢI Ở DƯỚI","Nằm nghiêng bên phải, đầu tựa vào tay phải. Sau đó để chân trái thẳng trên sàn. Nhấc chân phải lên rồi hạ xuống. Lặp lại bài tập này từ 10-15 lần cho một lượt.",5);
            taodlBai(R.drawable.leg_9,"BƯỚC CHÙNG GỐI PHẢI","Đứng thẳng người. Sau đó chân phải bước ra sau và hạ thân người xuống. Khi đứng lên, nâng gối phải càng cao càng tốt. Trở lại tư thế bắt đầu, lặp lại bài tập này từ 10-15 lần cho một lượt.",5);
            taodlBai(R.drawable.leg_10,"NẰM NGHIÊNG XOAY CHÂN TRÁI","Nằm nghiêng bên phải, đầu kê vào tay phải. Sau đó nhấc chân trái và xoay vòng tròn bàn chân. Lặp lại bài tập này từ 10-15 lần cho một lượt.",5);
            taodlBai(R.drawable.leg_11,"NẰM NGHIÊNG XOAY CHÂN PHẢI","Nằm nghiêng bên trái, đầu kê vào tay trái. Sau đó nhấc chân phải và xoay vòng tròn bàn chân. Lặp lại bài tập này từ 10-15 lần cho một lượt.",5);
            taodlBai(R.drawable.leg_12,"CHÙNG CHÂN RA SAU ĐÁ CHÂN TRÁI LÊN TRƯỚC","Đứng thẳng người. Chân trái bước ra sau và hạ thân người xuống. Khi đứng lên, đá chân trái lên trên. Trở lại tư thế bắt đầu và lặp lại bài tập này từ 10-15 lần cho một lượt.",5);
            taodlBai(R.drawable.leg_13,"CHÙNG CHÂN RA SAU ĐÁ CHÂN PHẢI LÊN TRƯỚC","Đứng thẳng người. Chân phải bước ra sau và hạ thân người xuống. Khi đứng lên, đá chân phải lên trên. Trở lại tư thế bắt đầu và lặp lại bài tập này từ 10-15 lần cho một lượt.",5);

            //**************CÁC BÀI TẬP TAY***********************************
            taodlBai(R.drawable.abs_1,"GÁNH TẠ NHẢY","Bắt đầu ở tư thế gánh tạ, rồi dùng cơ bụng bật nhảy lên để tạo sức mạnh. Bài tập này tập cho bụng.",3);


        }


    }
    String testTen;
    int count = 0;
    private void taodlMon(String ten, String noidung, int Hinh) {
        if(count == 0) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), Hinh);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] img = bos.toByteArray();
            MainActivity.database.INSERT_TD(ten, noidung, img);
        }
    }
    private void taodlBai(int Hinh,String TenBai, String HuongDan,int id_mon ){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), Hinh);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] img = bos.toByteArray();
        MainActivity.database.INSERT_TDBai(id_mon,TenBai, HuongDan, img);

    }

    private void init() {
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.addFr(new Fragment_Work(),"Bài Tập");
        mainAdapter.addFr(new Fragment_Calendar(),"Lịch");
        viewPager.setAdapter(mainAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_fitness_center_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_today_black_24dp);

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#00DDFF"), PorterDuff.Mode.SRC_IN);
                //tabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#00DDFF"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void anhxa() {
        tabLayout = findViewById(R.id.MainTabLayout);
        viewPager = findViewById(R.id.MainViewPager);
    }

}
