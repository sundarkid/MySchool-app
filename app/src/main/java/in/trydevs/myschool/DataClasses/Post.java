package in.trydevs.myschool.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sundareswaran on 27-07-2015.
 */
public class Post implements Parcelable {
    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
    private String name, message, date, image, title, url, updateDate, updateTime, eventDate, eventTime;
    private long user_id, post_id, sno, school_id;

    public Post() {
        sno = 0;
        name = "";
        message = "";
        date = "";
        image = "";
        title = "";
        url = "";
        eventDate = "";
        eventTime = "";
        user_id = 0;
        post_id = 0;
        school_id = 0;
        updateDate = "";
        updateTime = "";
    }

    protected Post(Parcel in) {
        this.name = in.readString();
        this.message = in.readString();
        this.date = in.readString();
        this.image = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.updateDate = in.readString();
        this.updateTime = in.readString();
        this.eventDate = in.readString();
        this.eventTime = in.readString();
        this.user_id = in.readLong();
        this.post_id = in.readLong();
        this.sno = in.readLong();
        this.school_id = in.readLong();
    }

    public static Post fromJSON(JSONObject object) {
        Post post = new Post();
        try {

            if (object.has(UrlLinkNames.getJsonEventId()))
                post.setPost_id(Long.parseLong(object.getString(UrlLinkNames.getJsonEventId())));

            if (object.has(UrlLinkNames.getJsonEventId()))
                post.setSno(Long.parseLong(object.getString(UrlLinkNames.getJsonEventId())));

            if (object.has(UrlLinkNames.getJsonUserId()))
                post.setUser_id(object.getLong(UrlLinkNames.getJsonUserId()));

            if (object.has(UrlLinkNames.getJsonEventName()))
                post.setName(object.getString(UrlLinkNames.getJsonEventName()));

            if (object.has(UrlLinkNames.getJsonDescription()))
                post.setMessage(object.getString(UrlLinkNames.getJsonDescription()));

            if (object.has(UrlLinkNames.getJsonImage()))
                post.setImage(object.getString(UrlLinkNames.getJsonImage()));

            if (object.has(UrlLinkNames.getJsonSchoolid()))
                post.setSchool_id(Long.parseLong(object.getString(UrlLinkNames.getJsonSchoolid())));

            if (object.has(UrlLinkNames.getJsonDate()))
                post.setDate(object.getString(UrlLinkNames.getJsonDate()));

            if (object.has(UrlLinkNames.getJsonEventName()))
                post.setTitle(object.getString(UrlLinkNames.getJsonEventName()));

            if (object.has(UrlLinkNames.getJsonUrl()))
                post.setUrl(object.getString((UrlLinkNames.getJsonUrl())));

            if (object.has(UrlLinkNames.getJsonUpdateTime()))
                post.setUpdateTime(object.getString((UrlLinkNames.getJsonUpdateTime())));

            if (object.has(UrlLinkNames.getJsonUpdateDate()))
                post.setUpdateDate(object.getString((UrlLinkNames.getJsonUpdateDate())));

            if (object.has(UrlLinkNames.getJsonEventTime()))
                post.setEventTime(object.getString((UrlLinkNames.getJsonEventTime())));

            if (object.has(UrlLinkNames.getJsonEventDate()))
                post.setEventDate(object.getString((UrlLinkNames.getJsonEventDate())));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return post;
    }

    public JSONObject getJsonObject() {
        JSONObject object = new JSONObject();
        try {
            object.put(UrlLinkNames.getJsonSno(), getSno());
            object.put(UrlLinkNames.getJsonTitle(), getTitle());
            object.put(UrlLinkNames.getJsonDescription(), getMessage());
            object.put(UrlLinkNames.getJsonUrl(), getUrl());
            object.put(UrlLinkNames.getJsonSchoolid(), getSchool_id());
            object.put(UrlLinkNames.getJsonImage(), getImage());
            object.put(UrlLinkNames.getJsonEventName(), getName());
            object.put(UrlLinkNames.getJsonDate(), getDate());
            object.put(UrlLinkNames.getJsonUpdateDate(), getUpdateDate());
            object.put(UrlLinkNames.getJsonUpdateTime(), getUpdateTime());
            object.put(UrlLinkNames.getJsonEventDate(), getEventDate());
            object.put(UrlLinkNames.getJsonEventTime(), getEventTime());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public long getSchool_id() {
        return school_id;
    }

    public void setSchool_id(long school_id) {
        this.school_id = school_id;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public long getSno() {
        return sno;
    }

    public void setSno(long sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.message);
        dest.writeString(this.date);
        dest.writeString(this.image);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.updateDate);
        dest.writeString(this.updateTime);
        dest.writeString(this.eventDate);
        dest.writeString(this.eventTime);
        dest.writeLong(this.user_id);
        dest.writeLong(this.post_id);
        dest.writeLong(this.sno);
        dest.writeLong(this.school_id);
    }
}
