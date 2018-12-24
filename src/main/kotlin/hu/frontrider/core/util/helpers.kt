package hu.frontrider.core.util

fun Int.cap(cap: Int): Int {
    return if (this > cap)
        cap
    else
        this
}

fun Double.cap(cap: Double): Double {
    return if (this > cap)
        cap
    else
        this
}

