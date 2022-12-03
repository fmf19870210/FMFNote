package com.fmf.fmfnote.kotlin

import java.text.SimpleDateFormat
import java.util.*

val dateFormate= SimpleDateFormat("HH:mm:ss:SSS")
val now = {dateFormate.format(Date(System.currentTimeMillis()))}
fun log(msg: Any?) = println("${"TAG  "}${now()} [${Thread.currentThread().name}] $msg")