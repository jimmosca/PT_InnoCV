package com.jca.myapplication.ui.user_form

import com.jca.myapplication.data.remote.RemoteRepository
import com.jca.myapplication.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserFormPresenter(
    private val view: UserFormView,
    private val remoteRepository: RemoteRepository
) {
    lateinit var user: User
    fun init(user: User) {
        this.user = user
    }

    fun onAcceptClicked(name: String, id: Int) {
        this.user.name = name
        CoroutineScope(Dispatchers.IO).launch {
            val wasSuccessful: Boolean
            if (id == 0)
                wasSuccessful = remoteRepository.postUser(user)
            else
                wasSuccessful = remoteRepository.putUser(user)
            withContext(Dispatchers.Main) {
                if (wasSuccessful)
                    view.goBack()
                else
                    view.showError()

            }
        }
    }

    fun emptyText() {
        view.showEmptyText()
    }
}

interface UserFormView {
    fun showEmptyText()
    fun showError()
    fun goBack()

}
