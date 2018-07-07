package id.compunerds.kasirku.view.barang;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.compunerds.kasirku.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarangDetailFragment extends Fragment {


    public BarangDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_barang_detail, container, false);
    }

}
