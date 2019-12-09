package org.devconmyanmar.devconyangon.utils

import java.text.SimpleDateFormat
import java.util.*

fun convertMillisecond(hourAndMinute:String):Long{
    val sdf = SimpleDateFormat("hh:mm");
	val date = sdf.parse(hourAndMinute);
	val calendar = Calendar.getInstance();
	calendar.setTime(date);
	return calendar.timeInMillis
}