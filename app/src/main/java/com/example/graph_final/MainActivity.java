package com.example.graph_final;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Toast;

import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PanZoom;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.security.SecureRandom;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XYPlot plot;

        plot = findViewById(R.id.plot);
        Random R = new Random();
        int y = R.nextInt(65);

        //Create a arrays of y-value to plot:
        Number[] domainLabels = {R.nextInt(15),R.nextInt(15),R.nextInt(15),R.nextInt(15),R.nextInt(15),
        R.nextInt(15),R.nextInt(15),R.nextInt(15),R.nextInt(15),R.nextInt(15),R.nextInt(15),
        };

        Number[] series1Numbers = {R.nextInt(65),R.nextInt(65),R.nextInt(65),R.nextInt(65),R.nextInt(65),
        R.nextInt(65),R.nextInt(65),R.nextInt(65),R.nextInt(65),R.nextInt(65),R.nextInt(65),
        };

        Number[] series2Numbers = {R.nextInt(50),R.nextInt(50),R.nextInt(50),R.nextInt(50),R.nextInt(50),
        R.nextInt(50),R.nextInt(50),R.nextInt(50),R.nextInt(50),R.nextInt(50),R.nextInt(50),

        };


        // Turn the above arrays into XYSeries
        XYSeries series1 = new SimpleXYSeries(Arrays.asList(series1Numbers),
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"December");
        XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers),
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"November");

        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.rgb(75,83,32),Color.rgb(255,194,0),null,null);
        LineAndPointFormatter series2Format = new LineAndPointFormatter(Color.rgb(0, 0, 139), Color.RED,null,null);
        Paint paint = series1Format.getLinePaint();
        paint.setStrokeWidth(15);
        series1Format.setLinePaint(paint);
        series1Format.setInterpolationParams(new CatmullRomInterpolator.Params(10,
                CatmullRomInterpolator.Type.Uniform));

        Paint paint2 = series2Format.getLinePaint();
        paint2.setStrokeWidth(15);
        series2Format.setLinePaint(paint2);

        series2Format.setInterpolationParams(new CatmullRomInterpolator.Params(10,
                CatmullRomInterpolator.Type.Uniform));

        plot.addSeries(series1,series1Format);
        plot.addSeries(series2,series2Format);




        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.TOP).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                int i = Math.round( ((Number)obj).floatValue() );
                return toAppendTo.append(domainLabels[i]);
            }

            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });

        PanZoom.attach(plot);
    }
}