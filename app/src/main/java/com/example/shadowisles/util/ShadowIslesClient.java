package com.example.shadowisles.util;

import android.os.StrictMode;
import android.util.Log;

import com.example.shadowisles.entities.FolderEntity;
import com.example.shadowisles.entities.FriendEntity;
import com.example.shadowisles.entities.LobbyMatchmakingStateEntity;
import com.example.shadowisles.entities.LobbyRequestEntity;
import com.example.shadowisles.entities.LobbyResponseEntity;
import com.example.shadowisles.entities.Queue;
import com.example.shadowisles.entities.QueueEligibility;
import com.example.shadowisles.exception.IPNotFoundException;
import com.example.shadowisles.exception.LobbyNotCreatableException;
import com.example.shadowisles.exception.LockfileNotFoundException;
import com.example.shadowisles.exception.TimeoutException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Response;

import java.lang.reflect.Type;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class ShadowIslesClient extends HTTPClient {

    private static String basePath;

    public static void updateIP(String ip){
        basePath = "http://"+ ip +":17348";
    }

    public static ShadowIslesClient getInstance() {
        return new ShadowIslesClient();
    }

    public LobbyResponseEntity createLobby(LobbyRequestEntity requestEntity) throws LockfileNotFoundException, IPNotFoundException, TimeoutException, LobbyNotCreatableException {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String url = basePath+"/lobby";
            try {
                Gson gson = new GsonBuilder().create();
                Response response = post(url, gson.toJson(requestEntity, LobbyRequestEntity.class));
                if(response.code() == 200) {
                    return gson.fromJson(response.body().string(), LobbyResponseEntity.class);
                } else if (response.code() == 424) {
                    throw new LockfileNotFoundException();
                } else if (response.code() == 500) {
                    throw new LobbyNotCreatableException();
                } else {
                    Log.e("SHADOW ISLES CLIENT", "createLobby: "+response.code()+" "+response.body().string());
                }
            } catch (LockfileNotFoundException e) {
                throw e;
            } catch (LobbyNotCreatableException e){
                throw e;
            } catch (NoRouteToHostException e) {
                throw new IPNotFoundException();
            } catch (SocketTimeoutException e) {
                throw new TimeoutException();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean cancelLobby() throws LockfileNotFoundException, IPNotFoundException, TimeoutException {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String url = basePath+"/lobby";
            try {
                Response response = delete(url);
                if(response.code() == 200) {
                    return true;
                } else if (response.code() == 424) {
                    throw new LockfileNotFoundException();
                } else {
                    Log.e("SHADOW ISLES CLIENT", "deleteLobby: "+response.code()+" "+response.body().string());
                }
            } catch (LockfileNotFoundException e) {
                throw e;
            } catch (NoRouteToHostException e) {
                throw new IPNotFoundException();
            } catch (SocketTimeoutException e) {
                throw new TimeoutException();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public LobbyMatchmakingStateEntity getQueueState() throws LockfileNotFoundException, IPNotFoundException, TimeoutException {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String url = basePath+"/lobby/queue";
            try {
                Response response = get(url);
                if(response.code() == 200) {
                    Gson gson = new GsonBuilder().create();
                    return gson.fromJson(response.body().string(), LobbyMatchmakingStateEntity.class);
                } else if (response.code() == 424) {
                    throw new LockfileNotFoundException();
                } else {
                    Log.e("SHADOW ISLES CLIENT", "getQueueState: "+response.code()+" "+response.body().string());
                }
            } catch (LockfileNotFoundException e) {
                throw e;
            } catch (NoRouteToHostException e) {
                throw new IPNotFoundException();
            } catch (SocketTimeoutException e) {
                throw new TimeoutException();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean startQueue() throws LockfileNotFoundException, IPNotFoundException, TimeoutException {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String url = basePath+"/lobby/queue";
            try {
                Response response = post(url, "");
                if(response.code() == 200) {
                    return true;
                } else if (response.code() == 424) {
                    throw new LockfileNotFoundException();
                } else {
                    Log.e("SHADOW ISLES CLIENT", "startQueue: "+response.code()+" "+response.body().string());
                }
            } catch (LockfileNotFoundException e) {
                throw e;
            } catch (NoRouteToHostException e) {
                throw new IPNotFoundException();
            } catch (SocketTimeoutException e) {
                throw new TimeoutException();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean stopQueue() throws LockfileNotFoundException, IPNotFoundException, TimeoutException {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String url = basePath+"/lobby/queue";
            try {
                Response response = delete(url);
                if(response.code() == 200) {
                    return true;
                } else if (response.code() == 424) {
                    throw new LockfileNotFoundException();
                } else {
                    Log.e("SHADOW ISLES CLIENT", "stopQueue: "+response.code()+" "+response.body().string());
                }
            } catch (LockfileNotFoundException e) {
                throw e;
            } catch (NoRouteToHostException e) {
                throw new IPNotFoundException();
            } catch (SocketTimeoutException e) {
                throw new TimeoutException();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public LobbyResponseEntity getLobby() throws LockfileNotFoundException, IPNotFoundException, TimeoutException {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String url = basePath+"/lobby";
            try {
                Response response = get(url);
                if(response.code() == 200) {
                    Gson gson = new GsonBuilder().create();
                    return gson.fromJson(response.body().string(), LobbyResponseEntity.class);
                } else if (response.code() == 424) {
                    throw new LockfileNotFoundException();
                } else if (response.code() == 404) {
                    return null;
                } else {
                    Log.e("SHADOW ISLES CLIENT", "getLobby: "+response.code()+" "+response.body().string());
                }
            } catch (LockfileNotFoundException e) {
                throw e;
            } catch (NoRouteToHostException e) {
                throw new IPNotFoundException();
            } catch (SocketTimeoutException e) {
                throw new TimeoutException();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<QueueEligibility> getAllQueues() throws LockfileNotFoundException, IPNotFoundException, TimeoutException {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String url = basePath+"/lobby/queues";
            try {
                Response response = get(url);
                if(response.code() == 200) {
                    Gson gson = new GsonBuilder().create();
                    Type listType = new TypeToken<ArrayList<QueueEligibility>>(){}.getType();
                    return gson.fromJson(response.body().string(), listType);
                } else if (response.code() == 424) {
                    throw new LockfileNotFoundException();
                } else {
                    Log.e("SHADOW ISLES CLIENT", "getAvailableQueues: "+response.code()+" "+response.body().string());
                }
            } catch (LockfileNotFoundException e) {
                throw e;
            } catch (NoRouteToHostException e) {
                throw new IPNotFoundException();
            } catch (SocketTimeoutException e) {
                throw new TimeoutException();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<FriendEntity> getFriendList() throws LockfileNotFoundException, IPNotFoundException, TimeoutException {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String url = basePath+"/friendList";
            try {
                Response response = get(url);
                if(response.code() == 200) {
                    Gson gson = new GsonBuilder().create();
                    Type listType = new TypeToken<ArrayList<FriendEntity>>(){}.getType();
                    return gson.fromJson(response.body().string(), listType);
                } else if (response.code() == 424) {
                    throw new LockfileNotFoundException();
                } else {
                    Log.e("SHADOW ISLES CLIENT", "getFriendList: "+response.code()+" "+response.body().string());
                }
            } catch (LockfileNotFoundException e) {
                throw e;
            } catch (NoRouteToHostException e) {
                throw new IPNotFoundException();
            } catch (SocketTimeoutException e) {
                throw new TimeoutException();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<FolderEntity> getFolders() throws LockfileNotFoundException, IPNotFoundException, TimeoutException {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String url = basePath+"/friendList/folders";
            try {
                Response response = get(url);
                if(response.code() == 200) {
                    Gson gson = new GsonBuilder().create();
                    Type listType = new TypeToken<ArrayList<FolderEntity>>(){}.getType();
                    return gson.fromJson(response.body().string(), listType);
                } else if (response.code() == 424) {
                    throw new LockfileNotFoundException();
                } else {
                    Log.e("SHADOW ISLES CLIENT", "getFolders: "+response.code()+" "+response.body().string());
                }
            } catch (LockfileNotFoundException e) {
                throw e;
            } catch (NoRouteToHostException e) {
                throw new IPNotFoundException();
            } catch (SocketTimeoutException e) {
                throw new TimeoutException();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean updateFolder(FolderEntity folder) throws LockfileNotFoundException, IPNotFoundException, TimeoutException {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String url = basePath+"/friendList/folders/"+folder.getId();
            try {
                Gson gson = new GsonBuilder().create();
                Response response = put(url, gson.toJson(folder));
                if(response.code() == 200 || response.code() == 500) {
                    return true;
                } else if (response.code() == 424) {
                    throw new LockfileNotFoundException();
                } else {
                    Log.e("SHADOW ISLES CLIENT", "getFolders: "+response.code()+" "+response.body().string());
                }
            } catch (LockfileNotFoundException e) {
                throw e;
            } catch (NoRouteToHostException e) {
                throw new IPNotFoundException();
            } catch (SocketTimeoutException e) {
                throw new TimeoutException();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    protected int getReadTimeout(){
        return 5;
    }

    @Override
    protected int getConnectTimeout(){
        return 5;
    }
}
