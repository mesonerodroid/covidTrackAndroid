package com.smesonero.covidtrack.util

import java.text.SimpleDateFormat
import java.util.*




public fun getLocalDate (original:String):String{

    var clear = original.replace("T", " ").replace("Z","")

//    ret ="2018-09-10 22:01:00".toDate().formatTo("dd MMM yyyy  hh:mm")

    var ret = clear.toDate().formatTo(("dd MMM yyyy  HH:mm"))
    return ret
}

fun String.toDate(dateFormat: String = "yyyy-MM-dd HH:mm:ss", timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}