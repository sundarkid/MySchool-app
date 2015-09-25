package in.trydevs.myschool.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sundareswaran on 26-08-2015.
 */
public class Image implements Parcelable {
    int id;
    String link, title;

    public Image() {
        id = 0;
        link = "";
        title = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static Image getImageFromJSON(JSONObject object) {
        Image image = new Image();
        try {
            if (object.has(UrlLinkNames.getJsonSno()))
                image.setId(object.getInt(UrlLinkNames.getJsonSno()));
            if (object.has(UrlLinkNames.getJsonImage()))
                image.setLink(object.getString(UrlLinkNames.getJsonImage()));
            if (object.has(UrlLinkNames.getJsonTitle()))
                image.setTitle(object.getString(UrlLinkNames.getJsonTitle()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.link);
        dest.writeString(this.title);
    }

    protected Image(Parcel in) {
        this.id = in.readInt();
        this.link = in.readString();
        this.title = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
