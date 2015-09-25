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
import in.trydevs.myschool.DataClasses.UrlLinkNames;

/**
 * Created by Sundareswaran on 30-08-2015.
 */
public class Decoder {
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
            Log.d("decode post",json);
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
            Log.d("decoder people",json);
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
            Log.d("Decoder images",json);
            e.printStackTrace();
        }
        return images;
    }
}
