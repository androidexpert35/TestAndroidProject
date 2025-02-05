package com.androidexpert35.testproject.domain.usecase

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadPdfUseCaseImpl @Inject constructor() : LoadPdfUseCase {
    override suspend fun invoke(url: String, context: Context): Result<List<Bitmap>> = withContext(Dispatchers.IO) {
        try {
            val urlConnection = URL(url).openConnection()
            urlConnection.connect()
            val inputStream = urlConnection.getInputStream()

            val tempFile = File.createTempFile("temp_pdf", ".pdf", context.cacheDir)
            val outputStream = FileOutputStream(tempFile)

            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }

            val fileDescriptor = ParcelFileDescriptor.open(tempFile, ParcelFileDescriptor.MODE_READ_ONLY)
            val pdfRenderer = PdfRenderer(fileDescriptor)

            val bitmaps = mutableListOf<Bitmap>()
            for (i in 0 until pdfRenderer.pageCount) {
                val page = pdfRenderer.openPage(i)
                val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                bitmaps.add(bitmap)
                page.close()
            }

            pdfRenderer.close()
            tempFile.delete()

            Result.success(bitmaps)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}