package mtstudio.tools;

/**
 * Created by mengtianwxs on 2017/5/14.
 */

public class O_Chu extends Operation {
    @Override
    public float getResult() {
        float result;
        if(get_numB()==0){
         return 0;
        }else {
            result = get_numA() / get_numB();
        }
        return result;
    }
}
