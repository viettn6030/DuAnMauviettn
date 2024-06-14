package com.example.duanmauviettn.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmauviettn.Adapter.ThanhVienAdapter;
import com.example.duanmauviettn.DAO.ThanhVienDAO;
import com.example.duanmauviettn.DTO.ThanhVienDTO;
import com.example.duanmauviettn.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QLThanhVienFragment extends Fragment {
    RecyclerView recyclerView;
    ThanhVienDAO thanhVienDAO;
    ThanhVienAdapter thanhVienAdapter;
    SearchView searchView;
    ArrayList<ThanhVienDTO> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_qlthanhvien, container, false);

       recyclerView = view.findViewById(R.id.recycleThanhVien);
        FloatingActionButton actionButton = view.findViewById(R.id.floatAdd);
        thanhVienDAO = new ThanhVienDAO(getContext());
        searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
ArrayList<ThanhVienDTO> list1 = new ArrayList<>();
for (ThanhVienDTO thanhvien : list){
    if (thanhvien.getHoten().toLowerCase().contains(newText.toLowerCase())){
list1.add(thanhvien);
    }
}
loadData1(list1);
                return false;
            }
        });
        loadData();
        //


        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    showDailog();
            }
        });

       return view;
    }

    private void loadData1(ArrayList<ThanhVienDTO> list   ){
        thanhVienAdapter = new ThanhVienAdapter(getContext(),list,thanhVienDAO);
        recyclerView.setAdapter(thanhVienAdapter);
    }

    private void loadData(){
         list = thanhVienDAO.getDSThanhVien();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
         thanhVienAdapter = new ThanhVienAdapter(getContext(), list, thanhVienDAO);
        recyclerView.setAdapter(thanhVienAdapter);
    }

    private void showDailog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_thanhvien, null);
        builder.setView(view);

        EditText edtHoTen = view.findViewById(R.id.edt_ten_tv);
        EditText edtNgay = view.findViewById(R.id.edt_ngay_tv);
        EditText edtCccd = view.findViewById(R.id.edt_cccd);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String hoten = edtHoTen.getText().toString();
                String ngay = edtNgay.getText().toString();
                String cccd = edtCccd.getText().toString();

                boolean check = thanhVienDAO.InsertThanhVien(hoten, ngay, cccd);
                if(check){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
