package com.airlibs.app.features.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airlibs.app.R
import com.airlibs.app.features.dashboard.models.GetMedicineUIState
import com.airlibs.data.sources.local.dataStore.AirLibsDataStore
import com.airlibs.domain.models.data.MedicineData
import com.airlibs.domain.models.data.utils.UiText
import com.airlibs.domain.repositories.medicine.MedicineRepository
import com.airlibs.domain.useCases.GetMedicinesUseCase
import com.airlibs.domain.useCases.GetUserDetailsUseCase
import com.airlibs.domain.utils.Constants.INVALID_ENTITY_ID
import com.airlibs.domain.utils.Constants.USER_ID_KEY
import com.airlibs.domain.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val repository: MedicineRepository,
    private val getMedicinesUseCase: GetMedicinesUseCase,
    private val dataStore: AirLibsDataStore,
    private val getUserDetailsUseCase: GetUserDetailsUseCase
) : ViewModel() {
    private val _dashBoardUiStateState: MutableStateFlow<GetMedicineUIState> =
        MutableStateFlow(GetMedicineUIState())
    val dashBoardUiStateState: StateFlow<GetMedicineUIState> get() = _dashBoardUiStateState.asStateFlow()

    private val _events = Channel<DashBoardEvents>()
    val events = _events.receiveAsFlow()

    init {
        getMedicineRemote()
    }

     fun getCurrentUserDetails(): Flow<String> {
            return dataStore.getInt(USER_ID_KEY).flatMapLatest { userId ->
                getUserDetailsUseCase(userId = userId).map { it?.email?:"" }
                }
     }

    fun getMedicines(): Flow<List<MedicineData>> = getMedicinesUseCase()

    private fun getMedicineRemote() {
        viewModelScope.launch {
            repository.getMedicinesRemote().onStart {
                _dashBoardUiStateState.update {
                    it.copy(isLoading = true)
                }
            }.collect { result ->
                when (result) {
                    is ResultWrapper.GenericError -> {
                        _dashBoardUiStateState.update {
                            it.copy(isLoading = false)
                        }
                        _events.send(DashBoardEvents.Error(error = result.errorMessage))
                    }

                    ResultWrapper.Loading -> {
                        _dashBoardUiStateState.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is ResultWrapper.Success -> {
                        _dashBoardUiStateState.update {
                            it.copy(isLoading = false, isSuccessful = result.value)
                        }
                    }
                }

            }
        }
    }

    fun onEvent(event: DashBoardEvents) {
        when(event){
            is DashBoardEvents.Error, DashBoardEvents.IdleState ->{
                viewModelScope.launch {
                    _events.send(event)
                }
            }
        }
    }
}

sealed interface DashBoardEvents {
    data class Error(
        val error: UiText,
        val errorTitle: UiText = UiText.StringResource(R.string.error)
    ) : DashBoardEvents

    data object IdleState : DashBoardEvents
}