package nz.co.asb.viewmodels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import nz.co.asb.App

open class BaseViewModel(app: App) : AndroidViewModel(app) {

    val errorData = MutableLiveData<Exception>()

}