package mtstudio.tools;

/**
 * Created by mengtianwxs on 2017/5/14.
 */

public class OperationFactory {
    public final static int P_jia=0;
    public final static int P_jian=1;
    public final static int P_cheng=2;
    public final static int P_chu=3;


    public static Operation createOperation(int p){
        Operation oper=null;
        switch (p){
            case 0:
                oper=new O_Jia();
                break;
            case 1:
                oper=new O_Jian();
                break;
            case 2:
                oper=new O_Cheng();
                break;
            case 3:
                oper = new O_Chu();
                break;

        }
        return oper;
    }

}
