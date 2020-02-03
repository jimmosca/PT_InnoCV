package com.jca.myapplication.ui.main_screen

import com.jca.myapplication.data.remote.RemoteRepository
import com.jca.myapplication.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(private val view: MainView, private val remoteRepository: RemoteRepository) {

    fun init() {
        getUsers()
    }

    private fun getUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val users = remoteRepository.getUsers()
            withContext(Dispatchers.Main) {

                if (users!!.isNotEmpty())

                    view.showUserList(users)

            }
        }
    }

    fun onEditClicked(id: Int) {

    }

    fun onDeleteClicked(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val wasSuccessful = remoteRepository.deleteUser(id)
            withContext(Dispatchers.Main) {

                if (wasSuccessful) {
                    view.showToast("User deleted Successfully")
                    getUsers()
                } else
                    view.showToast("An error occurred while deleting")
            }
        }
    }

    fun onSearchTextChanged(id: String) {

    }

    fun onAddClicked() {

    }
}

interface MainView {
    fun showUserList(users: List<User>)
    fun showToast(msg: String)

}
