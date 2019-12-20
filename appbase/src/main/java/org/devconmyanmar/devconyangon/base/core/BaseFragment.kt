package org.devconmyanmar.devconyangon.base.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by Vincent on 12/6/18
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

  protected lateinit var binding: VB

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): android.view.View? {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    binding = bindView(inflater)
    val view = binding.root
    return view
  }

  abstract fun bindView(inflater: LayoutInflater): VB

}
