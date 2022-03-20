package com.example.mynotes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class  SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<Notes> mData;
    private Notes notes;
    private LayoutInflater mInflater;
    private View view;
    public Context context;
    public View.OnClickListener listener2;

    public void setListener2(View.OnClickListener listener2) {
        this.listener2 = listener2;
    }

    public SearchAdapter(){}

    public SearchAdapter(Context context, List<Notes> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.search_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        notes = mData.get(position);


        if (notes.getNote_text().length() > 20 && notes.getNote_text().length() != 0) {
            holder.s_text.setText(notes.getNote_text().substring(0, 15)+"...");
        }else{
            holder.s_text.setText(notes.getNote_text());
        }

        holder.my_searches_date.setText(notes.getNote_date());
        holder.n_ids.setText(""+notes.getId());
        holder.n_themes.setText(""+notes.getNote_theme());


        if(notes.getNote_theme().equals("yellow")){

            holder.search_layout_1.setBackgroundResource(R.drawable.yellow_color);
            holder.search_layout_1.setPadding(40,20,20,20);
            holder.s_text.setTextColor(Color.BLACK);

        }
        else  if(notes.getNote_theme().equals("red")){

            holder.search_layout_1.setBackgroundResource(R.drawable.red_color);
            holder.search_layout_1.setPadding(40,20,20,20);
            holder.s_text.setTextColor(Color.WHITE);
            holder.my_searches_date.setTextColor(Color.WHITE);

        }

        else  if(notes.getNote_theme().equals("purple")){

            holder.search_layout_1.setBackgroundResource(R.drawable.color_purple);
            holder.search_layout_1.setPadding(40,20,20,20);
            holder.s_text.setTextColor(Color.WHITE);
            holder.my_searches_date.setTextColor(Color.WHITE);

        }
        else  if(notes.getNote_theme().equals("black")){

            holder.search_layout_1.setBackgroundResource(R.drawable.color_black);
            holder.search_layout_1.setPadding(40,20,20,20);
            holder.s_text.setTextColor(Color.WHITE);
            holder.my_searches_date.setTextColor(Color.WHITE);

        }


        holder.open_note_text.setOnClickListener(listener2);

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView s_text, my_searches_date, n_ids, n_themes;
        ImageButton open_note_text;
        RelativeLayout search_layout_1;

        ViewHolder(View itemView) {
            super(itemView);
            s_text = itemView.findViewById(R.id.my_searches);
            my_searches_date = itemView.findViewById(R.id.my_searches_date);
            search_layout_1 = itemView.findViewById(R.id.search_layout_1);
            open_note_text = itemView.findViewById(R.id.open_note_text);
            n_ids = itemView.findViewById(R.id.n_ids);
            n_themes = itemView.findViewById(R.id.n_themes);


            n_themes.setVisibility(View.GONE);
            n_ids.setVisibility(View.GONE);

        }


    }

}