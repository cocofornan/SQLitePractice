package com.example.sqlite;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MainActivity extends Activity {
    private GoodsDatabaseHelper databaseHelper;
    private int count = 0;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.result);

        databaseHelper = new GoodsDatabaseHelper(this);

        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        count = sp.getInt("count", 0);
    }

    public void insert(View view) {
        Goods goods = new Goods(
                count,
                UUID.randomUUID().toString(),
                "商品" + count,
                String.format("%.2f", Math.random()*100),
                "http://example.com/pic" + count,
                (int) (Math.random() * 100),
                (new Date()).getTime()
        );
        count++;
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putInt("count", count);
        editor.commit();

        databaseHelper.inertGoods(goods);
    }

    public void queryAll(View view) {
        List<Goods> goods = databaseHelper.queryAllGoods();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < goods.size(); i++) {
            sb.append(String.format("%s     %4s    %02d\n",
                    goods.get(i).getDesc(), goods.get(i).getPrice(), goods.get(i).getLeft()));
        }
        tvResult.setText(sb.toString());
    }

}
