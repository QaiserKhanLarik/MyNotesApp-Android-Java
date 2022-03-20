package com.example.mynotes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ViewNoteActivity extends AppCompatActivity {

    private ImageButton goback;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.my_navigation);



       // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                        .setAction("Action", null).show();



        goback = (ImageButton)findViewById(R.id.goback);


        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView note_text = (TextView)findViewById(R.id.myNote_Text);
        TextView titleText = (TextView)findViewById(R.id.title_activity);


        Intent intent = getIntent();
        final long id = intent.getIntExtra("text_id",0);
        String n_t = intent.getStringExtra("note_text");
        final String  mytheme = intent.getStringExtra("note_theme");

        if(mytheme.equals("yellow")){

            toolbar.setBackgroundResource(R.color.colorDarkYellow);
            goback.setBackgroundResource(R.color.colorYellow);
            titleText.setTextColor(Color.BLACK);

        }

        else if(mytheme.equals("black")){

            toolbar.setBackgroundResource(R.color.colorBlack1);
            goback.setImageResource(R.drawable.ic_white_back_arrow);
            titleText.setTextColor(Color.WHITE);

        }

        else if(mytheme.equals("purple")){

            toolbar.setBackgroundResource(R.color.colorDarkPurple);
            goback.setBackgroundResource(R.color.colorDarkPurple);
            goback.setImageResource(R.drawable.ic_white_back_arrow);
            titleText.setTextColor(Color.WHITE);

        }

        else if(mytheme.equals("red")){

            toolbar.setBackgroundResource(R.color.colorRed);
            goback.setImageResource(R.drawable.ic_white_back_arrow);
            titleText.setTextColor(Color.WHITE);

        }




        //Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();

        final Notes notes = Notes.findById(Notes.class, id);


        note_text.setText(notes.getNote_text());
        titleText.setText(n_t);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.delete:


                        AlertDialog.Builder builder = new AlertDialog.Builder(ViewNoteActivity.this);
                        builder.setTitle("DELETE NOTE");
                        builder.setMessage("Are you sure? Want to delete this note perminently?")
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int ida) {

                                        Notes delete = Notes.findById(Notes.class, id);
                                        delete.delete();

                                        Toast.makeText(ViewNoteActivity.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                                        finish();


                                    }
                                })
                                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        builder.show();




                        break;
                    case R.id.edit:


                        AlertDialog.Builder al = new AlertDialog.Builder(ViewNoteActivity.this);

                        LayoutInflater layoutInflater2 = getLayoutInflater();





                        View edit_view = layoutInflater2.inflate(R.layout.update_notes,null);
                        final EditText note_text = edit_view.findViewById(R.id.edit_n_text);
                        final Button update_note = edit_view.findViewById(R.id.update_note);


                        note_text.setText(notes.getNote_text());

                        al.setView(edit_view);
                        al.show();



                        update_note.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Notes up = Notes.findById(Notes.class, id);
                                up.setNote_text(note_text.getText().toString());
                                up.save();
                                Toast.makeText(ViewNoteActivity.this, "Updated successfully!", Toast.LENGTH_SHORT).show();


                                finish();

                            }
                        });




                        //Toast.makeText(ViewNoteActivity.this, "Edited!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.hide:
                        Animation animation = AnimationUtils.loadAnimation(ViewNoteActivity.this, R.anim.top_to_bottom);
                        bottomNavigationView.startAnimation(animation);
                        //bottomNavigationView.setAnimation(R.anim.top_to_bottom);
                        //bottomNavigationView.setVisibility(View.GONE);
                        break;
                }


                return false;
            }
        });


    }


}
