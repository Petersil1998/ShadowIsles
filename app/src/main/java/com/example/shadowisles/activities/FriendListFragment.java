package com.example.shadowisles.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shadowisles.R;
import com.example.shadowisles.adapter.FriendAdapter;
import com.example.shadowisles.adapter.LobbyInvitationAdapter;
import com.example.shadowisles.adapter.LobbyMemberAdapter;
import com.example.shadowisles.entities.FolderEntity;
import com.example.shadowisles.entities.FriendEntity;
import com.example.shadowisles.entities.LobbyMatchmakingStateEntity;
import com.example.shadowisles.entities.LobbyResponseEntity;
import com.example.shadowisles.entities.Queue;
import com.example.shadowisles.exception.IPNotFoundException;
import com.example.shadowisles.exception.LockfileNotFoundException;
import com.example.shadowisles.exception.TimeoutException;
import com.example.shadowisles.util.ShadowIslesClient;

import java.util.List;

public class FriendListFragment extends Fragment {

    private ListView friendList;
    private TextView fatalError;

    private ShadowIslesClient shadowIslesClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friendlist, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        friendList = view.findViewById(R.id.friendList);
        fatalError = view.findViewById(R.id.fatalError);
        shadowIslesClient = ShadowIslesClient.getInstance();
    }

    public void refresh(){
        try {
            final List<FriendEntity> friendList = shadowIslesClient.getFriendList();
            final List<FolderEntity> folders = shadowIslesClient.getFolders();
            if(friendList != null){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //try {
                        FriendListFragment.this.friendList.setVisibility(View.VISIBLE);
                        if(FriendListFragment.this.friendList.getAdapter()==null) {
                            FriendListFragment.this.friendList.setAdapter(new FriendAdapter(getActivity(), friendList, folders));
                        } else {
                            FriendAdapter adapter = (FriendAdapter) FriendListFragment.this.friendList.getAdapter();
                            adapter.update(friendList, folders);
                        }
                        /*} catch (LockfileNotFoundException | TimeoutException | IPNotFoundException e) {
                            fatalError.setVisibility(View.VISIBLE);
                            fatalError.setText(e.getMessage());
                        }*/
                    }
                });
            } else {
                getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            resetLayout();
                        }
                    }
                );
            }
        } catch (LockfileNotFoundException | TimeoutException | IPNotFoundException e) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    resetLayout();
                    fatalError.setVisibility(View.VISIBLE);
                    fatalError.setText(e.getMessage());
                }
            });
        }
    }

    private void resetLayout(){
        fatalError.setVisibility(View.INVISIBLE);
        friendList.setVisibility(View.GONE);
    }
}
