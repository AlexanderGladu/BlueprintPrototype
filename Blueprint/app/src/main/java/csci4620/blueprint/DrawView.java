package csci4620.blueprint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by 100481892 on 11/25/2015.
 */
public class DrawView extends View {

    Paint mypaint;
    Canvas canvas;
    double convMetToPix;

    float lastX;
    float lastY;
    float nextX;
    float nextY;

    int task = -1;
    int errorFlag = 0;
    int roomDraw = 0;
    int furnitureDraw = 0;

    ArrayList<Furniture> furniture = new ArrayList<>();
    ArrayList<Room> room = new ArrayList<>();

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attribs) {
        super(context, attribs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        ArrayList<Float> points;

        /**
         * Draws the room that is in queue
         */

        if (task == 1) {
            mypaint = new Paint();
            mypaint.setColor(Color.BLACK);
            points = drawRoom(room.get(roomDraw));
            int pointIter = 0;
            while (pointIter < points.size()) {
                canvas.drawLine(points.get(pointIter),
                                points.get(pointIter + 1),
                                points.get(pointIter + 2),
                                points.get(pointIter + 3),
                                mypaint);
                pointIter += 4;
            }
            invalidate();
            mypaint.setColor(Color.BLUE);
            points = new ArrayList<>();

            /**
             * Draws all of the furniture that is added to the canvas
             * Iterates through to makes sure all is drawn
             */

            while (furnitureDraw < furniture.size()) {
                points = drawFurniture(furniture.get(furnitureDraw));

                /**
                 * Catches an error if something is too big to fit
                 * in a room, anddraws a red line.
                 * Only a temporary warning, and did not implement a
                 * way to get ris of it,
                 */

                if (errorFlag == 1) {
                    pointIter = 0;
                    while (pointIter < points.size()) {
                        canvas.drawLine(points.get(pointIter),
                                points.get(pointIter + 1),
                                points.get(pointIter + 2),
                                points.get(pointIter + 3),
                                mypaint);
                        pointIter += 4;
                    }
                    furnitureDraw += 1;
                    invalidate();
                    errorFlag = 0;

                    /*

                    /**
                     * Re-draw the canvas after error.
                     **
                    canvas.drawColor(Color.WHITE);
                    points = drawRoom(room.get(roomDraw));
                    while (pointIter < points.size()) {
                        canvas.drawLine(points.get(pointIter),
                                points.get(pointIter + 1),
                                points.get(pointIter + 2),
                                points.get(pointIter + 3),
                                mypaint);
                        pointIter += 4;
                    }

                    */
                } else {

                    /**
                     * After an error is finished, or if no error is present,
                     * Changes the colour to blue, and draws the normal furniture
                     */

                    pointIter = 0;
                    mypaint.setColor(Color.BLUE);
                    while (pointIter < points.size()) {
                        canvas.drawLine(points.get(pointIter),
                                points.get(pointIter + 1),
                                points.get(pointIter + 2),
                                points.get(pointIter + 3),
                                mypaint);
                        pointIter += 4;
                    }
                    furnitureDraw += 1;
                }
            }

            furnitureDraw = 0;
        } else if (task == 0) {

            /**
             * When a new room is created, only this portion needs to be called,
             * so there is no reference to a blank furniture arraylist.
             */

            furniture = new ArrayList<>();
            mypaint = new Paint();
            mypaint.setColor(Color.BLACK);
            points = drawRoom(room.get(roomDraw));
            int pointIter = 0;
            while (pointIter < points.size()) {
                canvas.drawLine(points.get(pointIter),
                        points.get(pointIter + 1),
                        points.get(pointIter + 2),
                        points.get(pointIter + 3),
                        mypaint);
                pointIter += 4;
            }
            invalidate();
        }

    }

    public void setFurniture(Furniture furniture) {
        this.furniture.add(furniture);
    }

    public void setRoom(Room room) {
        this.room.add(room);
    }

    public void setRoomDraw(int rmNum) {
        this.roomDraw = rmNum;
    }

    public void setFurnitureDraw(int frnNum) {
        this.furnitureDraw = frnNum;
    }

    public void setTask(int task) {
        this.task = task;
    }

    public void setPaint(Paint paint) {
        this.mypaint = paint;
        this.mypaint.setStrokeWidth((float) 5);
    }

    public ArrayList<Float> drawFurniture(Furniture furniture) {

        ArrayList<Float> point;

        /**
         * Different furniture types require different draw points,
         * and this parses that data, and calls the functions to
         * get the proper points.
         */

        if (furniture.getType().equals("Couch")) {
            point =  drawCouch(furniture);
        } else if (furniture.getType().equals("Chair")) {
            point =  drawChair(furniture);
        } else if (furniture.getType().equals("Table")) {
            point =  drawTable(furniture);
        } else {
            point = drawError(furniture);
        }

        return point;
    }

    public ArrayList<Float> drawCouch(Furniture furniture) {

        ArrayList<Float> point = new ArrayList<>();

        double length = furniture.getLength()*convMetToPix;
        double width = furniture.getWidth()*convMetToPix;

        if ((length + nextX) > (room.get(roomDraw).getLength()*convMetToPix)) {
            nextX = lastX;
        }
        if ((width + nextY) > (room.get(roomDraw).getWidth()*convMetToPix)) {
            nextY = lastY;
        }

        /**
         * Throws the error if the furniture is too wide or long
         */

        if ((width + nextY) > (room.get(roomDraw).getWidth()*convMetToPix)
                || (length + nextX) > (room.get(roomDraw).getLength()*convMetToPix)) {
            point = drawError(furniture);
            return point;
        } else {

            /**
             * Draws the main rectangle/square of the furniture.
             */

            point.add((float) nextX);
            point.add((float) nextY);
            point.add((float) nextX);
            point.add((float) (nextY + width));

            point.add((float) nextX);
            point.add((float) (nextY + width));
            point.add((float) (nextX + length));
            point.add((float) (nextY + width));

            point.add((float) (nextX + length));
            point.add((float) (nextY + width));
            point.add((float) (nextX + length));
            point.add((float) nextY);

            point.add((float) (nextX + length));
            point.add((float) nextY);
            point.add((float) nextX);
            point.add((float) nextY);

            double hLength = length/2.0;

            /**
             * Draws the middle line, creating the two cushions.
             */

            point.add((float) (nextX + hLength));
            point.add((float) nextY);
            point.add((float) (nextX + hLength));
            point.add((float) (nextY + width));

            double qWidth = 3*(width/4.0);

            /**
             * Draws the back line to show cushions on the back of the couch
             */

            point.add((float) nextX);
            point.add((float) (nextY + qWidth));
            point.add((float) (nextX + length));
            point.add((float) (nextY + qWidth));

            lastX = nextX;
            lastY = nextY;
            nextX = nextX + (float) length;
            nextY = nextY + (float) width;

            return point;
        }
    }

    public ArrayList<Float> drawChair(Furniture furniture) {

        ArrayList<Float> point = new ArrayList<>();

        double length = furniture.getLength()*convMetToPix;
        double width = furniture.getWidth()*convMetToPix;

        if ((length + nextX) > (room.get(roomDraw).getLength()*convMetToPix)) {
            nextX = lastX;
        }
        if ((width + nextY) > (room.get(roomDraw).getWidth()*convMetToPix)) {
            nextY = lastY;
        }

        /**
         * Throws the error if the furniture is too wide or long
         */

        if ((width + nextY) > (room.get(roomDraw).getWidth()*convMetToPix)
                || (length + nextX) > (room.get(roomDraw).getLength()*convMetToPix)) {
            point = drawError(furniture);
            return point;
        } else {

            /**
             * Draws the main rectangle/square of the furniture.
             */

            point.add((float) nextX);
            point.add((float) nextY);
            point.add((float) nextX);
            point.add((float) (nextY + width));

            point.add((float) nextX);
            point.add((float) (nextY + width));
            point.add((float) (nextX + length));
            point.add((float) (nextY + width));

            point.add((float) (nextX + length));
            point.add((float) (nextY + width));
            point.add((float) (nextX + length));
            point.add((float) nextY);

            point.add((float) (nextX + length));
            point.add((float) nextY);
            point.add((float) nextX);
            point.add((float) nextY);

            double qWidth = 3 * (width / 4.0);

            /**
             * Draws the back line to show cushions on the back of the chair
             */

            point.add((float) nextX);
            point.add((float) (nextY + qWidth));
            point.add((float) (nextX + length));
            point.add((float) (nextY + qWidth));

            lastX = nextX;
            lastY = nextY;
            nextX = nextX + (float) length;
            nextY = nextY + (float) width;

            return point;
        }
    }

    public ArrayList<Float> drawTable(Furniture furniture) {
        ArrayList<Float> point = new ArrayList<>();

        double length = furniture.getLength()*convMetToPix;
        double width = furniture.getWidth()*convMetToPix;

        if ((length + nextX) > (room.get(roomDraw).getLength()*convMetToPix)) {
            nextX = lastX;
        }
        if ((width + nextY) > (room.get(roomDraw).getWidth()*convMetToPix)) {
            nextY = lastY;
        }

        /**
         * Throws the error if the furniture is too wide or long
         */

        if ((width + nextY) > (room.get(roomDraw).getWidth()*convMetToPix)
                || (length + nextX) > (room.get(roomDraw).getLength()*convMetToPix)) {
            point = drawError(furniture);
            return point;
        } else {

            /**
             * Draws the main rectangle/square of the furniture.
             */

            point.add((float) nextX);
            point.add((float) nextY);
            point.add((float) nextX);
            point.add((float) (nextY + width));

            point.add((float) nextX);
            point.add((float) (nextY + width));
            point.add((float) (nextX + length));
            point.add((float) (nextY + width));

            point.add((float) (nextX + length));
            point.add((float) (nextY + width));
            point.add((float) (nextX + length));
            point.add((float) nextY);

            point.add((float) (nextX + length));
            point.add((float) nextY);
            point.add((float) nextX);
            point.add((float) nextY);

            lastX = nextX;
            lastY = nextY;
            nextX = nextX + (float) length;
            nextY = nextY + (float) width;

            return point;
        }
    }

    public ArrayList<Float> drawError(Furniture furniture) {

        ArrayList<Float> point = new ArrayList<>();

        mypaint.setColor(Color.RED);
        errorFlag = 1;

        point.add((float) 0.0);
        point.add((float) 0.0);
        point.add((float) 600.0);
        point.add((float) 600.0);

        return point;
    }

    public ArrayList<Float> drawRoom(Room room) {

        ArrayList<Float> point = new ArrayList<>();

        /**
         * This is the scale that determines how much a meter is in terms
         * of pixels. When this is created, the convert value is assigned, and used
         * when drawing the rooms, and furniture.
         */

        if (room.getLength() > room.getWidth()) {
            convMetToPix = (500.0 - 60.0)/room.getLength();
        } else {
            convMetToPix = (800.0 - 60.0)/room.getWidth();
        }

        double length = room.getLength()*convMetToPix;
        double width = room.getWidth()*convMetToPix;

        /**
        * Draws the main rectangle/square of the room.
        */

        point.add((float) 20.0);
        point.add((float) 20.0);
        point.add((float) 20.0);
        point.add((float) (20.0 + width));

        point.add((float) 20.0);
        point.add((float) (20.0 + width));
        point.add((float) (20.0 + length));
        point.add((float) (20.0 + width));

        point.add((float) (20.0 + length));
        point.add((float) (20.0 + width));
        point.add((float) (20.0 + length));
        point.add((float) 20.0);

        point.add((float) (20.0 + length));
        point.add((float) 20.0);
        point.add((float) 20.0);
        point.add((float) 20.0);

        lastX = (float) 20.0;
        lastY = (float) 20.0;
        nextX = (float) 30.0;
        nextY = (float) 30.0;

        return point;
    }

    public void clearCanvas() {
        canvas.drawColor(Color.WHITE);
    }

    public void setConvMetToPix(double convMetToPix) {
        this.convMetToPix = convMetToPix;
    }
}
