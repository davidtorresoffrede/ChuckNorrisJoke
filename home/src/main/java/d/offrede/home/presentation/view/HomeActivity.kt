package d.offrede.home.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import d.offrede.base.presentation.view.BaseActivity
import d.offrede.home.R
import d.offrede.home.databinding.ActivityHomeBinding
import d.offrede.navigator.HomeNavigation
import org.koin.android.ext.android.get
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.core.qualifier.named

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupKoinFragmentFactory()

        bindViews()
        showRandomJokeFragment()
    }

    private fun bindViews() {
        binding = ActivityHomeBinding.inflate(
            layoutInflater,
            baseBinding.activityContainer,
            true
        )

        binding
            .bottomNavigation
            .setOnNavigationItemSelectedListener(
                onNavigationItemSelectedListener
            )
    }

    private val onNavigationItemSelectedListener: (MenuItem) -> Boolean = { item ->
        when (item.itemId) {
            R.id.random -> {
                showRandomJokeFragment()
                true
            }
            R.id.search -> {
                showSearchFragment()
                true
            }
            R.id.categories -> {
                showCategoriesFragment()
                true
            }
            else -> false
        }
    }

    private fun showRandomJokeFragment() {
        val randomJokeClass : Class<Fragment> = get(named("RandomJokeFragmentClass"))
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainer.id, randomJokeClass, null)
            .commit()
    }

    private fun showSearchFragment() {
        val searchClass : Class<Fragment> = get(named("SearchFragmentClass"))
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainer.id, searchClass, null)
            .commit()
    }

    private fun showCategoriesFragment() {
        val categoriesClass : Class<Fragment> = get(named("CategoriesFragmentClass"))
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainer.id, categoriesClass, null)
            .commit()
    }

    companion object HomeStart: HomeNavigation {
        override fun open(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }
}