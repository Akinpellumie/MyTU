//
//package com.akinpelumi.c2068220.mytu.service.impl
//
//import com.akinpelumi.c2068220.mytu.service.AccountService
//import com.akinpelumi.c2068220.mytu.model.User
//import com.akinpelumi.c2068220.mytu.service.trace
//import com.google.firebase.FirebaseApp
//import com.google.firebase.auth.EmailAuthProvider
//import com.google.firebase.auth.FirebaseAuth
//import javax.inject.Inject
//import kotlinx.coroutines.channels.awaitClose
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.callbackFlow
//import kotlinx.coroutines.tasks.await
//
//class AccountServiceImpl @Inject constructor(private val auth: FirebaseAuth) : AccountService {
//  //private lateinit var auth: FirebaseAuth = FirebaseApp.initializeApp();
//  //FirebaseApp.initializeApp();
//  override val currentUserId: String
//    get() = auth.currentUser?.uid.orEmpty()
//
//  override val hasUser: Boolean
//    get() = auth.currentUser != null
//
//  override val currentUser: Flow<User>
//    get() = callbackFlow {
//      val listener =
//        FirebaseAuth.AuthStateListener { auth ->
//          this.trySend(auth.currentUser?.let { User(it.uid, it.isAnonymous) } ?: User())
//        }
//      auth.addAuthStateListener(listener)
//      awaitClose { auth.removeAuthStateListener(listener) }
//    }
//
//  override suspend fun authenticate(email: String, password: String) {
//    var result = auth.signInWithEmailAndPassword(email, password).await()
//    println(result)
//  }
////  override fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit) {
////    auth.signInWithEmailAndPassword(email, password)
////      .addOnCompleteListener { onResult(it.exception) }
////  }
//
//  override suspend fun sendRecoveryEmail(email: String) {
//    auth.sendPasswordResetEmail(email).await()
//  }
//
//  override suspend fun createAnonymousAccount() {
//    auth.signInAnonymously().await()
//  }
//
//  override suspend fun linkAccount(email: String, password: String): Unit =
//    trace(LINK_ACCOUNT_TRACE) {
//      val credential = EmailAuthProvider.getCredential(email, password)
//      auth.currentUser!!.linkWithCredential(credential).await()
//    }
//
//  override suspend fun deleteAccount() {
//    auth.currentUser!!.delete().await()
//  }
//
//  override suspend fun signOut() {
//    if (auth.currentUser!!.isAnonymous) {
//      auth.currentUser!!.delete()
//    }
//    auth.signOut()
//
//    // Sign the user back in anonymously.
//    createAnonymousAccount()
//  }
//
//  companion object {
//    private const val LINK_ACCOUNT_TRACE = "linkAccount"
//  }
//}
