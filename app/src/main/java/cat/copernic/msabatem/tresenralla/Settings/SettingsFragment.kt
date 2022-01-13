package cat.copernic.msabatem.tresenralla.Settings

import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import cat.copernic.msabatem.tresenralla.R

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }


    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        Log.d("HELP", "${preference?.key}")
        return super.onPreferenceTreeClick(preference)
    }


    override fun onResume() {
        Log.i("HELP",preferenceManager.sharedPreferencesName);
            super.onResume()
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
        super.onPause()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if(key == "darkmode"){
            when (sharedPreferences?.getBoolean(key, false)){
                true -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
                false -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
            }
        }

      //Log.d("HELP", "${sharedPreferences?.getBoolean(key, false)}")
    }

}