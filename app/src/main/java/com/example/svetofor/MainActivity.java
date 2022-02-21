package com.example.svetofor;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private LinearLayout b_1, b_2, b_3;
    private Button button_1;
    private int counter = 0;
    private boolean start_stop = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_1 = findViewById(R.id.bulb_1);
        b_2 = findViewById(R.id.bulb_2);
        b_3 = findViewById(R.id.bulb_3);
        button_1 = findViewById(R.id.button1);
    }

    public void onClickStart(View view)
    {
        if(!start_stop) {
            start_stop = true;
            button_1.setText("STOP"); button_1.setBackgroundColor(getResources().getColor(R.color.red));
            new Thread(() -> {
                while (start_stop) {
                    counter++;

                    try {
                        runOnUiThread(() -> {
                            switch (counter) {
                                case 1:
                                    b_1.setBackgroundColor(getResources().getColor(R.color.red));
                                    b_2.setBackgroundColor(getResources().getColor(R.color.grey));
                                    b_3.setBackgroundColor(getResources().getColor(R.color.grey));
                                    break;
                                case 2:
                                    b_1.setBackgroundColor(getResources().getColor(R.color.grey));
                                    b_2.setBackgroundColor(getResources().getColor(R.color.yellow));
                                    b_3.setBackgroundColor(getResources().getColor(R.color.grey));
                                    break;
                                case 3:
                                    b_1.setBackgroundColor(getResources().getColor(R.color.grey));
                                    b_2.setBackgroundColor(getResources().getColor(R.color.grey));
                                    b_3.setBackgroundColor(getResources().getColor(R.color.green));
                                    counter = 0;
                                    break;
                            }
                        });
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        else
        {
            start_stop=false;
            button_1.setText("START"); button_1.setBackgroundColor(getResources().getColor(R.color.green));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        start_stop = false;
    }
}