package totok.sulistyo.s0;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class StakeOut extends AppCompatActivity {
    Button btocc, btso, btsr, btsd, btbs, btmsr;
    final Context context = this;
    DataHelper dbcenter;
    private AnimationDrawable anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stake_out);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btocc = findViewById(R.id.btOcc);
        btso = findViewById(R.id.btSO);
        btsr = findViewById(R.id.btSR);
        btsd = findViewById(R.id.btSD);
        btbs = findViewById(R.id.btbs);
        btmsr = findViewById(R.id.btmsr);
        final ImageView  iv = findViewById(R.id.iV5);
        final ImageView  ivi = findViewById(R.id.iV6);
        final TextView tvclk = (TextView)findViewById(R.id.tvclick);
        tvclk.setVisibility(View.INVISIBLE);
        iv.setImageDrawable(null);
        ivi.setImageDrawable(null);
       // iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_fsbs1));









        dbcenter = new DataHelper(this);
        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        final SharedPreferences.Editor editor = pref.edit();
        final String id_pro=getIntent().getStringExtra("id");
        final String pro=getIntent().getStringExtra("pro");
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i = new Intent(StakeOut.this, Project_Activity.class);
            i.putExtra("no",id_pro);
            i.putExtra("nama_pro",pro);
            startActivity(i);
            finish();
            }
        });

        btocc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.occupy);
                dialog.setTitle("Title...");
                final EditText etRemarks =(EditText)dialog.findViewById(R.id.etRemarks);
                etRemarks.setText(String.valueOf(pref.getString("ket", "")));
                final EditText etHI =(EditText)dialog.findViewById(R.id.etHI);
                etHI.setText(String.valueOf(pref.getFloat("hi", (float) 0.000)));
                final EditText etXA =(EditText)dialog.findViewById(R.id.etXA);
                etXA.setText(String.valueOf(pref.getFloat("xA", (float) 0.000)));
                final EditText etYA =(EditText)dialog.findViewById(R.id.etYA);
                etYA.setText(String.valueOf(pref.getFloat("yA", (float) 0.000)));
                final EditText etZA =(EditText)dialog.findViewById(R.id.etZA);
                etZA.setText(String.valueOf(pref.getFloat("zA", (float) 0.000)));
                Button btclr=(Button)dialog.findViewById(R.id.btclr);
                btclr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        etRemarks.setText("");
                        etHI.setText("");
                        etXA.setText("");
                        etYA.setText("");
                        etZA.setText("");
                    }
                });
                Button btsave=(Button)dialog.findViewById(R.id.btsave);
                btsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ket=etRemarks.getText().toString();
                        float hi=Float.parseFloat(etHI.getText().toString());
                        float xA=Float.parseFloat(etXA.getText().toString());
                        float yA=Float.parseFloat(etYA.getText().toString());
                        float zA=Float.parseFloat(etZA.getText().toString());

                        editor.putString("ket", ket);
                        editor.putFloat("hi", hi);
                        editor.putFloat("xA", xA);
                        editor.putFloat("yA", yA);
                        editor.putFloat("zA", zA);
                        editor.commit();
                        dialog.dismiss();
                    }
                });
                Button btcancel=(Button)dialog.findViewById(R.id.btcancel);
                btcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        btbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.bs);
                dialog.setTitle("Title...");
                Button btcancel=(Button)dialog.findViewById(R.id.btcancel);
                Button btA=(Button)dialog.findViewById(R.id.btA);
                Button btC=(Button)dialog.findViewById(R.id.btC);
                btA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog2 = new Dialog(context);
                        dialog2.setContentView(R.layout.bsa);
                        dialog2.setTitle("Title...");
                        final EditText etD=(EditText)dialog2.findViewById(R.id.etX);
                        etD.setText(String.valueOf(pref.getInt("dBS",0)));
                        final EditText etM=(EditText)dialog2.findViewById(R.id.etY);
                        etM.setText(String.valueOf(pref.getInt("mBS", 0)));
                        final EditText etS=(EditText)dialog2.findViewById(R.id.etZ);
                        etS.setText(String.valueOf(pref.getInt("sBS", 0)));
                        Button btclr=(Button)dialog2.findViewById(R.id.btclr);
                        btclr.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                etD.setText("");
                                etM.setText("");
                                etS.setText("");
                            }
                        });
                        Button btsave=(Button)dialog2.findViewById(R.id.btsave);
                        btsave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                int dBS=Integer.parseInt(etD.getText().toString());
                                int mBS=Integer.parseInt(etM.getText().toString());
                                int sBS=Integer.parseInt(etS.getText().toString());

                                editor.putInt("dBS", dBS);
                                editor.putInt("mBS", mBS);
                                editor.putInt("sBS", sBS);
                                editor.remove("xBS");
                                editor.remove("yBS");
                                editor.remove("zBS");
                                editor.commit();

                                final Dialog dialog3 = new Dialog(context);
                                dialog3.setContentView(R.layout.kosong);
                                dialog3.setTitle("Title...");
                                RelativeLayout cl =(RelativeLayout)dialog3.findViewById(R.id.cL1);
                                cl.setVisibility(View.GONE);
                                TextView textInfo=(TextView)dialog3.findViewById(R.id.txtInfo);
                                Button btok=(Button)dialog3.findViewById(R.id.btOK);
                                textInfo.setText("\bRotate & Turn Telescope align to Back Sight Peg/Pole/Stake, then fasten the horisontal lock and press button 0-SET in LCD Panel of Your Theodolite, and make sure the HR has value 0º0'0'', then release horisontal lock. \n  " +
                                        "\bPutar & Luruskan Teleskop dengan Patok Back Sight, kemudian kencangkan penguci horisontal dan tekan tombol 0-SET pada panel LCD Theodolite anda, dan pastikan nilai HR 0º0'0'', kemudian lepas kunci horisontal.");
                                btok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog3.dismiss();
                                    }
                                });
                                ImageView img = (ImageView)dialog3.findViewById(R.id.simple_anim);
                                img.setImageDrawable(null);
                                img.setBackgroundResource(R.drawable.anim);
                                AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
                                frameAnimation.start();
                                LinearLayout ll = (LinearLayout)dialog3.findViewById(R.id.lL1);
                                ll.setVisibility(View.VISIBLE);
                                TextView lcd= (TextView)dialog3.findViewById(R.id.tvHR);
                                lcd.setText("HR 0º0'0''");
                                dialog3.show();

                                dialog2.dismiss();


                            }
                        });
                        Button btcancel=(Button)dialog2.findViewById(R.id.btcancel);
                        btcancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog2.dismiss();
                            }
                        });
                        dialog2.show();
                        dialog.dismiss();

                    }
                });
                btC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog2 = new Dialog(context);
                        dialog2.setContentView(R.layout.bsc);
                        dialog2.setTitle("Title...");
                        final EditText etX=(EditText)dialog2.findViewById(R.id.etX);
                        etX.setText(String.valueOf(pref.getFloat("xBS",0)));
                        final EditText etY=(EditText)dialog2.findViewById(R.id.etY);
                        etY.setText(String.valueOf(pref.getFloat("yBS", 0)));
                        final EditText etZ=(EditText)dialog2.findViewById(R.id.etZ);
                        etZ.setText(String.valueOf(pref.getFloat("zBS", 0)));
                        Button btclr=(Button)dialog2.findViewById(R.id.btclr);
                        btclr.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                etX.setText("");
                                etY.setText("");
                                etZ.setText("");
                            }
                        });

                        Button btsave=(Button)dialog2.findViewById(R.id.btsave);
                        btsave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                float xBS=Float.parseFloat(etX.getText().toString());
                                float yBS=Float.parseFloat(etY.getText().toString());
                                float zBS=Float.parseFloat(etZ.getText().toString());

                                editor.putFloat("xBS", xBS);
                                editor.putFloat("yBS", yBS);
                                editor.putFloat("zBS", zBS);
                                editor.remove("dBS");
                                editor.remove("mBS");
                                editor.remove("sBS");
                                editor.commit();

                                final Dialog dialog3 = new Dialog(context);
                                dialog3.setContentView(R.layout.kosong);
                                dialog3.setTitle("Title...");
                                RelativeLayout cl =(RelativeLayout)dialog3.findViewById(R.id.cL1);
                                cl.setVisibility(View.GONE);
                                TextView textInfo=(TextView)dialog3.findViewById(R.id.txtInfo);
                                Button btok=(Button)dialog3.findViewById(R.id.btOK);
                                textInfo.setText("\bRotate & Turn Telescope align to Back Sight Peg/Pole/Stake, then fasten the horisontal lock and press button 0-SET in LCD Panel of Your Theodolite, and make sure the HR has value 0º0'0'', then release horisontal lock \n  " +
                                        "\bPutar & Luruskan Teleskop dengan Patok Back Sight, kemudian kencangkan penguci horisontal dan tekan tombol 0-SET pada panel LCD Theodolite anda, dan pastkan nilai HR 0º0'0'', kemudia lepas kunci horisontal");
                                btok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog3.dismiss();
                                    }
                                });
                                ImageView img = (ImageView)dialog3.findViewById(R.id.simple_anim);
                                img.setImageDrawable(null);
                                img.setBackgroundResource(R.drawable.anim);
                                AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
                                frameAnimation.start();
                                LinearLayout ll = (LinearLayout)dialog3.findViewById(R.id.lL1);
                                ll.setVisibility(View.VISIBLE);
                                TextView lcd= (TextView)dialog3.findViewById(R.id.tvHR);
                                lcd.setText("HR 0º0'0''");
                                dialog3.show();
                                dialog3.show();
                                dialog2.dismiss();


                            }
                        });
                        Button btcancel=(Button)dialog2.findViewById(R.id.btcancel);
                        btcancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog2.dismiss();
                            }
                        });
                        dialog2.show();
                        dialog.dismiss();

                    }
                });
                btcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        btso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.so);
                dialog.setTitle("Title...");
                final EditText etKodeSO=(EditText)dialog.findViewById(R.id.etKODESO);
                etKodeSO.setText(String.valueOf(pref.getString("kodeSO","")));
                final EditText etXSO=(EditText)dialog.findViewById(R.id.etXSO);
                etXSO.setText(String.valueOf(pref.getFloat("xSO",0)));
                final EditText etYSO=(EditText)dialog.findViewById(R.id.etYSO);
                etYSO.setText(String.valueOf(pref.getFloat("ySO",0)));
                final EditText etZSO=(EditText)dialog.findViewById(R.id.etZSO);
                etZSO.setText(String.valueOf(pref.getFloat("zSO",0)));
                Button btclr=(Button)dialog.findViewById(R.id.btclr);
                final TextView trtele=(TextView)findViewById(R.id.txtTR);
                btclr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        etKodeSO.setText("");
                        etXSO.setText("");
                        etYSO.setText("");
                        etZSO.setText("");

                    }
                });
                Button btsave=(Button)dialog.findViewById(R.id.btsave);
                btsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String kodeSO=etKodeSO.getText().toString();
                        final float xSO=Float.parseFloat(etXSO.getText().toString());
                        final float ySO=Float.parseFloat(etYSO.getText().toString());
                        final float zSO=Float.parseFloat(etZSO.getText().toString());

                        editor.putString("kodeSO", kodeSO);
                        editor.putFloat("xSO", xSO);
                        editor.putFloat("ySO", ySO);
                        editor.putFloat("zSO", zSO);
                        editor.commit();



                        // call
                        //Occupy
                        String ket = pref.getString("ket", "");
                        float hi=pref.getFloat("hi", (float) 0.000);
                        float xA=pref.getFloat("xA", (float) 0.000);
                        float yA=pref.getFloat("yA", (float) 0.000);
                        float zA=pref.getFloat("zA", (float) 0.000);
                        //BS Angle
                        int dBS=pref.getInt("dBS",800);
                        int mBS=pref.getInt("mBS", 0);
                        int sBS=pref.getInt("sBS", 0);
                        //BS Coordinate
                        float xBS=pref.getFloat("xBS",0);
                        float yBS=pref.getFloat("yBS", 0);
                        float zBS=pref.getFloat("zBS", 0);
                        //SO Data
                        //String kodeSO=pref.getString("kodeSO","");
                        //float xSO=pref.getFloat("xSO",0);
                        //float ySO=pref.getFloat("ySO",0);
                        //float zSO=pref.getFloat("zSO",0);
                        //SR Data
                        int vAD=pref.getInt("vAD",0);
                        int vAM=pref.getInt("vAM",0);
                        int vAS=pref.getInt("vAS",0);
                        int ba=pref.getInt("ba",0);
                        int bt=pref.getInt("bt",0);
                        int bb=pref.getInt("bb",0);

                        double azbs=0;
                        double az=0;
                        double tel_dir=0;

                        if(dBS==800){
                            // float az;


                            double xb= xA - xBS;
                            double yb= yA - yBS;
                            double ab = xb / yb;
                            double azimuthbs = Math.toDegrees(Math.atan(ab));


                            if (xb >0 && yb >0) {
                                azbs = azimuthbs;
                            } else if (xb >0 && yb <0) {
                                azbs = 180 + azimuthbs;
                            } else if (xb <0 && yb <0) {
                                azbs = 180 + azimuthbs;
                            } else if (xb <0 && yb >0) {
                                azbs = 360 + azimuthbs;
                            } else if (xb == 0 && yb >0) {
                                azbs = 0;
                            } else if (xb == 0 && yb <0) {
                                azbs = 180;
                            } else if (xb >0 && yb == 0) {
                                azbs = 90;
                            } else if (xb <0 && yb == 0) {
                                azbs = 270;
                            }

                            double x = xA - xSO;
                            double y = yA - ySO;
                            double a = x / y;
                            double azimuth = Math.toDegrees(Math.atan(a));
                            double hd = Math.pow(Math.pow((x), 2)
                                    + Math.pow((y), 2), 0.5);


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

                            if(azbs<az){
                                tel_dir = az - azbs;
                            }else{
                                tel_dir=(360-azbs)+ az;
                            }
                            int der_tel=(int)tel_dir;
                            int men_tel=(int)((tel_dir - der_tel)*60);
                            float sec_tel=(float)((tel_dir - der_tel - (men_tel/60))*60);

                            trtele.setText(String.valueOf(der_tel)+"º "+String.valueOf(men_tel)+"' "+String.valueOf((int)Math.round(sec_tel))+'"');

                        }else{
                            // float az;


                            double azimuthbs = dBS+(mBS/60)+(sBS/3600);
                            if(azimuthbs>180){
                                azbs = azimuthbs - 180;
                            }else {
                                azbs = azimuthbs + 180;
                            }
                            double x = xA - xSO;
                            double y = yA - ySO;
                            double a = x / y;
                            double azimuth = Math.toDegrees(Math.atan(a));
                            double hd = Math.pow(Math.pow((x), 2)
                                    + Math.pow((y), 2), 0.5);


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

                            if(azbs<az){
                                tel_dir = az - azbs;
                            }else{
                                tel_dir=(360-azbs)+ az;
                            }
                            int der_tel=(int)tel_dir;
                            int men_tel=(int)((tel_dir - der_tel)*60);
                            float sec_tel=(float)((tel_dir - der_tel - (men_tel/60))*60);
                            TextView trtele=(TextView)findViewById(R.id.txtTR);
                            TextView dist_staff=(TextView)findViewById(R.id.txtRambu);
                            trtele.setText(String.valueOf(der_tel)+"º "+String.valueOf(men_tel)+"' "+String.valueOf((int)Math.round(sec_tel))+'"');
                            String turn_tele=String.valueOf(der_tel)+"º "+String.valueOf(men_tel)+"' "+String.valueOf((int)Math.round(sec_tel))+'"';
                            String hor_dist=String.format("%.3f",hd);
                            dist_staff.setText(hor_dist+" M");




                        }

                        final Dialog dialog2 = new Dialog(context);
                        dialog2.setContentView(R.layout.kosong);
                        dialog2.setTitle("Title...");
                        RelativeLayout cl =(RelativeLayout)dialog2.findViewById(R.id.cL1);
                        cl.setVisibility(View.GONE);
                        TextView tvInfo =(TextView)dialog2.findViewById(R.id.txtInfo);
                        Button btok=(Button)dialog2.findViewById(R.id.btOK);
                        String turn_tele=trtele.getText().toString();
                        tvInfo.setText("\b Kindly Rotate Theodolit's Telescope until HR in LCD display reach : "+turn_tele+"'' , then fasten horisontal lock!\n" +
                                "\b Silahkan Putar Teleskop Theodolite anda sapai HR dalam LCD mencapai: "+turn_tele+"'' , kemudian kunci sekerup horisontal!");
                        btok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog2.dismiss();
                            }
                        });


                            ImageView img = (ImageView)dialog2.findViewById(R.id.simple_anim);
                            img.setImageDrawable(null);
                            img.setBackgroundResource(R.drawable.anim);
                             AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
                             frameAnimation.start();
                        LinearLayout ll = (LinearLayout)dialog2.findViewById(R.id.lL1);
                        ll.setVisibility(View.VISIBLE);
                        TextView lcd= (TextView)dialog2.findViewById(R.id.tvHR);
                        lcd.setText("HR "+turn_tele);




                        dialog2.show();

                        dialog.dismiss();


                    }
                });
                Button btcancel=(Button)dialog.findViewById(R.id.btcancel);
                btcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        btsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.sr);
                dialog.setTitle("Title...");
                Button btcancel=(Button)dialog.findViewById(R.id.btcancel);
                Button btsave=(Button)dialog.findViewById(R.id.btsave);
                Button btclr=(Button)dialog.findViewById(R.id.btclr);
                final EditText etVAD=(EditText)dialog.findViewById(R.id.etVAD);
                etVAD.setText(String.valueOf(pref.getInt("vAD",0)));
                final EditText etVAM=(EditText)dialog.findViewById(R.id.etVAM);
                etVAM.setText(String.valueOf(pref.getInt("vAM",0)));
                final EditText etVAS=(EditText)dialog.findViewById(R.id.etVAS);
                etVAS.setText(String.valueOf(pref.getInt("vAS",0)));
                final EditText etUCH=(EditText)dialog.findViewById(R.id.etuch);
                etUCH.setText(String.valueOf(pref.getInt("ba",0)));
                final EditText etMCH=(EditText)dialog.findViewById(R.id.etmch);
                etMCH.setText(String.valueOf(pref.getInt("bt",0)));
                final EditText etLCH=(EditText)dialog.findViewById(R.id.etlch);
                etLCH.setText(String.valueOf(pref.getInt("bb",0)));
                btclr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        etVAD.setText("");
                        etVAM.setText("");
                        etVAS.setText("");
                        etUCH.setText("");
                        etMCH.setText("");
                        etLCH.setText("");

                    }
                });
                btsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int vAD=Integer.parseInt(etVAD.getText().toString());
                        int vAM=Integer.parseInt(etVAM.getText().toString());
                        int vAS=Integer.parseInt(etVAS.getText().toString());
                        int ba=Integer.parseInt(etUCH.getText().toString());
                        int bt=Integer.parseInt(etMCH.getText().toString());
                        int bb=Integer.parseInt(etLCH.getText().toString());

                        editor.putInt("vAD", vAD);
                        editor.putInt("vAM", vAM);
                        editor.putInt("vAS", vAS);
                        editor.putInt("ba", ba);
                        editor.putInt("bt", bt);
                        editor.putInt("bb", bb);
                        editor.commit();
                        dialog.dismiss();
                    }
                });
                btcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        btmsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Occupy

                tvclk.setVisibility(View.VISIBLE);
                String ket = pref.getString("ket", "");
                float hi=pref.getFloat("hi", (float) 0.000);
                float xA=pref.getFloat("xA", (float) 0.000);
                float yA=pref.getFloat("yA", (float) 0.000);
                float zA=pref.getFloat("zA", (float) 0.000);
                //BS Angle
                int dBS=pref.getInt("dBS",800);
                int mBS=pref.getInt("mBS", 0);
                int sBS=pref.getInt("sBS", 0);
                //BS Coordinate
                float xBS=pref.getFloat("xBS",0);
                float yBS=pref.getFloat("yBS", 0);
                float zBS=pref.getFloat("zBS", 0);
                //SO Data
                String kodeSO=pref.getString("kodeSO","");
                float xSO=pref.getFloat("xSO",0);
                float ySO=pref.getFloat("ySO",0);
                float zSO=pref.getFloat("zSO",0);
                //SR Data
                int vAD=pref.getInt("vAD",0);
                int vAM=pref.getInt("vAM",0);
                int vAS=pref.getInt("vAS",0);
                int ba=pref.getInt("ba",0);
                int bt=pref.getInt("bt",0);
                int bb=pref.getInt("bb",0);

                if(dBS==800){
                   // float az;
                    double azbs=0;
                    double az=0;
                    double tel_dir=0;
                    double xb= xA - xBS;
                    double yb= yA - yBS;
                    double ab = xb / yb;
                    double azimuthbs = Math.toDegrees(Math.atan(ab));


                    if (xb >0 && yb >0) {
                        azbs = azimuthbs;
                    } else if (xb >0 && yb <0) {
                        azbs = 180 + azimuthbs;
                    } else if (xb <0 && yb <0) {
                        azbs = 180 + azimuthbs;
                    } else if (xb <0 && yb >0) {
                        azbs = 360 + azimuthbs;
                    } else if (xb == 0 && yb >0) {
                        azbs = 0;
                    } else if (xb == 0 && yb <0) {
                        azbs = 180;
                    } else if (xb >0 && yb == 0) {
                        azbs = 90;
                    } else if (xb <0 && yb == 0) {
                        azbs = 270;
                    }

                    double x = xA - xSO;
                    double y = yA - ySO;

                    double a = x / y;
                    double azimuth = Math.toDegrees(Math.atan(a));
                    double hd = Math.pow(Math.pow((x), 2)
                            + Math.pow((y), 2), 0.5);


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

                    if(azbs<az){
                        tel_dir = az - azbs;
                    }else{
                        tel_dir=(360-azbs)+ az;
                    }
                    int der_tel=(int)tel_dir;
                    int men_tel=(int)((tel_dir - der_tel)*60);
                    float sec_tel=(float)((tel_dir - der_tel - (men_tel/60))*60);
                    TextView trtele=(TextView)findViewById(R.id.txtTR);
                    TextView trambu=(TextView)findViewById(R.id.txtRbLb);
                    TextView trmove=(TextView)findViewById(R.id.txtRambu);
                    TextView lbUp=(TextView)findViewById(R.id.txtUpLb);
                    TextView txtUp=(TextView)findViewById(R.id.txtUP);
                    TextView txtHD=(TextView)findViewById(R.id.txtHD);
                    trtele.setText(String.valueOf(der_tel)+"º "+String.valueOf(men_tel)+"' "+String.valueOf((int)Math.round(sec_tel))+'"');
                    double zenit = vAD + (vAM/60) + (vAS/3600);
                    double heling = 90 - zenit;
                    double hdoptis = ((ba-bb)*0.1)* Math.cos(Math.toRadians(heling))*Math.cos(Math.toRadians(heling));
                    double dist = hd - hdoptis;
                    double dh=(hdoptis*(Math.tan(Math.toRadians(heling))))+hi-(bt/1000);
                    double z = zSO - zA;
                    double cf=dh-z;
                    if(z>dh){
                        String txtcf="Fill!:";
                        lbUp.setText(txtcf);
                    }else{
                        String txtcf="Cut!:";
                        lbUp.setText(txtcf);
                    }

                    if (hd>hdoptis){

                        String move="Move Backward!";
                        trambu.setText(move);
                    }else if(hd==hdoptis){

                        String move="Stop there!";
                        trambu.setText(move);
                    }else{

                        String move="Move Forward!!";
                        trambu.setText(move);
                    }
                    final String hds=String.format("%.3f",hd);
                    txtHD.setText(hds+" M");
                    final String jd=String.format("%.3f",dist);
                    trmove.setText(jd+" M");
                    String cutfill=String.format("%.3f",cf);
                    txtUp.setText(cutfill+" M");

                    Drawable dr = getResources().getDrawable(R.drawable.ic_fs);
                    Drawable dr2 = getResources().getDrawable(R.drawable.ic_bs);
                    //RotateDrawable rdr = (RotateDrawable)  getResources().getDrawable(R.drawable.ic_fs);
                    //rdr.setToDegrees(20);
                    // iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_fsbs1));
                    ivi.setRotation((float)azbs+180);
                    ivi.setBackground(dr2);
                    iv.setRotation((float)(az+180));
                    iv.setBackground(dr);
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.kosong);
                            dialog.setTitle("Title...");
                            Button ok=(Button)dialog.findViewById(R.id.btOK);
                            RelativeLayout cl =(RelativeLayout)dialog.findViewById(R.id.cL1);
                            cl.setVisibility(View.VISIBLE);
                            TextView tvRhd = (TextView)dialog.findViewById(R.id.tvRHD);
                            TextView tvhd = (TextView)dialog.findViewById(R.id.tvHD);
                            tvRhd.setText(jd+" M");
                            tvhd.setText(hds+" M");
                            ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();

                        }
                    });


                }else{
                    // float az;
                    double azbs=0;
                    double az=0;
                    double tel_dir=0;
                    double azimuthbs = dBS+(mBS/60)+(sBS/3600);
                    if(azimuthbs>180){
                        azbs = azimuthbs - 180;
                    }else {
                        azbs = azimuthbs + 180;
                    }
                    double x = xA - xSO;
                    double y = yA - ySO;

                    double a = x / y;
                    double azimuth = Math.toDegrees(Math.atan(a));
                    double hd = Math.pow(Math.pow((x), 2)
                                    + Math.pow((y), 2), 0.5);

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

                    if(azbs<az){
                        tel_dir = az - azbs;
                    }else{
                        tel_dir=(360-azbs)+ az;
                    }
                    int der_tel=(int)tel_dir;
                    int men_tel=(int)((tel_dir - der_tel)*60);
                    float sec_tel=(float)((tel_dir - der_tel - (men_tel/60))*60);
                    TextView trtele=(TextView)findViewById(R.id.txtTR);
                    TextView trambu=(TextView)findViewById(R.id.txtRbLb);
                    TextView trmove=(TextView)findViewById(R.id.txtRambu);
                    TextView lbUp=(TextView)findViewById(R.id.txtUpLb);
                    TextView txtUp=(TextView)findViewById(R.id.txtUP);
                    TextView txtHD=(TextView)findViewById(R.id.txtHD);
                    trtele.setText(String.valueOf(der_tel)+"º "+String.valueOf(men_tel)+"' "+String.valueOf((int)Math.round(sec_tel))+'"');
                    double zenit = vAD + (vAM/60) + (vAS/3600);
                    double heling = 90 - zenit;
                    double hdoptis = ((ba-bb)*0.1)* Math.cos(Math.toRadians(heling))*Math.cos(Math.toRadians(heling));
                    double dist = hd - hdoptis;
                    double dh=(hdoptis*(Math.tan(Math.toRadians(heling))))+hi-(bt/1000);
                    double z = zSO - zA;
                    double cf=dh-z;
                    if(z>dh){
                        String txtcf="Fill!:";
                        lbUp.setText(txtcf);
                    }else{
                        String txtcf="Cut!:";
                        lbUp.setText(txtcf);
                    }

                    if (hd>hdoptis){

                        String move="Move Backward!";
                        trambu.setText(move);
                    }else if(hd==hdoptis){

                        String move="Stop there!";
                        trambu.setText(move);
                    }else{

                        String move="Move Forward!!";
                        trambu.setText(move);
                    }
                    final String hds=String.format("%.3f",hd);
                    txtHD.setText(hds+" M");
                    final String jd=String.format("%.3f",dist);
                    trmove.setText(jd+" M");
                    String cutfill=String.format("%.3f",cf);
                    txtUp.setText(cutfill+" M");

                    Drawable dr = getResources().getDrawable(R.drawable.ic_fs);
                    Drawable dr2 = getResources().getDrawable(R.drawable.ic_bs);
                    //RotateDrawable rdr = (RotateDrawable)  getResources().getDrawable(R.drawable.ic_fs);
                    //rdr.setToDegrees(20);
                    // iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_fsbs1));

                    ivi.setRotation((float)azbs+180);
                    ivi.setBackground(dr2);
                    iv.setRotation((float)(az+180));
                    iv.setBackground(dr);
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.kosong);
                            dialog.setTitle("Title...");
                            Button ok=(Button)dialog.findViewById(R.id.btOK);
                            RelativeLayout cl =(RelativeLayout)dialog.findViewById(R.id.cL1);
                            cl.setVisibility(View.VISIBLE);
                            TextView tvRhd = (TextView)dialog.findViewById(R.id.tvRHD);
                            TextView tvhd = (TextView)dialog.findViewById(R.id.tvHD);
                            tvRhd.setText(jd+" M");
                            tvhd.setText(hds+" M");
                            ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();

                        }
                    });


                }


            }
        });
        btsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_pro=getIntent().getStringExtra("id");
                String ket = pref.getString("ket", "");
                float hi=pref.getFloat("hi", (float) 0.000);
                float xA=pref.getFloat("xA", (float) 0.000);
                float yA=pref.getFloat("yA", (float) 0.000);
                float zA=pref.getFloat("zA", (float) 0.000);
                //BS Angle
                int dBS=pref.getInt("dBS",800);
                int mBS=pref.getInt("mBS", 0);
                int sBS=pref.getInt("sBS", 0);
                //BS Coordinate
                float xBS=pref.getFloat("xBS",0);
                float yBS=pref.getFloat("yBS", 0);
                float zBS=pref.getFloat("zBS", 0);
                //SO Data
                String kodeSO=pref.getString("kodeSO","");
                float xSO=pref.getFloat("xSO",0);
                float ySO=pref.getFloat("ySO",0);
                float zSO=pref.getFloat("zSO",0);
                //SR Data
                int vAD=pref.getInt("vAD",0);
                int vAM=pref.getInt("vAM",0);
                int vAS=pref.getInt("vAS",0);
                int ba=pref.getInt("ba",0);
                int bt=pref.getInt("bt",0);
                int bb=pref.getInt("bb",0);

                if(dBS==800){
                    // float az;
                    double azbs=0;
                    double az=0;
                    double tel_dir=0;
                    double xb= xA - xBS;
                    double yb= yA - yBS;
                    double ab = xb / yb;
                    double azimuthbs = Math.toDegrees(Math.atan(ab));


                    if (xb >0 && yb >0) {
                        azbs = azimuthbs;
                    } else if (xb >0 && yb <0) {
                        azbs = 180 + azimuthbs;
                    } else if (xb <0 && yb <0) {
                        azbs = 180 + azimuthbs;
                    } else if (xb <0 && yb >0) {
                        azbs = 360 + azimuthbs;
                    } else if (xb == 0 && yb >0) {
                        azbs = 0;
                    } else if (xb == 0 && yb <0) {
                        azbs = 180;
                    } else if (xb >0 && yb == 0) {
                        azbs = 90;
                    } else if (xb <0 && yb == 0) {
                        azbs = 270;
                    }

                    double x = xA - xSO;
                    double y = yA - ySO;

                    double a = x / y;
                    double azimuth = Math.toDegrees(Math.atan(a));
                    double hd = Math.pow(Math.pow((x), 2)
                            + Math.pow((y), 2), 0.5);


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

                    if(azbs<az){
                        tel_dir = az - azbs;
                    }else{
                        tel_dir=(360-azbs)+ az;
                    }
                    int der_tel=(int)tel_dir;
                    int men_tel=(int)((tel_dir - der_tel)*60);
                    float sec_tel=(float)((tel_dir - der_tel - (men_tel/60))*60);
                    TextView trtele=(TextView)findViewById(R.id.txtTR);
                    TextView trambu=(TextView)findViewById(R.id.txtRbLb);
                    TextView trmove=(TextView)findViewById(R.id.txtRambu);
                    TextView lbUp=(TextView)findViewById(R.id.txtUpLb);
                    TextView txtUp=(TextView)findViewById(R.id.txtUP);
                    TextView txtHD=(TextView)findViewById(R.id.txtHD);
                    trtele.setText(String.valueOf(der_tel)+"º "+String.valueOf(men_tel)+"' "+String.valueOf((int)Math.round(sec_tel))+'"');
                    double zenit = vAD + (vAM/60) + (vAS/3600);
                    double heling = 90 - zenit;
                    double hdoptis = ((ba-bb)*0.1)* Math.cos(Math.toRadians(heling))*Math.cos(Math.toRadians(heling));
                    double dist = hd - hdoptis;
                    double dh=(hdoptis*(Math.tan(Math.toRadians(heling))))+hi-(bt/1000);
                    double z = zSO - zA;
                    double cf=dh-z;
                    if(z>dh){
                        String txtcf="Fill!:";
                        lbUp.setText(txtcf);
                    }else{
                        String txtcf="Cut!:";
                        lbUp.setText(txtcf);
                    }

                    if (hd>hdoptis){

                        String move="Move Backward!";
                        trambu.setText(move);
                    }else if(hd==hdoptis){

                        String move="Stop there!";
                        trambu.setText(move);
                    }else{

                        String move="Move Forward!!";
                        trambu.setText(move);
                    }
                    String hds=String.format("%.3f",hd);
                    txtHD.setText(hds+" M");
                    String jd=String.format("%.3f",dist);
                    trmove.setText(jd+" M");
                    String cutfill=String.format("%.3f",cf);
                    txtUp.setText(cutfill+" M");
                    final double azbsk;
                    if((azbs-180)<0){
                        azbsk = azbs - 180 + 360;
                    }else{
                        azbsk = azbs -180;
                    }
                    SQLiteDatabase db = dbcenter.getWritableDatabase();
//                    db.execSQL("insert into data( id_pro, code, azbs, xa, ya, za, xso, yso, zso, hi, vd, vm, vs, ba, bt, bb, az, hd, fb, cf  ) values('"+id_pro+"', '"+
  //                          ket+"','"+azbs+"','"+xA+"','"+yA+"','"+zA+"','"+xSO+"','"+ySO+"','"+zSO+"','"+hi+"','"+vAD+"','"+vAM+"','"+vAS+"','"+
    //                        ba+"','"+bt+"','"+bb+"', '"+az+"','"+hd+"','"+hd+"','"+cf+"' )");
                    db.execSQL("insert into data(id_pro, code, azbs, xa, ya, za, xso, yso, zso, hi, vd, vm, vs, ba, bt, bb, az, hd, fb, cf)values" +
                            "('"+id_pro+"','"+ket+"','"+azbsk+"','"+xA+"', '"+yA+"', '"+zA+"', '"+xSO+"', '"+ySO+"', '"+zSO+"', '"+hi+"', '"+vAD+"', '"+vAM+"', '"+vAS+"', '"+ba+"', '"+bt+"', '"+bb+"', '"+tel_dir+"', '"+hd+"', '"+jd+"', '"+cutfill+"')");
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    Project_Activity.ma.RefreshList();
                    finish();


                }else{
                    // float az;
                    double azbs=0;
                    double az=0;
                    double tel_dir=0;
                    double azimuthbs = dBS+(mBS/60)+(sBS/3600);
                    if(azimuthbs>180){
                        azbs = azimuthbs - 180;
                    }else {
                        azbs = azimuthbs + 180;
                    }
                    double x = xA - xSO;
                    double y = yA - ySO;

                    double a = x / y;
                    double azimuth = Math.toDegrees(Math.atan(a));
                    double hd = Math.pow(Math.pow((x), 2)
                            + Math.pow((y), 2), 0.5);

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

                    if(azbs<az){
                        tel_dir = az - azbs;
                    }else{
                        tel_dir=(360-azbs)+ az;
                    }
                    int der_tel=(int)tel_dir;
                    int men_tel=(int)((tel_dir - der_tel)*60);
                    float sec_tel=(float)((tel_dir - der_tel - (men_tel/60))*60);
                    TextView trtele=(TextView)findViewById(R.id.txtTR);
                    TextView trambu=(TextView)findViewById(R.id.txtRbLb);
                    TextView trmove=(TextView)findViewById(R.id.txtRambu);
                    TextView lbUp=(TextView)findViewById(R.id.txtUpLb);
                    TextView txtUp=(TextView)findViewById(R.id.txtUP);
                    TextView txtHD=(TextView)findViewById(R.id.txtHD);
                    trtele.setText(String.valueOf(der_tel)+"º "+String.valueOf(men_tel)+"' "+String.valueOf((int)Math.round(sec_tel))+'"');
                    double zenit = vAD + (vAM/60) + (vAS/3600);
                    double heling = 90 - zenit;
                    double hdoptis = ((ba-bb)*0.1)* Math.cos(Math.toRadians(heling))*Math.cos(Math.toRadians(heling));
                    double dist = hd - hdoptis;
                    double dh=(hdoptis*(Math.tan(Math.toRadians(heling))))+hi-(bt/1000);
                    double z = zSO - zA;
                    double cf=dh-z;
                    if(z>dh){
                        String txtcf="Fill!:";
                        lbUp.setText(txtcf);
                    }else{
                        String txtcf="Cut!:";
                        lbUp.setText(txtcf);
                    }

                    if (hd>hdoptis){

                        String move="Move Backward!";
                        trambu.setText(move);
                    }else if(hd==hdoptis){

                        String move="Stop there!";
                        trambu.setText(move);
                    }else{

                        String move="Move Forward!!";
                        trambu.setText(move);
                    }
                    String hds=String.format("%.3f",hd);
                    txtHD.setText(hds+" M");
                    String jd=String.format("%.3f",dist);
                    trmove.setText(jd+" M");
                    String cutfill=String.format("%.3f",cf);
                    txtUp.setText(cutfill+" M");
                    SQLiteDatabase db = dbcenter.getWritableDatabase();
                   // db.execSQL("insert into data( id_pro, code, azbs, xa, ya, za, xso, yso, zso, hi, vd, vm, vs, ba, bt, bb, az, hd, fb, cf  ) values('"+id_pro+"', '"+
                   //         ket+"','"+azbs+"','"+xA+"','"+yA+"','"+zA+"','"+xSO+"','"+ySO+"','"+zSO+"','"+hi+"','"+vAD+"','"+vAM+"','"+vAS+"','"+
                    //        ba+"','"+bt+"','"+bb+"', '"+az+"','"+hd+"','"+hd+"','"+cf+"' )");
                    db.execSQL("insert into data(id_pro, code, azbs, xa, ya, za, xso, yso, zso, hi, vd, vm, vs, ba, bt, bb, az, hd, fb, cf)values" +
                            "('"+id_pro+"','"+ket+"','"+azimuthbs+"','"+xA+"', '"+yA+"', '"+zA+"', '"+xSO+"', '"+ySO+"', '"+zSO+"', '"+hi+"', '"+vAD+"', '"+vAM+"', '"+vAS+"', '"+ba+"', '"+bt+"', '"+bb+"', '"+tel_dir+"', '"+hd+"', '"+jd+"', '"+cutfill+"')");
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

                    Project_Activity.ma.RefreshList();
                    finish();

                }


            }
        });
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



}
