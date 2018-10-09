package id.co.asyst.gabriella.louisa.chocolateorderlouisa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    EditText etName;
    RadioGroup rgChocolate;
    RadioButton rbOriginal, rbMilkChoco, rbChocolate, rbChocoNut, rbWhiteChoco, rbGreenChoco;
    CheckBox cbOreo, cbBubble, cbWhippedCream;
    Button bOreder;
    String selectedMenu;
    String selectedTopping = "";
    int total;

    ArrayList<String> listTopping = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        etName = findViewById(R.id.editTextOrderName);
        rgChocolate = findViewById(R.id.radioGroupChocolate);
        rbOriginal = findViewById(R.id.radioButtonOriginal);
        rbMilkChoco = findViewById(R.id.radioButtonMilkChoco);
        rbChocolate = findViewById(R.id.radioButtonChocholate);
        rbChocoNut = findViewById(R.id.radioButtonChocoNut);
        rbWhiteChoco = findViewById(R.id.radioButtonWhiteChocholate);
        rbGreenChoco = findViewById(R.id.radioButtonGreenChocholate);
        cbOreo = findViewById(R.id.checkboxOreo);
        cbBubble = findViewById(R.id.checkboxBubble);
        cbWhippedCream = findViewById(R.id.checkboxWhippedCream);
        bOreder = findViewById(R.id.buttonOrder);
        rgChocolate.setOnCheckedChangeListener(this);
        cbOreo.setOnCheckedChangeListener(this);
        cbBubble.setOnCheckedChangeListener(this);
        cbWhippedCream.setOnCheckedChangeListener(this);
        bOreder.setOnClickListener(this);
        rbOriginal.setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radioButtonOriginal)).setChecked(true);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButtonOriginal:
                selectedMenu = "Original Choco";
                break;
            case R.id.radioButtonChocholate:
                selectedMenu = "Chocolate";
                break;
            case R.id.radioButtonChocoNut:
                selectedMenu = "ChocoNut";
                break;
            case R.id.radioButtonMilkChoco:
                selectedMenu = "Milk Choco";
                break;
            case R.id.radioButtonWhiteChocholate:
                selectedMenu = "White Choco";
                break;
            case R.id.radioButtonGreenChocholate:
                selectedMenu = "Green Choco";
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        switch (id) {
            case R.id.checkboxOreo:
                if (isChecked) {
                    listTopping.add("- Oreo\n");
                } else {
                    listTopping.remove("- Oreo\n");
                }
                break;
            case R.id.checkboxBubble:
                if (isChecked) {
                    listTopping.add("- Bubble\n");
                } else {
                    listTopping.remove("- Bubble\n");
                }
                break;
            case R.id.checkboxWhippedCream:
                if (isChecked) {
                    listTopping.add("- Whipped Cream\n");
                } else {
                    listTopping.remove("- Whipped Cream\n");
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        selectedTopping = "";
        for (int i = 0; i < listTopping.size(); i++) {
            selectedTopping = selectedTopping + " " + listTopping.get(i);
        }
        switch (v.getId()) {
            case R.id.buttonOrder:
                countOrder();
                if (!TextUtils.isEmpty(etName.getText().toString())) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle("Confirmation")
                            .setCancelable(false)
                            .setMessage("Are You Sure Order?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent returnIntent = new Intent();
                                    String result = "Name Order  : " + etName.getText().toString() + "\n" + "Menu Order  :  " + selectedMenu + "\n" + "Topping : \n" + selectedTopping + "Total : ";
                                    int harga = total;
                                    returnIntent.putExtra("result", result);
                                    returnIntent.putExtra("harga", harga);
                                    setResult(Activity.RESULT_OK, returnIntent);
                                    finish();
                                }
                            }).setNegativeButton("No", null).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Order Name Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                    etName.setError("Empty!");
                }
                break;
        }
    }

    public void countOrder() {
        if (rbOriginal.isChecked()) {
            if (cbBubble.isChecked() && cbOreo.isChecked() && cbWhippedCream.isChecked()) {
                total = 15000 + 13000;
            } else if (cbBubble.isChecked() && cbOreo.isChecked()) {
                total = 15000 + 8000;
            } else if (cbOreo.isChecked() && cbWhippedCream.isChecked()) {
                total = 15000 + 8000;
            } else if (cbBubble.isChecked() && cbWhippedCream.isChecked()) {
                total = 15000 + 10000;
            } else if (cbBubble.isChecked()) {
                total = 15000 + 5000;
            } else if (cbOreo.isChecked()) {
                total = 15000 + 3000;
            } else if (cbWhippedCream.isChecked()) {
                total = 15000 + 5000;
            } else {
                total = 15000;
            }
        } else if (rbMilkChoco.isChecked()) {
            if (cbBubble.isChecked() && cbOreo.isChecked() && cbWhippedCream.isChecked()) {
                total = 18000 + 13000;
            } else if (cbBubble.isChecked() && cbOreo.isChecked()) {
                total = 18000 + 8000;
            } else if (cbOreo.isChecked() && cbWhippedCream.isChecked()) {
                total = 18000 + 8000;
            } else if (cbBubble.isChecked() && cbWhippedCream.isChecked()) {
                total = 18000 + 10000;
            } else if (cbBubble.isChecked()) {
                total = 18000 + 5000;
            } else if (cbOreo.isChecked()) {
                total = 18000 + 3000;
            } else if (cbWhippedCream.isChecked()) {
                total = 18000 + 5000;
            } else {
                total = 18000;
            }
        } else if (rbChocoNut.isChecked()) {
            if (cbBubble.isChecked() && cbOreo.isChecked() && cbWhippedCream.isChecked()) {
                total = 18000 + 13000;
            } else if (cbBubble.isChecked() && cbOreo.isChecked()) {
                total = 18000 + 8000;
            } else if (cbOreo.isChecked() && cbWhippedCream.isChecked()) {
                total = 18000 + 8000;
            } else if (cbBubble.isChecked() && cbWhippedCream.isChecked()) {
                total = 18000 + 10000;
            } else if (cbBubble.isChecked()) {
                total = 18000 + 5000;
            } else if (cbOreo.isChecked()) {
                total = 18000 + 3000;
            } else if (cbWhippedCream.isChecked()) {
                total = 18000 + 5000;
            } else {
                total = 18000;
            }
        } else if (rbChocolate.isChecked()) {
            if (cbBubble.isChecked() && cbOreo.isChecked() && cbWhippedCream.isChecked()) {
                total = 18000 + 13000;
            } else if (cbBubble.isChecked() && cbOreo.isChecked()) {
                total = 18000 + 8000;
            } else if (cbOreo.isChecked() && cbWhippedCream.isChecked()) {
                total = 18000 + 8000;
            } else if (cbBubble.isChecked() && cbWhippedCream.isChecked()) {
                total = 18000 + 10000;
            } else if (cbBubble.isChecked()) {
                total = 18000 + 5000;
            } else if (cbOreo.isChecked()) {
                total = 18000 + 3000;
            } else if (cbWhippedCream.isChecked()) {
                total = 18000 + 5000;
            }
        } else if (rbGreenChoco.isChecked()) {
            if (cbBubble.isChecked() && cbOreo.isChecked() && cbWhippedCream.isChecked()) {
                total = 20000 + 13000;
            } else if (cbBubble.isChecked() && cbOreo.isChecked()) {
                total = 20000 + 8000;
            } else if (cbOreo.isChecked() && cbWhippedCream.isChecked()) {
                total = 20000 + 8000;
            } else if (cbBubble.isChecked() && cbWhippedCream.isChecked()) {
                total = 20000 + 10000;
            } else if (cbBubble.isChecked()) {
                total = 20000 + 5000;
            } else if (cbOreo.isChecked()) {
                total = 20000 + 3000;
            } else if (cbWhippedCream.isChecked()) {
                total = 20000 + 5000;
            } else {
                total = 20000;
            }
        } else if (rbWhiteChoco.isChecked()) {
            if (cbBubble.isChecked() && cbOreo.isChecked() && cbWhippedCream.isChecked()) {
                total = 20000 + 13000;
            } else if (cbBubble.isChecked() && cbOreo.isChecked()) {
                total = 20000 + 8000;
            } else if (cbOreo.isChecked() && cbWhippedCream.isChecked()) {
                total = 20000 + 8000;
            } else if (cbBubble.isChecked() && cbWhippedCream.isChecked()) {
                total = 20000 + 10000;
            } else if (cbBubble.isChecked()) {
                total = 20000 + 5000;
            } else if (cbOreo.isChecked()) {
                total = 20000 + 3000;
            } else if (cbWhippedCream.isChecked()) {
                total = 20000 + 5000;
            } else {
                total = 20000;
            }
        }
    }
}
