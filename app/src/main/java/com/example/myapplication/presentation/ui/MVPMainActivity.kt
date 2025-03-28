package com.example.myapplication.presentation.ui

import android.R
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.data.repository.DataRepository
import com.example.myapplication.presentation.contracts.MainContract
import com.example.myapplication.presentation.presenter.MainPresenter


class MVPMainActivity : AppCompatActivity(), MainContract.View {
    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(com.example.myapplication.R.layout.activity_main)

        presenter = MainPresenter(this, DataRepository())
        presenter?.loadData()
    }

    override fun showData(data: String?) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show()
    }

    override fun showError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}
