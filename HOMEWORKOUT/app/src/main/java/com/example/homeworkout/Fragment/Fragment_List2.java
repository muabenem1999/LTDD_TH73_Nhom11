package com.example.homeworkout.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.homeworkout.Activity.MainActivity;
import com.example.homeworkout.Adapter.ListAdapter2;
import com.example.homeworkout.Activity.BaiTap;
import com.example.homeworkout.Model.List;
import com.example.homeworkout.R;

import java.util.ArrayList;

public class Fragment_List2 extends Fragment {
    View view;
    ListView listView2;
    ArrayList<List> listArrayList;
    Button btStart;
    Cursor cursortest2;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArr();


    }

    private void initArr() {
        cursortest2 = MainActivity.database.getData("Select * from TheDuc");
        listArrayList = new ArrayList<>();
        while (cursortest2.moveToNext()) {
            listArrayList.add(new List(
                    cursortest2.getInt(0),
                    cursortest2.getString(1),
                    cursortest2.getString(2),
                    cursortest2.getBlob(3)
            ));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list,container,false);
        listView2 = view.findViewById(R.id.list_item);
        listView2.setAdapter(new ListAdapter2(getActivity(),listArrayList));
        /*listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),BaiTap.class);
                intent.putExtra("test1",listArrayList.get(position).getTen());
                intent.putExtra("valueid",)
                startActivity(intent);

            }
        });*/

        return view;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
