package com.example.taskmanager.presentation.detail.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.taskmanager.common.Result
import com.example.taskmanager.domain.group.GroupRepository
import com.example.taskmanager.domain.group.MessagesItem

const val USER_STARTING_PAGE_INDEX = 1

class GroupMessageDataSource(
    private val groupRepository: GroupRepository,
    private val groupId: Int
) : PagingSource<Int, MessagesItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MessagesItem> {
        val pageNumber = params.key ?: USER_STARTING_PAGE_INDEX
        return try {
            val result = groupRepository.getGroupMessages(groupId, pageNumber)
            if (result is Result.ApiSuccess) {

                LoadResult.Page(
                    data = result.data.content,
                    prevKey = if (pageNumber == USER_STARTING_PAGE_INDEX) null else pageNumber - 1,
                    nextKey = if (result.data.content.isEmpty()) null else pageNumber + 1
                )
            } else {
                LoadResult.Error(Exception("Error fetching data"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MessagesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
