package br.com.wsilva.codewars.features.userdetail.principal

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.codewars.R
import kotlinx.android.synthetic.main.lay_user_detail_principal_fragment.*

class UserDetailPrincipalFragment: Fragment() {

    lateinit var mListPagerAdapter: UserDetailPrincipalStatePagerAdapter
    lateinit var mViewPager: ViewPager
    lateinit var mBottomNavigationView: BottomNavigationView

    companion object {
        val TAG: String = "UserDetailPrincipalFragment"
        fun newInstance(): UserDetailPrincipalFragment {
            return UserDetailPrincipalFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_user_detail_principal_fragment, container, false)

        //Lista de Páginas a serem exibidas
        mListPagerAdapter = UserDetailPrincipalStatePagerAdapter(fragmentManager!!, arguments!!)

        //ViewPage
        mViewPager = view?.findViewById<ViewPager>(R.id.viewPager)!!
        mViewPager.adapter = mListPagerAdapter
        mViewPager.addOnPageChangeListener(object: ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    UserDetailPrincipalStatePagerAdapter.PAGE_HOME -> navigation.selectedItemId = R.id.navigation_home
                    UserDetailPrincipalStatePagerAdapter.PAGE_COMPLETED -> navigation.selectedItemId = R.id.navigation_completed
                    UserDetailPrincipalStatePagerAdapter.PAGE_AUTHORED -> navigation.selectedItemId = R.id.navigation_authored
                }
            }
        })
        mBottomNavigationView = view?.findViewById(R.id.navigation)
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        return view
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mViewPager.currentItem = UserDetailPrincipalStatePagerAdapter.PAGE_HOME
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_completed -> {
                mViewPager.currentItem = UserDetailPrincipalStatePagerAdapter.PAGE_COMPLETED
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_authored -> {
                mViewPager.currentItem = UserDetailPrincipalStatePagerAdapter.PAGE_AUTHORED
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}