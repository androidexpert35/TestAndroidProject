package com.androidexpert35.testproject.domain

import com.androidexpert35.testproject.domain.entity.Item

data class ApiResponse(
    val status: Boolean,
    val lang: String,
    val content: List<Item>
)