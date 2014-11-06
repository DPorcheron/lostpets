package fr.esiea.mobile.lostpets.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import fr.esiea.mobile.lostpets.R;
import fr.esiea.mobile.lostpets.fragment.PetFragment;

//This class is the PetMarkerActivity linked to activity_pet_marker.xml
public class PetMarkerActivity extends Activity implements PetFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_marker);

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().get("petId") != null) {
                //Create fragment only if there is a petId argument
                PetFragment nextFrag = new PetFragment();

                Bundle args = new Bundle();
                args.putInt(PetFragment.ARG_PET_ID, (Integer) getIntent().getExtras().get("petId"));
                nextFrag.setArguments(args);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fgt_pet_infos_container,nextFrag)
                        .commit();
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // do nothing
    }

}
