package csci4620.blueprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 100481892 on 11/25/2015.
 */
public class AddRoomActivity extends Activity {

    Intent addRoomIntent;
    private Room tempRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimensions);

        addRoomIntent = getIntent();

        TextView addTitle = (TextView) findViewById(R.id.add_what_label);
        addTitle.setText(getResources().getString(R.string.room_label));
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
        }

        return super.onOptionsItemSelected(item);
    }

    public void addDimensions(View view) {
        tempRoom = new Room(findViewById(R.id.name_input).toString(),
                Double.parseDouble(findViewById(R.id.length_input).toString()),
                Double.parseDouble(findViewById(R.id.width_input).toString()),
                Double.parseDouble(findViewById(R.id.height_input).toString()));

        addRoomIntent.putExtra("Ident", 0);
        addRoomIntent.putExtra("NewRoom", tempRoom);
        setResult(RESULT_OK, addRoomIntent);
        finish();
    }
}
