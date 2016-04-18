package in.trydevs.myschool.DataClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kid on 15/04/16.
 */
public class Subscription implements Parcelable {
    public static final Parcelable.Creator<Subscription> CREATOR = new Parcelable.Creator<Subscription>() {
        @Override
        public Subscription createFromParcel(Parcel source) {
            return new Subscription(source);
        }

        @Override
        public Subscription[] newArray(int size) {
            return new Subscription[size];
        }
    };
    String schoolId;
    String token;
    String name;

    public Subscription() {
    }

    protected Subscription(Parcel in) {
        this.schoolId = in.readString();
        this.token = in.readString();
        this.name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.schoolId);
        dest.writeString(this.token);
        dest.writeString(this.name);
    }
}
