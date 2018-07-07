package id.compunerds.kasirku.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.compunerds.kasirku.R;
import id.compunerds.kasirku.apihelper.BaseApiService;
import id.compunerds.kasirku.apihelper.UtilsApi;
import id.compunerds.kasirku.view.barang.BarangFragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

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

    Button btnHapus, btnEdit;

    String mNama, mStok, mKode, mHargaDasar, mHargaJual;
    Context mContext;
    BaseApiService mApiService;

    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;

    EditText txtNamaBarang, txtStokBarang, txtKodeBarang, txtHargaDasar, txtHargaJual;

    public String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        btnHapus = (Button) findViewById(R.id.btnHapusBarang);
        btnEdit = (Button) findViewById(R.id.btnEditBarang);

        Intent intent = getIntent();
        mNama = intent.getStringExtra("KEY_NAMA");
        mStok = intent.getStringExtra("KEY_STOK");
        mKode = intent.getStringExtra("KEY_KODE");
        mHargaDasar = intent.getStringExtra("KEY_HARGA_DASAR");
        mHargaJual = intent.getStringExtra("KEY_HARGA_JUAL");

        tvNamaBarangDetail.setText(mNama);
        tvStokBarangDetail.setText(mStok);
        tvKodeBarangDetail.setText(mKode);
        tvHargaDasarBarangDetail.setText(mHargaDasar);
        tvHargaJualBarangDetail.setText(mHargaJual);

        String firstCharNama = mNama.substring(0,1);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstCharNama, getColor());
        ivTextDrawableDetail.setImageDrawable(drawable);

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Anda Yakin akan menghapus barang?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteBarang();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEditForm("", "", "", "", "", "Update");

            }
        });
    }

    private void DialogEditForm(String namaEdit, String stokEdit, String kodeEdit, String hDasarEdit, String hJualEdit, String update) {
        dialog = new AlertDialog.Builder(DetailActivity .this);
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
            txtNamaBarang.setText(mNama);
            txtStokBarang.setText(mStok);
            txtKodeBarang.setText(mKode);
            txtHargaDasar.setText(mHargaDasar);
            txtHargaJual.setText(mHargaJual);

        } else {
            kosong();
        }

        dialog.setPositiveButton(update, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String namaBarang = txtNamaBarang.getText().toString();
                String stokBarang = txtStokBarang.getText().toString();
                String kodeBarang = txtKodeBarang.getText().toString();
                String hargaDasarBarang = txtHargaDasar.getText().toString();
                String hargaJualBarang = txtHargaJual.getText().toString();

                mApiService.updateBarang(namaBarang, stokBarang, kodeBarang, hargaDasarBarang, hargaJualBarang)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(mContext, "Berhasil Merubah Data Barang", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(mContext, "Gagal Merubah Data Barang", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(mContext, "Gagal Merubah Data Barang", Toast.LENGTH_SHORT).show();
                            }
                        });

                dialog.dismiss();
                finish();
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

    @Override
    public void onBackPressed() {
        finish();
    }

    private void deleteBarang() {
        mApiService.deleteBarang(mKode).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Toast.makeText(mContext, "Berhasil menghapus barang", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, BarangFragment.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }else {
                    Toast.makeText(mContext, "Gagal menghapus barang", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int getColor(){
        String color;

        // Randomly select a fact
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }

    private void kosong() {
        txtNamaBarang.setText(null);
        txtStokBarang.setText(null);
        txtKodeBarang.setText(null);
        txtHargaDasar.setText(null);
        txtHargaJual.setText(null);
    }

}
