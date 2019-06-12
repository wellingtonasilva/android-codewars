package br.com.wsilva.codewars.core

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

open class BasicActivity : AppCompatActivity()
{
    fun addFragmentToActivity(fragmentManager: FragmentManager,
                              fragment: Fragment,
                              frameId: Int,
                              tag: String)
    {

        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment, tag)
        transaction.commit()
    }
}