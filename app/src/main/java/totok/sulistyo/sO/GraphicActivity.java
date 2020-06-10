package totok.sulistyo.s0;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GraphicActivity extends AppCompatActivity {
    private Context mContext;
    private Resources mResources;
    private RelativeLayout mRelativeLayout;
    private Button mButton;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String xAs = getIntent().getStringExtra("xA");
        double xA = Double.parseDouble(getIntent().getStringExtra("xA"));
        String yAs = getIntent().getStringExtra("yA");
        double yA = Double.parseDouble(yAs);
        String xSOs = getIntent().getStringExtra("xSO");
        double xSO = Double.parseDouble(xSOs);
        String ySOs = getIntent().getStringExtra("ySO");
        double ySO = Double.parseDouble(ySOs);
        String oPsi = getIntent().getStringExtra("oPsi");
        String hor_dist = getIntent().getStringExtra("hor_dist");

        float x = (float)(xSO - xA);
        float y = (float) (ySO - yA);

        // Get the application context
        mContext = getApplicationContext();

        // Get the Resources
        mResources = getResources();

        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout) findViewById(R.id.r1);

        mImageView = (ImageView) findViewById(R.id.iv);

        // Set a click listener for Button widget

                // Initialize a new Bitmap object
                Bitmap bitmap = Bitmap.createBitmap(
                        1000, // Width
                        1000, // Height
                        Bitmap.Config.ARGB_8888 // Config
                );

                // Initialize a new Canvas instance
                Canvas canvas = new Canvas(bitmap);

                // Draw a solid color on the canvas as background
                canvas.drawColor(Color.LTGRAY);

                // Initialize a new Paint instance to draw the line
                Paint paint = new Paint();
                // Line color
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.STROKE);
                // Line width in pixels
                paint.setStrokeWidth(8);
                paint.setTextSize(30);
                paint.setAntiAlias(true);

        Paint paint2 = new Paint();
        Paint paint3 = new Paint();
        Paint paint4 = new Paint();
        // Line color
        paint2.setColor(Color.BLUE);
        paint3.setColor(Color.GREEN);
        paint3.setStrokeWidth(5);
        paint4.setColor(Color.BLACK);
        paint4.setStrokeWidth(8);
        paint4.setTextSize(35);
      //  paint2.setStyle(Paint.Style.STROKE);
        // Line width in pixels
       // paint2.setStrokeWidth(20);
        paint2.setTextSize(30);
        paint2.setAntiAlias(true);

                // Set a pixels value to offset the line from canvas edge
              //  int offset = 50;

                /*
                    public void drawLine (float startX, float startY, float stopX, float stopY, Paint paint)
                        Draw a line segment with the specified start and stop x,y coordinates, using
                        the specified paint.

                        Note that since a line is always "framed", the Style is ignored in the paint.

                        Degenerate lines (length is 0) will not be drawn.

                    Parameters
                        startX : The x-coordinate of the start point of the line
                        startY : The y-coordinate of the start point of the line
                        paint : The paint used to draw the line

                */

                // Draw a line on canvas at the center position
        /**
                canvas.drawLine(
                        (canvas.getWidth()/2)+10,
                        (canvas.getHeight()/2)+10,
                        500,
                        900,
                        paint

                );
        canvas.drawLine(
                (canvas.getWidth()/2)+10,
                (canvas.getHeight()/2)+10,
                900,
                500,
                paint

        );
        */
        //canvas.drawPoint(canvas.getWidth()/2, canvas.getHeight()/2, paint );
        canvas.drawLine(20, 150, 20, 40, paint4);
        canvas.drawLine(20,40, 30, 60, paint4);
        canvas.drawText("N", 15,35, paint4);
        canvas.drawLine((canvas.getWidth()/2), (canvas.getHeight()/2), (canvas.getWidth()/2)+(x*5), (canvas.getHeight()/2)-(y*5), paint3);
        canvas.drawText("X", canvas.getWidth()/2, canvas.getHeight()/2, paint);
        canvas.drawText("Instrument", (canvas.getWidth()/2)+30, canvas.getHeight()/2, paint2);
        canvas.drawText("("+xAs+", "+yAs+")", canvas.getWidth()/2, (canvas.getHeight()/2)-50, paint2);


        canvas.drawText("X", (canvas.getWidth()/2)+(x*5), (canvas.getHeight()/2)-(y*5), paint);
        canvas.drawText("Target-SO", (canvas.getWidth()/2)+(x*5)+30 , (canvas.getHeight()/2)-(y*5), paint2);
        canvas.drawText("("+xSOs+", "+ySOs+")", (canvas.getWidth()/2)+(x*5), (canvas.getHeight()/2)-(y*5)-50, paint2);
        canvas.drawText(hor_dist+ "M", (canvas.getWidth()/2)+((x/2)*5), (canvas.getHeight()/2)-((y/2)*5), paint2);




         if(oPsi.equals("2")) {
             final String xBSs = getIntent().getStringExtra("xBS");
             final float xBS = Float.parseFloat(xBSs);
             final String yBSs = getIntent().getStringExtra("yBS");
             final float yBS = Float.parseFloat(yBSs);
             final float xb = xBS - (float) xA;
             final float yb = yBS - (float) yA;
             canvas.drawText("X", (canvas.getWidth() / 2) + (xb * 5), (canvas.getHeight() / 2) - (yb * 5), paint);
             canvas.drawText("BS", (canvas.getWidth() / 2) + (xb * 5) + 30, (canvas.getHeight() / 2) - (yb * 5), paint2);
             canvas.drawText("(" + xBSs + ", " + yBSs + ")", (canvas.getWidth() / 2) + (xb * 5), (canvas.getHeight() / 2) - (yb * 5) - 50, paint2);
         }

        //canvas.drawArc(0, 200, 1000, 1000, 100, 180, false, paint);
                // Display the newly created bitmap on app interface
                mImageView.setImageBitmap(bitmap);


  /**      FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
   */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
