package ps08944.phaptt.ps08944_asignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ps08944.phaptt.ps08944_asignment.database.LopDAO;
import ps08944.phaptt.ps08944_asignment.model.Lop;

public class LopActivity extends AppCompatActivity {
    Button btnThem, btnSua;
    Lop l;
    LopDAO lDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop);
        final EditText edtMaLop, edtTenLop;
        edtMaLop=findViewById(R.id.edtMaLop);
        edtTenLop=findViewById(R.id.edtTenLop);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        lDAO = new LopDAO(this);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maLop = edtMaLop.getText().toString();
                String tenLop = edtTenLop.getText().toString();

                l = new Lop(maLop,tenLop);
                long kq = lDAO.insert(l);
                if(kq==-1){
                    Toast.makeText(LopActivity.this, "Thất Bại", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LopActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                }
                //ketthuc activity
                finish();
            };

        });

         Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("lop");
        if(bundle!= null){
            String MaSV = bundle.getString("MaLop","");
            if(!MaSV.isEmpty()){
                edtMaLop.setEnabled(false);

            }

            edtTenLop.setText(bundle.getString("tenLop",""));


        }
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maLop = edtMaLop.getText().toString();
                String tenLop = edtTenLop.getText().toString();
                l = new Lop(maLop,tenLop);
                lDAO.update(l);
                finish();
            }
        });

    }
}
