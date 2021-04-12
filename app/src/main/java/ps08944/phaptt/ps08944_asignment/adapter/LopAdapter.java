package ps08944.phaptt.ps08944_asignment.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ps08944.phaptt.ps08944_asignment.LopActivity;
import ps08944.phaptt.ps08944_asignment.R;
import ps08944.phaptt.ps08944_asignment.XuLyActivity;
import ps08944.phaptt.ps08944_asignment.XuLyLopActivity;
import ps08944.phaptt.ps08944_asignment.model.Lop;

public class LopAdapter extends ArrayAdapter {
    TextView tvMaLop, tvTenLop;
    Context context;
    ArrayList<Lop> lops;
    Button btnXoa, btnSua;

    public LopAdapter(Context context,ArrayList<Lop> lops){
        super(context,0,lops);
        this.context =context;
        this.lops=lops;
    };

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.item_lop, parent, false);
        }
        final Lop l = lops.get(position);
        if (l != null) {
            //anh xa
            tvMaLop = (TextView) v.findViewById(R.id.tvMaLop);
            tvTenLop = (TextView) v.findViewById(R.id.tvTenLop);
            btnXoa = (Button) v.findViewById(R.id.btnXoa);
            btnSua = (Button) v.findViewById(R.id.btnSua);
            //set data len
            tvMaLop.setText(l.maLop);
            tvTenLop.setText(l.tenLop);


        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Bạn vừa click" + position, Toast.LENGTH_SHORT).show();
            }
        });
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                lops.remove(position);
                ((XuLyLopActivity) context).adapter.notifyDataSetChanged();
                return false;
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có chắc chắn xóa?");
                builder.setCancelable(false);
                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        ((XuLyLopActivity) context).xoaLop(l.maLop);
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, LopActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("MaLop", l.maLop);
                        bundle.putString("tenLop", l.tenLop);
                        intent.putExtra("lop", bundle);
                        ((XuLyLopActivity) context).startActivity(intent);
                    }
                });
            }
        });
        return v;
    }
}
