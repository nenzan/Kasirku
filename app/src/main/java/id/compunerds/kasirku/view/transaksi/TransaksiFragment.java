package id.compunerds.kasirku.view.transaksi;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.compunerds.kasirku.R;
import id.compunerds.kasirku.adapter.BarangAdapter;
import id.compunerds.kasirku.apihelper.BaseApiService;
import id.compunerds.kasirku.model.ResponseBarang;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransaksiFragment extends Fragment {


    Context mContext;
    List<ResponseBarang.Result> results = new ArrayList<>();
    BarangAdapter barangAdapter;
    BaseApiService mApiService;
    RecyclerView recyclerView;

    public TransaksiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaksi, container, false);
    }

}
