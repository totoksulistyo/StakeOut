package totok.sulistyo.s0;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.widget.TextView;

public class OpendataActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    TextView txtA, txtBS, txtSO, txtHD, txtAz, txtHOP, txtCF, txtMBF, txtdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opendata);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DataHelper(this);
        txtA = (TextView) findViewById(R.id.txtInstru);
        txtBS = (TextView) findViewById(R.id.txtBS);
        txtSO = (TextView) findViewById(R.id.txtSO);
        txtHD = (TextView) findViewById(R.id.txtHD);
        txtAz = (TextView) findViewById(R.id.txtAzimuth);
        txtHOP = (TextView) findViewById(R.id.txtOD);
        txtCF = (TextView) findViewById(R.id.txtcf);
        txtMBF = (TextView) findViewById(R.id.txtmbf);
        txtdata = (TextView) findViewById(R.id.txtData);
        final String id = getIntent().getStringExtra("id");
        final String nama_data= getIntent().getStringExtra("nama_data");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data WHERE id = '" +
                getIntent().getStringExtra("id") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            //String.format("%.3f",dist);
            txtdata.setText("Data ID : "+(cursor.getString(0).toString())+" Code : "+cursor.getString(2).toString());
            txtA.setText("HI : "+(cursor.getString(10).toString())+" Meter, E : "+cursor.getString(4).toString()+", N : "+cursor.getString(5).toString()+", Z : "+cursor.getString(6).toString());
            txtBS.setText("N "+(cursor.getString(3).toString())+" º E");
            txtSO.setText(" E : "+cursor.getString(7).toString()+", N : "+cursor.getString(8).toString()+", Z : "+cursor.getString(9).toString());
            float hds = cursor.getFloat(18);
            txtHD.setText(String.format("%.3f",hds)+" Meter");
            txtAz.setText("N "+cursor.getString(17).toString()+"º E");
            txtHOP.setText("VA :"+cursor.getString(11).toString()+"º "+cursor.getString(12).toString()+" '"+cursor.getString(13).toString()+" '', UCH :"+cursor.getString(14).toString()+"mm, MCH :"+cursor.getString(15).toString()+"mm, LCH :"+cursor.getString(16).toString()+"mm");
            txtCF.setText("Cut/Fill :"+cursor.getString(19).toString()+" Meter");
            txtMBF.setText("Move Backward/Forward :"+cursor.getString(20).toString()+" Meter");

            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(OpendataActivity.this,Project_Activity.class);
                    String no = getIntent().getStringExtra("no");
                    String nama_pro = getIntent().getStringExtra("nama_pro");
                    i.putExtra("no", no);
                    i.putExtra("nama_pro", nama_pro);
                    startActivity(i);
                    finish();
                }
            });
           // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        }

    }
}
