package org.devconmyanmar.devconyangon.feature.about.Infofragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.devconmyanmar.devconyangon.R
import org.devconmyanmar.devconyangon.databinding.FragmentSessionBinding
import org.devconmyanmar.devconyangon.databinding.FragmentSettingBinding
import org.devconmyanmar.devconyangon.utils.darkmode.DARK_MODE
import org.devconmyanmar.devconyangon.utils.darkmode.LIGHT_MODE
import org.devconmyanmar.devconyangon.utils.darkmode.ThemeHelper

class SettingFragment:Fragment(){

    private val binding by lazy{
        FragmentSettingBinding.bind(view!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_setting,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}