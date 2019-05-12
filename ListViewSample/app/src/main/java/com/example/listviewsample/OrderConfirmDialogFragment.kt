package com.example.listviewsample

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.Toast

class OrderConfirmDialogFragment : DialogFragment() {

    private val clickListener = DialogInterface.OnClickListener { dialog, which ->
        var msg = ""
        when (which) {
            DialogInterface.BUTTON_POSITIVE ->
                msg = getString(R.string.dialogOkToast)
            DialogInterface.BUTTON_NEGATIVE ->
                msg = getString(R.string.dialogNgToast)
            DialogInterface.BUTTON_NEUTRAL ->
                msg = getString(R.string.dialogNuToast)
        }
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.dialogTitle)
        builder.setMessage(R.string.dialogMsg)
        builder.setPositiveButton(R.string.dialogBtnOk, clickListener)
        builder.setNegativeButton(R.string.dialogBtnNg, clickListener)
        builder.setNeutralButton(R.string.dialogBtnNu, clickListener)
        return builder.create()
    }

}
