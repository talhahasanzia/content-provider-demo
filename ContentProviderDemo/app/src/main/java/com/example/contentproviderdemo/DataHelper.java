package com.example.contentproviderdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tzia on 06-Jan-17.
 */

public class DataHelper extends SQLiteOpenHelper
{
	
	
	static final int version = 1;
	
	
	final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE " + DataContract.TABLE_NAME + " (" +
			
			DataContract.NAME_COLUMN + " TEXT NOT NULL, " +
			DataContract.AGE_COLUMN + " TEXT NOT NULL " +
			
			" );";
	
	
	public DataHelper( Context context, String name, SQLiteDatabase.CursorFactory factory, int version )
	{
		super( context, name, factory, version );
		
		
	}
	
	@Override
	public void onCreate( SQLiteDatabase sqLiteDatabase )
	{
		sqLiteDatabase.execSQL( SQL_CREATE_LOCATION_TABLE );
	}
	
	@Override
	public void onUpgrade( SQLiteDatabase sqLiteDatabase, int i, int i1 )
	{
		sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS " + DataContract.TABLE_NAME );
		onCreate( sqLiteDatabase );
	}
	
	
	public long insertIntoDatabase( SQLiteDatabase sqLiteDatabase, ContentValues contentValues )
	{
		
		sqLiteDatabase.beginTransaction();
		
		long status = sqLiteDatabase.insert( DataContract.TABLE_NAME, null, contentValues );
		
		
		if ( status > 0 )
		{
			sqLiteDatabase.setTransactionSuccessful();
			
			
		}
		
		sqLiteDatabase.endTransaction();
		
		return status;
		
		
	}
	
	
	public Cursor getDataByAge( SQLiteDatabase database, String names )
	{
		
		String query = "SELECT " + DataContract.AGE_COLUMN + " FROM " + DataContract.TABLE_NAME + " WHERE " + DataContract.NAME_COLUMN + "='" + names + "';";
		
		
		database.beginTransaction();
		
		
		Cursor c = database.rawQuery( query, null );
		
		return c;
		
	}
	
	public Cursor getDataByNames( SQLiteDatabase database, String ages )
	{
		
		String query = "SELECT " + DataContract.NAME_COLUMN + " FROM " + DataContract.TABLE_NAME + " WHERE " + DataContract.AGE_COLUMN + "='" + ages + "';";
		
		
		database.beginTransaction();
		
		
		Cursor c = database.rawQuery( query, null );
		
		return c;
		
	}
	
	
}
