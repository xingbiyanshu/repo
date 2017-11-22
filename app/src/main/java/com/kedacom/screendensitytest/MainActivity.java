package com.kedacom.screendensitytest;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.img);
        textView = (TextView) findViewById(R.id.tv);
        imageView.setImageResource(R.drawable.ic_launcher);

    }

    @Override
    protected void onResume() {
        super.onResume();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        String screenInfo = metrics.toString();

        //获得ImageView中Image的真实宽高，
        int dw = imageView.getDrawable().getBounds().width();
        int dh = imageView.getDrawable().getBounds().height();

        //获得ImageView中Image的变换矩阵
        Matrix m = imageView.getImageMatrix();
        float[] values = new float[10];
        m.getValues(values);

        //Image在绘制过程中的变换矩阵，从中获得x和y方向的缩放系数
        float sx = values[0];
        float sy = values[4];

        //计算Image在屏幕上实际绘制的宽高
        int showW = (int) (dw * sx);
        int showH = (int) (dh * sy);

        String imgInfo = "originSize:"+dw+"*"+dh+", showSize:"+showW+"*"+showH+"\n";

        textView.setText(imgInfo+screenInfo);
    }
}
