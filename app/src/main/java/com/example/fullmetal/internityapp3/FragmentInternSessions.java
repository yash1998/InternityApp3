package com.example.fullmetal.internityapp3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentInternSessions extends Fragment {
    DatabaseReference myRefIntern;
    private static RecyclerView.Adapter adapterIntern;
    private RecyclerView.LayoutManager layoutManagerIntern;
    private static RecyclerView recyclerViewIntern;
    private static ArrayList<Sessions> dataIntern;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intern_sessions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myRefIntern = FirebaseDatabase.getInstance().getReference("sessions");
        recyclerViewIntern = view.findViewById(R.id.recycler_view_intern);
        recyclerViewIntern.setHasFixedSize(true);
        recyclerViewIntern.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewIntern.setItemAnimator(new DefaultItemAnimator());
        dataIntern = new ArrayList<Sessions>();

        myRefIntern.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapshot: dataSnapshot.getChildren()){
                    dataIntern.add(new Sessions(childSnapshot.child("title").getValue().toString(),
                            childSnapshot.child("details").getValue().toString(),
                            childSnapshot.child("date").getValue().toString(),
                            childSnapshot.child("time").getValue().toString()));
                }
                adapterIntern = new SessionsRecyclerViewAdapter(dataIntern);
                recyclerViewIntern.setAdapter(adapterIntern);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
