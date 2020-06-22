package com.example.homeworkout.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homeworkout.Activity.BaiTap;
import com.example.homeworkout.Model.List;
import com.example.homeworkout.R;

import java.util.ArrayList;

public class ListAdapter2 extends BaseAdapter {
    Context context;
    ArrayList<List> listArrayList;
    int position1;

    public ListAdapter2(Context context,ArrayList<List> listArrayList){
        this.context = context;
        this.listArrayList = listArrayList;
    }
    @Override
    public int getCount() {
        return listArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    class ViewHolder {
        ImageView imglist;
        TextView txtimglist, txtnoidunglist;
        Button btSt;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(convertView ==  null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.dong_list, null);
            viewHolder = new ViewHolder();
            viewHolder.txtimglist = (TextView) convertView.findViewById(R.id.textimagelist);
            viewHolder.imglist = (ImageView) convertView.findViewById(R.id.imagelist);
            viewHolder.txtnoidunglist = convertView.findViewById(R.id.noidunglist);
            viewHolder.btSt = convertView.findViewById(R.id.buttonStart);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtimglist.setText(listArrayList.get(position).getTen());
        viewHolder.txtnoidunglist.setText(listArrayList.get(position).getNoidung());
        //viewHolder.imglist.setImageResource(listArrayList.get(position).getHinh());

        //chuyen mang byte thanh bitmap
        byte[] Anh = listArrayList.get(position).getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(Anh, 0, Anh.length);
        viewHolder.imglist.setImageBitmap(bitmap);

        viewHolder.imglist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position1 = position;
               setIntent();
            }
        });
        viewHolder.btSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position1 = position;
                setIntent();
            }
        });
        return convertView;

    }
    public void setIntent(){
        Intent intent = new Intent(context,BaiTap.class);
        intent.putExtra("test1",listArrayList.get(position1).getTen());
        context.startActivity(intent);
    }
}
