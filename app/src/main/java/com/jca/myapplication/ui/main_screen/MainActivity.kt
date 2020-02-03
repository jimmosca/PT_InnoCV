package com.jca.myapplication.ui.main_screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jca.myapplication.R
import com.jca.myapplication.data.remote.RetrofitFactory
import com.jca.myapplication.data.remote.RetrofitRemoteRepository
import com.jca.myapplication.model.User
import com.jca.myapplication.ui.user_form.UserFormActivity

class MainActivity : AppCompatActivity(),
    MainView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var searchBar: SearchView
    private lateinit var btnAdd: FloatingActionButton
    private lateinit var txtNoResults: TextView
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter =
            MainPresenter(
                this,
                RetrofitRemoteRepository(RetrofitFactory.makeRetrofitService())
            )

        btnAdd = findViewById(R.id.btnAdd)
        txtNoResults = findViewById(R.id.txtNoResults)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(presenter)
        recyclerView.adapter = userAdapter

        presenter.init()

        searchBar = findViewById(R.id.searchView)
        searchBar.queryHint = getString(R.string.search_hint)

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {

                presenter.onSearchTextChanged(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

        })

        btnAdd.setOnClickListener {
            presenter.onAddClicked()
        }
    }

    override fun onRestart() {
        super.onRestart()
        presenter.init()
    }

    override fun showUserList(users: List<User>) {
        txtNoResults.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        userAdapter.addUsers(users)
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun openFormView(id: Int) {
        val intent = Intent(this, UserFormActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

}
