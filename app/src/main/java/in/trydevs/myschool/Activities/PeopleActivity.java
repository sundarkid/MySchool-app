package in.trydevs.myschool.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import in.trydevs.myschool.Adapters.MyAdapterPeople;
import in.trydevs.myschool.R;
import in.trydevs.myschool.extras.MyApplication;
import in.trydevs.myschool.extras.SpacesItemDecoration;


public class PeopleActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPeople);
        MyAdapterPeople adapterPeople = new MyAdapterPeople(PeopleActivity.this, MyApplication.getWritableDatabase().getPeopleData());
        recyclerView.setLayoutManager(new LinearLayoutManager(PeopleActivity.this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        recyclerView.setAdapter(adapterPeople);
        // Setting Top bar 'Tool bar'
        toolbar = (Toolbar) findViewById(R.id.top_bar_people_activity);
        setSupportActionBar(toolbar);
    }

}
