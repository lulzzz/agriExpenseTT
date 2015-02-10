package uwi.dcit.AgriExpenseTT;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import uwi.dcit.AgriExpenseTT.fragments.FragmentAddData;
import uwi.dcit.AgriExpenseTT.helpers.GAnalyticsHelper;

public class AddData extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_new_cycle_redesigned);

        getFragmentManager()
            .beginTransaction()
            .add(R.id.NewCycleListContainer, new FragmentAddData())
            .commit();

        // Google Analytics
        GAnalyticsHelper.getInstance(this.getApplicationContext()).sendScreenView("Add Data");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_data, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void appendSub(String string) {
		TextView tv_sub=(TextView)findViewById(R.id.tv_mainNew_subheader);
		String text=tv_sub.getText().toString();
		tv_sub.setText(text+" "+string);
	}
}
