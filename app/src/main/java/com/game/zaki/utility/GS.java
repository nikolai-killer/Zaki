package com.game.zaki.utility;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.storage.FirebaseStorage;

public class GS {

    public static final String schokiEmail = "judith@schorell.com";
    public static final String kiwoEmail = "nikolai@killer.com";

    public static final long splashLength = 2000;


    public static FirebaseAuth auth() {
        return FirebaseAuth.getInstance();
    };
    public static FirebaseFirestore db() {
        return FirebaseFirestore.getInstance();
    };
    public static FirebaseFunctions functions() {
        return FirebaseFunctions.getInstance();
    };
    public static FirebaseStorage storage() { return FirebaseStorage.getInstance(); };



}
