package fr.esiea.mobile.lostpets.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import fr.esiea.mobile.lostpets.R;
import fr.esiea.mobile.lostpets.adapter.PetAdapter;
import fr.esiea.mobile.lostpets.dao.WebServiceDAO;
import fr.esiea.mobile.lostpets.model.Pet;
import fr.esiea.mobile.lostpets.model.Pets;

/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PetListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PetListFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class PetListFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private ListView listView;
    private WebServiceDAO webServiceDAO;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param login Le compte a utiliser.
     * @return A new instance of fragment PetListFragment.
     */
    public static PetListFragment newInstance(String login) {
        PetListFragment fragment = new PetListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public PetListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Initialize webServiceDAO
        webServiceDAO = new WebServiceDAO(getActivity());
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fgt_pet_list, container, false);
        listView = (ListView) rootView.findViewById(R.id.lst_lostPets);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Start the spinner at the beginning of the process
        getActivity().setProgressBarIndeterminate(true);
        Toast.makeText(this.getActivity(), R.string.msg_contactWebApi, Toast.LENGTH_LONG).show();

        PetAdapter adapter = null;
        try {
           adapter = webServiceDAO.getInfos();
        }
        catch (Exception e) {
            Toast.makeText(this.getActivity(), R.string.err_contactWebApi, Toast.LENGTH_LONG).show();
        }

        if (adapter != null) {
            // On associe l'adapter a notre ListView
            listView.setAdapter(adapter);
            // action de selection d'un objet dans la liste
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Pet selected = Pets.getInstance().getPets().get(i);
                    mListener.onPetSelected(selected.getM_petId());
                }
            });
        }
        else {
            Toast.makeText(this.getActivity(), R.string.err_contactWebApi, Toast.LENGTH_LONG).show();
        }

        // Stop the spinner at the end of the process
        getActivity().setProgressBarIndeterminate(false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onPetSelected(Integer id);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}