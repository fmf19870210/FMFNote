package com.fmf.fmfnote.kotlin

class Latitude private constructor(val value: Double){

    companion object{
        @JvmStatic
        fun ofDouble1(double: Double): Latitude{
            return Latitude(double)
        }
    }




    fun ofDouble2(double: Double): Latitude{
        return Latitude(double)
    }
}