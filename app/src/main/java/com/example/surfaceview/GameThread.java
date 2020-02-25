package com.example.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Random;

public class GameThread extends Thread {
    private  Bitmap bitmap;
    private  volatile boolean raning= true;
    private  SurfaceHolder surfaceHolder;
    private Paint backgraundPaint=new Paint();
    private Paint paint=new Paint();

    private double vx;
    private double vy;
    {backgraundPaint.setColor(Color.BLUE);
    backgraundPaint.setStyle(Paint.Style.FILL);
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(Color.YELLOW);

    }


    public GameThread(Context context,SurfaceHolder surfaceHolder) {
        bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.smail);
    this.surfaceHolder = surfaceHolder;
    }

    public void recStop(){
         raning=false;
     }

    @Override
    public void run() {
        float x=200;
        float y=200;
        Random random=new Random();
        vx=(random.nextFloat()-0.5)*10;
        vy=(random.nextDouble()-0.5)*10;

        super.run();
        while (raning){
            Canvas canvas=surfaceHolder.lockCanvas();
            if(canvas!=null){
                try {
                    x+=vx;
                    y+=vy;
                    if(x<70|| x>canvas.getWidth()-70){
                        vx=-vx;
                    }
                    if(y<70|| y>canvas.getHeight()-70){
                        vy=-vy;
                    }
                    canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),backgraundPaint);
                    canvas.drawCircle(x,y,70,paint);


                }finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

        }

    }
}
