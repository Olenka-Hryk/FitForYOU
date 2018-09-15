package com.example.olenka.fitforyou.DataBase;

        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteQueryBuilder;

        import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Olenka on 23.03.2018.
 */

public class FitForYouDB extends SQLiteAssetHelper {
    private static String DB_NAME = "FitForYOU.db";
    private static int DB_VER =1;

    public FitForYouDB(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public int getReminderMode()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Mode"};
        String sqlTable = "Reminder";
        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect,null,null,null,null,null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("Mode"));
    }

    public void saveReminderMode(int value)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE Reminder SET Mode = "+value;
        db.execSQL(query);
    }

    public List<String> getWorkoutDay()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Day"};
        String sqlTable = "WorkoutDays";
        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect,null,null,null,null,null);

        List<String> result = new ArrayList<>();
        if(c.moveToFirst())
        {
            do {
                result.add(c.getString(c.getColumnIndex("Day")));
            }  while (c.moveToNext());
        }
        return result;
    }

    public void saveDay(String value)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO WorkoutDays(Day) VALUES ('%s');", value);
        db.execSQL(query);
    }
}
