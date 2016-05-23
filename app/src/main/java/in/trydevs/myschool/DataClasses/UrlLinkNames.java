package in.trydevs.myschool.DataClasses;

/**
 * Created by Sundareswaran on 28-07-2015.
 */
public class UrlLinkNames {
    private static String JSON_USER_ID = "user_id";
    private static String JSON_POST_ID = "post_id";
    private static String JSON_MESSAGE = "message";
    private static String JSON_DATE = "date";
    private static String JSON_IMAGE = "image";
    private static String JSON_NAME = "name";
    private static String JSON_TITLE = "title";
    private static String JSON_URL = "url";

    private static String JSON_POST = "post";
    private static String JSON_POSTS = "posts";
    private static String JSON_PHOTOS = "photos";
    private static String JSON_PEOPLE = "people";


    private static String JSON_FACEBOOK_URL = "url_fb";
    private static String JSON_TWITTER_URL = "url_tweet";
    private static String JSON_ABOUT = "url_about";
    private static String JSON_SNO = "sno";

    private static String URL_GCM_TOKENS = "subscriptions.php";

    // Start of new Design
    //private static String URL_BASE = "http://10.12.111.230/myschool/api/v1/app/";
    //private static String URL_BASE = "http://192.168.56.1/myschool/api/v1/app/";
    private static String URL_BASE = "http://192.168.1.100/myschool/api/v1/app/";
    private static String URL_SUBSCRIPTIONS = "getSubscriptionList";
    private static String URL_GCM_REGISTER = "gcmRegister";
    private static String URL_GET_ALL_DATA = "getAllData";

    private static String JSON_TOKEN = "token";
    private static String JSON_SCHOOLID = "schoolId";
    private static String JSON_RESULT = "result";
    private static String JSON_SUCCESS = "success";
    private static String JSON_FAILURE = "failure";
    private static String JSON_UPDATE_DATE = "updateDate";
    private static String JSON_UPDATE_TIME = "dateTime";
    private static String JSON_EVENT_ID = "eventId";
    private static String JSON_EVENT_NAME = "eventName";
    private static String JSON_DESCRIPTION = "description";
    private static String JSON_EVENT_DATE = "eventDate";
    private static String JSON_EVENT_TIME = "eventTime";

    private static String JSON_PHOTO_ID = "photoId";
    private static String JSON_PHOTO_NAME = "photoName";
    private static String JSON_LINK = "link";
    private static String JSON_GALLERY_ID = "galleryId";

    private static String SHARED_PREFERENCES_FILE = "mySharedPrefsFile";
    private static String SHARED_PREFERENCES_SCHOOL_TOKEN = "school_token";
    private static String SHARED_PREFERENCES_SCHOOL_ID = "school_id";

    public static String getJsonGalleryId() {
        return JSON_GALLERY_ID;
    }

    public static String getJsonPhotos() {
        return JSON_PHOTOS;
    }

    public static String getSharedPreferencesFile() {
        return SHARED_PREFERENCES_FILE;
    }

    public static String getJsonLink() {
        return JSON_LINK;
    }

    public static String getJsonPhotoId() {
        return JSON_PHOTO_ID;
    }

    public static String getJsonPhotoName() {
        return JSON_PHOTO_NAME;
    }

    public static String getUrlBase() {
        return URL_BASE;
    }

    public static String getJsonSno() {
        return JSON_SNO;
    }

    public static String getJsonToken() {
        return JSON_TOKEN;
    }

    public static String getJsonSchoolid() {
        return JSON_SCHOOLID;
    }

    public static String getSharedPreferencesSchoolToken() {
        return SHARED_PREFERENCES_SCHOOL_TOKEN;
    }

    public static String getJsonUpdateDate() {
        return JSON_UPDATE_DATE;
    }

    public static String getJsonUpdateTime() {
        return JSON_UPDATE_TIME;
    }

    public static String getSharedPreferencesSchoolId() {
        return SHARED_PREFERENCES_SCHOOL_ID;
    }

    public static String getUrlGcmRegister() {
        return getUrlBase() + URL_GCM_REGISTER;
    }

    public static String getUrlGetAllData() {
        return getUrlBase() + URL_GET_ALL_DATA;
    }

    public static String getUrlGcmTokens() {
        return getUrlBase() + URL_GCM_TOKENS;
    }

    public static String getUrlSubscriptions() {
        return getUrlBase() + URL_SUBSCRIPTIONS;
    }

    public static String getJsonDate() {
        return JSON_DATE;
    }

    public static String getJsonImage() {
        return JSON_IMAGE;
    }

    public static String getJsonMessage() {
        return JSON_MESSAGE;
    }

    public static String getJsonName() {
        return JSON_NAME;
    }

    public static String getJsonPostId() {
        return JSON_POST_ID;
    }

    public static String getJsonUserId() {
        return JSON_USER_ID;
    }

    public static String getJsonTitle() {
        return JSON_TITLE;
    }

    public static String getJsonResult() {
        return JSON_RESULT;
    }

    public static String getJsonSuccess() {
        return JSON_SUCCESS;
    }

    public static String getJsonFailure() {
        return JSON_FAILURE;
    }

    public static String getJsonUrl() {
        return JSON_URL;
    }

    public static String getJsonAbout() {
        return JSON_ABOUT;
    }

    public static String getJsonFacebookUrl() {
        return JSON_FACEBOOK_URL;
    }

    public static String getJsonTwitterUrl() {
        return JSON_TWITTER_URL;
    }

    public static String getJsonPeople() {
        return JSON_PEOPLE;
    }

    public static String getJsonPost() {
        return JSON_POST;
    }

    public static String getJsonPosts() {
        return JSON_POSTS;
    }

    public static String getJsonDescription() {
        return JSON_DESCRIPTION;
    }

    public static String getJsonEventDate() {
        return JSON_EVENT_DATE;
    }

    public static String getJsonEventId() {
        return JSON_EVENT_ID;
    }

    public static String getJsonEventName() {
        return JSON_EVENT_NAME;
    }

    public static String getJsonEventTime() {
        return JSON_EVENT_TIME;
    }
}
