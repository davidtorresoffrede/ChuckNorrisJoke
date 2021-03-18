package d.offrede.categories.presentation.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import d.offrede.base.presentation.extension.gone
import d.offrede.base.presentation.extension.visible
import d.offrede.base.presentation.view.BaseAdapter
import d.offrede.base.presentation.view.BaseFragment
import d.offrede.categories.R
import d.offrede.categories.databinding.FragmentCategoriesBinding
import d.offrede.categories.presentation.viewmodel.CategoriesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CategoriesFragment : BaseFragment() {

    private val viewModel: CategoriesViewModel by viewModel()

    private lateinit var binding: FragmentCategoriesBinding

    override fun layoutId() = R.layout.fragment_categories

    override fun baseViewModel() = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCategoriesBinding.inflate(
            layoutInflater,
            baseBinding.fragContainer,
            true
        )

        observeCategories()
        if (savedInstanceState == null) getCategories()
    }

    private fun observeCategories() {
        viewModel
            .resultLiveData()
            .observe(
                this,
                {
                    showCategories(it.data)
                },
                {
                    hideList()
                },
                {
                    hideList()
                }
            )
    }

    private fun getCategories() = viewModel.getCategories()

    private fun showCategories(list: List<String>) {
        showList()
        bindList(list)
    }

    private fun bindList(list: List<String>) {
        binding.recycler.apply {
            visibility = View.VISIBLE
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = BaseAdapter(list) {
                CategoriesViewHolder(it)
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