package com.example.homeworkout.Fragment;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.SingleValueDataSet;
import com.anychart.charts.LinearGauge;
import com.anychart.enums.Anchor;
import com.anychart.enums.Layout;
import com.anychart.enums.MarkerType;
import com.anychart.enums.Orientation;
import com.anychart.enums.Position;
import com.anychart.scales.OrdinalColor;
import com.example.homeworkout.Activity.MainActivity;
import com.example.homeworkout.R;
import com.example.homeworkout.Model.Lich;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Fragment_Calendar extends Fragment {

    CalendarView calendarView;
    ArrayList<Lich> arrCalendar;
    View view;
    Button btBMI, btnSave, btnClose;
    Dialog popupDialog;
    AnyChartView anyChartView;
    EditText edtW, edtH;
    TextView txtBMI,txtBMIvalue;
    int we,he;
    double bmi;
    Cursor cursor;
    LinearGauge linearGauge;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar,container,false);
        popupDialog = new Dialog(getActivity());
        btBMI = view.findViewById(R.id.btTesst);
        txtBMI = view.findViewById(R.id.textBMI);
        txtBMIvalue = view.findViewById(R.id.textBMI1);
        anyChartView = view.findViewById(R.id.any_chart_view);
        setupPieChart();


        AnhXa();

        btBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPopup();
            }
            private void setPopup() {
                popupDialog.setContentView(R.layout.test1);
                popupDialog.getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupDialog.show();
                btnSave = popupDialog.findViewById(R.id.btLuu);
                edtW =  (EditText)popupDialog.findViewById(R.id.edtW);
                edtH =  (EditText)popupDialog.findViewById(R.id.edtH);


                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        we = Integer.parseInt(String.valueOf(edtW.getText()));
                        he = Integer.parseInt(String.valueOf(edtH.getText()));
                        bmi = (double)we/Math.pow(((double)he/100), 2);

                        Cursor cursor = MainActivity.database.getData("SELECT * From Lich");
                        while (cursor.moveToNext()) {
                            arrCalendar.add(new Lich(
                                    cursor.getInt(0),
                                    cursor.getInt(1),
                                    cursor.getInt(2),
                                    cursor.getDouble(3)
                            ));
                        }

                        MainActivity.database.INSERT_LICH(
                                we, he, bmi
                        );

                        DecimalFormat dcf=new DecimalFormat("#.0");
                        txtBMI.setText("BMI(kg/m²): " + String.valueOf(dcf.format(bmi)));
                        txtBMIvalue.setText(setValue(bmi));
                        linearGauge.data(new SingleValueDataSet(new Double[] {bmi}));
                        popupDialog.dismiss();

                    }
                });

            }


        });



        return view;
    }





    public void setupPieChart() {
        double doub = 0;
        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
        linearGauge = AnyChart.linear();
        //START
        cursor = MainActivity.database.getData("SELECT Bmi From Lich where Id = (select max(Id) from Lich)");
        DecimalFormat dcf=new DecimalFormat("#.0");
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            doub = Double.parseDouble(dcf.format(cursor.getDouble(0)));
        }
        txtBMI.setText("BMI(kg/m²): " + String.valueOf(doub));
        txtBMIvalue.setText(setValue(doub));
        //END
        linearGauge.data(new SingleValueDataSet(new Double[] {doub}));

        linearGauge.layout(Layout.HORIZONTAL);
        OrdinalColor scaleBarColorScale = OrdinalColor.instantiate();
        scaleBarColorScale.ranges(new String[]{
                "{ from: 15, to: 16, color: ['yellow 1'] }",
                "{ from: 16, to: 18.5, color: ['orange 1'] }",
                "{ from: 18.5, to: 25, color: ['green 1'] }",
                "{ from: 25, to: 30, color: ['red 1'] }",
                "{ from: 30, to: 35, color: ['blue 1'] }",
                "{ from: 35, to: 40, color: ['violet 1'] }"
        });

        linearGauge.scaleBar(0)
                .width("20%")
                .colorScale(scaleBarColorScale);

        linearGauge.marker(0)
                .type(MarkerType.TRIANGLE_UP).width("20")
                .color("black")
                .offset("22%")
                .zIndex(10);

        linearGauge.scale()
                .minimum(15)
                .maximum(40);
//        linearGauge.scale().ticks

        linearGauge.axis(0)
                .minorTicks(false)
                .width("1%");
        linearGauge.axis(0)
                .offset("-1.5%")
                .orientation(Orientation.TOP)
                .labels("top");

        linearGauge.padding(0, 10, 0, 10);

        anyChartView.setChart(linearGauge);
    }


    private void AnhXa() {
        calendarView = (CalendarView)view.findViewById(R.id.calenView);
        arrCalendar = new ArrayList<>();

    }
    public String setValue(double bmi) {
        if (bmi < 15) {
            return "Gầy như que tăm luôn";
        }
        if (bmi > 15 && bmi <= 16) {
            return "Quá gầy luôn";
        }
        if (bmi > 16 && bmi <= 18.5) {
            return "Thiếu cân";
        }
        if (bmi > 18.5 && bmi <= 25) {
            return "Đủ cân";
        }
        if (bmi > 25 && bmi <= 30) {
            return "Thừa cân";
        }
        if (bmi > 30 && bmi <= 35) {
            return "khá béo rồi đấy";
        }
        if (bmi > 35 && bmi <= 40) {
            return "Béo lắm rồi kìa";
        }
        if (bmi > 40) {
            return "Béo phì luôn rồi";
        }
        return null;
    }

}
