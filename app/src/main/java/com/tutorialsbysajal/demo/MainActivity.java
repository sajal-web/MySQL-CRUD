package com.tutorialsbysajal.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
private EditText edittextId,edittextName,edittextAddress;
private Button btnAdd,btnupdate,btndelete,btngetdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittextId = findViewById(R.id.edittextId);
        edittextName = findViewById(R.id.edittextName);
        edittextAddress = findViewById(R.id.edittextAddress);
        btnAdd = findViewById(R.id.btnAdd);
        btnupdate = findViewById(R.id.btnupdate);
        btnupdate = findViewById(R.id.btnupdate);
        btndelete = findViewById(R.id.btndelete);
        btngetdata = findViewById(R.id.btngetdata);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection = connectionclass();
                try {
                    if (connection!=null){
                        String sqlinsert = "Insert into UserInfo_Tab (ID, Name, Address) values ('" + edittextId.getText().toString().trim() + "','" + edittextName.getText().toString().trim() + "','" + edittextAddress.getText().toString().trim() + "')";
                        Statement st = connection.createStatement();
                        ResultSet rs =st.executeQuery(sqlinsert);
                        Toast.makeText(MainActivity.this, "Data insert successfully", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception exception){
                    Log.e("Error",exception.getMessage());
                    Toast.makeText(MainActivity.this, "Faild...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection = connectionclass();
                try {
                    if (connection!=null){
                        String sqlupdate = "update UserInfo_Tab set Name ='"+edittextName.getText().toString() +"',Address ='"+edittextAddress.getText().toString()+"' where ID = '"+edittextId.getText().toString()+"' ";
                        Statement st = connection.createStatement();
                        ResultSet rs =st.executeQuery(sqlupdate);
                        Toast.makeText(MainActivity.this, "Data Update  successfully", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception exception){
                    Toast.makeText(MainActivity.this, "Faild...", Toast.LENGTH_SHORT).show();
                    Log.e("Error",exception.getMessage());
                }
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection = connectionclass();
                try {
                    if (connection!=null){
                        String sqldelete = "delete UserInfo_Tab where ID = '"+edittextId.getText().toString()+"' ";
                        Statement st = connection.createStatement();
                        ResultSet rs =st.executeQuery(sqldelete);
                        Toast.makeText(MainActivity.this, "Data Delete  successfully", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception exception){
                    Toast.makeText(MainActivity.this, "Faild...", Toast.LENGTH_SHORT).show();
                    Log.e("Error",exception.getMessage());
                }
            }
        });
        btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection = connectionclass();
                try {
                    if (connection!=null){
                        String sqlupdatedata = "select * from UserInfo_Tab where ID = '"+edittextId.getText().toString()+"' ";
                        Statement st = connection.createStatement();
                        ResultSet rs =st.executeQuery(sqlupdatedata);

                        while (rs.next()){
                            edittextName.setText(rs.getString(2));
                            edittextAddress.setText(rs.getString(3));
                        }

                        Toast.makeText(MainActivity.this, "Data Fetch  successfully", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception exception){
                    Toast.makeText(MainActivity.this, "Faild...", Toast.LENGTH_SHORT).show();
                    Log.e("Error",exception.getMessage());
                }
            }
        });
    }
    @SuppressLint("NewApi")
    public Connection connectionclass(){
        Connection con = null;
        String ip ="185.214.124.73",port="3306",username="u502039025_wooble97785578",
                password="3k*WnxyCGF",databasename="u502039025_demo_dt";
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try {

//            Class.forName("com.mysql.jdbc.Driver");
//
//            Connection connection = DriverManager.getConnection("jdbc:mysql://185.214.124.73:3306/u502039025_demo_dt", "u502039025_wooble97785578", "3k*WnxyCGF");
//
//            Statement statement = connection.createStatement();

//            ResultSet resultSet = statement.executeQuery("SELECT * FROM test");


            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + databasename + ";User=" + username + ";password=" + password + ";";
            con= DriverManager.getConnection(connectionUrl);
        }catch (Exception exception){
            Log.e("Error",exception.getMessage());
        }
        return  con;
    }
}