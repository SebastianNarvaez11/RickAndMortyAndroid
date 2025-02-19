package com.sebastiannarvaez.rickandmortyapp.modules.auth.presentation.screens

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    private var _authStatus = MutableStateFlow<AuthState>(AuthState.Checking)
    val authStatus: StateFlow<AuthState> = _authStatus

    fun login(email: String, password: String) {
        //aqui se hace el llamado al repositorio para hacer el login
        //y si sale bien, se cambia el estado, para que la pantalla login
        //navegue automaticamente con el efecto
        _authStatus.value = AuthState.Authenticated
    }

    fun logout() {
        _authStatus.value = AuthState.Unauthenticated
    }
}