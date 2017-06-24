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
public class AcZBar extends Andmt {

    private float _z1=0;
    private float _z2=0;
    private float _z3=0;

    public AcZBar(Context context,float z1,float z2,float z3, boolean isAnnote) {
        super(context, isAnnote);

        seg=8;

        this._z1=z1;
        this._z2=z2;
        this._z3=z3;
        
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
        anpoints.add(0, 0);
        anpoints.add(0, _z1 + get_d());
        anpoints.add(get_d() + _z2, _z1 + get_d());
        anpoints.add(get_d() + _z2, _z1 + get_d() + _z3);
        anpoints.add(get_d() + _z2 + get_d(), _z1 + get_d() + _z3);
        anpoints.add(get_d() + _z2 + get_d(), _z1);
        anpoints.add(get_d(), _z1);
        anpoints.add(get_d(), 0);

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
            an.addtext("z1-len", String.valueOf(_z1));
            an.addtext("z2-len", String.valueOf(_z2));
            an.addtext("z3-len", String.valueOf(_z3));

            an.addtext("total", String.valueOf(_z1 + _z2+_z3));
        }

    }

    @Override
    public void draw() {
        super.draw();
        if(_z1>0) {
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

        canvas.drawPath(pen, paint);
        if (isAnnote) {
            an.autoDrawAnnotation(canvas, 3, 40, 40);
        }


    }
}
