package csci4620.blueprint;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 100481892 on 11/25/2015.
 */

public class MainActivity extends Activity {

    DrawView drawView;
    Canvas canvas = new Canvas();
    User curUser;

    Intent mainIntent;
    Intent addRoomIntent;
    Intent addFurnitureIntent;

    ArrayList<Room> tempRooms;
    int roomsIter = 0;
    ArrayList<Furniture> tempFurnitures;
    int furnitureIter = 0;

    TextView roomName;

    public static int goToRoom = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempRooms = new ArrayList<>();
        tempFurnitures = new ArrayList<>();

        mainIntent = getIntent();
        curUser = (User) mainIntent.getSerializableExtra("User");

        roomName = (TextView) findViewById(R.id.room_name_label);
        roomName.setText(getResources().getString(R.string.no_room_label));

        drawView = new DrawView(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.add_room) {
            addRoom();
        } else if (id == R.id.add_furniture) {
            addFurniture();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent resultIntent) {
        super.onActivityResult(requestCode, responseCode, resultIntent);

        if (responseCode == RESULT_OK) {
            if (requestCode == 474785743) {
                tempFurnitures.add((Furniture) resultIntent.getSerializableExtra("NewFurniture"));
                drawView.setFurnitureDraw(furnitureIter);
                drawView.setFurniture(tempFurnitures.get(furnitureIter));
                drawView.setTask(1);
                drawView.draw(canvas);
                furnitureIter += 1;

            } else if (requestCode == 122334) {
                tempRooms.add((Room) resultIntent.getSerializableExtra("NewRoom"));

                /*
                roomName = (TextView) findViewById(R.id.room_name_label);
                if (tempRooms.get(roomsIter).getName() == null) {
                    roomName.setText(getResources().getString(R.string.no_room_label));
                } else {
                    roomName.setText(tempRooms.get(roomsIter).getName());
                }
                */

                drawView.setRoomDraw(roomsIter);
                drawView.setRoom(tempRooms.get(roomsIter));
                drawView.setTask(0);
                setContentView(drawView);
                roomsIter += 1;
            }
        }
    }

    public void addRoom() {
        addRoomIntent = new Intent(MainActivity.this, AddRoomActivity.class);
        startActivityForResult(addRoomIntent, 122334);
    }

    public void addFurniture() {
        if (tempRooms.size() == 0) {

        } else {
            addFurnitureIntent = new Intent(MainActivity.this, AddFurnitureActivity.class);
            startActivityForResult(addFurnitureIntent, 474785743);
        }
    }
}
