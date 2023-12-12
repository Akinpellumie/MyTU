package com.akinpelumi.c2068220.mytu.data.repository

import android.app.Activity
import android.net.Uri
import com.akinpelumi.c2068220.mytu.domain.model.Response.Failure
import com.akinpelumi.c2068220.mytu.domain.model.Response.Success
import com.akinpelumi.c2068220.mytu.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {
    override val currentUser get() = auth.currentUser

    override suspend fun firebaseSignUpWithEmailAndPassword(
        email: String, password: String
    ) = try {
        auth.createUserWithEmailAndPassword(email, password).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }
    override suspend fun firebaseUpdateUserProfile(
        fullName: String,
        imageUri: Uri
    ) = try {
        val profileUpdates = userProfileChangeRequest {
            this.displayName = fullName
            this.photoUri = imageUri
        }
        auth.currentUser!!.updateProfile(profileUpdates).await()
        Success(true)
    }catch (e: Exception){
        Failure(e)
    }

    override suspend fun sendEmailVerification() = try {
        auth.currentUser?.sendEmailVerification()?.await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String, password: String
    ) = try {
        auth.signInWithEmailAndPassword(email, password).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun firebaseSignInWithMicrosoft(activity: Activity
    ) = try{
        // [START auth_microsoft_provider_create]
        val provider = OAuthProvider.newBuilder("microsoft.com")
        //call signinWithProvider...
        //signInWithProvider(provider, activity)
        auth.startActivityForSignInWithProvider(activity, provider.build()).await()
        Success(true)
    }catch (e: Exception){
        Failure(e)
    }

    override suspend fun reloadFirebaseUser() = try {
        auth.currentUser?.reload()?.await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }



    override suspend fun sendPasswordResetEmail(email: String) = try {
        auth.sendPasswordResetEmail(email).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override fun signOut() = auth.signOut()

    override suspend fun revokeAccess() = try {
        auth.currentUser?.delete()?.await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), auth.currentUser == null)

    private fun signInWithProvider(provider: OAuthProvider.Builder, activity: Activity) {
        // [START auth_oidc_provider_signin]
        auth
            .startActivityForSignInWithProvider(activity, provider.build())
            .addOnSuccessListener {
                // User is signed in.
                // IdP data available in
                // authResult.getAdditionalUserInfo().getProfile().
                // The OAuth access token can also be retrieved:
                // ((OAuthCredential)authResult.getCredential()).getAccessToken().
                // The OAuth secret can be retrieved by calling:
                // ((OAuthCredential)authResult.getCredential()).getSecret().
            }
            .addOnFailureListener {
                // Handle failure.
            }
        // [END auth_oidc_provider_signin]
    }

    fun getPendingAuthResult() {
        // [START auth_oidc_pending_result]
        val pendingResultTask = auth.pendingAuthResult
        if (pendingResultTask != null) {
            // There's something already here! Finish the sign-in for your user.
            pendingResultTask
                .addOnSuccessListener {
                    // User is signed in.
                    // IdP data available in
                    // authResult.getAdditionalUserInfo().getProfile().
                    // The OAuth access token can also be retrieved:
                    // ((OAuthCredential)authResult.getCredential()).getAccessToken().
                    // The OAuth secret can be retrieved by calling:
                    // ((OAuthCredential)authResult.getCredential()).getSecret().
                }
                .addOnFailureListener {
                    // Handle failure.
                }
        } else {
            // There's no pending result so you need to start the sign-in flow.
            // See below.
        }
        // [END auth_oidc_pending_result]
    }
}