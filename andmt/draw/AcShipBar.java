package andmt.draw;

import andmt.methods.Annotations;
import andmt.methods.Utils;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/26.
 */
public class AcShipBar extends Andmt {
    private float _sw=0;
    private float _h1=0;
    private float _w1=0;
    private float _zw=0;
    private float _w2=0;
    private float _h2=0;
    private float _xw=0;
    private float _l1=0;
    private float _l2=0;
    private float w3=0;
    private float i=0;

    private float _C1=0;
    private float _C2=0;

    public AcShipBar(Context context, float sw,float zw,float xw,float w1,float w2,float H,float h1,boolean isAnnote) {
        super(context, isAnnote);
        seg=12;
        this._sw=sw;
        this._h1=h1;
        this._w1=w1;
        this._zw=zw;
        this._w2=w2;
        this._xw=xw;
        this._h2=H-sw-h1-zw-xw;
        this._l1=Utils.MMP(get_d(),h1 / w1);
        this._l2=Utils.MMP(get_d(),_h2 / w2);
        this._C1=Utils.T_C(_h1, _w1);
        this._C2=Utils.T_C(_h2,_w2);
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

        if(_w1>_w2){
            w3=_w1-_w2;
        }else if(_w1==_w2){
            w3=0;
        }else if(_w1<_w2){
            w3=_w1-_w2;
            i=Math.abs(_w1-_w2);
        }
       anpoints.add(0 + i, 0);
       anpoints.add(0 + i, _sw);
       anpoints.add(_w1 + i, _sw + _h1);
       anpoints.add(_w1 + i, _sw + _h1 + _zw);
       anpoints.add(w3 + i, _sw + _h1 + _zw + _h2);
       anpoints.add(w3 + i, _sw + _h1 + _zw + _h2 + _xw);
       anpoints.add(w3 + get_d() + i, _sw + _h1 + _zw + _h2 + _xw);
       anpoints.add(w3 + get_d() + i, _sw + _h1 + _zw + _h2 + _l2);
       anpoints.add(_w1 + get_d() + i, _sw + _h1 + _zw + _l2);
       anpoints.add(_w1 + get_d() + i, _sw + _h1 - _l1);
       anpoints.add(get_d() + i, _sw - _l1);
       anpoints.add(get_d() + i, 0);
       anpoints.add(0 + i, 0);
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

            an.addtext("s-len", String.valueOf(_sw));
            an.addtext("T->c1",String.valueOf(_C1));
            an.addtext("s-width",String.valueOf(_w1));
            an.addtext("s-h1",String.valueOf(_h1));
            an.addtext("mid-len", String.valueOf(_zw));
            an.addtext("T->c2",String.valueOf(_C2));
            an.addtext("x-width",String.valueOf(_w2));
            an.addtext("x-h2",String.valueOf(_h2));
            an.addtext("x-len", String.valueOf(_xw));

        }

    }

    @Override
    public void draw() {
        super.draw();
        if(_sw>0) {
            pen.reset();
            pen.moveTo(anpoints.getObjById(0), anpoints.getObjById(1));
            for (int i = 0; i <((seg-1)*2-1) ; i+=2) {
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
            an.autoDrawAnnotation(canvas,9, 20, 25);
        }


    }
}
