package org.devconmyanmar.devconyangon.base.helper

import android.app.Activity
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar.Duration
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

/**
 * Created by Vincent on 12/6/18
 */
fun View.setVisible(isVisible: Boolean) {
  if (isVisible) {
    this.visibility = View.VISIBLE
  } else {
    this.visibility = View.GONE
  }
}

fun Array<View>.setVisible(isVisible: Boolean) {

  val visibility = if (isVisible) {
    android.view.View.VISIBLE
  } else {
    android.view.View.GONE
  }

  this.forEach {
    it.visibility = visibility
  }
}

fun ViewGroup.inflater(): LayoutInflater {
  return LayoutInflater.from(this.context)
}

fun Activity.showShortToast(message: CharSequence) {
  Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun Activity.showShortToast(@StringRes resId: Int) {
  Toast.makeText(applicationContext, resId, Toast.LENGTH_SHORT).show()
}

fun Activity.showLongToast(message: CharSequence) {
  Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
}

fun Activity.showLongToast(@StringRes resId: Int) {
  Toast.makeText(applicationContext, resId, Toast.LENGTH_LONG).show()
}

fun Fragment.showShortToast(message: CharSequence) {
  Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showShortToast(@StringRes resId: Int) {
  Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
}

fun Fragment.showLongToast(message: CharSequence) {
  Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun Fragment.showLongToast(@StringRes resId: Int) {
  Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
}

fun spinnerSafeSelectionErrorStub(error: Exception) {
  Timber.e(error)
}

fun spinnerSafeSelectionDoAfterSelectStub(position: Int) {
  // Do Nothing
}

fun Spinner.safeSelection(
  position: Int,
  doAfterSelect: ((Int) -> (Unit)) = ::spinnerSafeSelectionDoAfterSelectStub,
  onError: ((Exception) -> (Unit)) = ::spinnerSafeSelectionErrorStub
) {
  try {
    setSelection(position)
  } catch (e: Exception) {
    onError.invoke(e)
  }

}

fun Activity.showSnackbar(text: CharSequence, @Duration duration: Int) {
  val contentView = findViewById<View>(android.R.id.content)
  val snackbar = Snackbar.make(contentView, text, duration)
  snackbar.show()
}

fun Activity.showSnackbar(@StringRes stringResId: Int, @Duration duration: Int) {
  val contentView = findViewById<View>(android.R.id.content)
  val snackbar = Snackbar.make(contentView, stringResId, duration)
  snackbar.show()
}

fun Int.toPx(): Float = (this * Resources.getSystem().displayMetrics.density)

fun Int.toDp(): Float = (this / Resources.getSystem().displayMetrics.density)

fun Float.toPx(): Float = (this * Resources.getSystem().displayMetrics.density)

fun Float.toDp(): Float = (this / Resources.getSystem().displayMetrics.density)

fun Activity.hideKeyboard() {
  val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
  //Find the currently focused view, so we can grab the correct window token from it.
  var view = currentFocus
  //If no view currently has focus, create a new one, just so we can grab a window token from it
  if (view == null) {
    view = View(this)
  }
  imm.hideSoftInputFromWindow(view.windowToken, 0)
  view.clearFocus()
}