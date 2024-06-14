package com.example.duanmauviettn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmauviettn.DAO.ThuThuDAO;

public class DangNhapActivity extends AppCompatActivity {
    EditText namedn , matkhaudn;
    Button btndangnhap ;


    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        namedn = findViewById(R.id.edt_namedn);
        matkhaudn = findViewById(R.id.edt_matkhaudn);
        btndangnhap = findViewById(R.id.btn_dang_nhap);

        ThuThuDAO thuDAO = new ThuThuDAO(this);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = namedn.getText().toString();
                String matkhau = matkhaudn.getText().toString();

                if(thuDAO.checkDangNhap(user , matkhau)){

                    startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
                    
                }else {
                    Toast.makeText(DangNhapActivity.this, "Usename và mật khẩu không đúng ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}