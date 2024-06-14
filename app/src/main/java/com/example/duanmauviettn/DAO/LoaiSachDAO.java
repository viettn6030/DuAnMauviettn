package com.example.duanmauviettn.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmauviettn.DTO.LoaiSachDTO;
import com.example.duanmauviettn.DbHelper.MyDbHelper;
import com.example.duanmauviettn.DTO.LoaiSachDTO;
import com.example.duanmauviettn.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class LoaiSachDAO {
    MyDbHelper myDbHelper;
    public LoaiSachDAO(Context context){
        myDbHelper = new MyDbHelper(context);
    }

    public ArrayList<LoaiSachDTO> getDSLoaiSach(){
        ArrayList<LoaiSachDTO> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISACH", null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new LoaiSachDTO(cursor.getInt(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }

        return list;
    }

    public boolean themLoaiSach(String tenloai){
        SQLiteDatabase sqLiteDatabase = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloai",tenloai);
        long check = sqLiteDatabase.insert("LOAISACH", null,values);
        if(check ==-1){
            return false;
        }else {
            return true;
        }
    }

    public int DeleteLoaiSach(int id){
        SQLiteDatabase sqLiteDatabase = myDbHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH WHERE maloai =?", new String[]{String.valueOf(id)});
        if(cursor.getCount()!= 0){
            return -1;
        }
        long check = sqLiteDatabase.delete("LOAISACH", "maloai =?", new String[]{String.valueOf(id)});
        if(check ==-1)
            return 0;
        return 1;
    }

    public boolean EditLoaiSach(LoaiSachDTO loaiSachDTO){
        SQLiteDatabase sqLiteDatabase = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloai",loaiSachDTO.getTenloai());

        long check = sqLiteDatabase.update("LOAISACH", values, "maloai =?", new String[]{String.valueOf(loaiSachDTO.getId())});

        if(check ==-1)
            return false;
        return true;
    }

}
