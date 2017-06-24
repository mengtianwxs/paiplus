package andmt.draw;

import andmt.methods.Annotations;
import andmt.methods.Points;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by Administrator on 2016/6/22 0022.
 */
public abstract class Andmt extends View {

    public Paint paint;
    public Canvas canvas;

    protected final int scale=10;



    public int get_color() {
        return _color;
    }

    public void set_color(int _color) {
        this._color = _color;

    }

    private int _color=Color.GREEN;

    public int get_StrokeWidth() {
        return _strokeWidth;

    }

    public void set_StrokeWidth(int strokeWidth) {
        this._strokeWidth = strokeWidth;

    }

    private int _strokeWidth=3;

    public int get_d() {
        return _d*scale;
    }

    public void set_d(int _d) {
        this._d = _d;
    }

    private int _d=1;




    public Paint anpt;
    public Annotations an;
    public Points anpoints;
    public boolean isAnnote=false;
    public Path pen;
    public int seg=0;
    public Andmt(Context context,boolean isAnnote) {
        super(context);
        paint = new Paint();
        canvas = new Canvas();
        init();



        this.isAnnote=isAnnote;
        anpoints=new Points();
        pen=new Path();



    }





    public void addPointData() {

    }
    public void startDraw() {
        invalidate();
    }

    private void init() {
        paint.setColor(get_color());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(get_StrokeWidth());
        paint.setAntiAlias(true);
    }

    public void move(int xpos,int ypos) {
        this.setX(xpos);
        this.setY(ypos);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       init();
    }




    public void draw() {

    }


    public void annotate() {

    }

}
