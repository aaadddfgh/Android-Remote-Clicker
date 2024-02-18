package mm.pp.clicker.view;

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
import android.widget.EditText;
import android.widget.TextView;

import mm.pp.clicker.R;
import mm.pp.clicker.security.Crypto;
import mm.pp.clicker.security.CryptoKey;
import mm.pp.clicker.service.HttpService;
import mm.pp.clicker.viewmodel.HomeViewViewModel;

public class HomeView extends Fragment {

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
        EditText key = getView().findViewById(R.id.editTextKey);
        port.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                mViewModel.port.setValue( textView.getText().toString());
                return true;
            }
        });

        Button button =getView().findViewById(R.id.stop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serverintent = new Intent(getContext(), HttpService.class );
                getActivity().stopService(serverintent);
            }
        });

        button =getView().findViewById(R.id.start);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CryptoKey.key=key.getText().toString();
                Intent serverIntent = new Intent(getContext(), HttpService.class );
                getActivity().startService(serverIntent);
            }
        });

    }

}