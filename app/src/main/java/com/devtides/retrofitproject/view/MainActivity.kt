package com.devtides.retrofitproject.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.devtides.retrofitproject.databinding.ActivityMainBinding
import com.devtides.retrofitproject.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val itemsAdapter = ListAdapter(arrayListOf())
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel.fetchData()
//        viewModel.fetchDataSync()
        viewModel.fetchDataRx()

        binding.itemsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.apiResponse.observe(this, Observer { items ->
            items?.let {
                binding.itemsList.visibility = View.VISIBLE
                itemsAdapter.updateItems(it)
            }
        })

        viewModel.error.observe(this) { errorMsg ->
            binding.listError.visibility = if (errorMsg == null) View.GONE else View.VISIBLE
            binding.listError.text = "Error\n$errorMsg"
        }

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.listError.visibility = View.GONE
                    binding.itemsList.visibility = View.GONE
                }
            }
        })
    }
}
