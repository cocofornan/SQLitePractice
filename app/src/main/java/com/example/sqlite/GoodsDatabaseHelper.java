package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangt on 2017/2/18.
 */

public class GoodsDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "goods.db";
    private static final int DB_VERSION = 1;

    private static class GoodsTable {
        private static final String TABLE_NAME = "goods";

        private static final String COLUMN_NAME_ID = "_id";
        private static final String COLUMN_NAME_GOODS_ID = "goods_id";
        private static final String COLUMN_NAME_DESC = "desc";
        private static final String COLUMN_NAME_PRICE = "price";
        private static final String COLUMN_NAME_PIC_URL = "pic_url";
        private static final String COLUMN_NAME_LEFT = "left";
        private static final String COLUMN_NAME_TIMESTAMP = "timestamp";
    }

    public GoodsDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + GoodsTable.TABLE_NAME + " ("
                + GoodsTable.COLUMN_NAME_ID + " INTEGER PRIMARY KEY, "
                + GoodsTable.COLUMN_NAME_GOODS_ID + " TEXT NOT NULL, "
                + GoodsTable.COLUMN_NAME_DESC + " TEXT, "
                + GoodsTable.COLUMN_NAME_PRICE + " TEXT, "
                + GoodsTable.COLUMN_NAME_PIC_URL + " TEXT, "
                + GoodsTable.COLUMN_NAME_LEFT + " INTEGER, "
                + GoodsTable.COLUMN_NAME_TIMESTAMP + " INTEGER"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inertGoods(Goods goods) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(GoodsTable.COLUMN_NAME_ID, goods.getId());
        cv.put(GoodsTable.COLUMN_NAME_GOODS_ID, goods.getGoodsId());
        cv.put(GoodsTable.COLUMN_NAME_DESC, goods.getDesc());
        cv.put(GoodsTable.COLUMN_NAME_PRICE, goods.getPrice());
        cv.put(GoodsTable.COLUMN_NAME_PIC_URL, goods.getPicUrl());
        cv.put(GoodsTable.COLUMN_NAME_LEFT, goods.getLeft());
        cv.put(GoodsTable.COLUMN_NAME_TIMESTAMP, goods.getTimestamp());

        db.insertWithOnConflict(
                GoodsTable.TABLE_NAME,
                null,
                cv,
                SQLiteDatabase.CONFLICT_REPLACE
        );
    }

    private String[] projection = {
            GoodsTable.COLUMN_NAME_DESC,
            GoodsTable.COLUMN_NAME_PRICE,
            GoodsTable.COLUMN_NAME_LEFT
    };

    String sortOrder = GoodsTable.COLUMN_NAME_TIMESTAMP + " DESC";

    public List<Goods> queryAllGoods() {
        SQLiteDatabase database = getReadableDatabase();

        List<Goods> goodsList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = database.query(
                    GoodsTable.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    sortOrder);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String desc = cursor.getString(cursor.getColumnIndex(GoodsTable.COLUMN_NAME_DESC));
                    String price = cursor.getString(cursor.getColumnIndex(GoodsTable.COLUMN_NAME_PRICE));
                    int left = cursor.getInt(cursor.getColumnIndex(GoodsTable.COLUMN_NAME_LEFT));

                    Goods message = new Goods(0, null, desc, price, null, left, 0);
                    goodsList.add(message);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return goodsList;
    }
}
