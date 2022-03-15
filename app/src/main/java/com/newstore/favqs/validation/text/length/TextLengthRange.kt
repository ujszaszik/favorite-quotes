package com.newstore.favqs.validation.text.length

class TextLengthRange private constructor(val start: Int, val end: Int) {

    companion object {

        private const val ILLEGAL_RANGE_STATE =
            "The provided text length range has bigger starting value: %d, then ending value: %d"


        fun of(start: Int, end: Int): TextLengthRange {
            return if (start > end) {
                throw IllegalArgumentException(
                    String.format(ILLEGAL_RANGE_STATE, start, end)
                )
            } else TextLengthRange(start, end)
        }
    }
}