package com.bank.practiceapplication

data class CounterModel(var count:Int)

class CounterRepository{
    private var counter = CounterModel(0)
    fun getCounter()=counter
    fun increment()
    {
        counter.count++
    }
    fun decrement()
    {
        counter.count--
    }
}