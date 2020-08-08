package co.com.babyrecord.ui.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import co.com.babyrecord.R
import co.com.babyrecord.databinding.FragmentHomeBinding
import co.com.babyrecord.ui.records.mvi.RecordWish
import co.com.babyrecord.ui.records.mvi.RecordsViewState
import com.google.android.material.snackbar.Snackbar
import com.oscarg798.babyrecord.core.usecase.GetRecordsUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class RecordsFragment : Fragment() {

    @Inject
    lateinit var recordsViewModel: RecordsViewModel

    private lateinit var biding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        biding = FragmentHomeBinding.inflate(layoutInflater)
        return biding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recordsViewModel.state.onEach { state ->
            when {
                state.error != null -> biding.textHome.text = "Error"
                state.isLoading -> biding.textHome.text = "Loading"
                state.records != null -> Snackbar.make(
                    biding.textHome,
                    state.records.joinToString(",") { it.type.toString() }, Snackbar.LENGTH_LONG
                ).show()
            }
        }.launchIn(lifecycleScope)

        recordsViewModel.onWish(RecordWish.GetRecords)
    }
}
