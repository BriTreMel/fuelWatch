package com.example.mel76.fuelwatch11;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;



public class MainActivity extends Activity {

    private EditText emailField, passwordField;
    private TextView oilLevel;
    String OilLevelFromDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emailField = (EditText) findViewById(R.id.editTextEmail);
        passwordField = (EditText) findViewById(R.id.editTextPassword);

        oilLevel = (TextView) findViewById(R.id.textViewLevel);

        TextView dbOilLevel = (TextView) findViewById(R.id.textViewLevel);
        String dbOilLevelConverted = (String) dbOilLevel.getText().toString();
        int dbOilLevelInt = Integer.parseInt(dbOilLevelConverted);
        //adding the google charts
        WebView webview = (WebView) findViewById(R.id.webView1);



        String content = "<html>"
                + "  <head>"
                + "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
                + "    <script type=\"text/javascript\">"
                + "      google.charts.load('current', {'packages':['gauge']});"
                + "      google.charts.setOnLoadCallback(drawChart);"
                + "      function drawChart() {"
                + "        var data = google.visualization.arrayToDataTable(["
                + "          ['Label', 'Value'],"
                + "          ['Oil Level', "+ dbOilLevelConverted +"],"
                + "        ]);"
                + "        var options = {"
                + "          width: 800, height: 240,"
                + "          redFrom: 0, redTo: 10,"
                + "          yellowFrom:10, yellowTo: 20,"
                + "          minorTicks: 5"
                + "        };"

                + "        var chart = new google.visualization.Gauge(document.getElementById('chart_div'));"
                + "        chart.draw(data, options);"
                + "        setInterval(function() {"
                + "        data.setValue(0, 1, 40 + Math.round(60 * Math.random()));"
                + "        chart.draw(data, options);"
                + "        }, 13000);"
                + "      }"
                + "    </script>"
                + "  </head>"
                + "  <body>"
                + "    <div id=\"chart_div\" style=\"width: 400px; height: 120px;\"></div>"
                + "  </body>" + "</html>";



        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.requestFocusFromTouch();

        webview.loadDataWithBaseURL("file:///android_asset/", content, "text/html", "utf-8", null);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

   /* public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_to_image:
            Intent intent = new Intent(MainActivity.this, GoogleImageGraphActivity.class);
            startActivity(intent);
            return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    public void loginPost(View view){
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        // Passes the data to the singinActivity.java
        //oil level passed to new activity, with flag set to 1 (means using a POST method)
        new SigninActivity(this,oilLevel,1).execute(email, password);


    }
}

