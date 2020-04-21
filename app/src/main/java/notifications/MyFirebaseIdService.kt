package notifications

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFirebaseIdService: FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        super.onTokenRefresh()
        val firebaseUser:FirebaseUser? = FirebaseAuth.getInstance().currentUser
        val refreshToken = FirebaseInstanceId.getInstance().getToken()
        if(firebaseUser !=null){
            updateToken(refreshToken)
        }
    }

    fun updateToken(refreshToken: String?){
        val firebaseUser:FirebaseUser? = FirebaseAuth.getInstance().currentUser
        val reference: DatabaseReference = FirebaseDatabase.getInstance().getReference("tokens")
        val token:Token = Token(refreshToken)
        reference.child(firebaseUser!!.uid).setValue(token)
    }

}