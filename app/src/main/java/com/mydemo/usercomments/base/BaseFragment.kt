package com.mydemo.usercomments.base

import android.app.ProgressDialog
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.mydemo.usercomments.R

open class BaseFragment : Fragment() {

    @Suppress("DEPRECATION")
    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(requireActivity())
    }

   public fun showToast(strMessage: String?) {
        Toast.makeText(requireContext(), strMessage, Toast.LENGTH_SHORT).show()
    }

    fun showToast(@StringRes strID: Int) {
        showToast(getString(strID))
    }

    @Suppress("DEPRECATION")
    fun showProgressDialog() {
        if (!progressDialog.isShowing) {
            progressDialog.setMessage(getString(R.string.loading))
            progressDialog.setCancelable(false)
            progressDialog.show()
        }
    }

    fun hideProgressDialog() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

}