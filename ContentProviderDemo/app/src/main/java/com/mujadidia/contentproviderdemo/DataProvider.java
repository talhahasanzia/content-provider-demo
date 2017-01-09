package com.mujadidia.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import static com.mujadidia.contentproviderdemo.DataContract.AGE;
import static com.mujadidia.contentproviderdemo.DataContract.CONTENT_AUTHORITY;
import static com.mujadidia.contentproviderdemo.DataContract.NAME;
import static com.mujadidia.contentproviderdemo.DataContract.PATH_INFO;

/**
 * Created by tzia on 06-Jan-17.
 */

public class DataProvider extends ContentProvider
{
    DataHelper mDataHelper;

    @Override public boolean onCreate()
    {

        mDataHelper= new DataHelper(getContext() , "sample_database", null, 1 );

        return true;
    }

    @Nullable @Override public Cursor query( Uri uri, String[] strings, String s, String[] strings1, String s1 )
    {
        Cursor c;

        UriMatcher uriMatcher=buildUriMatcher();

        switch ( uriMatcher.match( uri ) )
        {


            case NAME:
                c=mDataHelper.getDataByNames( mDataHelper.getReadableDatabase(),uri.getQueryParameter( "age" ) );
                break;
            case AGE:
                c=mDataHelper.getDataByAge( mDataHelper.getReadableDatabase(),uri.getQueryParameter( "name" ) );
                break;
            default:
                throw new UnsupportedOperationException("Cannot execute query, URI mismatch : "+uri );


        }


        return c;
    }

    @Nullable @Override public String getType( Uri uri )
    {
        UriMatcher uriMatcher=buildUriMatcher();

        switch ( uriMatcher.match( uri ) )
        {

            case NAME:
             return "com.mujadidia.contentproviderdemo/personal_info?name";

            case AGE:
                return "com.mujadidia.contentproviderdemo/personal_info?age";
            default:
                throw new UnsupportedOperationException("Cannot find any type listed in URI : "+uri );


        }



    }

    @Nullable @Override public Uri insert( Uri uri, ContentValues contentValues )
    {
        return null;
    }

    @Override public int delete( Uri uri, String s, String[] strings )
    {
        return 0;
    }

    @Override public int update( Uri uri, ContentValues contentValues, String s, String[] strings )
    {
        return 0;
    }

    public static UriMatcher buildUriMatcher()
    {
        UriMatcher uriMatcher=new UriMatcher( UriMatcher.NO_MATCH );

        uriMatcher.addURI( CONTENT_AUTHORITY, PATH_INFO, NAME );
        uriMatcher.addURI( CONTENT_AUTHORITY, PATH_INFO+"?name="+"/*",NAME );
        uriMatcher.addURI( CONTENT_AUTHORITY, PATH_INFO+"?age="+"/*",AGE );



        return uriMatcher;

    }
}
