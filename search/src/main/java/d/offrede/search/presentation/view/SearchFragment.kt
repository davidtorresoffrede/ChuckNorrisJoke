package d.offrede.search.presentation.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import d.offrede.base.presentation.extension.gone
import d.offrede.base.presentation.extension.visible
import d.offrede.base.presentation.view.BaseAdapter
import d.offrede.base.presentation.view.BaseFragment
import d.offrede.core.presentation.model.Joke
import d.offrede.search.R
import d.offrede.search.databinding.FragmentSearchBinding
import d.offrede.search.presentation.viewmodel.SearchViewModel
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment() {

    private val viewModel: SearchViewModel by viewModel()

    private lateinit var binding: FragmentSearchBinding

    override fun layoutId() = R.layout.fragment_search

    override fun baseViewModel() = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView()
        observeSearchResult()
    }

    private fun bindView() {
        binding = FragmentSearchBinding.inflate(
            layoutInflater,
            baseBinding.fragContainer,
            true
        )

        binding.searchButton.setOnClickListener {
            search()
        }
    }

    private fun search() {
        viewModel.search(binding.editQuery.text.toString())
    }

    private fun observeSearchResult() {
        viewModel
            .resultLiveData()
            .observe(
                this,
                {
                    showSearchResult(it.data.jokes)
                },
                {
                    hideList()
                },
                {
                    hideList()
                }
            )
    }

    private fun showSearchResult(list: List<Joke>) {
        showList()
        bindlist(list)
    }

    private fun bindlist(list: List<Joke>) {
        binding.recycler.apply {
            visibility = View.VISIBLE
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = BaseAdapter(list) {
                SearchViewHolder(it)
            }
        }
    }

    private fun showList() {
        binding.recycler.visible()
    }

    private fun hideList() {
        binding.recycler.gone()
    }

}