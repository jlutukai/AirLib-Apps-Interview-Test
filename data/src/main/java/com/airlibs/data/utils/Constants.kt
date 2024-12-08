package com.airlibs.data.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.util.UUID

object Constants {



    val PREFS_NAME = "com.airlibs.data.AppPreferences"
    const val STORE_NAME = "com.airlibs.data.preferences_store"

    const val DATABASE_NAME = "airlibs_db"




    fun getUUID(): String {
        return UUID.randomUUID().toString()
    }


}

class EmptyPagingSource<T : Any> : PagingSource<Int, T>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return LoadResult.Page(
            data = emptyList(),
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return null
    }
}