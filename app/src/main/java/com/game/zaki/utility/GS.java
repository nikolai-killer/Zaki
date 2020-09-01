package com.game.zaki.utility;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.storage.FirebaseStorage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class GS {

    public static final String chatsChild = "chats";
    public static final String sendDateChild = "sendDate";

    public static final String schokiEmail = "judith@schorell.com";
    public static final String kiwoEmail = "nikolai@killer.com";

    public static final long splashLength = 2000;

    public static final int amountPages = 3;


    public static FirebaseAuth auth() {
        return FirebaseAuth.getInstance();
    }
    public static FirebaseDatabase db() {
        return FirebaseDatabase.getInstance();
    }
    public static FirebaseFunctions functions() {
        return FirebaseFunctions.getInstance();
    }
    public static FirebaseStorage storage() { return FirebaseStorage.getInstance(); }


    /**
     * Return date in specified format.
     * @param milliSeconds Date in milliseconds
     * @param dateFormat Date format
     * @return String representing date in specified format
     */
    public static String getDateFromMillis(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.getDefault(Locale.Category.FORMAT));

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
