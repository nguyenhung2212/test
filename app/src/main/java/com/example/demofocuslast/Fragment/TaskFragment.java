package com.example.demofocuslast.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demofocuslast.Adapter.SendTextListenner;
import com.example.demofocuslast.MainActivity;
import com.example.demofocuslast.R;

import java.util.Locale;
import java.util.StringTokenizer;

public class TaskFragment extends Fragment {
    private String TAG = "TaskFragment";
    TextView txtCoundown;
    ImageView imgUp, imgDown, imgMenu, imgIsland;
    Animation animIsland;
    Button btnStart;

    private SendTextListenner sendTextListenner;


    private CountDownTimer countDownTimer;
    private boolean mTimeRunning;
    private long along;
    String timeSet = "";
    String taskComplete = "10";

    public TaskFragment() {

    }

    public void setSendTextListenner(SendTextListenner sendTextListenner) {
        this.sendTextListenner = sendTextListenner;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        //mapping
        txtCoundown = view.findViewById(R.id.txtCoundown);
        imgDown = view.findViewById(R.id.imgDown);
        imgIsland = view.findViewById(R.id.imgIsland);
        imgUp = view.findViewById(R.id.imgUp);
        imgMenu = view.findViewById(R.id.imgMenu);
        btnStart = view.findViewById(R.id.btnStart);
        timeSet = txtCoundown.getText().toString();

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });


        imgUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textTime = txtCoundown.getText().toString();
                final String[] splitTime = textTime.split(":");
                int time = Integer.parseInt(splitTime[1]);
                int hour = 0;
                if (time <= 55) {

                    time = time + 5;
                    int second = 0;
                    String dis = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, time, second);
                    txtCoundown.setText(dis);
                    timeSet = (String) txtCoundown.getText();
                }
            }
        });
        imgDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textTime = txtCoundown.getText().toString();
                final String[] splitTime = textTime.split(":");
                int time = Integer.parseInt(splitTime[1]);
                int second = Integer.parseInt(splitTime[2]);
                int hour = 0;
                if (time > 0) {
                    time = time - 5;
                    int seconds = 0;
                    String dis = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, time, second);
                    txtCoundown.setText(dis);
                    timeSet = (String) txtCoundown.getText();
                }
            }
        });

        //countdown
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick");
                sendTextListenner.sendText(taskComplete);
                if (mTimeRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });


        return view;
    }


    private void startTimer() {
        String textTime = txtCoundown.getText().toString();
        String[] splitTime = textTime.split(":");
        int h = Integer.parseInt(splitTime[0]);
        final int m = Integer.parseInt(splitTime[1]) * 60000;
        countDownTimer = new CountDownTimer(m, 1000) {
            @Override
            public void onTick(long l) {
                along = l;

                int hours = 0; // I don't know solving problems
                int minutes = (int) ((along / 1000) % 3600) / 60;
                int seconds = (int) ((along / 1000) % 60);

                String dis = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
                txtCoundown.setText(dis);

            }

            @Override
            public void onFinish() {
                mTimeRunning = false;
                btnStart.setText("Start");
                btnStart.setBackgroundResource(R.drawable.mybutton);
                taskComplete = taskComplete + 1;

            }
        }.start();
        mTimeRunning = true;
        btnStart.setText("Stop");
        btnStart.setBackgroundResource(R.drawable.mybuttonpause);

        animationImage();

    }

    private void pauseTimer() {
        countDownTimer.cancel();
        btnStart.setText("Start");
        btnStart.setBackgroundResource(R.drawable.mybutton);
        mTimeRunning = false;
        txtCoundown.setText(timeSet);
        imgIsland.setAnimation(null);
    }

    private void animationImage() {
        animIsland = AnimationUtils.loadAnimation(getContext(), R.anim.anim_img_island);
        imgIsland.setAnimation(animIsland);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}