package d.offrede.home.presentation.view

import android.os.Bundle
import android.view.MenuItem
import d.offrede.base.presentation.view.BaseActivity
import d.offrede.home.R
import d.offrede.home.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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
                // Respond to navigation item 1 click
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

}