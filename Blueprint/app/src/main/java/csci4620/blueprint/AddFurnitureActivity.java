package csci4620.blueprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by 100481892 on 11/25/2015.
 */
public class AddFurnitureActivity extends Activity {

    Intent addFurniture;
    Furniture tempFurniture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_furniture);

        addFurniture = getIntent();
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

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent resultIntent) {
        super.onActivityResult(requestCode, responseCode, resultIntent);

        if (responseCode == RESULT_OK) {
            tempFurniture = (Furniture) resultIntent.getSerializableExtra("NewFurniture");

            resultIntent.putExtra("Ident", 1);
            resultIntent.putExtra("Furniture", tempFurniture);
            setResult(RESULT_OK, resultIntent);
            finish();
        }

    }

    public void addCouch(View view) {
        addItem("Couch");
    }

    public void addChair(View view) {
        addItem("Chair");
    }

    public void addTable(View view) {
        addItem("Table");
    }

    public void addItem(String type) {
        Intent getDimensionsIntent = new Intent(AddFurnitureActivity.this, DimensionsActivity.class);
        getDimensionsIntent.putExtra("Type", type);
        startActivityForResult(getDimensionsIntent, 300003);
    }
}
