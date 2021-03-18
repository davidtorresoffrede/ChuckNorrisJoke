package d.offrede.categories.presentation.view

import android.os.Bundle
import android.view.View
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

        showCategories()
    }

    private fun showCategories() {
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