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
public class AcUBar extends Andmt {

    private float _u1=0;
    private float _u2=0;
    private float _u3=0;


    private float u=0;
    private float v=0;



    public AcUBar(Context context,float u1,float u2, float u3,boolean isAnnote ) {
        super(context, isAnnote);
        this._u1=u1;
        this._u2=u2;
        this._u3=u3;
        seg=8;


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
        if(_u1>_u3){
            u=_u1-_u3;
        }
        else if(_u1==_u3){
            u=0;

        }else if(_u1<_u3){
            v=Math.abs(_u1-_u3);
            u=-v;
        }
       anpoints.add(0, 0 + v);
       anpoints.add(0, _u1 + get_d() + v);
       anpoints.add(get_d() + get_d() + _u2, _u1 + get_d() + v);


       anpoints.add(get_d() + get_d() + _u2, u + v);
       anpoints.add(_u2 + get_d(), u + v);
       anpoints.add(get_d() + _u2, _u1 + v);
       anpoints.add(get_d(), _u1 + v);
       anpoints.add(get_d(), 0 + v);


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
            an.addtext("u1-len", String.valueOf(_u1));
            an.addtext("u2-len", String.valueOf(_u2));
            an.addtext("u3-len", String.valueOf(_u3));
            an.addtext("total", String.valueOf(_u1+_u2+_u3));
        }

    }

    @Override
    public void draw() {
        super.draw();
        if(_u1>0) {
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
            an.autoDrawAnnotation(canvas, 3, 40, 40);
        }


    }

}
