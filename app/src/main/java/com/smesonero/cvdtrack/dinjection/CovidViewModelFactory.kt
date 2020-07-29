package com.smesonero.cvdtrack.dinjection

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.smesonero.cvdtrack.service.CovidRepository
import com.smesonero.cvdtrack.viewmodel.CovidViewModel

//Con hilt, no necesario
class MyViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val repository: CovidRepository,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String, modelClass: Class<T>, handle: SavedStateHandle
    ): T {
        return CovidViewModel(
            repository, handle
        ) as T
    }
}