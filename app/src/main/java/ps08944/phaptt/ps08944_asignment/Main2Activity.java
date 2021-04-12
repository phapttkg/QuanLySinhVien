package ps08944.phaptt.ps08944_asignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
   Button btnQLLop,btnQLSinhVien,btnOUT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnQLSinhVien= findViewById(R.id.btnQLSinhVien);
        btnQLLop = findViewById(R.id.btnQLLop);
        btnOUT = findViewById(R.id.btnOUT);

        btnQLSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,XuLyActivity.class);
                startActivity(intent);
            }
        });
        btnQLLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,XuLyLopActivity.class);
                startActivity(intent);
            }
        });

        btnOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                // Tao su kien ket thuc app
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
        });



    }
}
