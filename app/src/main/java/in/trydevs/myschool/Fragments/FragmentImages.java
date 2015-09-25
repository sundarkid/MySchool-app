package in.trydevs.myschool.Fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.trydevs.myschool.Adapters.MyAdapterImages;
import in.trydevs.myschool.DataClasses.Post;
import in.trydevs.myschool.Database.MySQLiteHelper;
import in.trydevs.myschool.R;
import in.trydevs.myschool.extras.MyApplication;
import in.trydevs.myschool.extras.SpacesItemDecoration;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentImages extends Fragment {

    RecyclerView recyclerView;
    View view;
    MyAdapterImages adapterImages;
    private BroadcastReceiver mNewMessageBroadcastReciever;

    public FragmentImages() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_images, container, false);
        initialize();
        return view;
    }

    private void initialize() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapterImages = new MyAdapterImages(getActivity(), MyApplication.getWritableDatabase().getImagetData());
        recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getAppContext()));
        recyclerView.addItemDecoration(new SpacesItemDecoration(5));
        recyclerView.setAdapter(adapterImages);
    }


}
