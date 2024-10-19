package com.example.recyclerview;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

      RecyclerContactAdapter adapter;
    RecyclerView recyclerView;
     FloatingActionButton btnOpenDialog;
    ArrayList<ContactModel> arrContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        RecyclerView recyclerView = findViewById(R.id.recyclerContact);
//           recyclerView.setLayoutManager(new LinearLayoutManager(this));



           recyclerView = findViewById(R.id.recyclerContact);
           btnOpenDialog = findViewById(R.id.btnOpenDialog);

           btnOpenDialog.setOnClickListener(new View.OnClickListener(){
               @Override
               public void onClick(View v) {

                   Dialog dialog = new Dialog(MainActivity.this);
                   dialog.setContentView(R.layout.add_update_lay);
                   EditText edtName = dialog.findViewById(R.id.edtName);
                   EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                   Button btnAction = dialog.findViewById(R.id.btnAction);

                   btnAction.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           String name = "",number= "";
                           if(!edtName.getText().toString().equals("")){
                                name = edtName.getText().toString();
                       }else{

                               Toast.makeText(MainActivity.this, "Please Enter Contact name!", Toast.LENGTH_SHORT).show();
                       }
                           if(!edtNumber.getText().toString().equals("")){
                               number = edtNumber.getText().toString();
                       }else{
                               Toast.makeText(MainActivity.this,"Please Enter Contact Number", Toast.LENGTH_SHORT).show();
                           }

                           arrContacts.add(new ContactModel( R.mipmap.mark,name, number));

                           adapter.notifyItemInserted(arrContacts.size()-1);

                           recyclerView.scrollToPosition(arrContacts.size()-1);
                           dialog.dismiss();
                       }
                   });

                   dialog.show();
               }
           });

           arrContacts.add(new ContactModel(R.mipmap.contact,"A","94847583482"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"A","9794784956"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"B","9794787395"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"C","9793384168"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"D","29495787168"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"E","293587168"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"F","94949587168"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"G","979478484"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"H","34555168"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"I","979478405958"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"J","40506787168"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"K","979444168"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"L","4564787168"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"M","455687168"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"N","495895686"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"O","9445665566"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"P","30596702"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"Q","203959786"));
        arrContacts.add(new ContactModel(R.mipmap.contact,"R","2984868482"));


      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      adapter = new RecyclerContactAdapter(this,arrContacts);
       recyclerView.setAdapter(adapter);


    }
}