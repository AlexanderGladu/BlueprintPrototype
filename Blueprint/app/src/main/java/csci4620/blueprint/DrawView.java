package csci4620.blueprint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 100481892 on 11/25/2015.
 */
public class DrawView extends View {

    Paint paint;
    Canvas canvas;
    double convMetToPix;

    int task = 0;
    Furniture furniture;
    Room room;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attribs) {
        super(context, attribs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (task == 1) {
            Paint mypaint = new Paint();
            mypaint.setColor(Color.BLUE);
            canvas.drawRect(30, 30, 200, 200, mypaint);

        } else {
            Paint mypaint = new Paint();
            mypaint.setColor(Color.RED);
            canvas.drawRect(60, 60, 400, 400, mypaint);
        }

    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setTask(int task) {
        this.task = task;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
        this.paint.setStrokeWidth((float) 5);
    }

    public void drawFurniture(Furniture furniture) {
        if (furniture.getLength() > furniture.getWidth()) {
            convMetToPix = (600.0 - 40.0)/furniture.getLength();
        } else {
            convMetToPix = (1000.0 - 40.0)/furniture.getWidth();
        }

        if (furniture.getType().equals("Couch")) {
            drawCouch(furniture);
        } else if (furniture.getType().equals("Chair")) {
            drawChair(furniture);
        } else if (furniture.getType().equals("Table")) {
            drawTable(furniture);
        } else {
            drawError(furniture);
        }
    }

    public void drawCouch(Furniture furniture) {
        double length = furniture.getLength()*convMetToPix;
        double width = furniture.getWidth()*convMetToPix;

        this.canvas.drawLine((float) 20.0,            (float) 20.0,           (float) 20.0,            (float) (20.0 + width),  paint);
        this.canvas.drawLine((float) 20.0,            (float) (20.0 + width), (float) (20.0 + length), (float) (20.0 + width),  paint);
        this.canvas.drawLine((float) (20.0 + length), (float) (20.0 + width), (float) (20.0 + length), (float) 20.0,            paint);
        this.canvas.drawLine((float) (20.0 + length), (float) 20.0,           (float) 20.0,            (float) 20.0,            paint);

        double hLength = length/2.0;

        this.canvas.drawLine((float) (20.0 + hLength),(float) 20.0,           (float) (20.0 + hLength),(float) (20.0 + width),  paint);

        double qWidth = 3*(width/4.0);

        this.canvas.drawLine((float) 20.0, (float) (20.0 + qWidth), (float) (20.0 + length), (float) (20.0 + qWidth), paint);
    }

    public void drawChair(Furniture furniture) {
        double length = furniture.getLength()*convMetToPix;
        double width = furniture.getWidth()*convMetToPix;

        this.canvas.drawLine((float) 20.0,            (float) 20.0,           (float) 20.0,            (float) (20.0 + width),  paint);
        this.canvas.drawLine((float) 20.0,            (float) (20.0 + width), (float) (20.0 + length), (float) (20.0 + width),  paint);
        this.canvas.drawLine((float) (20.0 + length), (float) (20.0 + width), (float) (20.0 + length), (float) 20.0,            paint);
        this.canvas.drawLine((float) (20.0 + length), (float) 20.0,           (float) 20.0,            (float) 20.0,            paint);

        double qWidth = 3*(width/4.0);

        this.canvas.drawLine((float) 20.0,            (float) (20.0 + qWidth),(float) (20.0 + length), (float) (20.0 + qWidth), paint);
    }

    public void drawTable(Furniture furniture) {
        double length = furniture.getLength()*convMetToPix;
        double width = furniture.getWidth()*convMetToPix;

        this.canvas.drawLine((float) 20.0,            (float) 20.0,           (float) 20.0,            (float) (20.0 + width),  paint);
        this.canvas.drawLine((float) 20.0,            (float) (20.0 + width), (float) (20.0 + length), (float) (20.0 + width),  paint);
        this.canvas.drawLine((float) (20.0 + length), (float) (20.0 + width), (float) (20.0 + length), (float) 20.0,            paint);
        this.canvas.drawLine((float) (20.0 + length), (float) 20.0,           (float) 20.0,            (float) 20.0,            paint);
    }

    public void drawError(Furniture furniture) {
        paint.setColor(Color.RED);

        this.canvas.drawLine((float) 20.0,            (float) 20.0,           (float) 90.0,            (float) 90.0,            paint);
    }

    public void drawRoom(Room room) {
        if (room.getLength() > room.getWidth()) {
            convMetToPix = (600.0 - 40.0)/room.getLength();
        } else {
            convMetToPix = (1000.0 - 40.0)/room.getWidth();
        }

        double length = room.getLength()*convMetToPix;
        double width = room.getWidth()*convMetToPix;

        this.canvas.drawLine((float) 10.0,            (float) 10.0,           (float) 10.0,            (float) (10.0 + width), paint);
        this.canvas.drawLine((float) 10.0,            (float) (10.0 + width), (float) (10.0 + length), (float) (10.0 + width), paint);
        this.canvas.drawLine((float) (10.0 + length), (float) (10.0 + width), (float) (10.0 + length), (float) 10.0, paint);
        this.canvas.drawLine((float) (10.0 + length), (float) 10.0,           (float) 10.0,            (float) 10.0, paint);
    }

    public void clearCanvas() {
        this.canvas.drawColor(Color.WHITE);
    }

    public void setConvMetToPix(double convMetToPix) {
        this.convMetToPix = convMetToPix;
    }
}
