package id.compunerds.kasirku.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import id.compunerds.kasirku.R;
import id.compunerds.kasirku.adapter.BarangAdapter;
import id.compunerds.kasirku.apihelper.BaseApiService;
import id.compunerds.kasirku.apihelper.UtilsApi;
import id.compunerds.kasirku.model.ResponseBarang;
import id.compunerds.kasirku.utils.RecyclerItemClickListener;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatabaseActivity extends AppCompatActivity {


    private static final String TAG = "DatabaseActivity";
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefresh;
    FloatingActionButton fab;
    ProgressBar pb;

    Context mContext;
    List<ResponseBarang.Result> results = new ArrayList<>();
    BarangAdapter barangAdapter;
    BaseApiService mApiService;

    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txtNamaBarang, txtStokBarang, txtKodeBarang, txtHargaDasar, txtHargaJual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        init();

        barangAdapter = new BarangAdapter(this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getResultListBarang();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogForm("", "", "", "", "", "SIMPAN");
            }
        });

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getResultListBarang();
                mSwipeRefresh.setRefreshing(false);
                Toast.makeText(mContext, "Data Sudah Update", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getResultListBarang() {
        mApiService.getSemuaBarang().enqueue(new Callback<ResponseBarang>() {
            @Override
            public void onResponse(Call<ResponseBarang> call, Response<ResponseBarang> response) {
                pb.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    final List<ResponseBarang.Result> results = response.body().getResult();
                    recyclerView.setAdapter(new BarangAdapter(mContext, results));
                    barangAdapter.notifyDataSetChanged();

                    initDataIntent(results);
                }else {
                    Toast.makeText(mContext, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBarang> call, Throwable t) {
                Log.e(null, t.toString());
                Toast.makeText(mContext, "Response bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDataIntent(final List<ResponseBarang.Result> results) {
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String namaDetail = results.get(position).getNama();
                        String stokDetail = results.get(position).getStok();
                        String kodeDetail = results.get(position).getKode();
                        String hDasarDetail = results.get(position).getHargaDasar();
                        String hJualDetail = results.get(position).getHargaJual();

                        Intent detailBarang = new Intent(mContext, DetailActivity.class);
                        detailBarang.putExtra("KEY_NAMA", namaDetail);
                        detailBarang.putExtra("KEY_STOK", stokDetail);
                        detailBarang.putExtra("KEY_KODE", kodeDetail);
                        detailBarang.putExtra("KEY_HARGA_DASAR", hDasarDetail);
                        detailBarang.putExtra("KEY_HARGA_JUAL", hJualDetail);
                        startActivity(detailBarang);
                    }
                })
        );
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.rvBarang);
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        fab = (FloatingActionButton) findViewById(R.id.fab_add);
        pb = (ProgressBar) findViewById(R.id.progress_bar);
    }

    private void DialogForm(String nama, String stok, String kodeB, String hargaDasar, String hargaJual, String button) {
        dialog = new AlertDialog.Builder(DatabaseActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_barang, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Form Barang");

        txtNamaBarang   = (EditText) dialogView.findViewById(R.id.txtNamaBarang);
        txtStokBarang   = (EditText) dialogView.findViewById(R.id.txtStokBarang);
        txtKodeBarang   = (EditText) dialogView.findViewById(R.id.txtKodeBarang);
        txtHargaDasar   = (EditText) dialogView.findViewById(R.id.txtHargaDasar);
        txtHargaJual    = (EditText) dialogView.findViewById(R.id.txtHargaJual);

        if (!txtNamaBarang.equals("")){
            txtNamaBarang.setText(nama);
            txtStokBarang.setText(stok);
            txtKodeBarang.setText(kodeB);
            txtHargaDasar.setText(hargaDasar);
            txtHargaJual.setText(hargaJual);

        } else {
            kosong();
        }


        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String namaBarang = txtNamaBarang.getText().toString();
                String stokBarang = txtStokBarang.getText().toString();
                String kodeBarang = txtKodeBarang.getText().toString();
                String hargaDasarBarang = txtHargaDasar.getText().toString();
                String hargaJualBarang = txtHargaJual.getText().toString();

                mApiService.tambahBarang("6",namaBarang,stokBarang,kodeBarang,hargaDasarBarang,hargaJualBarang)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(mContext, "Berhasil Menambahkan Data Barang", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(mContext, "Gagal Menambahkan Data Barang", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(mContext, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                            }
                        });

                dialog.dismiss();
                getResultListBarang();
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
