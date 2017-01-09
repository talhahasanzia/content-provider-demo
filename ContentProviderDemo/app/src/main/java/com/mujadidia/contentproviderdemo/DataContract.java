package com.mujadidia.contentproviderdemo;

import android.content.ContentUris;
import android.content.UriMatcher;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;

/**
 * Created by tzia on 06-Jan-17.
 */

public class DataContract
{




    public static final String TABLE_NAME="personal_info";


    public static final String NAME_COLUMN="name_column";


    public static final String AGE_COLUMN="age_column";



    public static final String CONTENT_AUTHORITY = "com.mujadidia.contentproviderdemo";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_INFO = "personal_info";

    public static final int NAME=6869;
    public static final int AGE=849;

    public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH_INFO).build();


    public static Uri buildInfoUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);

    }


    public static Uri buildUriWithInfoAge(
           String parameter) {

        return CONTENT_URI.buildUpon()
                .appendQueryParameter("age",parameter).build();
    }

    public static Uri buildUriWithInfoName(
            String parameter) {

        return CONTENT_URI.buildUpon()
                .appendQueryParameter("name",parameter).build();
    }






}
