package mtstudio.tools;

/**
 * Created by mengtianwxs on 2017/5/14.
 */

public class O_Cheng extends Operation {
    @Override
    public float getResult() {
        float result;
        result=get_numA()*get_numB();
        return result;
    }
}
