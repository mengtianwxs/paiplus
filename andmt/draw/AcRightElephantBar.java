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
public class AcRightElephantBar extends Andmt {
    private float _sw=0;
    private float _h=0;
    private float _w=0;
    private float _xw=0;
    private float _bottomLen=0;
    private float _l=0;
    private float _C=0;


    public AcRightElephantBar(Context context,float sh,float h,float w,float xh,float bottomLen,boolean isAnnote ) {
        super(context, isAnnote);
        seg=10;
        this._sw=sh;
        this._h=h-sh-xh;
        this._w=w;
        this._xw=xh;
        this._bottomLen=bottomLen;
        this._l= Utils.MMP(get_d(), _h / w);
        this._C=Utils.T_C(_h,_w);
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

      anpoints.add(_w,0);
      anpoints.add(_w+get_d(),0);
      anpoints.add(_w+get_d(),_sw);
      anpoints.add(get_d(),_sw+_h);
      anpoints.add(get_d(),_sw+_h+_xw);
      anpoints.add(get_d()+_bottomLen,_sw+_h+_xw);
      anpoints.add(get_d()+_bottomLen,_sw+_h+_xw+get_d());
      anpoints.add(0,_sw+_h+_xw+get_d());
      anpoints.add(0,_sw+_h-_l);
      anpoints.add(_w,_sw-_l);
      anpoints.add(_w,0);

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
            an.addtext("T->c",String.valueOf(_C));
            an.addtext("width", String.valueOf(_w));
            an.addtext("x-len", String.valueOf(_xw));
            an.addtext("bottom-len", String.valueOf(_xw));
            an.addtext("total", String.valueOf(_sw+_C+_xw+_bottomLen));
        }

    }

    @Override
    public void draw() {
        super.draw();
        if(_sw>0&&_bottomLen>0) {
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
            an.autoDrawAnnotation(canvas, 5, 20, 25);
        }


    }
}
