package com.example.domain

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import com.example.data.model.PicImageEntity
import java.io.IOException

class MockPagingSource : PagingSource<Int, PicImageEntity>() {
    private var throwException = false
    private var data: List<PicImageEntity>

    init {
        data = ArrayList()
    }

    fun setData(data: List<PicImageEntity>) {
        this.data = data
    }

    fun setThrowException(throwException: Boolean) {
        this.throwException = throwException
    }

    override fun getRefreshKey(state: PagingState<Int, PicImageEntity>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PicImageEntity> {
        return try {
            if (throwException) {
                throw IOException("Mock exception")
            }
            val page = if (params.key != null) params.key!! else 0
            val pageSize = params.loadSize
            val start = page * pageSize
            val end = start + pageSize
            val pageData = data.subList(start, Math.min(end, data.size))
            val prevKey = if (page > 0) page - 1 else null
            val nextKey = if (end < data.size) page + 1 else null
            Page(pageData, prevKey, nextKey)

        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }
}
