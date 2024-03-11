package mm.pp.clicker.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public boolean onPreferenceTreeClick(Preference preference) {

            switch (preference.getKey()){
                case "reboot":{
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("mm.pp.clicker", MODE_PRIVATE).edit();
                    SwitchPreference switchPreference= findPreference("reboot");
                    editor.putBoolean("reboot", switchPreference.isChecked() );
                    editor.commit();
                    break;
                }
                case "wake":{
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("mm.pp.clicker", MODE_PRIVATE).edit();
                    SwitchPreference switchPreference= findPreference("wake");
                    editor.putBoolean("wake", switchPreference.isChecked() );
                    editor.commit();
                }
            }



            return super.onPreferenceTreeClick(preference);


        }



        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            getPreferenceManager().getSharedPreferences().getAll().forEach((e,i)->{Log.d("mm.pp.clicker.setting", "onCreatePreferences: "+e+" "+ i.toString());});

        }

    }
}