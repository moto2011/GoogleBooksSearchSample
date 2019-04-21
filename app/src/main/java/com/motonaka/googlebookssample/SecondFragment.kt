package com.motonaka.googlebookssample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.motonaka.googlebookssample.databinding.FragmentSecondBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_PARAM1 = "keyword"

class SecondFragment : Fragment() {
    val viewModel: SecondViewModel by viewModel()

    private var keyword: String? = null
    private lateinit var binding: FragmentSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            keyword = it.getString(ARG_PARAM1)
            Toast.makeText(context!!, keyword, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)

        val controller = SecondController()

        viewModel.keyword = keyword

        viewModel.state.observe(this, Observer {
            if (it) binding.progressBar.visibility = View.GONE
        })

        viewModel.item.observe(this, Observer {
            controller.valumes = it.items
            controller.requestModelBuild()
            binding.recyclerView.adapter = controller.adapter
        })

        viewModel.search()

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
                SecondFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                    }
                }
    }
}
