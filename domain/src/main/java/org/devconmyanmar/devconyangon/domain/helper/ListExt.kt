package org.devconmyanmar.devconyangon.domain.helper

/**
 * Created by Vincent on 2019-10-06
 */
fun List<String>.asDelimitedString(delimiter: Char): String {

  val stringBuilder = StringBuilder()

  this.forEachIndexed { index, string ->
    stringBuilder.append(string)

    if (index != this.lastIndex) {
      stringBuilder.append(delimiter)
    }
  }

  return stringBuilder.toString()
}