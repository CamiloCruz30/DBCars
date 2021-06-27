package com.camilocruz.dbcars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.camilocruz.dbcars.models.Car;
import com.camilocruz.dbcars.models.CarAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView listViewNames;
    private ArrayList<Car> listCars = new ArrayList<>();
    private Button btnReturn;
    private Button btnDelete;
    HelperDB db = new HelperDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewNames = findViewById(R.id.listViewNames);
        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);

        CarAdapter adapter = new CarAdapter(this,db.selectCar(db.getWritableDatabase()));
        listViewNames.setAdapter(adapter);
        listViewNames.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this,"Ha pulsado el elemento numero "+position,Toast.LENGTH_LONG).show();
        Toast.makeText(this,"Ha pulsado el elemento numero "+ listCars.get(position).getName(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnReturn){
            Intent intentReturn = new Intent(this,carRegister.class);
            startActivity(intentReturn);
        }else if (v.getId() == R.id.btnDelete){
            db.deleteAll();
            CustomToastView.makeSuccessToast(this, "Registros eliminados", R.layout.custom_toast).show();

        }
    }
}