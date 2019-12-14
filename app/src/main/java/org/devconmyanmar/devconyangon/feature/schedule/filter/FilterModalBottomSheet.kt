package org.devconmyanmar.devconyangon.feature.schedule.filter

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.devconmyanmar.devconyangon.R

class FilterModalBottomSheet : BottomSheetDialogFragment() {

  @SuppressLint("RestrictedApi")
  override fun setupDialog(dialog: Dialog, style: Int) {
    super.setupDialog(dialog, style)

    //Set the custom view
    val view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_filter, null)
    dialog.setContentView(view)

    val params = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
    val behavior = params.behavior



    if (behavior != null && behavior is BottomSheetBehavior<*>) {
      behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
        @SuppressLint("SwitchIntDef")
        override fun onStateChanged(bottomSheet: View, newState: Int) {
          var state = ""

          when (newState) {
            BottomSheetBehavior.STATE_DRAGGING -> {
              state = "DRAGGING"
            }
            BottomSheetBehavior.STATE_SETTLING -> {
              state = "SETTLING"
            }
            BottomSheetBehavior.STATE_EXPANDED -> {
              state = "EXPANDED"
            }
            BottomSheetBehavior.STATE_COLLAPSED -> {
              state = "COLLAPSED"
            }
            BottomSheetBehavior.STATE_HIDDEN -> {
              dismiss()
              state = "HIDDEN"
            }
          }

        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
      })
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val v = inflater.inflate(R.layout.bottom_sheet_filter, container, false)
    val dropFilter = v.findViewById<ImageView>(R.id.drop_filter)
    val bottomSheet = v.findViewById<ConstraintLayout>(R.id.bottomSheet)
    dropFilter.setOnClickListener {
      dismiss()
      val bottomSheetBehavior =
        BottomSheetBehavior.from<ConstraintLayout>(bottomSheet)

      if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
      } else {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
      }
    }
    return v
  }

}