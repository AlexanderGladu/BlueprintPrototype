package csci4620.blueprint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by 100481892 on 11/25/2015.
 */
public class DrawView extends ImageView {

    Paint paint;
    Canvas canvas;
    double convMetToPix;

    public DrawView(Context context) {
        super(context);

        canvas = new Canvas();
        clearCanvas();

        convMetToPix = 1.0;
    }

    public DrawView(Context context, AttributeSet attribs) {
        super(context, attribs);

        canvas = new Canvas();
        clearCanvas();

        convMetToPix = 1.0;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void drawFurniture(Furniture furniture) {
        if (furniture.getLength() > furniture.getWidth()) {
            convMetToPix = (350.0 - 40.0)/furniture.getLength();
        } else {
            convMetToPix = (450.0 - 40.0)/furniture.getWidth();
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

        canvas.drawLine((float) 20.0,            (float) 20.0,           (float) 20.0,            (float) (20.0 + width),  paint);
        canvas.drawLine((float) 20.0,            (float) (20.0 + width), (float) (20.0 + length), (float) (20.0 + width),  paint);
        canvas.drawLine((float) (20.0 + length), (float) (20.0 + width), (float) (20.0 + length), (float) 20.0,            paint);
        canvas.drawLine((float) (20.0 + length), (float) 20.0,           (float) 20.0,            (float) 20.0,            paint);

        double hLength = length/2.0;

        canvas.drawLine((float) (20.0 + hLength),(float) 20.0,           (float) (20.0 + hLength),(float) (20.0 + width),  paint);

        double qWidth = 3*(width/4.0);

        canvas.drawLine((float) 20.0,            (float) (20.0 + qWidth),(float) (20.0 + length), (float) (20.0 + qWidth), paint);
    }

    public void drawChair(Furniture furniture) {
        double length = furniture.getLength()*convMetToPix;
        double width = furniture.getWidth()*convMetToPix;

        canvas.drawLine((float) 20.0,            (float) 20.0,           (float) 20.0,            (float) (20.0 + width),  paint);
        canvas.drawLine((float) 20.0,            (float) (20.0 + width), (float) (20.0 + length), (float) (20.0 + width),  paint);
        canvas.drawLine((float) (20.0 + length), (float) (20.0 + width), (float) (20.0 + length), (float) 20.0,            paint);
        canvas.drawLine((float) (20.0 + length), (float) 20.0,           (float) 20.0,            (float) 20.0,            paint);

        double qWidth = 3*(width/4.0);

        canvas.drawLine((float) 20.0,            (float) (20.0 + qWidth),(float) (20.0 + length), (float) (20.0 + qWidth), paint);
    }

    public void drawTable(Furniture furniture) {
        double length = furniture.getLength()*convMetToPix;
        double width = furniture.getWidth()*convMetToPix;

        canvas.drawLine((float) 20.0,            (float) 20.0,           (float) 20.0,            (float) (20.0 + width),  paint);
        canvas.drawLine((float) 20.0,            (float) (20.0 + width), (float) (20.0 + length), (float) (20.0 + width),  paint);
        canvas.drawLine((float) (20.0 + length), (float) (20.0 + width), (float) (20.0 + length), (float) 20.0,            paint);
        canvas.drawLine((float) (20.0 + length), (float) 20.0,           (float) 20.0,            (float) 20.0,            paint);
    }

    public void drawError(Furniture furniture) {
        paint.setColor(Color.RED);

        canvas.drawLine((float) 20.0,            (float) 20.0,           (float) 90.0,            (float) 90.0,            paint);
    }

    public void drawRoom(Room room) {
        if (room.getLength() > room.getWidth()) {
            convMetToPix = (350.0 - 40.0)/room.getLength();
        } else {
            convMetToPix = (450.0 - 40.0)/room.getWidth();
        }

        double length = room.getLength()*convMetToPix;
        double width = room.getWidth()*convMetToPix;

        canvas.drawLine((float) 10.0,            (float) 10.0,           (float) 10.0,            (float) (10.0 + width), paint);
        canvas.drawLine((float) 10.0,            (float) (10.0 + width), (float) (10.0 + length), (float) (10.0 + width), paint);
        canvas.drawLine((float) (10.0 + length), (float) (10.0 + width), (float) (10.0 + length), (float) 10.0, paint);
        canvas.drawLine((float) (10.0 + length), (float) 10.0,           (float) 10.0,            (float) 10.0, paint);
    }

    public void clearCanvas() {
        canvas.drawColor(Color.WHITE);
    }

    public void setConvMetToPix(double convMetToPix) {
        this.convMetToPix = convMetToPix;
    }
}
