package andmt.debug;

import andmt.draw.*;
import android.app.Activity;
import android.os.Bundle;


public class Acat extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //AcRightElephantBar acLBar=new AcRightElephantBar(this,300,800,100,200,300,true);
       // AcShipBar acLBar=new AcShipBar(this,100,50,150,200,400,600,95.3f,true);
       // AcSnakeBar acLBar=new AcSnakeBar(this,100,20,400,700,true);
       // AcUBar acLBar=new AcUBar(this,100,400,300,true);
        //AcZBar acLBar=new AcZBar(this,100,300,20,true);
        AcLineBar acLBar=new AcLineBar(this,100,true);
        acLBar.startDraw();
        setContentView(acLBar);
        acLBar.move(50, 40);


    }


}
