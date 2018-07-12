package com.example.fullmetal.internityapp3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentCoachSessions extends Fragment {
    DatabaseReference myRefCoach;
    private static RecyclerView.Adapter adapterCoach;
    private RecyclerView.LayoutManager layoutManagerCoach;
    private static RecyclerView recyclerViewCoach;
    private static ArrayList<Sessions> dataCoach;
    String m_Text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coach_sessions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myRefCoach = FirebaseDatabase.getInstance().getReference("sessions");
        recyclerViewCoach = view.findViewById(R.id.recycler_view_coach);
        recyclerViewCoach.setHasFixedSize(true);
        recyclerViewCoach.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewCoach.setItemAnimator(new DefaultItemAnimator());
        dataCoach = new ArrayList<Sessions>();

        myRefCoach.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    dataCoach.add(new Sessions(childSnapshot.child("title").getValue().toString(),
                            childSnapshot.child("details").getValue().toString(),
                            childSnapshot.child("date").getValue().toString(),
                            childSnapshot.child("time").getValue().toString()));
                }
                adapterCoach = new SessionsRecyclerViewAdapter(dataCoach);
                recyclerViewCoach.setAdapter(adapterCoach);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void add_session(View v) {


    }
}
