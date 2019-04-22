package com.motonaka.googlebookssample.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.motonaka.googlebookssample.databinding.ActivityMainBinding
import androidx.navigation.findNavController
import com.mancj.materialsearchbar.MaterialSearchBar
import com.motonaka.googlebookssample.R

class MainActivity : AppCompatActivity(), MaterialSearchBar.OnSearchActionListener {

    private val binding : ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.searchBar.setOnSearchActionListener(this)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host).navigateUp()

    override fun onSearchConfirmed(text: CharSequence?) {
        hideKeyboard()
        supportFragmentManager.findFragmentById(R.id.nav_host)?.let {
            if (it.childFragmentManager.fragments[0] is FirstFragment) {
                val bundle = bundleOf("keyword" to binding.searchBar.text.toString())
                findNavController(R.id.nav_host).navigate(R.id.action_firstFragment_to_secondFragment, bundle)
            } else if (it.childFragmentManager.fragments[0] is SecondFragment) {
                (it.childFragmentManager.fragments[0] as SecondFragment).reload(binding.searchBar.text.toString())
            }
        }
    }

    override fun onButtonClicked(buttonCode: Int) {
//        Toast.makeText(this, "onButtonClicked", Toast.LENGTH_SHORT).show()
    }

    override fun onSearchStateChanged(enabled: Boolean) {
//        Toast.makeText(this, "onButtonClicked", Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard() {
        currentFocus?.let {
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}
