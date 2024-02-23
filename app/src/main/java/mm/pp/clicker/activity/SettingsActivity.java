package mm.pp.clicker.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import mm.pp.clicker.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();

        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public boolean onPreferenceTreeClick(Preference preference) {

            SharedPreferences.Editor editer = getActivity().getSharedPreferences("mm.pp.clicker", MODE_PRIVATE).edit();
            SwitchPreference switchPreference= findPreference("reboot");
            editer.putBoolean("reboot", switchPreference.isChecked() );
            editer.commit();

            return super.onPreferenceTreeClick(preference);


        }



        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            getPreferenceManager().getSharedPreferences().getAll().forEach((e,i)->{Log.d("mm.pp.clicker.setting", "onCreatePreferences: "+e+" "+ i.toString());});

        }

    }
}