package com.jca.myapplication.ui.user_form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.jca.myapplication.R
import com.jca.myapplication.data.remote.RetrofitFactory
import com.jca.myapplication.data.remote.RetrofitRemoteRepository
import com.jca.myapplication.model.User

class UserFormActivity : AppCompatActivity(), UserFormView {

    lateinit var btnAccept: Button
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_form)
        val presenter = UserFormPresenter(
            this,
            RetrofitRemoteRepository(RetrofitFactory.makeRetrofitService())
        )
        val id = intent.extras?.getInt("id")!!
        presenter.init(User(id, "", "2020-02-01T14:32:14.367"))
        btnAccept = findViewById(R.id.btnAccept)
        editText = findViewById(R.id.editText)

        btnAccept.setOnClickListener {
            if (editText.text.isNotEmpty()) {
                presenter.onAcceptClicked(
                    editText.text.toString(),
                    id
                )
            } else
                presenter.emptyText()
        }


    }

    override fun showEmptyText() =
        Toast.makeText(this, getString(R.string.empty_text), Toast.LENGTH_SHORT).show()

    override fun showError() =
        Toast.makeText(this, getString(R.string.insertion_error), Toast.LENGTH_SHORT).show()

    override fun goBack() {
        finish()
    }
}
