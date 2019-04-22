package com.motonaka.googlebookssample.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.motonaka.googlebookssample.BuildConfig
import com.motonaka.googlebookssample.R
import com.motonaka.googlebookssample.databinding.FragmentFirstBinding
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import android.content.Intent

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)
        binding.textVersion.text = BuildConfig.VERSION_NAME
        binding.setOnClickLicense {
            OssLicensesMenuActivity.setActivityTitle(getString(R.string.license))
            startActivity(Intent(context, OssLicensesMenuActivity::class.java))
        }
        return binding.root
    }
}
