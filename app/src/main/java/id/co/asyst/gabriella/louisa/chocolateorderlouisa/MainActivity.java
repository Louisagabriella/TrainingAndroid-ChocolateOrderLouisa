package id.co.asyst.gabriella.louisa.chocolateorderlouisa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bOrder, bFinish;
    ListView lvOrder;
    TextView tvTotal;
    ArrayList<String> listOrder = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    int harga;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bOrder = findViewById(R.id.buttonOrder);
        lvOrder = findViewById(R.id.listView);
        bFinish = findViewById(R.id.buttonFinish);
        tvTotal = findViewById(R.id.textViewTotal);

        bOrder.setOnClickListener(this);
        bFinish.setOnClickListener(this);
        lvOrder.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Confirmation")
                        .setCancelable(false)
                        .setMessage("Are You Sure Delete this Order?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final int positionToRemove = position;
                                listOrder.remove(positionToRemove);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("No", null).show();
                return false;
            }
        });

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listOrder);
        lvOrder.setAdapter(arrayAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOrder:
                Intent intent = new Intent(this, OrderActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.buttonFinish:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("THANK YOU")
                        .setCancelable(false)
                        .setMessage("Are You Sure to Exit?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).setNegativeButton("No", null).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.getExtras() != null) {
                    Bundle bundle = data.getExtras();
                    String name = bundle.getString("result");
                    harga = bundle.getInt("harga");
                    listOrder.add(name + harga);
                    arrayAdapter.notifyDataSetChanged();
                    total += (harga + ((harga * 10) / 100));
                    tvTotal.setText("Rp. " + total + "");
                } else {
                    Log.d("DATA", "NULL");

                }
            }
        }
    }
}
