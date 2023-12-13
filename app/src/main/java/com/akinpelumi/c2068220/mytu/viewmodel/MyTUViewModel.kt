package com.akinpelumi.c2068220.mytu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akinpelumi.c2068220.mytu.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
open class MyTUViewModel @Inject constructor (private val repo: AuthRepository?) : ViewModel() {
    init {
        getAuthState()
    }
    fun getAuthState() = repo?.getAuthState(viewModelScope)

    val isEmailVerified get() = repo?.currentUser?.isEmailVerified ?: false

    fun launchCatching(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
//                if (snackbar) {
//                    //SnackbarManager.showMessage(throwable.toSnackbarMessage())
//                    showToastMessage(context,throwable.message)
//                }
                println(throwable)
            },
            block = block
        )
}