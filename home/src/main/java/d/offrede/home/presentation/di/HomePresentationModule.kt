package d.offrede.home.presentation.di

import d.offrede.home.presentation.view.HomeActivity
import d.offrede.navigator.HomeNavigation
import org.koin.dsl.module

val homePresentationModule = module {
    factory<HomeNavigation> { HomeActivity.HomeStart }
}