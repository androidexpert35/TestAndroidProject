package com.androidexpert35.testproject.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.androidexpert35.testproject.domain.entity.Item

@Composable
fun DetailScreen(item: Item) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = item.mediaTitleCustom, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.mediaDate.dateString, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Link: ${item.mediaUrl}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        if (item.mediaType == "pdf") {
            // Use Coil to load and display the image
            AsyncImage(
                model = item.mediaUrl,
                contentDescription = "Document Preview",
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text("No document preview available")
        }
    }
}