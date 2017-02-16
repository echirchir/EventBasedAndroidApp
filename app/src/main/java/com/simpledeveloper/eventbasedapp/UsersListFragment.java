package com.simpledeveloper.eventbasedapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersListFragment extends Fragment {

    public UsersListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users_list, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    /*
    * ButterKnife library for binding views in Android
    * helps you avoid the popular findViewById calls everywhere you need to do that
    * and in fact, does let you handle clicks using this simple annotation
    * Here you simply notify the subscribers by doing a post(EventName);
    * Every Subscriber will  be notified of this event automatically
    * Next time, to stop notifications, you simply remove the subscription method in that class
    * */
    @OnClick(R.id.load_details)
    public void onUserSelected(){
        EventBus.getDefault().post(new NotifyActivityEvent(1, true));
    }

}
