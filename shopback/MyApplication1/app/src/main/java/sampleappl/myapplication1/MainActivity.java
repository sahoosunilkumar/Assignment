package sampleappl.myapplication1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        header = (TextView) findViewById(R.id.header);

        Log.d("Sunil", "Sunil : "+header.getText().toString()+" = "+header.getContentDescription().toString());
        header.setText(getString(R.string.error));
        Log.d("Sunil", "Sunil11 : "+header.getText().toString()+" = "+header.getContentDescription().toString());
    }
}
