package com.androidexpert35.testproject.data.remote

import android.util.Log
import com.androidexpert35.testproject.domain.ApiResponse
import com.androidexpert35.testproject.domain.entity.Item
import com.androidexpert35.testproject.domain.entity.MediaDate
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class ItemService @Inject constructor(private val client: HttpClient) {

    suspend fun getItems(): List<Item> {
        return try {
            val response = client.get("https://apivegans.veganslab.xyz/test.json").body<Map<String, Any>>()
            val content = response["content"] as? List<Map<String, Any>> ?: emptyList()
            content.map { item ->
                val mediaDateMap = item["mediaDate"] as? Map<String, String> ?: emptyMap()
                Item(
                    mediaId = (item["mediaId"] as? Double)?.toInt() ?: 0,
                    mediaUrl = item["mediaUrl"] as? String ?: "",
                    mediaUrlBig = item["mediaUrlBig"] as? String ?: "",
                    mediaType = item["mediaType"] as? String ?: "",
                    mediaDate = MediaDate(
                        dateString = mediaDateMap["dateString"] ?: "",
                        year = mediaDateMap["year"] ?: ""
                    ),
                    mediaTitleCustom = item["mediaTitleCustom"] as? String ?: ""
                )
            }
        } catch (e: Exception) {
            Log.e("ItemService", "Error fetching items", e)
            emptyList()
        }
    }
}