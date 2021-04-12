package ps08944.phaptt.ps08944_asignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtuser, edtpass;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtuser=(EditText)findViewById(R.id.edtuser);
        edtpass=(EditText)findViewById(R.id.edtpass);
        btnLogin=(Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtuser.getText().toString().equalsIgnoreCase("admin")&&edtpass.getText().toString().equalsIgnoreCase("admin")){

                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                } else
                    Toast.makeText(MainActivity.this,"Vui lòng không bỏ trống",Toast.LENGTH_SHORT).show();
            }
        }
        );



    }

}
