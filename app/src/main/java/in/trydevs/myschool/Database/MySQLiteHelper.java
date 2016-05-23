package in.trydevs.myschool.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Sundareswaran on 06-06-2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_POST = "post";
    public static final String TABLE_PEOPLE = "people";
    public static final String TABLE_IMAGE = "image";

    public static final String COLUMN_SNO = "sno";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_MAIL = "mail";

    public static final String COLUMN_UPDATE_TIME = "updateTime";
    public static final String COLUMN_UPDATE_DATE = "updateDate";

    public static final String COLUMN_SCHOOL_ID = "school_id";


    public static final String COLUMN_EVENT_ID = "event_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_URL = "url";

    public static final String COLUMN_IMAGE_ID = "image_id";

    public static final String COLUMN_GALLERY_ID = "gallery_id";

    public static final String COLUMN_PEOPLE_ID = "people_id";
    public static final String COLUMN_ABOUT = "message";
    public static final String COLUMN_FACEBOOK_URL = "url_fb";
    public static final String COLUMN_TWITTER_URL = "url_tweet";

    private static final String DB_NAME = "myschool";
    private static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_POSTS = "CREATE TABLE " + TABLE_POST + " ( " +
            COLUMN_SNO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USER_ID + " INTEGER, " +
            COLUMN_EVENT_ID + " INTEGER, " +
            COLUMN_SCHOOL_ID + " INTEGER, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_TITLE + " TEXT, " +
            COLUMN_MESSAGE + " TEXT, " +
            COLUMN_IMAGE + " TEXT, " +
            COLUMN_DATE + " TEXT, " +
            COLUMN_URL + " TEXT " +
            COLUMN_UPDATE_DATE + " TEXT " +
            COLUMN_UPDATE_TIME + " TEXT " +
            ");";

    private static final String CREATE_TABLE_PEOPLE = "CREATE TABLE " + TABLE_PEOPLE + " ( " +
            COLUMN_SNO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_ABOUT + " TEXT, " +
            COLUMN_IMAGE + " TEXT, " +
            COLUMN_FACEBOOK_URL + " TEXT, " +
            COLUMN_TWITTER_URL + " TEXT " +
            ");";

    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + TABLE_IMAGE + " ( " +
            COLUMN_SNO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_IMAGE_ID + " INTEGER, " +
            COLUMN_SCHOOL_ID + " INTEGER, " +
            COLUMN_IMAGE + " TEXT, " +
            COLUMN_TITLE + " TEXT " +
            COLUMN_GALLERY_ID + " INTEGER " +
            COLUMN_UPDATE_TIME + " TEXT " +
            COLUMN_UPDATE_DATE + " TEXT " +
            COLUMN_EVENT_ID + " INTEGER  " +
            ");";

    private Context context;

    public MySQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("database ", "create");
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE_POSTS);
            Log.d("Table created", TABLE_POST);
            sqLiteDatabase.execSQL(CREATE_TABLE_PEOPLE);
            Log.d("Table created", TABLE_PEOPLE);
            sqLiteDatabase.execSQL(CREATE_TABLE_IMAGE);
            Log.d("Table created", TABLE_IMAGE);
        } catch (SQLiteException exception) {
            Toast.makeText(context, exception.toString(), Toast.LENGTH_SHORT).show();
            Log.d("sql create exception", exception.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try {
            Log.d("Database Helper", "upgrade table box office executed");
            sqLiteDatabase.execSQL(CREATE_TABLE_POSTS);
            onCreate(sqLiteDatabase);
        } catch (SQLiteException exception) {
            Toast.makeText(context, exception + "", Toast.LENGTH_SHORT).show();
        }
    }
}
