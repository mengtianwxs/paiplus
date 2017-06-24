package andmt.methods;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/25 0025.
 */
public class Utils  {


    public static float[] al2floats(ArrayList<Float> al) {
        int s=al.size();
        float[] f = new float[s];
        for (int i = 0; i <s ; i++) {
            f[i] = al.get(i);
        }
        return f;
    }

    public static String[] al2Strings(ArrayList<String> arrayList) {
        int s= arrayList.size();
        String[] str=new String[s];
        for (int i = 0; i <s ; i++) {
            str[i]=arrayList.get(i);
        }
        return str;

    }


    public static float MMP(float d,float n) {
        return Float.valueOf(Double.toString(d/Math.tan(Math.PI/180*((180/Math.PI*(Math.atan(n))+90)/2))));
    }

    public static float T_C(float a,float b) {
        return Float.valueOf(Double.toString(Math.sqrt(a * a + b * b)));
    }


}
