/**
 * 
 */
package com.game.pileon;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import android.content.Context;
import android.content.res.Resources;

/**
 * @author breeze4
 *
 *	Class to handle file reading and writing for persistent game state functionality
 */
public class FileUtility
{
	
	
	public static Reader loadFileToString(String filename, Context context) {
	    Resources resources = context.getResources();
	    try {
	        InputStream is = context.getAssets().open(filename);
	        int size = is.available();
	        // Read the entire asset into a local byte buffer.
	        byte[] buffer = new byte[size];
	        is.read(buffer);
	        is.close();
	        // Convert the buffer into a string.
	        String text = new String(buffer);
	        Reader reader = new StringReader(text);
	        return reader;
	    } catch (IOException e) {
	        // Should never happen!
	        throw new RuntimeException(e);
	    }
	}
}
