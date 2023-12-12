package com.akinpelumi.c2068220.mytu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akinpelumi.c2068220.mytu.common.snackbar.SnackbarManager
import com.akinpelumi.c2068220.mytu.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import com.akinpelumi.c2068220.mytu.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
open class MyTUViewModel @Inject constructor (private val repo: AuthRepository?) : ViewModel() {
//    var isLoggedIn by mutableStateOf(false)
//    var isBusy by mutableStateOf(false)
    init {
        getAuthState()
    }
//    suspend fun signIn(email: String, password: String) {
//        isBusy = true
//        delay(2000)
//        isLoggedIn = true
//        isBusy = false
//    }
//
//    suspend fun signOut() {
//        isBusy = true
//        delay(2000)
//        isLoggedIn = false
//        isBusy = false
//    }
    fun getAuthState() = repo?.getAuthState(viewModelScope)

    val isEmailVerified get() = repo?.currentUser?.isEmailVerified ?: false

    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackbar) {
                    SnackbarManager.showMessage(throwable.toSnackbarMessage())
                }
                println(throwable)
            },
            block = block
        )
}
//val UserState = compositionLocalOf<MyTUViewModel> { error("User State Context Not Found!") }
