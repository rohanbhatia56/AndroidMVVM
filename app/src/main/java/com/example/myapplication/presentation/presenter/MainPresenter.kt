package com.example.myapplication.presentation.presenter

import com.example.myapplication.data.repository.DataRepository
import com.example.myapplication.presentation.contracts.MainContract
import com.example.myapplication.presentation.contracts.MainContract.Presenter


class MainPresenter(private val view: MainContract.View, repository: DataRepository) :
    Presenter {
    private val repository: DataRepository = repository

    override fun loadData() {
        val data: String = repository.getData()
        if (data != null) {
            view.showData(data)
        } else {
            view.showError("Error loading data")
        }
    }
}
