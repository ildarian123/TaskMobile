package com.example.taskmobile.ui.error;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.taskmobile.R;
import java.util.Objects;


public class ErrorScreenFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_error_screen, container, false);

        Button buttonReload = view.findViewById(R.id.button_reload);
        buttonReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.navigation.NavController navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
                navController.navigate(R.id.MainScreenFragment);
            }
        });

        return view;
    }
}
