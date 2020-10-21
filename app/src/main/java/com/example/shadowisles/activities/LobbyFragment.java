package com.example.shadowisles.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.shadowisles.R;
import com.example.shadowisles.adapter.LobbyInvitationAdapter;
import com.example.shadowisles.adapter.LobbyMemberAdapter;
import com.example.shadowisles.entities.LobbyMatchmakingStateEntity;
import com.example.shadowisles.entities.LobbyResponseEntity;
import com.example.shadowisles.entities.Queue;
import com.example.shadowisles.exception.IPNotFoundException;
import com.example.shadowisles.exception.LockfileNotFoundException;
import com.example.shadowisles.exception.TimeoutException;
import com.example.shadowisles.util.ShadowIslesClient;
import com.example.shadowisles.util.Util;

public class LobbyFragment extends Fragment {

    private ConstraintLayout lobbyLayout;
    private RelativeLayout noLobbyLayout;
    private RelativeLayout fatalErrorLayout;
    private TextView errorMessage;
    private SwipeRefreshLayout refreshLayout;
    private ListView playerList;
    private ListView invitationList;

    private TextView gameMode;
    private TextView queueState;
    private TextView players;

    private Button startQueue;
    private Button stopQueue;
    private Button createLobby;
    private Button cancelLobby;

    private ShadowIslesClient shadowIslesClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lobby, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lobbyLayout = view.findViewById(R.id.lobbyLayout);
        noLobbyLayout = view.findViewById(R.id.noLobbyLayout);
        fatalErrorLayout = view.findViewById(R.id.fatalError);
        errorMessage = view.findViewById(R.id.errorMessage);
        refreshLayout = view.findViewById(R.id.swipeRefresh);
        playerList = view.findViewById(R.id.playerList);
        invitationList = view.findViewById(R.id.invitationList);

        gameMode = view.findViewById(R.id.gameMode);
        queueState = view.findViewById(R.id.queueState);
        players = view.findViewById(R.id.players);
        startQueue = view.findViewById(R.id.startQueue);
        stopQueue = view.findViewById(R.id.stopQueue);
        createLobby = view.findViewById(R.id.createLobby);
        cancelLobby = view.findViewById(R.id.cancelLobby);

        shadowIslesClient = ShadowIslesClient.getInstance();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                refreshLayout.setRefreshing(false);
            }
        });

        createLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), NewLobbyActivity.class);
                startActivity(i);
            }
        });

        startQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    shadowIslesClient.startQueue();
                } catch (LockfileNotFoundException | TimeoutException | IPNotFoundException e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        stopQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    shadowIslesClient.stopQueue();
                } catch (LockfileNotFoundException | TimeoutException | IPNotFoundException e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    shadowIslesClient.cancelLobby();
                } catch (LockfileNotFoundException | TimeoutException | IPNotFoundException e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void refresh(){
        try {
            final LobbyResponseEntity lobby = shadowIslesClient.getLobby();
            if(lobby != null){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            resetLayout();
                            players.setText(getString(R.string.lobby_size, lobby.getMembers().size(), lobby.getGameConfig().getMaxTeamSize()));
                            playerList.setAdapter(new LobbyMemberAdapter(getActivity(), lobby.getMembers()));
                            invitationList.setAdapter(new LobbyInvitationAdapter(getActivity(), lobby.getInvitations()));
                            cancelLobby.setEnabled(true);
                            Queue currentQueue = Util.getQueue(Util.availableQueues, lobby.getGameConfig().getQueueId());
                            if (currentQueue != null) {
                                gameMode.setText(currentQueue.toString());
                            } else {
                                gameMode.setText(R.string.custom_game);
                            }
                            LobbyMatchmakingStateEntity lobbyState = shadowIslesClient.getQueueState();
                            if (lobbyState != null) {
                                switch (lobbyState.getSearchState()) {
                                    case Searching: {
                                        queueState.setTextColor(getResources().getColor(R.color.warning));
                                        stopQueue.setEnabled(true);
                                        break;
                                    }
                                    case Found: {
                                        queueState.setTextColor(getResources().getColor(R.color.green));
                                        break;
                                    }
                                    default: {
                                        queueState.setTextColor(getResources().getColor(androidx.appcompat.R.color.abc_secondary_text_material_light));
                                        createLobby.setEnabled(true);
                                        break;
                                    }
                                }
                                queueState.setText(lobbyState.getSearchState().toString());
                            }
                            lobbyLayout.setVisibility(View.VISIBLE);
                            if (lobby.getLocalMember().isAllowedStartActivity()) {
                                createLobby.setEnabled(true);
                                startQueue.setEnabled(true);
                            }
                        } catch (LockfileNotFoundException | TimeoutException | IPNotFoundException e) {
                            fatalErrorLayout.setVisibility(View.VISIBLE);
                            errorMessage.setText(e.getMessage());
                        }
                    }
                });
            } else {
                getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            resetLayout();
                            createLobby.setEnabled(true);
                            noLobbyLayout.setVisibility(View.VISIBLE);
                        }
                    }
                );
            }
        } catch (LockfileNotFoundException | TimeoutException | IPNotFoundException e) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    resetLayout();
                    fatalErrorLayout.setVisibility(View.VISIBLE);
                    errorMessage.setText(e.getMessage());
                }
            });
        }
    }

    private void resetLayout(){
        lobbyLayout.setVisibility(View.GONE);
        noLobbyLayout.setVisibility(View.GONE);
        fatalErrorLayout.setVisibility(View.GONE);
        startQueue.setEnabled(false);
        stopQueue.setEnabled(false);
        cancelLobby.setEnabled(false);
        createLobby.setEnabled(false);
    }
}
