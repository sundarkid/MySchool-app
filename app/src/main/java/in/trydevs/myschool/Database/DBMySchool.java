package in.trydevs.myschool.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import in.trydevs.myschool.DataClasses.Image;
import in.trydevs.myschool.DataClasses.People;
import in.trydevs.myschool.DataClasses.Post;


/**
 * Created by Sundareswaran on 28-07-2015.
 */
public class DBMySchool {

    private final short TABLE_POSTS = 0;
    private final short TABLE_PEOPLE = 1;
    private final short TABLE_IMAGES = 2;

    private MySQLiteHelper mySQLiteHelper;
    private SQLiteDatabase database;

    public DBMySchool(Context context) {
        mySQLiteHelper = new MySQLiteHelper(context);
        database = mySQLiteHelper.getWritableDatabase();
    }

    public void insertPostData(List<Post> posts) {
        // Create a sql prepared statement
        String sql = "INSERT INTO " + getTableName(TABLE_POSTS) + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        // Inserting into table
        SQLiteStatement statement = database.compileStatement(sql);
        database.beginTransaction();
        for (int i = 0; i < posts.size(); i++) {
            Post current = posts.get(i);
            statement.clearBindings();
            statement.bindString(2, Long.toString(current.getUser_id()));
            statement.bindString(3, Long.toString(current.getPost_id()));
            statement.bindString(4, Long.toString(current.getSchool_id()));
            statement.bindString(5, current.getName());
            statement.bindString(6, current.getName());
            statement.bindString(6, current.getMessage());
            statement.bindString(7, current.getImage());
            statement.bindString(8, current.getDate());
            statement.bindString(9, current.getUrl());
            statement.bindString(10, current.getUpdateDate());

            statement.execute();
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public void insertPeopleData(List<People> peoples) {
        // Create a sql prepared statement
        String sql = "INSERT INTO " + getTableName(TABLE_PEOPLE) + " VALUES ( ?, ?, ?, ?, ?, ?);";
        // Inserting into table
        SQLiteStatement statement = database.compileStatement(sql);
        database.beginTransaction();
        for (int i = 0; i < peoples.size(); i++) {
            People current = peoples.get(i);
            statement.clearBindings();
            statement.bindString(2, current.getName());
            statement.bindString(3, current.getAbout());
            statement.bindString(4, current.getImage());
            statement.bindString(5, current.getFacebook());
            statement.bindString(6, current.getTwitter());

            statement.execute();
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public void insertImageData(List<Image> images) {
        // Create a sql prepared statement
        String sql = "INSERT INTO " + getTableName(TABLE_IMAGES) + " VALUES ( ?, ?, ?, ?, ?);";
        // Inserting into table
        SQLiteStatement statement = database.compileStatement(sql);
        database.beginTransaction();
        for (int i = 0; i < images.size(); i++) {
            Image current = images.get(i);
            statement.clearBindings();
            statement.bindLong(2, current.getId());
            statement.bindLong(3, current.getSchool_id());
            statement.bindString(4, current.getLink());
            statement.bindString(4, current.getTitle());
            statement.bindLong(5, current.getGallery_id());

            statement.execute();
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }


    public List<Image> getImagetData() {
        List<Image> images;
        Cursor cursor = database.query(getTableName(TABLE_IMAGES), null, null, null, null, null, MySQLiteHelper.COLUMN_SNO + " DESC ");
        if (cursor != null && cursor.moveToFirst()) {
            // Creating indices for the table attributes
            images = new ArrayList<>();
            int index_sno = cursor.getColumnIndex(MySQLiteHelper.COLUMN_SNO);
            int index_title = cursor.getColumnIndex(MySQLiteHelper.COLUMN_TITLE);
            int index_image = cursor.getColumnIndex(MySQLiteHelper.COLUMN_IMAGE);
            int index_id = cursor.getColumnIndex(MySQLiteHelper.COLUMN_IMAGE_ID);
            int index_school_id = cursor.getColumnIndex(MySQLiteHelper.COLUMN_SCHOOL_ID);
            int index_gallery_id = cursor.getColumnIndex(MySQLiteHelper.COLUMN_GALLERY_ID);
            int index_update_time = cursor.getColumnIndex(MySQLiteHelper.COLUMN_UPDATE_TIME);
            int index_update_date = cursor.getColumnIndex(MySQLiteHelper.COLUMN_UPDATE_DATE);
            int index_event_id = cursor.getColumnIndex(MySQLiteHelper.COLUMN_EVENT_ID);
            do {
                Image image = new Image();
                image.setEvent_id(cursor.getLong(index_sno));
                image.setTitle(cursor.getString(index_image));
                image.setId(cursor.getLong(index_title));
                image.setSchool_id(cursor.getLong(index_school_id));
                image.setTitle(cursor.getString(index_title));
                images.add(image);
            } while (cursor.moveToNext());
            cursor.close();
        } else
            images = Collections.emptyList();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < images.size(); i++) {
            jsonArray.put(images.get(i).getLink());
        }
        Log.d("IMmages", jsonArray.toString());

        return images;
    }

    public List<Post> getPostData() {
        List<Post> posts;
        Cursor cursor = database.query(getTableName(TABLE_POSTS), null, null, null, null, null, MySQLiteHelper.COLUMN_EVENT_ID + " DESC ");
        if (cursor != null && cursor.moveToFirst()) {
            // Creating indices for the table attributes
            posts = new ArrayList<>();
            int index_sno = cursor.getColumnIndex(MySQLiteHelper.COLUMN_SNO);
            int index_user_id = cursor.getColumnIndex(MySQLiteHelper.COLUMN_USER_ID);
            int index_post_id = cursor.getColumnIndex(MySQLiteHelper.COLUMN_EVENT_ID);
            int index_school_id = cursor.getColumnIndex(MySQLiteHelper.COLUMN_SCHOOL_ID);
            int index_name = cursor.getColumnIndex(MySQLiteHelper.COLUMN_NAME);
            int index_title = cursor.getColumnIndex(MySQLiteHelper.COLUMN_TITLE);
            int index_message = cursor.getColumnIndex(MySQLiteHelper.COLUMN_MESSAGE);
            int index_image = cursor.getColumnIndex(MySQLiteHelper.COLUMN_IMAGE);
            int index_date = cursor.getColumnIndex(MySQLiteHelper.COLUMN_DATE);
            int index_url = cursor.getColumnIndex(MySQLiteHelper.COLUMN_URL);
            int index_update_date = cursor.getColumnIndex(MySQLiteHelper.COLUMN_UPDATE_DATE);
            int index_update_time = cursor.getColumnIndex(MySQLiteHelper.COLUMN_UPDATE_TIME);
            do {
                Post post = new Post();
                post.setSno(cursor.getLong(index_sno));
                post.setUser_id(cursor.getLong(index_user_id));
                post.setPost_id(cursor.getLong(index_post_id));
                post.setSchool_id(cursor.getLong(index_school_id));
                post.setMessage(cursor.getString(index_name));
                post.setTitle(cursor.getString(index_title));
                post.setUrl(cursor.getString(index_message));
                post.setImage(cursor.getString(index_message));
                post.setName(cursor.getString(index_date));
                post.setDate(cursor.getString(index_url));
                posts.add(post);
            } while (cursor.moveToNext());
            cursor.close();
        } else
            posts = Collections.emptyList();
        // check
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < posts.size(); i++) {
            jsonArray.put(posts.get(i).getJsonObject());
        }
        Log.d("Post Data table", jsonArray.toString());

        return posts;
    }

    public List<People> getPeopleData() {
        List<People> peoples;
        Cursor cursor = database.query(getTableName(TABLE_PEOPLE), null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            // Creating indices for the table attributes
            peoples = new ArrayList<>();
            int index_name = cursor.getColumnIndex(MySQLiteHelper.COLUMN_NAME);
            int index_about = cursor.getColumnIndex(MySQLiteHelper.COLUMN_ABOUT);
            int index_fb = cursor.getColumnIndex(MySQLiteHelper.COLUMN_FACEBOOK_URL);
            int index_tweet = cursor.getColumnIndex(MySQLiteHelper.COLUMN_TWITTER_URL);
            int index_image = cursor.getColumnIndex(MySQLiteHelper.COLUMN_IMAGE);
            do {
                People people = new People();
                people.setName(cursor.getString(index_name));
                people.setAbout(cursor.getString(index_about));
                people.setFacebook(cursor.getString(index_fb));
                people.setTwitter(cursor.getString(index_tweet));
                people.setImage(cursor.getString(index_image));

                peoples.add(people);
            } while (cursor.moveToNext());
            cursor.close();
        } else
            peoples = Collections.emptyList();
        return peoples;
    }

    private String getTableName(short i) {
        switch (i) {
            case TABLE_POSTS:
                return MySQLiteHelper.TABLE_POST;
            case TABLE_PEOPLE:
                return MySQLiteHelper.TABLE_PEOPLE;
            case TABLE_IMAGES:
                return MySQLiteHelper.TABLE_IMAGE;
        }
        return null;
    }

}
