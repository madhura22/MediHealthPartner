package com.example.cheezy;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Name;
    private EditText Password;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel("MyNotifications", "MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successful";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });


//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotifications")
//                .setContentTitle("This is my title")
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setAutoCancel(true)
//                .setContentText("Your medicine time");
//
//        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
//        manager.notify(999, builder.build());


        Name = (EditText)findViewById(R.id.editText);
        Password = (EditText)findViewById(R.id.editText2);
        Login = (Button)findViewById(R.id.button);
        Login.setOnClickListener(this);




    }
    private void userLogin(){
        String email  = Name.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String email1 = "akshay@gmail.com";
        String pass1 = "akshay123";
         if(email.equals(email1) && password.equals(pass1)){
             Intent i = getPackageManager().getLaunchIntentForPackage("com.tutsplus.dialogflowtutorial");
             Toast.makeText(MainActivity.this, "Complete", Toast.LENGTH_LONG).show();
             //if(i != null){
             MainActivity.this.startActivity(i);
             //}
         }

    }

    @Override
    public void onClick(View v) {
        if(v == Login){
            userLogin();
        }

    }


    //private void validate(String userName, String userPassword){
      //  if ((userName.equals(email1)) && (userPassword.equals(pass1))){
        //    finish();
         //   startActivity(new Intent(this,SecondActivity.class));
       // }
    //}

}
