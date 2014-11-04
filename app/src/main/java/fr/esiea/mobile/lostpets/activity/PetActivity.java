package fr.esiea.mobile.lostpets.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;

import fr.esiea.mobile.lostpets.R;
import fr.esiea.mobile.lostpets.fragment.PetFragment;
import fr.esiea.mobile.lostpets.fragment.PetListFragment;

public class PetActivity extends Activity implements PetFragment.OnFragmentInteractionListener, PetListFragment.OnFragmentInteractionListener {

    @Override
    public void onFragmentInteraction(Uri uri) {
        // do nothing
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_pet);

        // on regarde la configuration
        // si mode smartphone
        if (findViewById(R.id.fgt_container) != null){
            // si on a des info d'etat ne rien faire
            if (savedInstanceState != null){
                return;
            }

            // on peut creer le fragment
            PetListFragment firstFrag = new PetListFragment();
            // on lui passe les arguments de l'intent qui a déclencher le oncreate
            firstFrag.setArguments(getIntent().getExtras());

            //Ajout du fragment au layout
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fgt_container, firstFrag)
                    .commit();
        }

    }


    @Override
    public void onPetSelected(Integer id) {
        // si mode smartphone
        if (findViewById(R.id.fgt_container) != null){
            // on peut creer le fragment
            PetFragment nextFrag = new PetFragment();
            // on lui passe les arguments de l'intent
            Bundle args = new Bundle();
            args.putInt(PetFragment.ARG_PET_ID, id);
            nextFrag.setArguments(args);

            //Ajout du fragment au layout
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fgt_container,nextFrag)
                    .addToBackStack(null)
                    .commit();
        }
        else {
            // sinon on est en mode deux panneaux
            PetFragment petFragment = (PetFragment) getFragmentManager().findFragmentById(R.id.fgt_pet);
            petFragment.refresh(id);
        }

    }
}
