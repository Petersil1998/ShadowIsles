package com.example.shadowisles.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shadowisles.R;
import com.example.shadowisles.entities.LobbyResponseEntity;
import com.example.shadowisles.util.LeagueUtil;

import java.io.File;
import java.net.URL;
import java.util.List;

public class LobbyMemberAdapter extends BaseAdapter {

    Context context;
    List<LobbyResponseEntity.Member> data;
    private static LayoutInflater inflater = null;

    public LobbyMemberAdapter(Context context, List<LobbyResponseEntity.Member> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.lobby_member_row, null);

        TextView summonerName = vi.findViewById(R.id.summonerName);
        summonerName.setText(data.get(position).getSummonerName());
        if(data.get(position).isAllowedStartActivity()){
            summonerName.setTypeface(summonerName.getTypeface(), Typeface.BOLD);
        }

        TextView summonerLevel = vi.findViewById(R.id.summonerLevel);
        summonerLevel.setText(String.valueOf(data.get(position).getSummonerLevel()));
        summonerLevel.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        return vi;
    }
}
