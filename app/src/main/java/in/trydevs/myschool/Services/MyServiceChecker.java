package in.trydevs.myschool.Services;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import in.trydevs.myschool.DataClasses.Image;
import in.trydevs.myschool.DataClasses.Post;
import in.trydevs.myschool.DataClasses.UrlLinkNames;
import in.trydevs.myschool.Network.CustomRequest;
import in.trydevs.myschool.Network.VolleySingleton;
import in.trydevs.myschool.extras.Decoder;
import in.trydevs.myschool.extras.MyApplication;
import me.tatarka.support.job.JobParameters;
import me.tatarka.support.job.JobService;

import static com.android.volley.Request.Method.POST;


public class MyServiceChecker extends JobService {


    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        new CheckServer(this, this).execute(jobParameters);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    private static class CheckServer extends AsyncTask<JobParameters, Void, JobParameters> {

        MyServiceChecker myServiceChecker;
        Context context;
        SharedPreferences loginDetails;
        long school_id;
        HashMap<String, String> params = new HashMap<>();
        JSONObject response = null;
        private RequestQueue requestQueue;

        CheckServer(MyServiceChecker myServiceChecker, Context context) {
            this.myServiceChecker = myServiceChecker;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            // getting Logged in data
            loginDetails = myServiceChecker.getSharedPreferences(UrlLinkNames.getSharedPreferencesFile(), Context.MODE_PRIVATE);
            school_id = loginDetails.getInt(UrlLinkNames.getJsonSchoolid(), 0);
            requestQueue = VolleySingleton.getInstance().getmRequestQueue();
            super.onPreExecute();
        }

        @Override
        protected JobParameters doInBackground(JobParameters... jobParameterses) {
            getDataFromServer();
            return jobParameterses[0];
        }

        @Override
        protected void onPostExecute(JobParameters jobParameters) {
            if (response != null) {
                Log.d("Service", response.toString());
                if (response.has("result")) {
                    try {
                        if (response.getString(UrlLinkNames.getJsonResult()).equalsIgnoreCase(UrlLinkNames.getJsonSuccess())) {
                            decodeData(response);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            myServiceChecker.jobFinished(jobParameters, false);
        }

        private void getDataFromServer() {

            RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();

            CustomRequest request = new CustomRequest(POST, UrlLinkNames.getUrlGetAllData(), params, requestFuture, requestFuture);
            requestQueue.add(request);

            try {
                response = requestFuture.get(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

        }

        private void decodeData(JSONObject response) {

            if (response.has("posts")) {
                try {
                    String s = response.getString("posts");
                    JSONArray jsonArray = new JSONArray(s);
                    List<Post> posts = Decoder.decodePost(jsonArray);
                    if (posts.size() > 0)
                        MyApplication.getWritableDatabase().insertPostData(posts);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (response.has(UrlLinkNames.getJsonPhotos())) {
                if (response.has(UrlLinkNames.getJsonPhotos())) {
                    try {
                        String s = response.getString(UrlLinkNames.getJsonPhotos());
                        JSONArray jsonArray = new JSONArray(s);
                        List<Image> results = Decoder.decodeImage(jsonArray);
                        if (results.size() > 0)
                            MyApplication.getWritableDatabase().insertImageData(results);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }


    }

}
