package com.example.demofocuslast.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.demofocuslast.MainActivity;
import com.example.demofocuslast.R;

public class StatsFragment extends Fragment {
    private String TAG = "StatsFragment";
    ImageView imgMenu;
    private TextView txtCalendar, txtTaskCompleted, txtMinutesSpent;
    CalendarView calendarView;
    private String value = null;

    public StatsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        //mapping
        addControlers(view);


        //events
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });
        if (value != null) {
            Log.d(TAG, value);
            txtTaskCompleted.setText(value);
        }
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    private void addControlers(View view) {
        imgMenu = view.findViewById(R.id.imgMenu);
        txtCalendar = view.findViewById(R.id.txtCalendar);
        txtMinutesSpent = view.findViewById(R.id.txtMinutesCompleted);
        txtTaskCompleted = view.findViewById(R.id.txtTaskCompleted);
        calendarView = view.findViewById(R.id.calendar_view);


    }

    public void sendText(String text) {
        Log.d(TAG, "sendText " + text);
        value = text;
        if (txtTaskCompleted != null) {
            txtTaskCompleted.setText(text);
        }
    }
}