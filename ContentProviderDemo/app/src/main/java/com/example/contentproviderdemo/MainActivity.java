package com.example.contentproviderdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
	
	private static final String[] COLUMNS_TO_BE_BOUND = new String[]{
			UserDictionary.Words.WORD,
			UserDictionary.Words.FREQUENCY
	};
	
	private static final int[] LAYOUT_ITEMS_TO_FILL = new int[]{
			android.R.id.text1,
			android.R.id.text2
	};
	
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		
		
		final Button createDatabase = (Button) findViewById( R.id.create_database_button );
		Button runQuery = (Button) findViewById( R.id.resolver_query_button );
		final TextView resultsTextview = (TextView) findViewById( R.id.results_textview );
		
		
		createDatabase.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick( View view )
			{
				
				createDatabase();
				
			}
		} );
		
		
		runQuery.setOnClickListener( new View.OnClickListener()
		{
			@Override
			public void onClick( View view )
			{
				Uri uri = DataContract.buildUriWithInfoAge( "74" );
				
				Cursor c = getContentResolver().query( uri, null, null, null, null );
				
				Log.d( "RESOLVER RESPONSE", "Cursor count: " + c.getCount() );
				
				c.moveToFirst();
				
				if ( c.getCount() > 0 )
				{
					
					resultsTextview.setText( c.getString( c.getPosition() ) );
				}
				
			}
		} );

        /*ListView dictListView = (ListView) findViewById(R.id.dictionary_list_view);




        // Get the ContentResolver which will send a message to the ContentProvider.
        ContentResolver resolver = getContentResolver();

        // Get a Cursor containing all of the rows in the Words table.
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);

        // Set the Adapter to fill the standard two_line_list_item layout with data from the Cursor.
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item,
                cursor,
                COLUMNS_TO_BE_BOUND,
                LAYOUT_ITEMS_TO_FILL,
                0);

        // Attach the adapter to the ListView.
        dictListView.setAdapter(adapter);*/
	}
	
	
	void createDatabase()
	{
		
		
		DataHelper dataHelper = new DataHelper( this, "sample_database", null, 1 );
		
		
		ContentValues[] contentValues = new ContentValues[ 10 ];
		
		String names[] = new String[ 5 ];
		names[ 0 ] = "Smith";
		names[ 1 ] = "Marry";
		names[ 2 ] = "Max";
		names[ 3 ] = "Jane";
		names[ 4 ] = "Osama";
		
		Random rn = new Random();
		int n = 65 - 90 + 1;
		
		
		int i = rn.nextInt() % n;
		int randomNum = 65 + i;
		
		
		Random rname = new Random();
		int name = 0 - 4 + 1;
		
		
		int index = rname.nextInt() % name;
		int randomName = 0 + index;
		
		
		for ( int count = 0; count < contentValues.length; count++ )
		{
			index = rname.nextInt() % name;
			randomName = 0 + index;
			
			if ( randomName > 4 )
			{
				randomName = 4;
			}
			
			if ( randomName < 0 )
			{
				randomName = 0;
			}
			
			
			i = rn.nextInt() % n;
			randomNum = 65 + i;
			
			if ( randomNum < 65 )
			{
				randomNum = 74;
			}
			
			
			String nameString = Character.toString( (char) randomNum ) + ". " + names[ randomName ];
			
			String ageString = ( randomName + randomNum - 10 ) + "";
			
			ContentValues contentValues1 = new ContentValues();
			contentValues1.put( DataContract.NAME_COLUMN, nameString );
			contentValues1.put( DataContract.AGE_COLUMN, ageString );
			
			contentValues[ count ] = contentValues1;
			
		}
		
		
		SQLiteDatabase sqLiteDatabase = dataHelper.getWritableDatabase();
		
		
		for ( ContentValues contentValues1 : contentValues )
		{
			long status = dataHelper.insertIntoDatabase( sqLiteDatabase, contentValues1 );
			
			if ( status < 0 )
			{
				throw new SQLiteAbortException( "Something wen wrong." );
			}
		}
		
		
	}
}
