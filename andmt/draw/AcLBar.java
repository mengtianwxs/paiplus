package andmt.draw;


import andmt.methods.Annotations;
import andmt.methods.Points;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/22.
 */
public class AcLBar extends Andmt {


    private float _n1=0;
    private float _n2=0;
    private int _m=0;


    public AcLBar(Context context,boolean isAnnote ,float height,float width,int offsetm) {
        super(context, isAnnote);
        this._n1=height;
        this._n2=width;
        this._m=offsetm;
        seg=6;


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
            anpoints.add(0 + _m, 0 + _m);
            anpoints.add(0 + _m, _n1 + get_d() + _m);
            anpoints.add(get_d() + _n2 + _m, _n1 + get_d() + _m);
            anpoints.add(get_d() + _n2 + _m, _n1 + _m);
            anpoints.add(get_d() + _m, _n1 + _m);
            anpoints.add(get_d() + _m, 0 + _m);

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
             an.addtext("width", String.valueOf(_n1));
             an.addtext("height", String.valueOf(_n2));
            an.addtext("total", String.valueOf(_n1 + _n2));
        }

    }

    @Override
    public void draw() {
        super.draw();
        if(_n1>0&&_n2>0) {
            pen.reset();
            pen.moveTo(anpoints.getObjById(0), anpoints.getObjById(1));

            for (int i = 0; i <((seg-1)*2-1); i+=2) {
                pen.lineTo(anpoints.getObjById(i + 2), anpoints.getObjById(i + 3));
            }
            pen.close();
        }else{
            Toast.makeText(getContext(),"cannot empty values n1 ,n2 !",Toast.LENGTH_LONG).show();
        }
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       draw();

        canvas.drawPath(pen, paint);
        if (isAnnote) {
               an.autoDrawAnnotation(canvas, 3, 40, 40);
           }


    }



}
