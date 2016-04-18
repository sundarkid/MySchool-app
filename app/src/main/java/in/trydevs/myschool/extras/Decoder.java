package in.trydevs.myschool.extras;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import in.trydevs.myschool.DataClasses.Image;
import in.trydevs.myschool.DataClasses.People;
import in.trydevs.myschool.DataClasses.Post;
import in.trydevs.myschool.DataClasses.Subscription;
import in.trydevs.myschool.DataClasses.UrlLinkNames;

/**
 * Created by Sundareswaran on 30-08-2015.
 */
public class Decoder {

    public static List<Subscription> decodeSubscription(JSONObject json) {
        List<Subscription> data = Collections.emptyList();

        try {
            if (json.has(UrlLinkNames.getJsonResult())) {
                if (json.getString(UrlLinkNames.getJsonResult()).equalsIgnoreCase(UrlLinkNames.getJsonSuccess())) {

                    JSONArray array = json.getJSONArray("list");

                    if (array.length() > 0)
                        data = new ArrayList<>();

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject child = array.getJSONObject(i);
                        Subscription subscription = new Subscription();

                        subscription.setName(child.getString(UrlLinkNames.getJsonName()));
                        subscription.setToken(child.getString(UrlLinkNames.getJsonToken()));
                        subscription.setSchoolId(UrlLinkNames.getJsonSchoolid());

                        data.add(subscription);
                    }
                } else {
                    Log.d("Decode Sub result", json.toString());
                }
            } else {
                Log.d("Decode sub no result", json.toString());
            }
        } catch (JSONException e) {
            Log.d("decode subscription", json.toString());
            e.printStackTrace();
        }

        return data;
    }

    public static List<Post> decodePost(String json) {
        List<Post> posts = Collections.emptyList();
        try {
            JSONObject object = null;
            object = new JSONObject(json);
            if (object.has(UrlLinkNames.getJsonPost())) {
                // Getting posts if we have any
                JSONArray jsonArrayPost = new JSONArray(object.getString(UrlLinkNames.getJsonPost()));
                posts = new ArrayList<>();
                for (int i = 0; i < jsonArrayPost.length(); i++) {
                    JSONObject objectPost = jsonArrayPost.getJSONObject(i);
                    Post post = Post.fromJSON(objectPost);
                    posts.add(post);
                }
            }
        } catch (JSONException e) {
            Log.d("decode post", json);
            e.printStackTrace();
        }
        MyApplication.getWritableDatabase().insertPostData(posts);
        return posts;
    }

    public static List<People> decodePeople(String json) {
        List<People> peoples = Collections.emptyList();
        try {
            JSONObject object = null;
            object = new JSONObject(json);
            // Getting peoples details if we have any
            if (object.has(UrlLinkNames.getJsonPeople())) {
                JSONArray jsonArrayPeople = new JSONArray(object.getString(UrlLinkNames.getJsonPeople()));
                peoples = new ArrayList<>();
                for (int i = 0; i < jsonArrayPeople.length(); i++) {
                    JSONObject objectPeople = jsonArrayPeople.getJSONObject(i);
                    People people = People.getPeopleFromJson(objectPeople);
                    peoples.add(people);
                }
                MyApplication.getWritableDatabase().insertPeopleData(peoples);
            }
        } catch (JSONException e) {
            Log.d("decoder people", json);
            e.printStackTrace();
        }
        return peoples;
    }

    public static List<Image> decodeImage(String json) {
        List<Image> images = Collections.emptyList();
        try {
            JSONObject object = new JSONObject(json);
            if (object.has(UrlLinkNames.getJsonImage())) {
                JSONArray jsonArray = new JSONArray(object.getString(UrlLinkNames.getJsonImage()));
                images = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject objectImage = jsonArray.getJSONObject(i);
                    Image image = Image.getImageFromJSON(objectImage);
                    images.add(image);
                }
                MyApplication.getWritableDatabase().insertImageData(images);
            }
        } catch (JSONException e) {
            Log.d("Decoder images", json);
            e.printStackTrace();
        }
        return images;
    }
}
