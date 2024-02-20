package mm.pp.clicker.view;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import mm.pp.clicker.R;
import mm.pp.clicker.security.Crypto;
import mm.pp.clicker.security.CryptoKey;
import mm.pp.clicker.service.HttpService;
import mm.pp.clicker.viewmodel.HomeViewViewModel;

public class HomeView extends Fragment {
    Timer timer;
    Button stopButton;
    Button startButton;
    private HomeViewViewModel mViewModel;

    public static HomeView newInstance() {
        return new HomeView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_view_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewViewModel.class);
        EditText port = getView().findViewById(R.id.editTextPort);
        port.setText(mViewModel.port.getValue());
        EditText key = getView().findViewById(R.id.editTextKey);
        port.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                mViewModel.port.setValue( textView.getText().toString());
                return true;
            }
        });

        stopButton =getView().findViewById(R.id.stop);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serverintent = new Intent(getContext(), HttpService.class );
                getActivity().stopService(serverintent);
            }
        });

        startButton =getView().findViewById(R.id.start);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CryptoKey.key=key.getText().toString();
                Intent serverIntent = new Intent(getContext(), HttpService.class );
                getActivity().startService(serverIntent);
            }
        });

        CheckBox checkBox = getView().findViewById(R.id.checkBox);

        checkBox.setChecked(mViewModel.password.getValue());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                key.setEnabled(b);

                key.setVisibility(b? View.VISIBLE:View.INVISIBLE);
                getView().findViewById(R.id.textNeedKey).setVisibility(b? View.VISIBLE:View.INVISIBLE);
                mViewModel.password.setValue(b);

            }
        });

        HomeViewViewModel.serverRunning.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                startButton.setEnabled(! HomeViewViewModel.serverRunning.getValue());
                stopButton.setEnabled( HomeViewViewModel.serverRunning.getValue());
            }
        });

        timer=new Timer();
        timer.schedule(new CommitTimer(),100,1000);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(timer==null) {
            timer = new Timer();
            timer.schedule(new CommitTimer(), 100, 1000);
        }
    }

    @Override
    public void onPause() {
        if(timer!=null)
            timer.cancel();
        timer=null;
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(timer!=null)
            timer.cancel();
        timer=null;
    }

    class CommitTimer extends TimerTask {
        @Override
        public void run() {
            HomeViewViewModel.serverRunning.postValue(HttpService.isServiceRuning());
        }
    }

}