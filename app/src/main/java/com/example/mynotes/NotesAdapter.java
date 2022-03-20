package com.example.mynotes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class  NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Notes> mData;
    private Notes notes;
    private LayoutInflater mInflater;
    private View view;
    public Context context;
    public static  View.OnClickListener listener;


    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public NotesAdapter(){}

    public NotesAdapter(Context context, List<Notes> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.notes_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        notes = mData.get(position);


        if (notes.getNote_text().length() > 20 && notes.getNote_text().length() != 0) {
            holder.note_text.setText(notes.getNote_text().substring(0, 15)+"...");
        }else{
            holder.note_text.setText(notes.getNote_text());
        }

        if(notes.getNote_theme().equals("yellow")){

            holder.all_note_list.setBackgroundResource(R.drawable.yellow_color);
            holder.all_note_list.setPadding(40,20,20,20);
            holder.note_text.setTextColor(Color.BLACK);

        }
        else  if(notes.getNote_theme().equals("red")){

            holder.all_note_list.setBackgroundResource(R.drawable.red_color);
            holder.all_note_list.setPadding(40,20,20,20);
            holder.note_text.setTextColor(Color.WHITE);
            holder.note_date.setTextColor(Color.WHITE);

        }

        else  if(notes.getNote_theme().equals("purple")){

            holder.all_note_list.setBackgroundResource(R.drawable.color_purple);
            holder.all_note_list.setPadding(40,20,20,20);
            holder.note_text.setTextColor(Color.WHITE);
            holder.note_date.setTextColor(Color.WHITE);

        }
        else  if(notes.getNote_theme().equals("black")){

            holder.all_note_list.setBackgroundResource(R.drawable.color_black);
            holder.all_note_list.setPadding(40,20,20,20);
            holder.note_text.setTextColor(Color.WHITE);
            holder.note_date.setTextColor(Color.WHITE);

        }


        holder.note_date.setText(notes.getNote_date());
        holder.ids.setText(""+notes.getId());
        holder.themes.setText(""+notes.getNote_theme());


        holder.more_btn.setOnClickListener(listener);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView note_text, note_date, ids, themes;
        ImageButton more_btn;
        LinearLayout all_note_list;


        ViewHolder(View itemView) {
            super(itemView);
            note_text = itemView.findViewById(R.id.note_text);
            note_date = itemView.findViewById(R.id.note_date);
            ids = itemView.findViewById(R.id.ids);
            themes = itemView.findViewById(R.id.themes);
            more_btn = itemView.findViewById(R.id.more);

            all_note_list = itemView.findViewById(R.id.all_note_list);

            ids.setVisibility(View.GONE);
            themes.setVisibility(View.GONE);

        }


    }

}