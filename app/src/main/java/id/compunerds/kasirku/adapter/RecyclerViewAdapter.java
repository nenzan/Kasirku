package id.compunerds.kasirku.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.compunerds.kasirku.R;
import id.compunerds.kasirku.data.Data;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private Context context;
    private List<Data> results;

    public RecyclerViewAdapter(Context context, List<Data> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data result = results.get(position);
        holder.textViewNama.setText(result.getNama());
        holder.textViewStok.setText(result.getStok());
        holder.textViewKode.setText(result.getKode());
        holder.textViewHargaDasar.setText(result.getHargaDasar());
        holder.textViewHargaJual.setText(result.getHargaJual());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textNama) TextView textViewNama;
        @BindView(R.id.textStok) TextView textViewStok;
        @BindView(R.id.textKode) TextView textViewKode;
        @BindView(R.id.textHargaDasar) TextView textViewHargaDasar;
        @BindView(R.id.textHargaJual) TextView textViewHargaJual;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
