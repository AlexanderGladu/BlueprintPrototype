package csci4620.blueprint;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 100481892 on 11/25/2015.
 */

public class MainActivity extends Activity {

    Paint usePaint;
    DrawView drawView;

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

        mainIntent = getIntent();
        curUser = (User) mainIntent.getSerializableExtra("User");
        drawView = new DrawView(this);

        roomName = (TextView) findViewById(R.id.room_name_label);
        roomName.setText(getResources().getString(R.string.no_room_label));
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

    public static class NoRoomDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.dialog_no_room)
                    .setPositiveButton(R.string.dialog_go_there, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            goToRoom = 1;
                        }
                    })
                    .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dismiss();
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent resultIntent) {
        super.onActivityResult(requestCode, responseCode, resultIntent);

        if (responseCode == RESULT_OK) {
            if (requestCode == 474785743) {
                tempFurnitures.add((Furniture) resultIntent.getSerializableExtra("NewFurniture"));
                usePaint.setColor(Color.BLUE);
                drawView.setPaint(usePaint);
                drawView.drawFurniture(tempFurnitures.get(furnitureIter));
                furnitureIter += 1;
            } else if (requestCode == 4997) {
                drawView.clearCanvas();
                tempRooms.add((Room) resultIntent.getSerializableExtra("NewRoom"));

                roomName = (TextView) findViewById(R.id.room_name_label);
                if (tempRooms.get(roomsIter).getName() == null) {
                    roomName.setText(getResources().getString(R.string.no_room_label));
                } else {
                    roomName.setText(tempRooms.get(roomsIter).getName());
                }

                usePaint.setColor(Color.BLACK);
                drawView.setPaint(usePaint);
                drawView.drawRoom(tempRooms.get(roomsIter));
                roomsIter += 1;
            } else if (requestCode == 694997) {

            }
        }

    }

    public void addRoom() {
        addRoomIntent = new Intent(MainActivity.this, AddRoomActivity.class);
        startActivityForResult(addRoomIntent, 4997);
    }

    public void addFurniture() {
        if (tempRooms.size() == 0) {
            Intent dialogIntent = new Intent(MainActivity.this, NoRoomDialogFragment.class);
            startActivityForResult(dialogIntent, 694997);
        } else {
            addFurnitureIntent = new Intent(MainActivity.this, AddFurnitureActivity.class);
            startActivityForResult(addFurnitureIntent, 474785743);
        }
    }
}
