package com.korea.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.korea.common.constants.PackageNameConstants.SEARCH_PACKAGE_NAME
import com.korea.home.databinding.FragmentHomeBinding

internal class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchTv.setOnClickListener {
            moveSearchActivity()
        }
    }

    private fun moveSearchActivity() {
        val activity = activity ?: return
        val intent = Intent().apply {
            setClassName(activity, SEARCH_PACKAGE_NAME)
        }
        activity.startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
