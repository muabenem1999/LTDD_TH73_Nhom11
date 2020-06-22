package com.example.homeworkout.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.homeworkout.Model.Bai;
import com.example.homeworkout.Model.List;
import com.example.homeworkout.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListBaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<Bai> baiArrayList;
    Dialog popupDialogbai;
    Bundle bundle, bunblepopup;
    ImageView imghinhbaipopup;
    TextView texttenbaipopup,textnumberbai,txttuto_bai_popup;
    ImageButton btBackpopup,btNextpopup;
    Button btclosepopup;

    public ListBaiAdapter(Context context, ArrayList<Bai> baiArrayList){
        this.context = context;
        this.baiArrayList = baiArrayList;
    }
    @Override
    public int getCount() {
        return baiArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    class ViewHolder{
        ImageView imgBai;
        TextView txtBai;
        RelativeLayout test;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final int number_bai = position + 1;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.dong_bai,null);
            holder.txtBai = convertView.findViewById(R.id.textBai);
            holder.imgBai = convertView.findViewById(R.id.imageBai);
            holder.test = convertView.findViewById(R.id.Layout_dong);
            convertView.setTag(holder);
        }
        else{
           holder = (ViewHolder) convertView.getTag();
        }

        holder.txtBai.setText(baiArrayList.get(position).getTenBai());
        //holder.imgBai.setImageResource(baiArrayList.get(position).getHinhBai());
        byte[] Anh = baiArrayList.get(position).getHinhBai();
        Bitmap bitmap = BitmapFactory.decodeByteArray(Anh, 0, Anh.length);
        holder.imgBai.setImageBitmap(bitmap);
        holder.test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyển dữ liệu của listview vào popup
                setbundle(bundle,position);
                getbundle(bundle);

                popupDialogbai.show();
            }
        });

        //set view cho popup************************************************
        bundle = new Bundle();
        popupDialogbai = new Dialog(context);
        popupDialogbai.setContentView(R.layout.popup_tutorial_bai);
        texttenbaipopup = popupDialogbai.findViewById(R.id.text_ten_bai_popup);
        imghinhbaipopup = popupDialogbai.findViewById(R.id.image_bai_popup);
        textnumberbai = popupDialogbai.findViewById(R.id.number_popup);
        txttuto_bai_popup = popupDialogbai.findViewById(R.id.text_tuto_bai_popup);

                //set nút back
        btBackpopup = popupDialogbai.findViewById(R.id.button_back_popup);
        btBackpopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bunblepopup = new Bundle();
                int number_back =Integer.parseInt(textnumberbai.getText().toString());
                number_back = number_back - 2;
                if(number_back < 0){
                    number_back = baiArrayList.size() - 1;
                }
                setbundle(bunblepopup,number_back);
                getbundle(bunblepopup);

            }
        });

                //set nút next
        btNextpopup = popupDialogbai.findViewById(R.id.button_next_popup);
        btNextpopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bunblepopup = new Bundle();
                int number_next = Integer.parseInt(textnumberbai.getText().toString());
                if(number_next >= baiArrayList.size()){
                    number_next = 0;
                }
                setbundle(bunblepopup,number_next);
                getbundle(bunblepopup);

            }
        });


                //set nút đóng
        btclosepopup = popupDialogbai.findViewById(R.id.button_close_popup);
        btclosepopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupDialogbai.dismiss();
            }
        });


        return convertView;
    }
            //lấy dữ liệu cho bundle
    private void setbundle(Bundle bundleall,int pos) {
        bundleall.putString("tenbai",baiArrayList.get(pos).getTenBai());
        bundleall.putByteArray("hinhbai",baiArrayList.get(pos).getHinhBai());
        bundleall.putString("numberbai",String.valueOf(pos + 1));
        // sửa nội dung ở đây.....................
        //đây chỉ là test... sau này getNoidung để chuyển nội dung cho popup
        bundleall.putString("huongdan",baiArrayList.get(pos).getHuongDan());
    }
            //chuyển dữ liệu bundle cho popup
    private void getbundle(Bundle bundleall) {
        texttenbaipopup.setText(bundleall.getString("tenbai"));
        Bitmap bitmap = BitmapFactory.decodeByteArray(bundleall.getByteArray("hinhbai"), 0, bundleall.getByteArray("hinhbai").length);
        imghinhbaipopup.setImageBitmap(bitmap);

        textnumberbai.setText(bundleall.getString("numberbai"));
        txttuto_bai_popup.setText(bundleall.getString("huongdan"));
    }

}
