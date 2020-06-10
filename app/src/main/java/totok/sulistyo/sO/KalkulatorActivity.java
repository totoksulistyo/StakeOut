package totok.sulistyo.s0;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class KalkulatorActivity extends AppCompatActivity {
    String xBSS="";
    String yBSS="";
    String oPsi="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RadioGroup rg1 = (RadioGroup)findViewById(R.id.rg1);
        final RadioButton rb1 = (RadioButton)findViewById(R.id.rdb1);
        final RadioButton rb2 = (RadioButton)findViewById(R.id.rb2);
        final LinearLayout lbs1 = (LinearLayout)findViewById(R.id.bs1);
        final LinearLayout lbs2 = (LinearLayout) findViewById(R.id.bs2);
        final TextView tvAZBS = (TextView) findViewById(R.id.txtAZBS);
        final TextView tvAZSO = (TextView) findViewById(R.id.txtAZSO);
        final TextView tvBS2SO= (TextView) findViewById(R.id.txtBS2SO);
        final TextView tvHD = (TextView) findViewById(R.id.txtHD);
        final EditText etXA = (EditText) findViewById(R.id.etXA);
        final EditText etYA = (EditText) findViewById(R.id.etYA);
        final EditText etXSO = (EditText) findViewById(R.id.etSOX);
        final EditText etYSO = (EditText) findViewById(R.id.etSOY);
        final EditText etXBS = (EditText) findViewById(R.id.etBSX);
        final EditText etYBS = (EditText) findViewById(R.id.etBSY);
        final EditText etDBS = (EditText) findViewById(R.id.etBSD);
        final EditText etMBS = (EditText) findViewById(R.id.etBSM);
        final EditText etSBS = (EditText) findViewById(R.id.etBSS);
        final ImageView ivi = (ImageView)findViewById(R.id.ivbs);
        final ImageView iv = (ImageView)findViewById(R.id.ivfs);


        Button cal = (Button)findViewById(R.id.btCal);
        Button clr = (Button)findViewById(R.id.btCLR);
        final Button map = (Button)findViewById(R.id.btMap);
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            rb1.setChecked(true);
            rb2.setChecked(true);
                lbs1.setVisibility(View.VISIBLE);
                lbs2.setVisibility(View.VISIBLE);
                etDBS.setText("");
                etMBS.setText("");
                etSBS.setText("");
                etXBS.setText("");
                etYBS.setText("");
                rb1.setChecked(false);

            lbs1.setVisibility(View.GONE);

            tvAZBS.setText("");
            tvAZSO.setText("");
            tvBS2SO.setText("");
            tvHD.setText("");
            etXA.setText("");
            etYA.setText("");

            etXSO.setText("");
            etYSO.setText("");
            iv.setRotation((float)0);
            ivi.setRotation((float)0);



            }
        });

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    final String xAS = etXA.getText().toString();
                    final double xA = Double.parseDouble(xAS);
                    final String yAS = etYA.getText().toString();
                    final double yA = Double.parseDouble(yAS);
                    final String xSOS = etXSO.getText().toString();
                    final double xSO = Double.parseDouble(xSOS);
                    final String ySOS = etYSO.getText().toString();
                    final double ySO = Double.parseDouble(ySOS);


                    double aBS = 0;
                    double az = 0;
                    double abs2az=0;

                    if (rb1.isChecked()) {
                        oPsi ="1";
                        String dBSS = etDBS.getText().toString();
                        double dBS = Double.parseDouble(dBSS);
                        String mBSS = etMBS.getText().toString();
                        double mBS = Double.parseDouble(mBSS);
                        String sBSS = etSBS.getText().toString();
                        double sBS = Double.parseDouble(sBSS);
                        aBS = (dBS) + (mBS / 60.0) + (sBS / 3600.0);
                    } else if (rb2.isChecked()) {
                        oPsi ="2";
                        xBSS = etXBS.getText().toString();
                        double xBS = Double.parseDouble(xBSS);
                        yBSS = etYBS.getText().toString();
                        double yBS = Double.parseDouble(yBSS);
                        double xb = xBS - yA;
                        double yb = yBS - yA;
                        double ab = xb / yb;
                        double azimuthbs = Math.toDegrees(Math.atan(ab));

                        if (xb > 0 && yb > 0) {
                            aBS = azimuthbs;
                        } else if (xb > 0 && yb < 0) {
                            aBS = 180 + azimuthbs;
                        } else if (xb < 0 && yb < 0) {
                            aBS = 180 + azimuthbs;
                        } else if (xb < 0 && yb > 0) {
                            aBS = 360 + azimuthbs;
                        } else if (xb == 0 && yb > 0) {
                            aBS = 0;
                        } else if (xb == 0 && yb < 0) {
                            aBS = 180;
                        } else if (xb > 0 && yb == 0) {
                            aBS = 90;
                        } else if (xb < 0 && yb == 0) {
                            aBS = 270;
                        }


                    }
                    double x = xSO - xA;
                    double y = ySO - yA;

                    double a = x / y;
                    double azimuth = Math.toDegrees(Math.atan(a));
                    double hd = Math.pow(Math.pow((x), 2)
                            + Math.pow((y), 2), 0.5);
                    final String hor_dist=String.format("%.3f",hd);


                    if (x >0 && y >0) {
                        az = azimuth;
                    } else if (x >0 && y <0) {
                        az = 180 + azimuth;
                    } else if (x <0 && y <0) {
                        az = 180 + azimuth;
                    } else if (x <0 && y >0) {
                        az = 360 + azimuth;
                    } else if (x == 0 && y >0) {
                        az = 0;
                    } else if (x == 0 && y <0) {
                        az = 180;
                    } else if (x >0 && y == 0) {
                        az = 90;
                    } else if (x <0 && y == 0) {
                        az = 270;
                    }



                    tvAZBS.setTextColor(Color.BLUE);
                    int deg = (int) aBS;
                    double mens=(aBS-deg)*60;
                    int min = (int)mens;
                    double secs=(mens-min)*60;
                    int sec=(int)secs;

                    tvAZBS.setText(String.valueOf(deg)+"º "+String.valueOf(min)+"' "+String.valueOf(sec)+'"');

                    tvAZSO.setTextColor(Color.BLUE);
                    int deg2 = (int) az;
                    double mens2=(az-deg2)*60;
                    int min2 = (int)mens2;
                    double secs2=(mens2-min2)*60;
                    int sec2=(int)secs2;

                    tvAZSO.setText(String.valueOf(deg2)+"º "+String.valueOf(min2)+"' "+String.valueOf(sec2)+'"');
                    tvHD.setTextColor(Color.BLUE);
                    tvHD.setText(hor_dist+" M");



                    if (az>=aBS){
                        abs2az = az-aBS;
                        tvBS2SO.setTextColor(Color.BLUE);
                        int deg3 = (int) abs2az;
                        double mens3=(abs2az-deg3)*60;
                        int min3 = (int)mens3;
                        double secs3=(mens3-min3)*60;
                        int sec3=(int)secs3;

                        tvBS2SO.setText(String.valueOf(deg3)+"º "+String.valueOf(min3)+"' "+String.valueOf(sec3)+'"');
                    }else {
                        abs2az = (360-aBS)+az;
                        tvBS2SO.setTextColor(Color.BLUE);
                        int deg3 = (int) abs2az;
                        double mens3=(abs2az-deg3)*60;
                        int min3 = (int)mens3;
                        double secs3=(mens3-min3)*60;
                        int sec3=(int)secs3;

                        tvBS2SO.setText(String.valueOf(deg3)+"º "+String.valueOf(min3)+"' "+String.valueOf(sec3)+'"');
                    }
                    Drawable dr = getResources().getDrawable(R.drawable.ic_fs);
                    Drawable dr2 = getResources().getDrawable(R.drawable.ic_bs);
                    //RotateDrawable rdr = (RotateDrawable)  getResources().getDrawable(R.drawable.ic_fs);
                    //rdr.setToDegrees(20);
                    // iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_fsbs1));
                    ivi.setRotation((float)aBS);
                    ivi.setBackground(dr2);
                    iv.setRotation((float)az);
                    iv.setBackground(dr);


                    map.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                Intent i = new Intent(KalkulatorActivity.this, GraphicActivity.class);
                                i.putExtra("xA",xAS);
                                i.putExtra("yA",yAS);
                                i.putExtra("xSO",xSOS);
                                i.putExtra("ySO",ySOS);
                                i.putExtra("xBS", xBSS);
                                i.putExtra("yBS", yBSS);
                                i.putExtra("oPsi", oPsi);
                                i.putExtra("hor_dist", hor_dist);



                                startActivity(i);
                            }catch (Exception e){
                                tvAZBS.setText("Please fill up All Textfield !!");
                            }
                        }
                    });
                }catch (Exception e){
                    String warning = "Please fill up All Textfield !!";
                    tvAZBS.setTextColor(Color.RED);
                    tvAZBS.setText(warning);
                }

            }
        });


        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i==R.id.rdb1){
                    lbs1.setVisibility(View.VISIBLE);
                    lbs2.setVisibility(View.GONE);
                } else if(i==R.id.rb2){
                    lbs2.setVisibility(View.VISIBLE);
                    lbs1.setVisibility(View.GONE);
                }
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent i = new Intent(KalkulatorActivity.this,HomeActivity.class);
             startActivity(i);
             finish();


            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
