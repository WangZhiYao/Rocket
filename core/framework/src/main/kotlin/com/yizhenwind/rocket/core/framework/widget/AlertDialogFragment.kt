package com.yizhenwind.rocket.core.framework.widget

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnMultiChoiceClickListener
import android.database.Cursor
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListAdapter
import androidx.annotation.*
import androidx.fragment.app.FragmentManager

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/1/2
 */
class AlertDialogFragment private constructor(private val builder: Builder) :
    BaseDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return builder.create()
    }

    fun show(manager: FragmentManager) {
        super.show(manager, TAG)
    }

    data class Builder(val context: Context) {

        private val alertDialogBuilder = AlertDialog.Builder(context)

        fun setTitle(@StringRes titleId: Int) =
            apply { alertDialogBuilder.setTitle(titleId) }

        fun setTitle(title: CharSequence) =
            apply { alertDialogBuilder.setTitle(title) }

        fun setCustomTitle(customTitle: View) =
            apply { alertDialogBuilder.setCustomTitle(customTitle) }

        fun setMessage(@StringRes messageId: Int) =
            apply { alertDialogBuilder.setMessage(messageId) }

        fun setMessage(message: CharSequence) =
            apply { alertDialogBuilder.setMessage(message) }

        fun setIcon(@DrawableRes iconId: Int) =
            apply { alertDialogBuilder.setIcon(iconId) }

        fun setIcon(icon: Drawable) =
            apply { alertDialogBuilder.setIcon(icon) }

        fun setIconAttribute(@AttrRes attrId: Int) =
            apply { alertDialogBuilder.setIconAttribute(attrId) }

        fun setPositiveButton(
            @StringRes textId: Int,
            listener: DialogInterface.OnClickListener
        ) = apply { alertDialogBuilder.setPositiveButton(textId, listener) }

        fun setPositiveButton(
            text: CharSequence,
            listener: DialogInterface.OnClickListener
        ) = apply { alertDialogBuilder.setPositiveButton(text, listener) }

        fun setNegativeButton(
            @StringRes textId: Int,
            listener: DialogInterface.OnClickListener
        ) = apply { alertDialogBuilder.setNegativeButton(textId, listener) }

        fun setNegativeButton(
            text: CharSequence,
            listener: DialogInterface.OnClickListener
        ) = apply { alertDialogBuilder.setNegativeButton(text, listener) }

        fun setNeutralButton(
            @StringRes textId: Int,
            listener: DialogInterface.OnClickListener
        ) = apply { alertDialogBuilder.setNeutralButton(textId, listener) }

        fun setNeutralButton(
            text: CharSequence,
            listener: DialogInterface.OnClickListener
        ) = apply { alertDialogBuilder.setNeutralButton(text, listener) }

        fun setCancelable(cancelable: Boolean) =
            apply { alertDialogBuilder.setCancelable(cancelable) }

        fun setOnCancelListener(onCancelListener: DialogInterface.OnCancelListener) =
            apply { alertDialogBuilder.setOnCancelListener(onCancelListener) }

        fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener) =
            apply { alertDialogBuilder.setOnDismissListener(onDismissListener) }

        fun setOnKeyListener(onKeyListener: DialogInterface.OnKeyListener) =
            apply { alertDialogBuilder.setOnKeyListener(onKeyListener) }

        fun setItems(
            @ArrayRes itemsId: Int,
            listener: DialogInterface.OnClickListener
        ) = apply { alertDialogBuilder.setItems(itemsId, listener) }

        fun setItems(
            items: Array<CharSequence>,
            listener: DialogInterface.OnClickListener
        ) = apply { alertDialogBuilder.setItems(items, listener) }

        fun setAdapter(
            adapter: ListAdapter,
            listener: DialogInterface.OnClickListener
        ) = apply { alertDialogBuilder.setAdapter(adapter, listener) }

        fun setCursor(
            cursor: Cursor,
            listener: DialogInterface.OnClickListener,
            labelColumn: String
        ) = apply { alertDialogBuilder.setCursor(cursor, listener, labelColumn) }

        fun setMultiChoiceItems(
            @ArrayRes itemsId: Int,
            checkedItems: BooleanArray,
            listener: OnMultiChoiceClickListener
        ) = apply { alertDialogBuilder.setMultiChoiceItems(itemsId, checkedItems, listener) }

        fun setMultiChoiceItems(
            items: Array<CharSequence>,
            checkedItems: BooleanArray,
            listener: OnMultiChoiceClickListener
        ) = apply { alertDialogBuilder.setMultiChoiceItems(items, checkedItems, listener) }

        fun setMultiChoiceItems(
            cursor: Cursor,
            isCheckedColumn: String,
            labelColumn: String,
            listener: OnMultiChoiceClickListener
        ) = apply {
            alertDialogBuilder.setMultiChoiceItems(
                cursor,
                isCheckedColumn,
                labelColumn,
                listener
            )
        }

        fun setSingleChoiceItems(
            @ArrayRes itemsId: Int,
            checkedItem: Int,
            listener: DialogInterface.OnClickListener
        ) = apply { alertDialogBuilder.setSingleChoiceItems(itemsId, checkedItem, listener) }

        fun setSingleChoiceItems(
            cursor: Cursor,
            checkedItem: Int,
            labelColumn: String,
            listener: DialogInterface.OnClickListener
        ) = apply {
            alertDialogBuilder.setSingleChoiceItems(
                cursor,
                checkedItem,
                labelColumn,
                listener
            )
        }

        fun setSingleChoiceItems(
            items: Array<CharSequence?>,
            checkedItem: Int,
            listener: DialogInterface.OnClickListener
        ) = apply {
            alertDialogBuilder.setSingleChoiceItems(
                items,
                checkedItem,
                listener
            )
        }

        fun setSingleChoiceItems(
            adapter: ListAdapter,
            checkedItem: Int,
            listener: DialogInterface.OnClickListener
        ) = apply {
            alertDialogBuilder.setSingleChoiceItems(
                adapter,
                checkedItem,
                listener
            )
        }

        fun setOnItemSelectedListener(listener: AdapterView.OnItemSelectedListener) =
            apply { alertDialogBuilder.setOnItemSelectedListener(listener) }

        fun setView(@LayoutRes layoutResId: Int) =
            apply { alertDialogBuilder.setView(layoutResId) }

        fun setView(view: View) =
            apply { alertDialogBuilder.setView(view) }

        fun create() = alertDialogBuilder.create()

        fun build() = AlertDialogFragment(this)

    }

    companion object {

        private const val TAG = "MessageDialog"

    }
}