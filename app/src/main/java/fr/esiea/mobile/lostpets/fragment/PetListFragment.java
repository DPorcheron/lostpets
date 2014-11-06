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
    private OnFragmentInteractionListener m_listener;
    private ListView m_listView;
    private WebServiceDAO m_webServiceDAO;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PetListFragment.
     */
    public static PetListFragment newInstance() {
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
        //Initialize m_webServiceDAO
        m_webServiceDAO = new WebServiceDAO(getActivity());
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fgt_pet_list, container, false);
        m_listView = (ListView) rootView.findViewById(R.id.lst_lostPets);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(this.getActivity(), R.string.msg_contactWebApi, Toast.LENGTH_LONG).show();

        PetAdapter adapter = null;
        try {
           adapter = m_webServiceDAO.getInfos();
        }
        catch (Exception e) {
            Toast.makeText(this.getActivity(), R.string.err_contactWebApi, Toast.LENGTH_LONG).show();
        }

        if (adapter != null) {
            m_listView.setAdapter(adapter);
            m_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Pet selected = Pets.getInstance().getPets().get(i);
                    m_listener.onPetSelected(selected.getM_petId());
                }
            });
        }
        else {
            Toast.makeText(this.getActivity(), R.string.err_contactWebApi, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            m_listener = (OnFragmentInteractionListener) activity;
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
        m_listener = null;
    }

}