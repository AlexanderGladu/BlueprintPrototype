package csci4620.blueprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 100481892 on 11/25/2015.
 */
public class DimensionsActivity extends Activity {

    Intent getDimensions;
    Furniture tempFurniture;

    double length;
    double width;
    double height;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimensions);

        getDimensions = getIntent();

        TextView addTitle = (TextView) findViewById(R.id.add_what_label);
        addTitle.setText(getDimensions.getStringExtra("Type") +
                         " " + getResources().getString(R.string.furniture_label));
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

    /**
     * Used for furniture, as it has types, and needs to pass those values
     * Due to rooms not needing, they don't need this activity to run
     **/

    public void addDimensions(View view) {

        EditText getDouble = (EditText) findViewById(R.id.length_input);
        length = Double.parseDouble(getDouble.getText().toString());

        getDouble = (EditText) findViewById(R.id.width_input);
        width = Double.parseDouble(getDouble.getText().toString());

        getDouble = (EditText) findViewById(R.id.height_input);
        height = Double.parseDouble(getDouble.getText().toString());

        EditText getName = (EditText) findViewById(R.id.name_input);
        name = getName.getText().toString();

        tempFurniture = new Furniture(
                getDimensions.getStringExtra("Type"),
                name, length, width, height);

        getDimensions.putExtra("NewFurniture", tempFurniture);
        setResult(RESULT_OK, getDimensions);
        finish();
    }
}
