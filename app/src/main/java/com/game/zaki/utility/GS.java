package com.game.zaki.utility;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.storage.FirebaseStorage;

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



}
