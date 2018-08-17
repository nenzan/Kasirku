package id.compunerds.kasirku.view.barang;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.compunerds.kasirku.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarangDetailFragment extends Fragment {

    @BindView(R.id.ivTextDrawableDetail)
    ImageView ivTextDrawableDetail;
    @BindView(R.id.tvNamaBarangDetail)
    TextView tvNamaBarangDetail;
    @BindView(R.id.tvStokBarangDetail)
    TextView tvStokBarangDetail;
    @BindView(R.id.tvKodeBarangDetail)
    TextView tvKodeBarangDetail;
    @BindView(R.id.tvHargaDasarBarangDetail)
    TextView tvHargaDasarBarangDetail;
    @BindView(R.id.tvHargaJualBarangDetail)
    TextView tvHargaJualBarangDetail;
    @BindView(R.id.btnHapusBarang)
    Button btnHapusBarang;
    @BindView(R.id.btnEditBarang)
    Button btnEditBarang;
    Unbinder unbinder;

    public BarangDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_barang_detail, container, false);

        if (this.getArguments() != null) {
            String kode = getArguments().getString("KEY_KODE");
            String stok = getArguments().getString("KEY_STOK");
            String hdasar = getArguments().getString("KEY_HARGA_DASAR");
            String hjual = getArguments().getString("KEY_HARGA_JUAL");
            Toast.makeText(getActivity(), "Kode :" + kode + ", Stok :" + stok +
                    ", Harga Dasar :" + hdasar + ", Harga Jual:" + hjual, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Stream Data Kosong", Toast.LENGTH_SHORT).show();
        }


//        tvNamaBarangDetail.setText(kode);
//        tvKodeBarangDetail.setText(stok);
//        tvHargaDasarBarangDetail.setText(hdasar);
//        tvHargaJualBarangDetail.setText(hjual);

        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
