package totok.sulistyo.s0;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    String[] daftar;
    String[] surveyor;
    String[] angka;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static MainActivity ma;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog2 = new Dialog(context);
                dialog2.setContentView(R.layout.add_project);
                dialog2.setTitle("Title...");
                final TextView title =(TextView) dialog2.findViewById(R.id.txtTitle);
                title.setText("ADD PROJECT");
                final EditText etproject =(EditText)dialog2.findViewById(R.id.etProject);
                final EditText etsurveyor =(EditText)dialog2.findViewById(R.id.etSurveyor);
                Button btsave=(Button)dialog2.findViewById(R.id.btSave);
                btsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SQLiteDatabase db = dbcenter.getWritableDatabase();
                        db.execSQL("insert into project( nama_pro, nama_surveyor) values('" +
                                //  text2.getText().toString()+"','"+
                                etproject.getText().toString() +"','" +
                                etsurveyor.getText().toString() + "')");
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                        MainActivity.ma.RefreshList();
                        dialog2.dismiss();
                    }
                });
                Button btcancel=(Button)dialog2.findViewById(R.id.btCancel);
                btcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog2.dismiss();
                    }
                });

                dialog2.show();

            }
        });
        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM project",null);
        daftar = new String[cursor.getCount()];
        surveyor = new String[cursor.getCount()];
        angka = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
            surveyor[cc] = cursor.getString(2).toString();
            angka[cc] = cursor.getString(0).toString();

        }
        ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String,Object>>();;
        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
                final String survey = surveyor[arg2]; //.getItemAtPosition(arg2).toString();
                final String select = angka[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Open Project", "Update Project", "Delete Project"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Option");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        switch(item){
                            case 0 :
                                Intent i = new Intent(MainActivity.this, Project_Activity.class);
                                i.putExtra("no",select);
                                i.putExtra("nama_pro",selection);
                                i.putExtra("nama_surveyor",survey);
                                startActivity(i);
                                break;


                            case 1 :{
                                final Dialog dialog2 = new Dialog(context);
                                dialog2.setContentView(R.layout.add_project);
                                dialog2.setTitle("Title...");
                                final TextView title =(TextView) dialog2.findViewById(R.id.txtTitle);
                                title.setText("UPDATE PROJECT");
                                final EditText etproject =(EditText)dialog2.findViewById(R.id.etProject);
                                etproject.setText(selection);
                                final EditText etsurveyor =(EditText)dialog2.findViewById(R.id.etSurveyor);
                                etsurveyor.setText(survey);
                                Button btsave=(Button)dialog2.findViewById(R.id.btSave);
                                btsave.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        SQLiteDatabase db = dbcenter.getWritableDatabase();
                                        db.execSQL("update project set nama_pro='"+
                                                etproject.getText().toString() +"', nama_surveyor='" +
                                                etsurveyor.getText().toString()+ "' where no='" +
                                                select+"'");
                                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                        MainActivity.ma.RefreshList();
                                        dialog2.dismiss();
                                    }
                                });
                                Button btcancel=(Button)dialog2.findViewById(R.id.btCancel);
                                btcancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog2.dismiss();
                                    }
                                });

                                dialog2.show();
                                break;
                            }


                            case 2 :{
                                final Dialog dialogx = new Dialog(context);
                                dialogx.setContentView(R.layout.confirmasi);
                                dialogx.setTitle("Title...");
                                final TextView title2 =(TextView) dialogx.findViewById(R.id.txtTitle);
                                title2.setText("Are You Sure want to delete project? (Data inside will be deleted!)");

                                Button bty=(Button)dialogx.findViewById(R.id.btY);
                                bty.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        SQLiteDatabase db = dbcenter.getWritableDatabase();
                                        db.execSQL("delete from project where nama_pro = '"+selection+"'");
                                        db.execSQL("delete from data where id_pro = '"+select+"'");
                                        RefreshList();
                                        dialogx.dismiss();
                                    }
                                });
                                Button btn=(Button)dialogx.findViewById(R.id.btN);
                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialogx.dismiss();
                                    }
                                });
                                dialogx.show();}
                        }
                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    }

}
