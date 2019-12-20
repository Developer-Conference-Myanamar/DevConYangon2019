package org.devconmyanmar.devconyangon.feature.about

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.devconmyanmar.devconyangon.feature.about.Infofragments.AboutFragment
import org.devconmyanmar.devconyangon.feature.about.Infofragments.SettingFragment
import org.devconmyanmar.devconyangon.feature.about.Infofragments.sponsor.SponsorFragment

class MoreViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    fun getItemAtPosition(position: Int): String {
        return when(position){
            0->"About"
            1-> "Sponsor"
            else -> ""
        }
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
//            0 -> SettingFragment()
            0->AboutFragment()
            1-> SponsorFragment()
            else -> SettingFragment()
        }
    }

}