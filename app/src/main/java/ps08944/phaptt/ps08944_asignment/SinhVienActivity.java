package ps08944.phaptt.ps08944_asignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ps08944.phaptt.ps08944_asignment.database.SinhvienDAO;
import ps08944.phaptt.ps08944_asignment.model.SinhVien;

public class SinhVienActivity extends AppCompatActivity {
    Button btnThem, btnSua;
    SinhVien sv;
    SinhvienDAO svDAO;
String chon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinh_vien);

        final EditText edtMaSV, edtTenSV, edtMaLop;
        final Spinner spSV;



        edtMaSV=findViewById(R.id.edtMaSV);
        edtTenSV=findViewById(R.id.edtTenSV);
        edtMaLop=findViewById(R.id.edtMaLop);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        svDAO = new SinhvienDAO(this);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSV = edtMaSV.getText().toString();
                String tenSV = edtTenSV.getText().toString();
                String maLop = chon ;
                sv = new SinhVien(maSV,tenSV,maLop);
                long kq = svDAO.insert(sv);
                if(kq==-1){
                    Toast.makeText(SinhVienActivity.this, "Thất Bại", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(SinhVienActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                }
                //ketthuc activity
                finish();
            };

        });

         Intent intent = getIntent();
        Bundle bundle =intent.getBundleExtra("sinhvien");
        if(bundle!= null){
            String MaSV = bundle.getString("MaSV","");
            if(!MaSV.isEmpty()){
                edtMaSV.setEnabled(false);

            }
            edtMaSV.setText(MaSV);
            edtTenSV.setText(bundle.getString("TenSV",""));
            edtMaLop.setText(bundle.getString("MaLop",""));
        }
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSV = edtMaSV.getText().toString();
                String tenSV = edtTenSV.getText().toString();
                String maLop= edtMaLop.getText().toString();
                sv = new SinhVien(maSV,tenSV,maLop);
                svDAO.update(sv);
                finish();
            }
        });


        spSV = (Spinner)findViewById(R.id.spSV);
        svDAO = new SinhvienDAO(this);
        final List<String> list = new ArrayList<>();
        list.add("Lập trình máy tính");
        list.add("Ứng dụng phần mềm");
        list.add("Thiết kế đồ họa");
        list.add("Thiết kế website");


        final ArrayAdapter arrayAdapter = new ArrayAdapter(SinhVienActivity.this,R.layout.support_simple_spinner_dropdown_item,list);
        spSV.setAdapter(arrayAdapter);

        spSV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chon = parent.getItemAtPosition(position).toString();
                Toast.makeText(SinhVienActivity.this, "Ban vua chon"+ chon, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}
