package aryaman.harmonium;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<String, String> scaleMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scaleMap.put("C#", "8");
        scaleMap.put("D#", "9");
        Button subButton = (Button) findViewById(R.id.subButton);
        final TextInputLayout sendText = (TextInputLayout) findViewById(R.id.scale_text);
        subButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NavActivity.class);
                String scale_str = sendText.getEditText().getText().toString();
                String scale = scaleMap.getOrDefault(scale_str, "9");
                Log.d("Scale", ""+scale);
                intent.putExtra("Message", scale);
                startActivity(intent);
            }
        });

    }

}