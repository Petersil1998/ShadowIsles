package com.example.shadowisles.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;

import com.example.shadowisles.R;
import com.example.shadowisles.entities.FolderEntity;
import com.example.shadowisles.entities.FriendEntity;
import com.example.shadowisles.exception.IPNotFoundException;
import com.example.shadowisles.exception.LockfileNotFoundException;
import com.example.shadowisles.exception.TimeoutException;
import com.example.shadowisles.util.ShadowIslesClient;
import com.example.shadowisles.util.Util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FriendAdapter extends BaseAdapter {

    private Context context;
    private List<FriendEntity> friends;
    private List<FolderEntity> folders;
    private LayoutInflater inflater;
    private ShadowIslesClient shadowIslesClient;

    public FriendAdapter(Context context, List<FriendEntity> friends, List<FolderEntity> folders) {
        this.context = context;
        this.friends = friends;
        this.folders = folders;
        sortFriends();
        sortFolders();
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        shadowIslesClient = ShadowIslesClient.getInstance();
    }

    @Override
    public int getCount() {
        return folders.size();
    }

    @Override
    public Object getItem(int position) {
        return folders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void update(List<FriendEntity> friends, List<FolderEntity> folders) {
        this.friends = friends;
        this.folders = folders;
        sortFriends();
        sortFolders();
        notifyDataSetChanged();
    }

    private void sortFriends(){
        Collections.sort(friends, new Comparator<FriendEntity>() {
            @Override
            public int compare(FriendEntity friend, FriendEntity friend2) {
             return friend.getName().compareTo(friend2.getName());
            }
        });
    }

    private void sortFolders(){
        Collections.sort(folders, new Comparator<FolderEntity>() {
            @Override
            public int compare(FolderEntity folder, FolderEntity folder2) {
                if(folder.getPriority() < folder2.getPriority()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.friendlist_row, null);

        final TextView folderName = vi.findViewById(R.id.folderName);
        folderName.setText(folders.get(position).getName());

        final LinearLayout chatLayout = vi.findViewById(R.id.friendsLayout);
        chatLayout.removeAllViews();
        final FolderEntity currentFolder = folders.get(position);

        folderName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFolder.setCollapsed(!currentFolder.isCollapsed());
                try {
                    shadowIslesClient.updateFolder(currentFolder);
                    collapseFolder(currentFolder, chatLayout);
                } catch (TimeoutException | LockfileNotFoundException | IPNotFoundException e) {
                    Toast.makeText(context, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        List<FriendEntity> friendsInFolder = Util.getFriendsInFolder(friends, folders.get(position).getId());
        for(final FriendEntity friendEntity: friendsInFolder) {
            final TextView messageView = new TextView(context);
            messageView.setText(friendEntity.getName());
            messageView.setId(View.generateViewId());
            messageView.setTextSize(20);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMarginStart(50);
            messageView.setLayoutParams(layoutParams);
            chatLayout.addView(messageView);
            chatLayout.invalidate();
            messageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, friendEntity.getName(), Toast.LENGTH_SHORT).show();
                }
            });

            messageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu popup = new PopupMenu(FriendAdapter.this.context, messageView, Gravity.CENTER);
                    popup.getMenuInflater()
                            .inflate(R.menu.friend_popup_menu, popup.getMenu());

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            return true;
                        }
                    });

                    popup.show();
                    return true;
                }
            });
        }

        collapseFolder(currentFolder, chatLayout);

        return vi;
    }

    private void collapseFolder(FolderEntity folder, LinearLayout chatLayout){
        if(folder.isCollapsed()) {
            chatLayout.setVisibility(View.GONE);
        } else {
            chatLayout.setVisibility(View.VISIBLE);
        }
    }
}