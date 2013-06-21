package com.xteam.raincheque;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.app.Application;
import android.content.Context;

public class RainChequeApplication extends Application
{
	private static String LOCAL_FILE_FOR_ACCOUNTS = "session_list";
	protected static int sessionID;
	protected static ArrayList<SessionRecord> sessionList;
	
	protected static void writeAccountsToFile(Context context) 
	{
        ObjectOutputStream objectOut = null;
        try 
        {
            FileOutputStream fileOut = context.openFileOutput(LOCAL_FILE_FOR_ACCOUNTS, Context.MODE_PRIVATE);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(sessionList);
            fileOut.getFD().sync();

        }
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            if (objectOut != null) 
            {                
            	try 
            	{
                    objectOut.close();
                } 
            	catch (IOException e)
                {
                    
                }
            }
        }
    }

   
    @SuppressWarnings("unchecked")
	public static void readAccountsFromFile(Context context) 
    {
        ObjectInputStream objectIn = null;
        Object object = null;
        try 
        {
            FileInputStream fileIn = context.getApplicationContext().openFileInput(LOCAL_FILE_FOR_ACCOUNTS);
            objectIn = new ObjectInputStream(fileIn);
            object = objectIn.readObject();

        } 
        catch (FileNotFoundException e)
        {
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } 
        finally
        {
            if (objectIn != null)
            {
                try 
                {
                    objectIn.close();
                } 
                catch (IOException e)
                {
                    
                }
            }
        }
        if(object!=null)
        	sessionList = (ArrayList<SessionRecord>) object;
        else
        	sessionList = new ArrayList<SessionRecord>();
        
    }
}