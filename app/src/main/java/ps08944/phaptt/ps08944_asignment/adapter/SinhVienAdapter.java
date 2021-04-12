package ps08944.phaptt.ps08944_asignment.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;

import ps08944.phaptt.ps08944_asignment.Main2Activity;
import ps08944.phaptt.ps08944_asignment.MainActivity;
import ps08944.phaptt.ps08944_asignment.R;
import ps08944.phaptt.ps08944_asignment.SinhVienActivity;
import ps08944.phaptt.ps08944_asignment.XuLyActivity;
import ps08944.phaptt.ps08944_asignment.model.SinhVien;

public class SinhVienAdapter extends ArrayAdapter {
    TextView tvMaSV, tvTenSV, tvMaLop;
    Context context;
    ArrayList<SinhVien> sinhViens;
    Button btnXoa, btnSua;

    public SinhVienAdapter(Context context, ArrayList<SinhVien> sinhViens) {
        super(context, 0, sinhViens);
        this.context = context;
        this.sinhViens = sinhViens;
    }

    ;

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.item_sinhvien, parent, false);
        }
        final SinhVien sv = sinhViens.get(position);
        if (sv != null) {
            //anh xa
            tvMaSV = (TextView) v.findViewById(R.id.tvMaSV);
            tvTenSV = (TextView) v.findViewById(R.id.tvTenSV);
            tvMaLop = (TextView) v.findViewById(R.id.tvMaLop);
            btnXoa = (Button) v.findViewById(R.id.btnXoa);
            btnSua = (Button) v.findViewById(R.id.btnSua);
            //set data len
            tvMaSV.setText(sv.maSV);
            tvTenSV.setText(sv.tenSV);
            tvMaLop.setText(sv.maLop);

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
                sinhViens.remove(position);
                ((XuLyActivity) context).adapter.notifyDataSetChanged();
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
                        ((XuLyActivity) context).xoaSV(sv.maSV);
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
                        Intent intent = new Intent(context, SinhVienActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("MaSV", sv.maSV);
                        bundle.putString("TenSV", sv.tenSV);
                        bundle.putString("MaLop", sv.maLop);
                        intent.putExtra("sinhvien", bundle);
                        ((XuLyActivity) context).startActivity(intent);
                    }
                });
            }
        });
        return v;
    }
}
