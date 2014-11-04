package fr.esiea.mobile.lostpets.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import fr.esiea.mobile.lostpets.R;


public class MainActivity extends Activity implements View.OnClickListener {

    Button btnInfos;
    Button btnLostPet;
    Button btnFoundPet;
    Button btnLostPetAroundMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLostPet = (Button) findViewById(R.id.btn_lostMyPet);
        btnLostPet.setOnClickListener(this);
        btnFoundPet = (Button) findViewById(R.id.btn_findPet);
        btnFoundPet.setOnClickListener(this);
        btnLostPetAroundMe = (Button) findViewById(R.id.btn_lostPetsAroundMe);
        btnLostPetAroundMe.setOnClickListener(this);
        btnInfos = (Button) findViewById(R.id.btn_infos);
        btnInfos.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent nextActivity;
        if (R.id.btn_infos == view.getId()){
            nextActivity = new Intent(this, InfosActivity.class);
            startActivity(nextActivity);
        }
        else if (R.id.btn_findPet == view.getId()){
            nextActivity = new Intent(this, PetActivity.class);
            startActivity(nextActivity);
        }
        else if (R.id.btn_lostMyPet == view.getId()) {
            nextActivity = new Intent(this, CreateLostPetActivity.class);
            startActivity(nextActivity);
        }
    }
}
