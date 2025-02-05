package com.androidexpert35.testproject.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaDate(
    val dateString: String,
    val year: String
) : Parcelable
