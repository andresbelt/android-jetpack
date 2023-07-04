package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.commons.Constants.UNSPLASH_IMAGE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = UNSPLASH_IMAGE_TABLE)
data class PicImageEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = "",
    val author: String = "",
    val width: Int = 0,
    val height: Int= 0,
    val url: String = "",
    val download_url: String = "",
    val fav: Boolean = false
)
