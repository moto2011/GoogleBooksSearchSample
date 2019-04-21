package com.motonaka.googlebookssample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.motonaka.googlebookssample.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.findNavController
import com.mancj.materialsearchbar.MaterialSearchBar

class MainActivity : AppCompatActivity(), MaterialSearchBar.OnSearchActionListener {

    val viewModel: MainViewModel by viewModel()

    private val binding : ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val host = supportFragmentManager.findFragmentById(R.id.nav) as NavHostFragment?
//        val controller = NavHostFragment.findNavController(Objects.requireNonNull(host))

        binding.searchBar.setOnSearchActionListener(this)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host).navigateUp()

    override fun onSearchConfirmed(text: CharSequence?) {
        Toast.makeText(this, "onSearchConfirmed", Toast.LENGTH_SHORT).show()
        supportFragmentManager.findFragmentById(R.id.nav_host)?.let {
            hideKeyboard()

            val bundle = bundleOf("keyword" to binding.searchBar.text.toString())
            NavHostFragment.findNavController(it).navigate(R.id.action_firstFragment_to_secondFragment, bundle)
//            when (it) {
//                is FirstFragment -> {
//                    val bundle = bundleOf("keyword" to binding.searchBar.text.toString())
//                    NavHostFragment.findNavController(it).navigate(R.id.action_firstFragment_to_secondFragment, bundle)
//                }
//                is SecondFragment -> {
//                    val bundle = bundleOf("keyword" to binding.searchBar.text.toString())
//                    NavHostFragment.findNavController(it).navigate(R.id.action_firstFragment_to_secondFragment, bundle)
//                }
//            }
        }
    }

    override fun onButtonClicked(buttonCode: Int) {
        Toast.makeText(this, "onButtonClicked", Toast.LENGTH_SHORT).show()
    }

    override fun onSearchStateChanged(enabled: Boolean) {
        Toast.makeText(this, "onButtonClicked", Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard() {
        currentFocus?.let {
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}
