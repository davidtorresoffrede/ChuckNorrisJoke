package d.offrede.chucknorris

import android.app.Application
import d.offrede.core.coreModules
import d.offrede.home.di.homeModules
import d.offrede.random_joke.randomJokeModules
import d.offrede.search.searchModules
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class ChuckApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ChuckApplication)
            fragmentFactory()
            modules(
                provideDependency()
            )
        }
    }

    private fun provideDependency() = listOf<Module>()
        .plus(coreModules)
        .plus(homeModules)
        .plus(randomJokeModules)
        .plus(searchModules)
}
