package com.motonaka.googlebookssample.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.motonaka.googlebookssample.R
import com.motonaka.googlebookssample.databinding.FragmentSecondBinding
import com.motonaka.googlebookssample.extenstion.addDefaultItemDecoration
import com.motonaka.googlebookssample.extenstion.toGone
import com.motonaka.googlebookssample.extenstion.toVisible
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_PARAM1 = "keyword"

class SecondFragment : Fragment() {
    private val viewModel: SecondViewModel by viewModel()

    private var keyword: String? = null
    private lateinit var binding: FragmentSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            keyword = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)
        binding.recyclerView.addDefaultItemDecoration()

        val controller = SecondController()

        viewModel.state.observe(this, Observer {
            if (it) binding.progressBar.toGone() else binding.progressBar.toVisible()
        })
        viewModel.item.observe(this, Observer {
            controller.valumes = it.items
            controller.requestModelBuild()
            binding.recyclerView.adapter = controller.adapter
        })
        viewModel.search(keyword)

        return binding.root
    }

    fun reload(keyword: String?) {
        arguments = bundleOf(ARG_PARAM1 to keyword)
        this.keyword = keyword
        viewModel.reload(this.keyword)
    }
}
