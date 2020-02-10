package com.shanjinur.healthguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.budiyev.android.circularprogressbar.CircularProgressBar;
import com.google.android.material.bottomappbar.BottomAppBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CircularProgressBar progressBar = findViewById(R.id.progress_bar);
        AnyChartView anyChartView = findViewById(R.id.any_chart_view);
        Cartesian cartesian = AnyChart.column();
        //BottomAppBar bottomAppBar = (BottomAppBar) findViewById(R.id.bottom_app_bar);
        //setSupportActionBar(bottomAppBar);
        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Saturday", 128000));
        data.add(new ValueDataEntry("Sunday", 94190));
        data.add(new ValueDataEntry("Monday", 102610));
        data.add(new ValueDataEntry("Tuesday", 170670));
        data.add(new ValueDataEntry("Wednesday", 128000));
        data.add(new ValueDataEntry("Thursday", 143760));
        data.add(new ValueDataEntry("Friday", 94190));

        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: } Steps");

        cartesian.animation(true);

        cartesian.yScale().minimum(0d);
        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);
        cartesian.xAxis(0).title("Day");
        cartesian.yAxis(0).title("Steps");

        anyChartView.setChart(cartesian);
        progressBar.animate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bottomappbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.app_bar_fav) {
            Toast.makeText(this, "Pressed Fav Icon", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onPress(View view) {
        System.out.println("Action");
    }
}
