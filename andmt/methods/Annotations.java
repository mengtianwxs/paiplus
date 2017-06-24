package andmt.methods;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.*;

/**
 * Created by Administrator on 2016/6/25 0025.
 */
public class Annotations   {

    private Points ps;
    private ArrayList<String> txtset = new ArrayList<String>();
    private int couter=0;
    private Paint pt;
    public Annotations(Paint paint, Points p) {
        this.pt = paint;
        this.ps =p;

    }

    public int length() {
        return ps.length();
    }
    public int size(){return ps.xsets().length;};

    public String[] txtset() {
        return Utils.al2Strings(txtset);
    }

    public float[] xset() {
        return ps.xsets();
    }

    public float[] yset() {
        return ps.ysets();
    }

    public float[] pset() {
        return ps.psets();
    }


  public void addtext(String first,String str) {
       txtset.add("@"+(couter++)+first+" "+str);
   }


    public void autoAddNonotation() {
        for (int i = 0; i < xset().length; i++) {
            txtset.add("@P" + (couter++) + ", " + xset()[i] + " , " + yset()[i]);
        }
    }
    public void autoDrawAnnotation(Canvas can,int lockX,int xmove,int ymove) {
        if (txtset().length != 0) {

            for (int i = 0; i <couter; i++) {
                can.drawText(txtset.get(i), xset()[lockX] + xmove, i * 50 + ymove, pt);
            }

        } else {
            System.out.println("////////////////error record");
        }

    }

    public void clear() {
        ps.clear();
        txtset.clear();
    }

    public String getTextById(int id) {
        return txtset.get(id);
    }

   /* public void drawAnnotationById(int id) {
        ancanvas.drawText(getTextById(id), xset()[id], yset()[id], pt);

    }*/

    public void testDraw(Canvas can) {
        can.drawText("test", 200, 200, pt);
        System.out.println("///////////testdraw");
    }



}
