package org.devconmyanmar.devconyangon.feature.about

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.devconmyanmar.devconyangon.feature.about.Infofragments.AboutFragment
import org.devconmyanmar.devconyangon.feature.about.Infofragments.SettingFragment
import org.devconmyanmar.devconyangon.feature.about.Infofragments.sponsor.SponsorFragment

class MoreViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    fun getItemAtPosition(position: Int): String {
        return when(position){
            0-> "Setting"
            1->"About"
            2-> "Sponsor"
            else -> ""
        }
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SettingFragment()
            1->AboutFragment()
            2-> SponsorFragment()
            else -> SettingFragment()
        }
    }

}