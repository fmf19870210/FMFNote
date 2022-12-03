package com.fmf.fmfnote.kotlin

class Student(var name: String, var age: Int):Person()  {

    override fun toString(): String {
        return "Name: $name Age: $age"
    }

}