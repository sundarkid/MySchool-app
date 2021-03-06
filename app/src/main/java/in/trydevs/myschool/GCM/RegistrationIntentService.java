/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package in.trydevs.myschool.GCM;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import in.trydevs.myschool.DataClasses.Image;
import in.trydevs.myschool.DataClasses.Post;
import in.trydevs.myschool.DataClasses.UrlLinkNames;
import in.trydevs.myschool.Network.CustomRequest;
import in.trydevs.myschool.Network.VolleySingleton;
import in.trydevs.myschool.R;
import in.trydevs.myschool.extras.Decoder;
import in.trydevs.myschool.extras.MyApplication;

import static com.android.volley.Request.Method.POST;

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private final String[] TOPICS = {""};
    private final int[] SCHOOL_ID = {0};
    SharedPreferences sharedPreferences;

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sharedPreferences = getSharedPreferences(UrlLinkNames.getSharedPreferencesFile(), MODE_PRIVATE);

        try {
            // In the (unlikely) event that multiple refresh operations occur simultaneously,
            // ensure that they are processed sequentially.
            synchronized (TAG) {
                // [START register_for_gcm]
                // Initially this call goes out to the network to retrieve the token, subsequent calls
                // are local.
                // [START get_token]
                InstanceID instanceID = InstanceID.getInstance(this);
                String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                        GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                // [END get_token]
                Log.i(TAG, "GCM Registration Token: " + token);

                // Sending token to server for storage
                sendRegistrationToServer(token);


                // TODO GET data from shared preference and assign it to TOPICS[0]
                TOPICS[0] = sharedPreferences.getString(UrlLinkNames.getSharedPreferencesSchoolToken(), "");
                SCHOOL_ID[0] = sharedPreferences.getInt(UrlLinkNames.getSharedPreferencesSchoolId(), 0);
                if (!TOPICS[0].equalsIgnoreCase("")) {
                    boolean sentToken = sharedPreferences
                            .getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
                    Log.d(TAG, sentToken + "");
                    if (!sentToken) {
                        // Sending token to server for storage
                        sendRegistrationToServer(token);
                    }
                    // Subscribe to topic channels
                    //subscribeTopics(token);

                    // You should store a boolean that indicates whether the generated token has been
                    // sent to your server. If the boolean is false, send the token to your server,
                    // otherwise your server should have already received the token.
                    // [END register_for_gcm]
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.
            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false).apply();
        }
    }

    /**
     * Persist registration to third-party servers.
     * <p/>
     * Modify this method to associate the user's GCM registration token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
        RequestQueue requestQueue = VolleySingleton.getInstance().getmRequestQueue();
        HashMap<String, String> params = new HashMap<>();

        //params.put(UrlLinkNames.getJsonToken(), token);
        params.put(UrlLinkNames.getJsonSchoolid(), Integer.toString(SCHOOL_ID[0]));
        params.put(UrlLinkNames.getJsonToken(), TOPICS[0]);

        Log.d(TAG, params.toString());

        final CustomRequest request = new CustomRequest(POST, UrlLinkNames.getUrlGcmRegister(), params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    if (response.has(UrlLinkNames.getJsonResult()))
                        if (response.getString(UrlLinkNames.getJsonResult()).equalsIgnoreCase(UrlLinkNames.getJsonSuccess())) {
                            Toast.makeText(MyApplication.getAppContext(), getResources().getString(R.string.gcm_registration_success), Toast.LENGTH_LONG).show();
                   /*
                   if (response.has(UrlLinkNames.getJsonPeople())) {
                        JSONArray jsonArray = response.getJSONArray(UrlLinkNames.getJsonPeople());
                        List<People> peoples = Collections.emptyList();
                        peoples = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject objectPeople = jsonArray.getJSONObject(i);
                            People people = People.getPeopleFromJson(objectPeople);
                            peoples.add(people);
                        }
                        MyApplication.getWritableDatabase().insertPeopleData(peoples);
                    }

                    */
                            if (response.has(UrlLinkNames.getJsonPosts())) {
                                // Getting posts if we have any
                                JSONArray jsonArrayPost = new JSONArray(response.getString(UrlLinkNames.getJsonPosts()));
                                List<Post> posts = Decoder.decodePost(jsonArrayPost);
                                MyApplication.getWritableDatabase().insertPostData(posts);
                            }
                            if (response.has(UrlLinkNames.getJsonPhotos())) {
                                // Getting Images if any
                                String s = response.getString(UrlLinkNames.getJsonPhotos());
                                JSONArray jsonArray = new JSONArray(s);
                                List<Image> results = Decoder.decodeImage(jsonArray);
                                if (results.size() > 0)
                                    MyApplication.getWritableDatabase().insertImageData(results);
                            }

                            Decoder.decodePost(response.toString());
                            Decoder.decodePeople(response.toString());
                            Decoder.decodeImage(response.toString());

                            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply();
                            // Notify UI that registration has completed, so the progress indicator can be hidden.
                            Intent registrationComplete = new Intent(QuickstartPreferences.REGISTRATION_COMPLETE);
                            LocalBroadcastManager.getInstance(RegistrationIntentService.this).sendBroadcast(registrationComplete);
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("registration error", error.toString());
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    /**
     * Subscribe to any GCM topics of interest, as defined by the TOPICS constant.
     *
     * @param token GCM token
     * @throws IOException if unable to reach the GCM PubSub service
     */
    // [START subscribe_topics]
    private void subscribeTopics(String token) throws IOException {
        for (String topic : TOPICS) {
            GcmPubSub pubSub = GcmPubSub.getInstance(this);
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
    // [END subscribe_topics]

}
