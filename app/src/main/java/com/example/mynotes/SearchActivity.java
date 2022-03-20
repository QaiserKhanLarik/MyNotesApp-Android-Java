package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {


    private EditText search;
    private List<Notes> notes;
    private RecyclerView search_list;
    private TextView note_found;
    private ImageButton clear_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final ImageButton goback = (ImageButton)findViewById(R.id.go_back_s);
        search_list = (RecyclerView)findViewById(R.id.my_search_list);
        search = (EditText) findViewById(R.id.search);
        note_found = (TextView) findViewById(R.id.not_found);


        note_found.setVisibility(View.GONE);




        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


           /*     String query = "SELECT * FROM Notes WHERE note_text like '%" + search.getText().toString() + "%' ";
                List<Notes> search_h = Notes.findWithQuery(Notes.class, query);

                final ListView data = (ListView)findViewById(R.id.mylistview);
                String[] array = {"Asdfasdf","ASDfasdf","Asdfasfda"};
                ArrayAdapter arrayAdapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_spinner_item, array);

                //for (int i=0; i< search_h.size(); i++)
                Toast.makeText(SearchActivity.this, ""+search_h.size(), Toast.LENGTH_SHORT).show();
*/


                String query = "Select * from Notes where NOTETEXT like '%" + search.getText() + "%'";
                notes = Notes.findWithQuery(Notes.class, query);

                if(notes.size() <= 0){
                    note_found.setVisibility(View.VISIBLE);

                }else {
                    note_found.setVisibility(View.GONE);
                    //List<Notes> notes1 = Notes.listAll(Notes.class);
                    SearchAdapter searchAdapter = new SearchAdapter(SearchActivity.this, notes);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
                    search_list.setLayoutManager(linearLayoutManager);
                    search_list.setAdapter(searchAdapter);


                    searchAdapter.setListener2(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            RelativeLayout parent = (RelativeLayout) v.getParent();
                            TextView text = (TextView) parent.getChildAt(1);
                            TextView date = (TextView) parent.getChildAt(2);
                            TextView id = (TextView) parent.getChildAt(4);
                            TextView theme = (TextView) parent.getChildAt(5);


                            Intent intent = new Intent(SearchActivity.this,ViewNoteActivity.class);
                            String d = id.getText().toString();
                            intent.putExtra("text_id",Integer.parseInt(d));
                            intent.putExtra("note_text",""+text.getText());
                            intent.putExtra("note_theme",""+theme.getText());
                            startActivity(intent);


                            //Toast.makeText(SearchActivity.this, "asdfasdf", Toast.LENGTH_SHORT).show();
                        }
                    });


                }




            }
        });


        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
