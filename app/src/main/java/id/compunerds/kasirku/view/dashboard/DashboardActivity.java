package id.compunerds.kasirku.view.dashboard;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import id.compunerds.kasirku.R;
import id.compunerds.kasirku.view.barang.BarangFragment;
import id.compunerds.kasirku.view.cs.CsFragment;
import id.compunerds.kasirku.view.reports.ReportsFragment;
import id.compunerds.kasirku.view.settings.SettingsFragment;
import id.compunerds.kasirku.view.transaksi.TransaksiFragment;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.Fragment fragment = null;
                if (v == findViewById(R.id.btnDashboardBarang)){
                    fragment = new BarangFragment();
                }else if (v == findViewById(R.id.btnDashboardCS)){
                    fragment = new CsFragment();
                }else if (v == findViewById(R.id.btnDashboardTransaksi)) {
                    fragment = new TransaksiFragment();
                }else if (v == findViewById(R.id.btnDashboardSettings)) {
                    fragment = new SettingsFragment();
                }else if (v == findViewById(R.id.btnDashboardReports)) {
                    fragment = new ReportsFragment();
                }

                android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fLDashboard, fragment);
                transaction.commit();
            }
        };

        ImageButton btnBarang = (ImageButton) findViewById(R.id.btnDashboardBarang);
        btnBarang.setOnClickListener(listener);
        ImageButton btnCS = (ImageButton) findViewById(R.id.btnDashboardCS);
        btnCS.setOnClickListener(listener);
        ImageButton btnTransaksi = (ImageButton) findViewById(R.id.btnDashboardTransaksi);
        btnTransaksi.setOnClickListener(listener);
        ImageButton btnSettings = (ImageButton) findViewById(R.id.btnDashboardSettings);
        btnSettings.setOnClickListener(listener);
        ImageButton btnReports = (ImageButton) findViewById(R.id.btnDashboardReports);
        btnReports.setOnClickListener(listener);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Anda Yakin akan keluar dari aplikasi?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DashboardActivity.this.finish();
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

}
