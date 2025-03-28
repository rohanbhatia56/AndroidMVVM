package com.example.myapplication.presentation.contracts

// Contract Interface - Defines the communication between View and Presenter
interface MainContract {
    interface View {
        fun showData(data: String?)
        fun showError(error: String?)
    }

    interface Presenter {
        fun loadData()
    }
}
