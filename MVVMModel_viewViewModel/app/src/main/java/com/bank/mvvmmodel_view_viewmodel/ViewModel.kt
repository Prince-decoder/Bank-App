package com.bank.mvvmmodel_view_viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ViewMModel(): ViewModel()
{
    private val _CVM: CounterViewMode= CounterViewMode()
    private var CVVM = mutableStateOf(_CVM.getval())

    val Cvvm: MutableState<Int> =CVVM
    fun increase()
    {
        _CVM.increment()
        CVVM.value= _CVM.getval()
    }
    fun decrease()
    {
        _CVM.decrement()
        CVVM.value= _CVM.getval()
    }
}