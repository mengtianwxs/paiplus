package andmt.draw;

import andmt.methods.Annotations;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/26.
 */
public class AcLineBar extends Andmt {

    private float _len=0;

    public AcLineBar(Context context, float len,boolean isAnnote) {
        super(context, isAnnote);
        this._len=len;
        seg=4;


        addPointData();
        if (isAnnote) {
            initAnnotate();
        }
    }




   
    private void initAnnotate() {
        anpt =new Paint();

        anpt.setColor(Color.RED);
        anpt.setStrokeWidth(1);
        anpt.setAntiAlias(true);
        anpt.setTextSize(30);

        annotate();
    }

    @Override
    public void addPointData() {

        super.addPointData();
       anpoints.add(0,0);
       anpoints.add(get_d(),0);
       anpoints.add(get_d(),_len);
       anpoints.add(0,_len);


    }

    @Override
    public void startDraw() {
        super.startDraw();

    }

    @Override
    public void annotate() {
        super.annotate();
        if (isAnnote) {
            an=new Annotations(anpt, anpoints);
            an.autoAddNonotation();
            an.addtext("len", String.valueOf(_len));
          
        }

    }

    @Override
    public void draw() {
        super.draw();
        if(_len>0) {
            pen.reset();
            pen.moveTo(anpoints.getObjById(0), anpoints.getObjById(1));

            for (int i = 0; i <((seg-1)*2-1); i+=2) {
                pen.lineTo(anpoints.getObjById(i + 2), anpoints.getObjById(i + 3));
            }
            pen.close();
        }else{
            Toast.makeText(getContext(), "cannot empty values n1 ,n2 !", Toast.LENGTH_LONG).show();
        }
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        draw();
        // canvas.drawText("BeiJing", 200, 200, paint);

        canvas.drawPath(pen, paint);
        if (isAnnote) {
            an.autoDrawAnnotation(canvas, 2, 40, 40);
        }


    }
}
