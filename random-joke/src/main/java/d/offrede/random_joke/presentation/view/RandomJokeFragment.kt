package d.offrede.random_joke.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import d.offrede.base.presentation.view.BaseFragment
import d.offrede.random_joke.R
import d.offrede.random_joke.databinding.FragmentRandomJokeBinding
import d.offrede.random_joke.presentation.viewmodel.RandomJokeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RandomJokeFragment : BaseFragment() {

    private val viewModel: RandomJokeViewModel by viewModel()

    private lateinit var binding: FragmentRandomJokeBinding

    override fun layoutId() = R.layout.fragment_random_joke

    override fun baseViewModel() = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRandomJokeBinding.inflate(
            layoutInflater,
            baseBinding.fragContainer,
            true
        )

        showJoke()
        if (savedInstanceState == null) getJoke()
    }

    private fun showJoke() {
        viewModel
            .resultLiveData()
            .observe(
                this,
                {
                    binding.joke.text = it.data.text
                },
                {},
                {}
            )
    }

    private fun getJoke() {
        viewModel.getJoke()
    }

}
