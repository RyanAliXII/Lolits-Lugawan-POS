package models;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class User extends Model {
    String collection;


    public User(){
        this.collection = "users";
    }

    public void getUserByEmail(String email, ModelCallback cb){
        final QueryDocumentSnapshot[] user = new QueryDocumentSnapshot[1];
        db.collection(this.collection).whereEqualTo("email", email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if(!task.getResult().isEmpty()){
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            System.out.println(document.getData().get("email"));
                            cb.getOne(document);
                            break;
                        }
                    }
                    else{
                        cb.getOne(null);
                    }

                } else {
                    System.out.println("Failed");
                }
            }
        });
    }
}
