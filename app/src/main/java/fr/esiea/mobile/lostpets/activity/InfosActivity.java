package fr.esiea.mobile.lostpets.activity;

import android.app.Activity;
import android.os.Bundle;

import fr.esiea.mobile.lostpets.R;

//This class is the InfosActivity linked to activity_infos.xml
public class InfosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);
    }
}
