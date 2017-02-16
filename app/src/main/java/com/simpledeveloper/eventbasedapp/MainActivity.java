package com.simpledeveloper.eventbasedapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* set initial fragment of list items */
        setFragment(new UsersListFragment(), "users");
        setTitle("Users List");
    }

    /* EventBus is a cool little library for handling events
    *  this facilitates the famous -- or is it? Publisher/Subscriber design pattern
    *  which mainly decouples your code and makes it easy to manage
    *  large projects - through the idea that you should not have your fragments depend on
    *  each other too much; now whenever you one to change this code later down the road,
    *  you can go to your fragment or activity, and un-subscribe from the event
    *  instead of changing a large chunk of the files
    *  EventBus provides this @Subscribe annotation to listen for events once you register in onStart
    *  and unregister inside onStop()
    * */
    @Subscribe
    public void onEvent(NotifyActivityEvent event){
        if (event.isActionTriggered()){
            UserDetailsFragment fragment = new UserDetailsFragment();
            Bundle bundle = new Bundle();

            bundle.putLong("id", event.getId());

            fragment.setArguments(bundle);
            setFragment(fragment, "detailsFrag");
            setTitle("User Details");
        }
    }

    /*
    * This is a simple utility method to set any fragment on the activity
    * Remember a fragment lives in an activity - which manages it is lifestyle
    * So you can set and unset or aka remove or replace
    * But most often, let the activity do the least possible
    * while the fragment should do most of the load
    * */
    private void setFragment(Fragment frag, String tag){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment fragment = getFragmentManager().findFragmentById(R.id.container);

        if(fragment == null) {
            ft.add(R.id.container, frag, tag);
        } else {
            ft.replace(R.id.container, frag, tag);
        }

        ft.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
