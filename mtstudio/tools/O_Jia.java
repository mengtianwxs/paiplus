package mtstudio.tools;

/**
 * Created by mengtianwxs on 2017/5/14.
 */

public class O_Jia extends Operation {

    @Override
    public float getResult() {
        float result=0;
        result=get_numA()+get_numB();
        return result;
    }
}
