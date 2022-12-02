package com.example.criminalintent

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.criminalintent.databinding.FragmentPhotoDialogBinding
import java.io.File

class PhotoPickerFragment(private val photoCrime: File?) :
    DialogFragment(R.layout.fragment_photo_dialog) {
    private val binding: FragmentPhotoDialogBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photoUri = photoCrime?.toUri()?.let { photoCrime.toUri() }
            ?: savedInstanceState?.getString(KEY_IMAGE_URI)?.toUri()

        Glide.with(this)
            .load(photoUri)
            .error(R.drawable.ic_error_photo_load)
            .centerCrop()
            .into(binding.fullSizeCrimePhoto)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_IMAGE_URI, photoCrime.toString())
    }

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
        const val KEY_IMAGE_URI = "KEY_IMAGE_URI"
    }

}
