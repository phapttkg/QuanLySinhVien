package ps08944.phaptt.ps08944_asignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ps08944.phaptt.ps08944_asignment.adapter.LopAdapter;
import ps08944.phaptt.ps08944_asignment.database.LopDAO;
import ps08944.phaptt.ps08944_asignment.model.Lop;

public class XuLyLopActivity extends AppCompatActivity {
    LopDAO lopDAO;
    ArrayList<Lop> lops;
    ListView lvLop;
    Button btnAdd;
    public LopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xu_ly_lop);
        lvLop = findViewById(R.id.lvLop);
        btnAdd = findViewById(R.id.btnAdd);
        lops = new ArrayList<Lop>();
        lopDAO = new LopDAO(this);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(XuLyLopActivity.this, LopActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        capnhatLV();
    }
    public void capnhatLV(){
        lops =(ArrayList<Lop>)lopDAO.getLopAll();
        adapter = new LopAdapter(this,lops);
        lvLop.setAdapter(adapter);
    }
    public void xoaLop(String maLop){
        lopDAO.delete(maLop);
        capnhatLV();
    };
}
