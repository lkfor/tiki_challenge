package com.tiki.challenge.utils

class StringUtils {
    companion object {
        fun splitString(source: String): String {
            val data: List<String> = source.split(" ").map { it.trim() }
            if (data.isNullOrEmpty()) {
                return source
            }
            val lstValues = data.toMutableList()
            if (lstValues.size < 2) {
                return source
            }

            val topLine = StringBuilder(lstValues.removeAt(0))
            val bottomLine = StringBuilder(lstValues.removeAt(lstValues.size - 1))

            while (lstValues.isNotEmpty()) {
                if (topLine.length <= bottomLine.length) {
                    topLine.append(" ${lstValues.removeAt(0)}")
                } else {
                    bottomLine.insert(0, "${lstValues.removeAt(lstValues.size - 1)} ")
                }
            }
            return topLine.append("\n").append(bottomLine.toString()).toString()
        }
    }
}