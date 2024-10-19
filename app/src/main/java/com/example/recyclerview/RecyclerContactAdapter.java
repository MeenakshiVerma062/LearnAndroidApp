package com.example.recyclerview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {

     Context context;
     ArrayList<ContactModel> arrContacts;
     RecyclerContactAdapter(Context context, ArrayList<ContactModel> arrContacts){
         this.context = context;
         this.arrContacts = arrContacts;
     }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( @NonNull ViewHolder holder, int position) {
         ContactModel model = (ContactModel) arrContacts.get(position);
         holder.imgContact.setImageResource(model.img);
         holder.txtName.setText(model.name);
         holder.txtNumber.setText(model.number);
         final int currentPosition = position;
         holder.llRow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Dialog dialog = new Dialog(context);

                 dialog.setContentView(R.layout.add_update_lay);

                 EditText edtName = dialog.findViewById(R.id.edtName);
                 EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                 Button btnAction = dialog.findViewById(R.id.btnAction);
                 TextView txtTitle = dialog.findViewById(R.id.txtTitle);

                 txtTitle.setText("Update Contact");
                  btnAction.setText("Update");
                  edtName.setText(arrContacts.get(currentPosition).name);
                  edtNumber.setText(arrContacts.get(currentPosition).number);
                 btnAction.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         String name = edtName.getText().toString().trim();
                         String number = edtNumber.getText().toString().trim();
                         if (name.isEmpty()) {
                             Toast.makeText(context, "Please Enter Contact name!", Toast.LENGTH_SHORT).show();
                             return;
                         }
                         if (number.isEmpty()) {
                             Toast.makeText(context, "Please Enter Contact Number", Toast.LENGTH_SHORT).show();
                             return;
                         }

                         // Update the contact at the current position
                         arrContacts.set(currentPosition, new ContactModel(arrContacts.get(currentPosition).img,name, number));
                         notifyItemChanged(currentPosition);
                         dialog.dismiss();
                     }
                 });
                 dialog.show();

             }
         });
         holder.llRow.setOnLongClickListener(new View.OnLongClickListener(){
             @Override
             public boolean onLongClick(View v){

                 AlertDialog.Builder builder = new AlertDialog.Builder(context)
                         .setTitle("Delete Contact")
                         .setMessage("Are you sure want to delete?")
                         .setIcon(R.drawable.baseline_delete_24)
                         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                arrContacts.remove(currentPosition);
                                notifyItemRemoved(currentPosition);
                             }
                         })
                         .setNegativeButton("No", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {

                             }
                         });
                 builder.show();
                 return true;
             }
         });
    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

         TextView txtName, txtNumber;
         ImageView imgContact;
         LinearLayout llRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtNumber = itemView.findViewById(R.id.txtNumber);
            imgContact = itemView.findViewById(R.id.imgContact);
            llRow = itemView.findViewById(R.id.llRow);
        }
    }

}
