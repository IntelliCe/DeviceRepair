package com.qianyun.devicerepair.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qianyun.devicerepair.model.UiState
import com.qianyun.devicerepair.model.isSuccess
import com.qianyun.devicerepair.model.mutableUiStateOfContent
import com.qianyun.devicerepair.network.AuthApi
import com.qianyun.devicerepair.network.NetworkErrorString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var uiState by mutableUiStateOfContent(false)
    var username by mutableStateOf("")
    var password by mutableStateOf("")

    fun login() {
        uiState = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            uiState = try {
                val response = AuthApi.login(username, password)
                if (response.isSuccess()) {
                    UiState.Content(true)
                } else {
                    UiState.Error(response.message)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                UiState.Error(NetworkErrorString)
            }
        }
    }
}