package com.example.kamusindonesiaminang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kamusindonesiaminang.R;
import com.example.kamusindonesiaminang.database.DatabaseAccess;
import com.example.kamusindonesiaminang.model.ModelKamus;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import cn.refactor.lib.colordialog.PromptDialog;

public class IndonesiaMinangAdapter extends RecyclerView.Adapter<IndonesiaMinangAdapter.IndonesiaMinangHolder> {

    Context ctx;
    ArrayList<ModelKamus> modelKamusArrayList;

    public IndonesiaMinangAdapter(Context context, ArrayList<ModelKamus> modelKamus) {
        this.ctx = context;
        this.modelKamusArrayList = modelKamus;
    }

    @NonNull
    @Override
    public IndonesiaMinangAdapter.IndonesiaMinangHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_data, parent, false);
        return new IndonesiaMinangHolder(view);
    }

    @Override
    public void onBindViewHolder(IndonesiaMinangAdapter.IndonesiaMinangHolder holder, final int position) {
        final ModelKamus modelKamus = modelKamusArrayList.get(position);

        holder.tvListData.setText(modelKamus.getStrKata());

        holder.cvListData.setOnClickListener(v -> {
            String strSelected = modelKamusArrayList.get(position).getStrKata();
            getArti(strSelected);
        });
    }

    private void getArti(String selectedFromList) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(ctx);
        databaseAccess.open();

        String strHasilArti = databaseAccess.getSelectedIndonesia(selectedFromList);
        databaseAccess.close();

        new PromptDialog(ctx)
                .setDialogType(PromptDialog.DIALOG_TYPE_INFO)
                .setAnimationEnable(true)
                .setTitleText(selectedFromList)
                .setContentText(strHasilArti)
                .setPositiveListener("TUTUP", PromptDialog::dismiss).show();
    }

    @Override
    public int getItemCount() {
        return modelKamusArrayList.size();
    }

    static class IndonesiaMinangHolder extends RecyclerView.ViewHolder {
        public MaterialCardView cvListData;
        public TextView tvListData;

        public IndonesiaMinangHolder(View itemView) {
            super(itemView);
            cvListData = itemView.findViewById(R.id.cvListData);
            tvListData = itemView.findViewById(R.id.tvListData);
        }
    }

}
