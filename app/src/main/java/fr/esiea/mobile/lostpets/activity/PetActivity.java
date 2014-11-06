package fr.esiea.mobile.lostpets.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;

import fr.esiea.mobile.lostpets.R;
import fr.esiea.mobile.lostpets.fragment.PetFragment;
import fr.esiea.mobile.lostpets.fragment.PetListFragment;

//This class is the PetActivity linked to activity_pet.xml
public class PetActivity extends Activity implements PetFragment.OnFragmentInteractionListener, PetListFragment.OnFragmentInteractionListener {

    @Override
    public void onFragmentInteraction(Uri uri) {
        // do nothing
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        //If it's a smartphone
        if (findViewById(R.id.fgt_container) != null){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            PetListFragment firstFrag = new PetListFragment();
            firstFrag.setArguments(getIntent().getExtras());

            //Add this fragment on the layout
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fgt_container, firstFrag)
                    .commit();
        }
        else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

    }


    @Override
    public void onPetSelected(Integer id) {
        //If it's a smartphone
        if (findViewById(R.id.fgt_container) != null){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            PetFragment nextFrag = new PetFragment();
            //Pass selected petId argument
            Bundle args = new Bundle();
            args.putInt(PetFragment.ARG_PET_ID, id);
            nextFrag.setArguments(args);

            //Add this fragment on the layout
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fgt_container,nextFrag)
                    .addToBackStack(null)
                    .commit();
        }
        else {
            //If it's a tablet
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            PetFragment petFragment = (PetFragment) getFragmentManager().findFragmentById(R.id.fgt_pet);
            petFragment.refresh(id);
        }

    }
}
