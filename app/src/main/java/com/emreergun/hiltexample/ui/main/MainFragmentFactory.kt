package com.emreergun.hiltexample.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class MainFragmentFactory
@Inject constructor(

) :FragmentFactory(){
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return super.instantiate(classLoader, className)

        // Burada MainActivity içindeki fragmentlar'a injectleri göndereceğiz...

    }
}