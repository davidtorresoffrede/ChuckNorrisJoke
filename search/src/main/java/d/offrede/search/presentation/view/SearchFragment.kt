package d.offrede.search.presentation.view

import android.os.Bundle
import android.view.View
import d.offrede.base.presentation.view.BaseFragment
import d.offrede.search.R
import d.offrede.search.databinding.FragmentSearchBinding
import d.offrede.search.presentation.viewmodel.SearchViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {

    private val viewModel: SearchViewModel by viewModel()

    private lateinit var binding: FragmentSearchBinding

    override fun layoutId() = R.layout.fragment_search

    override fun baseViewModel() = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.inflate(
            layoutInflater,
            baseBinding.fragContainer,
            true
        )

        showSearchResult()
    }

    private fun showSearchResult() {
        viewModel
            .resultLiveData()
            .observe(
                this,
                {},
                {},
                {}
            )
    }


}