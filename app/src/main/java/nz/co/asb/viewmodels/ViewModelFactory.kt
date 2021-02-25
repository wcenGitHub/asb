package nz.co.asb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nz.co.asb.App

class ViewModelFactory(var app: App) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(app) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}