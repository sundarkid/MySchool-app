package in.trydevs.myschool.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.List;

import in.trydevs.myschool.Adapters.MyAdapterSubscription;
import in.trydevs.myschool.DataClasses.Subscription;
import in.trydevs.myschool.DataClasses.UrlLinkNames;
import in.trydevs.myschool.Listners.ListItemClickListener;
import in.trydevs.myschool.Network.CustomRequest;
import in.trydevs.myschool.Network.VolleySingleton;
import in.trydevs.myschool.R;
import in.trydevs.myschool.extras.Decoder;

import static com.android.volley.Request.Method.GET;

public class SubscriptionActivity extends AppCompatActivity {

    static String VOLLEY_ERROR = "Can not connect to our website, please check your internet connection.";
    RequestQueue requestQueue;
    List<Subscription> data;
    MyAdapterSubscription adapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        final LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSubscription);
        final TextView message = (TextView) findViewById(R.id.message);
        // Hiding the recycler view
        layout.setVisibility(View.INVISIBLE);

        // Creating Volley request queue
        requestQueue = VolleySingleton.getInstance().getmRequestQueue();

        // opening Shared Preferences file
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Getting data from server
        CustomRequest request = new CustomRequest(GET, UrlLinkNames.getUrlSubscriptions(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // getting data ready
                data = Decoder.decodeSubscription(response);
                // Setting Adapter
                adapter = new MyAdapterSubscription(SubscriptionActivity.this, data, new ListItemClickListener() {
                    @Override
                    public void OnListItemClick(Subscription subscription) {
                        Toast.makeText(SubscriptionActivity.this, subscription.getName(), Toast.LENGTH_SHORT).show();
                        sharedPreferences.edit().putString(UrlLinkNames.getSharedPreferencesSchoolToken(), subscription.getToken()).apply();
                        Intent intent = new Intent(SubscriptionActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(SubscriptionActivity.this));
                // Showing the recycler view
                layout.setVisibility(View.VISIBLE);
                // Logging the process data
                Log.d("SubscriptionList", response.toString());
                Log.d("size", data.size() + "");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("SubscriptionList", error.toString());
                message.setText(VOLLEY_ERROR);
            }
        });

        requestQueue.add(request);

    }

}
