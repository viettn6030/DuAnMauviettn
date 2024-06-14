package com.example.duanmauviettn.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmauviettn.DTO.ThanhVienDTO;
import com.example.duanmauviettn.DbHelper.MyDbHelper;
import com.example.duanmauviettn.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class ThanhVienDAO {
   MyDbHelper myDbHelper;
   public ThanhVienDAO(Context context){
       myDbHelper = new MyDbHelper(context);
   }

    public ArrayList<ThanhVienDTO> getDSThanhVien(){
       ArrayList<ThanhVienDTO> list = new ArrayList<>();
       SQLiteDatabase sqLiteDatabase = myDbHelper.getReadableDatabase();
       Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THANHVIEN", null);
       if(cursor.getCount()!=0){
           cursor.moveToFirst();
           do {
               list.add(new ThanhVienDTO(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
           }while (cursor.moveToNext());
       }
       return list;
    }

    public boolean InsertThanhVien(String hoten , String namsinh, String cccd){
       SQLiteDatabase sqLiteDatabase = myDbHelper.getWritableDatabase();
       ContentValues values = new ContentValues();
       values.put("hoten", hoten);
       values.put("namsinh", namsinh);
       values.put("cccd", cccd);
       long check = sqLiteDatabase.insert("THANHVIEN", null, values);
       if(check ==-1)
           return false;
       return true;
    }


    public boolean UpdateThanhVien(int matv, String hoten, String namsinh, String cccd){
        SQLiteDatabase sqLiteDatabase = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", hoten);
        values.put("namsinh", namsinh);
        values.put("cccd", cccd);

        long check = sqLiteDatabase.update("THANHVIEN",values, "matv =?", new String[]{String.valueOf(matv)});
        if(check ==-1)
            return false;
        return true;
    }


    public int DeleteThongTin(int matv){
       SQLiteDatabase sqLiteDatabase = myDbHelper.getWritableDatabase();
       Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIEUMUON WHERE matv =?", new String[]{String.valueOf(matv)});
       if(cursor.getCount()!=0){
           return -1;
       }
       long check = sqLiteDatabase.delete("THANHVIEN","matv =?", new String[]{String.valueOf(matv)});
       if(check ==-1)
           return 0;
       return 1;

    }


    }

