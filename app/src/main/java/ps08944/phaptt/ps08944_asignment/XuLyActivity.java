package ps08944.phaptt.ps08944_asignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ps08944.phaptt.ps08944_asignment.adapter.SinhVienAdapter;
import ps08944.phaptt.ps08944_asignment.database.SinhvienDAO;
import ps08944.phaptt.ps08944_asignment.model.SinhVien;

public class XuLyActivity extends AppCompatActivity {
    SinhvienDAO sinhvienDAO;
    ArrayList<SinhVien> sinhViens;
    ListView lvSV;
    Button btnAdd;
    public SinhVienAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xu_ly);

        lvSV = findViewById(R.id.lvSV);
        btnAdd = findViewById(R.id.btnAdd);

        sinhViens = new ArrayList<SinhVien>();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(XuLyActivity.this, SinhVienActivity.class);
                startActivity(i);
            }
        });

    }
    @Override
    protected void onResume(){
        super.onResume();
        capnhatLV();
    };
    public void capnhatLV(){
        sinhvienDAO = new SinhvienDAO(this);
        sinhViens =(ArrayList<SinhVien>)sinhvienDAO.getSinhVienAll();
        adapter = new SinhVienAdapter(this,sinhViens);
        lvSV.setAdapter(adapter);

    }
    public void xoaSV(String maSV){
        sinhvienDAO.delete(maSV);
        capnhatLV();
    };
}
