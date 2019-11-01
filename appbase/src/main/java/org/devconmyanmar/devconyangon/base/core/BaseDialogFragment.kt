package org.devconmyanmar.devconyangon.base.core

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

/**
 * Created by Vincent on 2/27/19
 */
abstract class BaseDialogFragment : DialogFragment() {

  @get:LayoutRes
  protected abstract val layoutId: Int

  protected lateinit var rootView: View

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

    rootView = activity?.layoutInflater?.inflate(layoutId, null)!!

    val builder = getBuilder(savedInstanceState)
    builder.setView(rootView)
    val dialog = builder.create()
//    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//    dialog.window!!.setLayout(
//      LinearLayout.LayoutParams.MATCH_PARENT,
//      LinearLayout.LayoutParams.WRAP_CONTENT
//    )

    val modifiedDialog = modifyDialog(dialog)
    return modifiedDialog
  }

  fun getBuilder(savedInstanceState: Bundle?): AlertDialog.Builder {
    val builder = AlertDialog.Builder(activity!!)
    onBuilderInit(builder, savedInstanceState)
    return builder
  }

  abstract fun onBuilderInit(
    builder: AlertDialog.Builder,
    savedInstanceState: Bundle?
  )

  open fun modifyDialog(dialog: AlertDialog): AlertDialog {
    //Do nothing
    return dialog
  }

}
