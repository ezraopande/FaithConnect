package com.ezra.newmvvm.viewmodel.church

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezra.newmvvm.data.branches.Branch
import com.ezra.newmvvm.network.ChurchRetrofitInstance
import kotlinx.coroutines.launch

class BranchViewModel : ViewModel() {
    var branches: List<Branch> = emptyList()
        private set

    fun fetchBranches(onResult: (List<Branch>) -> Unit) {
        viewModelScope.launch {
            try {
                val fetchedBranches = ChurchRetrofitInstance.api.getBranches()
                branches = fetchedBranches
                onResult(fetchedBranches)
            } catch (e: Exception) {

                onResult(emptyList())
            }
        }
    }
}
