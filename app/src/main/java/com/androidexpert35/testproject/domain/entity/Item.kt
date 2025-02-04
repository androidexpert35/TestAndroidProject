package com.androidexpert35.testproject.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val mediaId: Int,
    val mediaUrl: String,
    val mediaUrlBig: String,
    val mediaType: String,
    val mediaDate: MediaDate,
    val mediaTitleCustom: String
) : Parcelable