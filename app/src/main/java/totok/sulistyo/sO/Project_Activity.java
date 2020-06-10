package totok.sulistyo.s0;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;







public class Project_Activity extends AppCompatActivity {
    String no;
    String pro;
    String sur;
    String[] daftar;
    String[] surveyor;
    String[] angka;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static Project_Activity ma;
    final Context context = this;
    public static String DIRECTORY_DOCUMENTS = "Documents";
    private static final int READ_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        no = getIntent().getStringExtra("no");
        pro = getIntent().getStringExtra("nama_pro");
        sur = getIntent().getStringExtra("nama_surveyor");
        TextView tv=(TextView)findViewById(R.id.label);
        tv.setText("SO Data ID: "+no+" Project: "+pro);
        Button btex=(Button)findViewById(R.id.bt2excel);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Project_Activity.this,StakeOut.class);
                i.putExtra("id",no);
                i.putExtra("pro",pro);
                startActivity(i);

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();
        btex.setOnClickListener(new View.OnClickListener() {
            SQLiteDatabase sqldb = dbcenter.getReadableDatabase(); //My Database class
            Cursor c = null;

            @Override
            public void onClick(View view) {

                try {

                    c = sqldb.rawQuery("SELECT * FROM data WHERE id_pro='"+no+"'", null);
                    int rowcount = 0;
                    int colcount = 0;

                   // File sdCardDir = Environment.getExternalStorageDirectory();
                   // File sdCardDir = Project_Activity
                    File sdCardDir=new File(android.os.Environment.getExternalStorageDirectory()
                            .getAbsolutePath()+ File.separator+"/Stakeout/");
                    sdCardDir.mkdirs();
                    String filename = pro+".csv";
                    // the name of the file to export with
                   //File saveFile = new File(context.getFilesDir(), filename);
                    File saveFile = new File(sdCardDir, filename);
                    FileWriter fw = new FileWriter(saveFile);


                    BufferedWriter bw = new BufferedWriter(fw);
                    rowcount = c.getCount();
                    colcount = c.getColumnCount();
                    if (rowcount > 0) {
                        c.moveToFirst();

                        for (int i = 0; i < colcount; i++) {
                            if (i != colcount - 1) {

                                bw.write(c.getColumnName(i) + ",");

                            } else {

                                bw.write(c.getColumnName(i));

                            }
                        }
                        bw.newLine();

                        for (int i = 0; i < rowcount; i++) {
                            c.moveToPosition(i);

                            for (int j = 0; j < colcount; j++) {
                                if (j != colcount - 1)
                                    bw.write(c.getString(j) + ",");
                                else
                                    bw.write(c.getString(j));
                            }
                            bw.newLine();
                        }
                        bw.flush();

                        Toast.makeText(getApplicationContext(), "Success, File has been saved in My File>Internal Storage>Stakeout", Toast.LENGTH_LONG).show();
                        //infotext.setText("Exported Successfully.");

                        //performFileSearch();
                        openFile(pro+".csv");



                    }
                } catch (Exception ex) {
                    if (sqldb.isOpen()) {
                        sqldb.close();
                        Toast.makeText(getApplicationContext(),ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                        //infotext.setText(ex.getMessage().toString());
                    }

                } finally {

                }



            }
        });
    }
    public void openFile(final String fileName){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(android.os.Environment.getExternalStorageDirectory()
                .getAbsolutePath()+ File.separator+"/Stakeout/"+fileName));
        intent.setDataAndType(uri, "text/csv");

        startActivity(intent);
    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data WHERE id_pro='"+no+"'", null);
        daftar = new String[cursor.getCount()];
        surveyor = new String[cursor.getCount()];
        angka = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(2).toString();
            surveyor[cc] = cursor.getString(1).toString();
            angka[cc] = cursor.getString(0).toString();

        }
        ListView01 = (ListView)findViewById(R.id.listView);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
                final String survey = surveyor[arg2]; //.getItemAtPosition(arg2).toString();
                final String select = angka[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Open Data", "Delete Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Project_Activity.this);
                builder.setTitle("Options");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        switch(item){
                            case 0 :
                                Intent i = new Intent(Project_Activity.this, OpendataActivity.class);
                                String no = getIntent().getStringExtra("no");
                                String pro = getIntent().getStringExtra("nama_pro");
                                i.putExtra("no", no);
                                i.putExtra("nama_pro", pro);
                                i.putExtra("id",select);
                                i.putExtra("nama_data",selection);
                                i.putExtra("nama_surveyor",survey);
                                startActivity(i);
                                break;



                            case 1 :{
                                final Dialog dialogx = new Dialog(context);
                                dialogx.setContentView(R.layout.confirmasi);
                                dialogx.setTitle("Title...");
                                final TextView title2 =(TextView) dialogx.findViewById(R.id.txtTitle);
                                title2.setText("Are You Sure want to delete Data ?");

                                Button bty=(Button)dialogx.findViewById(R.id.btY);
                                bty.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        SQLiteDatabase db = dbcenter.getWritableDatabase();
                                        db.execSQL("delete from data where id = '"+select+"'");
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
    public void performFileSearch() {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);


        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("text/*.csv");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }


}
