package com.droiddevil.fireweather;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

public class SimpleChildEventListener implements ChildEventListener {

    @Override
    public void onChildRemoved(DataSnapshot snapshot) {
        // no-op
    }

    @Override
    public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
        // no-op
    }

    @Override
    public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
        // no-op
    }

    @Override
    public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
        // no-op
    }

    @Override
    public void onCancelled(FirebaseError e) {
        // no-op
    }

}
