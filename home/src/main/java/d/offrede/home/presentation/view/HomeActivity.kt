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

        showRandomJokeClass()
    }

    private val onNavigationItemSelectedListener: (MenuItem) -> Boolean = { item ->
        when (item.itemId) {
            R.id.random -> {
                showRandomJokeClass()
                true
            }
            R.id.search -> {
                // Respond to navigation item 2 click
                true
            }
            R.id.categories -> {
                // Respond to navigation item 3 click
                true
            }
            else -> false
        }
    }

    private fun showRandomJokeClass() {
        val randomJokeClass : Class<Fragment> = get(named("RandomJokeFragmentClass"))
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainer.id, randomJokeClass, null)
            .commit()
    }

    companion object HomeStart: HomeNavigation {
        override fun open(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }
}