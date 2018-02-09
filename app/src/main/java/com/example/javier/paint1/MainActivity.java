package com.example.javier.paint1;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static Mode mode = Mode.PATH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonLine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode = Mode.LINE;
            }
        });

        findViewById(R.id.buttonRect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode = Mode.RECT;
            }
        });

        findViewById(R.id.buttonCircle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode = Mode.CIRCLE;
            }
        });

        findViewById(R.id.buttonPath).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode = Mode.PATH;
            }
        });

        findViewById(R.id.buttonRed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.color = Color.RED;
            }
        });

        findViewById(R.id.buttonGreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.color = Color.GREEN;
            }
        });

        findViewById(R.id.buttonBlue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.color = Color.BLUE;
            }
        });

        findViewById(R.id.buttonYellow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.color = Color.YELLOW;
            }
        });

        findViewById(R.id.buttonStroke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.style = Paint.Style.STROKE;
            }
        });

        findViewById(R.id.buttonFill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.style = Paint.Style.FILL;
            }
        });

        findViewById(R.id.buttonNarrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PaintView.strokeWidth >= 5) {
                    PaintView.strokeWidth = PaintView.strokeWidth - 5;
                }

                if (PaintView.strokeWidth == 0) {
                    Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, String.valueOf((int) PaintView.strokeWidth), Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.buttonWider).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaintView.strokeWidth = PaintView.strokeWidth + 5;
                Toast.makeText(MainActivity.this, String.valueOf((int) PaintView.strokeWidth), Toast.LENGTH_SHORT).show();
            }
        });
    }

    enum Mode {
        LINE, RECT, CIRCLE, PATH
    }
}
