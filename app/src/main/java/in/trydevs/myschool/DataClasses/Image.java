package in.trydevs.myschool.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sundareswaran on 26-08-2015.
 */
public class Image implements Parcelable {
    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
    long id, school_id, gallery_id, event_id;
    String link, title, updateDate, updateTime;

    public Image() {
        id = 0;
        school_id = 0;
        gallery_id = 0;
        event_id = 0;
        updateDate = "";
        updateTime = "";
        link = "";
        title = "";
    }

    protected Image(Parcel in) {
        this.id = in.readLong();
        this.school_id = in.readLong();
        this.gallery_id = in.readLong();
        this.event_id = in.readLong();
        this.link = in.readString();
        this.title = in.readString();
        this.updateDate = in.readString();
        this.updateTime = in.readString();
    }

    public static Image getImageFromJSON(JSONObject object) {
        Image image = new Image();
        try {
            if (object.has(UrlLinkNames.getJsonPhotoId()))
                image.setId(Long.parseLong(object.getString(UrlLinkNames.getJsonPhotoId())));

            if (object.has(UrlLinkNames.getJsonSchoolid()))
                image.setSchool_id(Long.parseLong(object.getString(UrlLinkNames.getJsonSchoolid())));

            if (object.has(UrlLinkNames.getJsonLink()))
                image.setLink(object.getString(UrlLinkNames.getJsonLink()));

            if (object.has(UrlLinkNames.getJsonPhotoName()))
                image.setTitle(object.getString(UrlLinkNames.getJsonPhotoName()));

            if (object.has(UrlLinkNames.getJsonGalleryId()))
                image.setGallery_id(Long.parseLong(object.getString(UrlLinkNames.getJsonGalleryId())));

            if (object.has(UrlLinkNames.getJsonUpdateDate()))
                image.setUpdateDate(object.getString(UrlLinkNames.getJsonUpdateDate()));

            if (object.has(UrlLinkNames.getJsonUpdateTime()))
                image.setUpdateTime(object.getString(UrlLinkNames.getJsonUpdateTime()));

            if (object.has(UrlLinkNames.getJsonEventId())) {
                Log.d("enent id", object.getString(UrlLinkNames.getJsonEventId()));
                if (object.getString(UrlLinkNames.getJsonEventId()) != null)
                    image.setEvent_id(Long.parseLong(object.getString(UrlLinkNames.getJsonEventId())));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
    }

    public long getGallery_id() {
        return gallery_id;
    }

    public void setGallery_id(long gallery_id) {
        this.gallery_id = gallery_id;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.school_id);
        dest.writeLong(this.gallery_id);
        dest.writeLong(this.event_id);
        dest.writeString(this.link);
        dest.writeString(this.title);
        dest.writeString(this.updateDate);
        dest.writeString(this.updateTime);
    }
}
