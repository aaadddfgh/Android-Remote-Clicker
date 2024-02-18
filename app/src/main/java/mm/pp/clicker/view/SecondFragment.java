package mm.pp.clicker.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import mm.pp.clicker.R;

import static mm.pp.clicker.service.ClickerService.isAccessibilityServiceSettingOn;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        isAccessibilityServiceSettingOn(getActivity());
        // Inflate the layout for this fragment
        Intent intent = new Intent("mm.pp.clicker.broadcast");
        intent.setAction("mm.pp.clicker.broadcast");
        intent.putExtra("commands", "swipe 500 10 500 800;");
        getActivity().sendBroadcast(intent);
//        Intent intent = new Intent("mm.pp.clicker.broadcast");
//        intent.setAction("mm.pp.clicker.broadcast");
//        intent.putExtra("x", 100);
//        intent.putExtra("y", 100);
//        getActivity().sendBroadcast(intent);
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("view","motion x:" +motionEvent.getX()+ "   y:" +motionEvent.getY());
                return false;
            }
        });


        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

}