package com.example.criminalintent

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.criminalintent.databinding.FragmentPhotoDialogBinding

class PhotoPickerFragment : DialogFragment(R.layout.fragment_photo_dialog) {
    private val binding: FragmentPhotoDialogBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photoUri = requireArguments().getString("KEY_IMAGE")
        requireDialog().window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        requireDialog().window?.setDimAmount(0F)

        Glide.with(this)
            .load(photoUri)
            .error(R.drawable.ic_error_photo_load)
            .centerCrop()
            .into(binding.fullSizeCrimePhoto)
    }

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }

}
