package id.compunerds.kasirku;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.compunerds.kasirku.adapter.RecyclerViewAdapter;
import id.compunerds.kasirku.apihelper.BaseApiService;
import id.compunerds.kasirku.apihelper.UtilsApi;
import id.compunerds.kasirku.data.Data;
import id.compunerds.kasirku.data.Value;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatabaseActivity extends AppCompatActivity {

    FloatingActionButton fab;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    private List<Data> results = new ArrayList<>();
    private RecyclerViewAdapter viewAdapter;
    BaseApiService mApiService;
    Context mContext;

    RecyclerView rv;
    ProgressBar pb;
    EditText txtNamaBarang, txtStokBarang, txtKodeBarang, txtHargaDasar, txtHargaJual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        init();

        viewAdapter = new RecyclerViewAdapter(this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(viewAdapter);

        loadDataBarang();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogForm("", "", "", "", "", "SIMPAN");
            }
        });
    }

    private void loadDataBarang() {
        mApiService.view().enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                pb.setVisibility(View.GONE);

                    results = response.body().getResult();
                    viewAdapter = new RecyclerViewAdapter(DatabaseActivity.this, results);
                    rv.setAdapter(viewAdapter);

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }

    private void init() {
        fab = (FloatingActionButton) findViewById(R.id.fab_add);
        rv = (RecyclerView) findViewById(R.id.recyclerView);
        pb = (ProgressBar) findViewById(R.id.progress_bar);
    }

    private void DialogForm(String nama, String stok, String kode, String hargaDasar, String hargaJual, String button) {
        dialog = new AlertDialog.Builder(DatabaseActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_barang, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Form Biodata");

        txtNamaBarang   = (EditText) dialogView.findViewById(R.id.txtNamaBarang);
        txtStokBarang   = (EditText) dialogView.findViewById(R.id.txtStokBarang);
        txtKodeBarang   = (EditText) dialogView.findViewById(R.id.txtKodeBarang);
        txtHargaDasar   = (EditText) dialogView.findViewById(R.id.txtHargaDasar);
        txtHargaJual    = (EditText) dialogView.findViewById(R.id.txtHargaJual);

        if (!txtNamaBarang.equals("")){
            txtNamaBarang.setText(nama);
            txtStokBarang.setText(stok);
            txtKodeBarang.setText(kode);
            txtHargaDasar.setText(hargaDasar);
            txtHargaJual.setText(hargaJual);
        } else {
            kosong();
        }

        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                kosong();
            }
        });

        dialog.show();
    }

    private void kosong() {
        txtNamaBarang.setText(null);
        txtStokBarang.setText(null);
        txtKodeBarang.setText(null);
        txtHargaDasar.setText(null);
        txtHargaJual.setText(null);
    }


}
