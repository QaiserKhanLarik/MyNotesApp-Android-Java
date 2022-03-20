package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotesActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private List<Notes> allnotes,allnotes1,allnotes2;
    public NotesAdapter notesAdapter;
    private Dialog builder;
    private View add_view;
    private TextView mytext;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    public static SharedPreferences.Editor editor;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public static String my_view;
    public static SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //for setting pref
        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();



        recyclerView = (RecyclerView) findViewById(R.id.mylist);
        mytext = (TextView)findViewById(R.id.my_text);

        allnotes = Notes.listAll(Notes.class);

        notesAdapter = new NotesAdapter(this,allnotes);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        notesAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    LinearLayout parent = (LinearLayout) v.getParent();
                    TextView text = (TextView) parent.getChildAt(0);
                    TextView date = (TextView) parent.getChildAt(1);
                    TextView id = (TextView) parent.getChildAt(2);
                    TextView theme = (TextView) parent.getChildAt(4);

                    //Toast.makeText(NotesActivity.this, "" + text.getText().toString() + "\n" + date.getText() + "\n" + id.getText(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(NotesActivity.this,ViewNoteActivity.class);
                    String d = id.getText().toString();
                    intent.putExtra("text_id",Integer.parseInt(d));
                    intent.putExtra("note_text",""+text.getText());
                    intent.putExtra("note_theme",""+theme.getText());
                    startActivity(intent);


                }catch (Exception ex){
                    Toast.makeText(NotesActivity.this, ""+ ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        my_view = prefs.getString("view", "No name defined");


        onStart();


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.add:

                builder = new Dialog(this);
                builder.setTitle("Add Note");
                layoutInflater = getLayoutInflater();
                add_view = layoutInflater.inflate(R.layout.add_notes,null);


                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(builder.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                builder.setContentView(add_view);
                builder.getWindow().setAttributes(lp);
                builder.show();

                final Button add = add_view.findViewById(R.id.add_note_bt);

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        final EditText note_text = add_view.findViewById(R.id.note_text);
                        final Spinner note_color = (Spinner)add_view.findViewById(R.id.note_color);
                        final Date d = new Date();
                        final CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());

                        if(note_text.getText().length() != 0) {
                            String color = note_color.getSelectedItem().toString();

                            Notes  notes1 = new Notes(note_text.getText().toString(),s.toString(),""+color);
                            notes1.save();

                            Toast.makeText(NotesActivity.this, "Note saved!", Toast.LENGTH_SHORT).show();
                            builder.dismiss();
                            onStart();


                        }

                        note_text.setText("");

                    }
                });

                break;
            case R.id.about:
                AlertDialog.Builder al = new AlertDialog.Builder(this);
                LayoutInflater layoutInflater1 = getLayoutInflater();
                View about_view = layoutInflater1.inflate(R.layout.about_notes,null);
                al.setView(about_view);
                al.show();

                break;
            case R.id.settings:

                final Dialog mysettings = new Dialog(this);

                WindowManager.LayoutParams lp1 = new WindowManager.LayoutParams();
                lp1.copyFrom(mysettings.getWindow().getAttributes());
                lp1.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp1.height = WindowManager.LayoutParams.WRAP_CONTENT;


                mysettings.setTitle("Settings");
                LayoutInflater layoutInflater3 = getLayoutInflater();
                View settings_view = layoutInflater3.inflate(R.layout.settings_box,null);
                mysettings.setContentView(settings_view);
                mysettings.getWindow().setAttributes(lp1);
                mysettings.show();


                // for views
                final Spinner list_of_views = (Spinner)settings_view.findViewById(R.id.list_ofView);
                final Button change_view_btn = (Button) settings_view.findViewById(R.id.update_view);


                change_view_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String text = list_of_views.getSelectedItem().toString();

                        editor.putString("view",""+text);
                        editor.apply();

                        Toast.makeText(NotesActivity.this, "Restart your app for the change of View!", Toast.LENGTH_LONG).show();

                        mysettings.dismiss();

                    }
                });



                break;
            case R.id.search:
                startActivity(new Intent(NotesActivity.this,SearchActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        allnotes.clear();

        allnotes1 = Notes.listAll(Notes.class);


        if(my_view.equals("Grid View")){


            NotesAdapter notesAdapter = new NotesAdapter(this,allnotes1);
            gridLayoutManager =  new GridLayoutManager(NotesActivity.this,2);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(notesAdapter);
            notesAdapter.notifyDataSetChanged();
            recyclerView.invalidate();

        }else if(my_view.equals("List View")){

            NotesAdapter notesAdapter = new NotesAdapter(this,allnotes1);
            recyclerView.setAdapter(notesAdapter);
            notesAdapter.notifyDataSetChanged();
            recyclerView.invalidate();


        }else{

            NotesAdapter notesAdapter = new NotesAdapter(this,allnotes1);
            recyclerView.setAdapter(notesAdapter);
            notesAdapter.notifyDataSetChanged();
            recyclerView.invalidate();

        }

        if(allnotes1.size() > 0){
            mytext.setVisibility(View.GONE);
        }
    }
}
