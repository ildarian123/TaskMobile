package com.example.taskmobile.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.taskmobile.App;
import com.example.taskmobile.R;

import javax.inject.Inject;


public class ErorScreenFragment extends Fragment {


    Button buttonReload;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_eror_screen, container, false);

        buttonReload = view.findViewById(R.id.button_reload);
        buttonReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)getActivity()).navController.navigate(R.id.MainScreenFragment);
            }
        });

        return view;
    }
}
