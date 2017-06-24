package andmt.methods;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/6/25 0025.
 */
public class Points {

    private ArrayList<Float> xpset = new ArrayList<Float>();
    private ArrayList<Float> ypset = new ArrayList<Float>();
    private ArrayList<Float> pset = new ArrayList<Float>();


    public void add(float xpos, float ypos) {
        xpset.add(xpos);
        ypset.add(ypos);
        pset.add(xpos);
        pset.add(ypos);
    }

    public void outputxpset() {
        Iterator it=xpset.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public float[] xsets() {


    return Utils.al2floats(xpset);

    }

    public float[] psets() {
        return Utils.al2floats(pset);
    }

    public void outputpset() {
        Iterator it=pset.iterator();
        while (it.hasNext()) {
            System.out.println("//////////this is psets "+it.next());
        }

    }

    public float[] ysets() {
        return Utils.al2floats(ypset);
    }

    public int length() {
        return pset.size();
    }

    public int len() {
        return xpset.size();
    }

    public float getObjById(int id) {
        return pset.get(id);
    }

    public void clear() {
        pset.clear();
        xpset.clear();
        ypset.clear();
    }

}
