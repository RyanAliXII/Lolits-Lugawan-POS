package models;
import com.google.firebase.firestore.FirebaseFirestore;
public class Model {

    FirebaseFirestore db;
    String collection;
    Model(){

        this.db = FirebaseFirestore.getInstance();

    }

}
