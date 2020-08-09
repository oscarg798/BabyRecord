package com.oscarg798.babyrecord.babylist

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oscarg798.babyrecord.babylist.databinding.FragmentBabyListBinding


class BabyListFragment : Fragment() {


    private lateinit var binding: FragmentBabyListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newContext = ContextThemeWrapper(requireContext(), R.style.AppTheme)
        val layoutInflater = inflater.cloneInContext(newContext)
        binding = FragmentBabyListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
