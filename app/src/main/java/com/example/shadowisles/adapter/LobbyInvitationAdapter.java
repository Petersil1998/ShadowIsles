package com.example.shadowisles.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shadowisles.R;
import com.example.shadowisles.entities.LobbyResponseEntity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LobbyInvitationAdapter extends BaseAdapter {

    Context context;
    List<LobbyResponseEntity.Invitation> data;
    private static LayoutInflater inflater = null;

    public LobbyInvitationAdapter(Context context, List<LobbyResponseEntity.Invitation> data) {
        this.context = context;
        Collections.sort(data, new Comparator<LobbyResponseEntity.Invitation>() {
            @Override
            public int compare(LobbyResponseEntity.Invitation invitation, LobbyResponseEntity.Invitation invitation2) {
                int compareVal = invitation.getState().toString().compareTo(invitation2.getState().toString());
                if(compareVal == 0) {
                    return invitation.getToSummonerName().compareTo(invitation2.getToSummonerName());
                }
                return compareVal;
            }
        });
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
            vi = inflater.inflate(R.layout.lobby_invitation_row, null);
        TextView invitedSummoner = vi.findViewById(R.id.invitedSummoner);
        invitedSummoner.setText(data.get(position).getToSummonerName());

        TextView summonerLevel = vi.findViewById(R.id.invitationState);
        summonerLevel.setText(String.valueOf(data.get(position).getState()));
        summonerLevel.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        return vi;
    }
}
