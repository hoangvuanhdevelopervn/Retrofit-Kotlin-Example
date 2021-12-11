package com.hva.retrofit_kotlin_example.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hva.retrofit_kotlin_example.R
import com.hva.retrofit_kotlin_example.data.api.ApiHelper
import com.hva.retrofit_kotlin_example.data.api.RetrofitBuilder
import com.hva.retrofit_kotlin_example.data.model.User
import com.hva.retrofit_kotlin_example.ui.base.CountryViewModelFactory
import com.hva.retrofit_kotlin_example.ui.base.ViewModelFactory
import com.hva.retrofit_kotlin_example.ui.main.adapter.MainAdapter
import com.hva.retrofit_kotlin_example.ui.main.viewmodel.CountryViewModel
import com.hva.retrofit_kotlin_example.utils.Status
import com.hva.retrofit_kotlin_example.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var countryViewModel: CountryViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()

        setupViewModel()
        getUsers()

//        setupViewModelCountry()
//        getCountries()
    }


    private fun setupViewModel() {
        viewModel =
            ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))).get(
                MainViewModel::class.java
            )
    }

    private fun setupViewModelCountry() {
        countryViewModel =
            ViewModelProvider(
                this,
                CountryViewModelFactory(ApiHelper(RetrofitBuilder.apiServiceCountry))
            ).get(
                CountryViewModel::class.java
            )
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun getUsers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }


    private fun getCountries() {
        countryViewModel.getCountries().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { data ->
                            Log.wtf("TAG", "data: $data")

                        }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<User>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}
