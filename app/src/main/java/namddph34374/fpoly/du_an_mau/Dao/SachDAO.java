package namddph34374.fpoly.du_an_mau.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.Dbheper.Dbheper;
import namddph34374.fpoly.du_an_mau.LopModel.Sach;

public class SachDAO {
    private  SQLiteDatabase database;
    private Dbheper dbheper;
    public SachDAO(Context context){dbheper = new Dbheper(context);}

    public ArrayList<Sach> getAllSanpham(){
        ArrayList<Sach> list = new ArrayList<>();
        database = dbheper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM  s", null);
        while (cursor.moveToNext()){
            Sach s = new Sach(
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3));
            s.setMas(cursor.getInt(0));
            list.add(s);
        }
        return list;
    }

    public long addSach(Sach s){
        database = dbheper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENS", s.getTens());
        values.put("GIAS", s.getGias());
        values.put("MALS", s.getMals());
        return database.insert("s", null, values);
    }
    public boolean updateSach(Sach s){
        database = dbheper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENS", s.getTens());
        values.put("GIAS", s.getGias());
        values.put("MALS", s.getMals());
        long row = database.update("s", values, "MASS=?", new String[]{
                String.valueOf(s.getMas())});
        return (row > 0);
    }
    public long deleteSach(int mas){
        database = dbheper.getWritableDatabase();
        int result = database.delete("s", "MAS=?", new String[]{String.valueOf(mas)});
        return result;
    }

}
