package com.bank.mvvmmodel_view_viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Counter(var  x:Int)

class CounterViewMode
{
    private val _count= Counter(0)

    fun getval()=_count.x

    fun increment()
    {
        _count.x++
    }
    fun decrement()
    {
        _count.x--
    }
}