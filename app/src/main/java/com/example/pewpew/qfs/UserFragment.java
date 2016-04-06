package com.example.pewpew.qfs;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pewpew.qfs.domain.User;
import com.example.pewpew.qfs.service.ApiHttpCallback;
import com.example.pewpew.qfs.service.ApiHttpResponse;
import com.example.pewpew.qfs.service.UserService;

import java.util.ArrayList;


public class UserFragment extends Fragment {


    public UserFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View viewRoot = inflater.inflate(R.layout.fragment_user, container, false);

        UserService us = new UserService().getInstance();
        us.index(new ApiHttpCallback<ApiHttpResponse<ArrayList<User>>>() {
            @Override
            public void onCompletion(ApiHttpResponse<ArrayList<User>> response) {
                if (response.getSuccess()) {
                    UserAdapter userAdapter = new UserAdapter(response.getData());
                    RecyclerView userRecycleView = (RecyclerView) viewRoot.findViewById(R.id.userRecyclerView);
                    LinearLayoutManager linearLayoutManagerUser = new LinearLayoutManager(getContext());
                    userRecycleView.setLayoutManager(linearLayoutManagerUser);
                    userRecycleView.setAdapter(userAdapter);
                } else {

                }
            }

            @Override
            public void onError(String message) {
                Log.d("QFS - Error", message);
            }
        });

        return viewRoot;
    }

}
