package com.oscarg798.babyrecord.records

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.oscarg798.babyrecord.core.di.RecordModuleDependencies
import com.oscarg798.babyrecord.records.adapter.RecordAdapter
import com.oscarg798.babyrecord.records.databinding.FragmentRecordsBinding
import com.oscarg798.babyrecord.records.di.DaggerRecordsComponent
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class RecordsFragment : Fragment() {

    @Inject
    lateinit var recordsViewModel: RecordsViewModel

    private lateinit var binding: FragmentRecordsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerRecordsComponent.factory()
            .create(
                EntryPointAccessors.fromApplication(
                    requireActivity().application,
                    RecordModuleDependencies::class.java
                )
            ).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecordsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.rvRecords) {
            layoutManager = LinearLayoutManager(context)
            adapter = RecordAdapter()
        }

        recordsViewModel.state.onEach { state ->
            when {
                state.error != null -> {
                    hideLoading()
                    showError()
                }
                state.isLoading -> showLoading()
                state.records != null -> {
                    hideLoading()
                    (binding.rvRecords.adapter as RecordAdapter).submitList(state.records.toList())
                }
            }
        }.launchIn(lifecycleScope)

        recordsViewModel.onWish(com.oscarg798.babyrecord.records.mvi.RecordWish.GetRecords)
    }

    private fun showLoading() {
        binding.srlRecords.isRefreshing = true
    }

    private fun hideLoading() {
        binding.srlRecords.isRefreshing = false
    }

    private fun showError() {
        Snackbar.make(
            binding.rvRecords,
            "Error", Snackbar.LENGTH_LONG
        ).show()
    }
}
