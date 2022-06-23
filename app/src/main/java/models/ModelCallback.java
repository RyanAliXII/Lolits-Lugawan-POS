package models;

import com.google.firebase.firestore.QueryDocumentSnapshot;

public interface ModelCallback {

    void getOne(QueryDocumentSnapshot obj);
}
