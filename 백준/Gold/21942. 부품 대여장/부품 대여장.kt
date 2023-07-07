import java.text.SimpleDateFormat

fun dateToLong(date: String): Long {
    return SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date).time
}

fun dateGap(date: String): Long {
//    return SimpleDateFormat("ddd/HH:mm").parse(date).time + 118800000L
    val day = date.split("/")[0].toLong()
    val (hour, minute) = date.split("/")[1].split(":").map { it.toLong() }
    return day * 24 * 60 + hour * 60 + minute
}

fun main() = with(System.`in`.bufferedReader()) {
    val oneMinuteToLong = dateToLong("2000-01-01 01:01") - dateToLong("2000-01-01 01:00")

    val (n, L, f) = readLine().split(" ")
    val N = n.toInt()
    val F = f.toLong()

    val fineGap = dateGap(L)

    val rentalMap = hashMapOf<String, HashMap<String, Long>>()
    val fineMap = hashMapOf<String, Long>()

    repeat(N) {
        val (date, time, part, name) = readLine().split(" ")

        if (rentalMap.containsKey(part) && rentalMap[part]!!.containsKey(name)) {
            // 반납
            val gap = (dateToLong("$date $time") - rentalMap[part]!![name]!!) / (1000 * 60)
            if (gap > fineGap) {
                val fine = ((gap - fineGap)) * F
                fineMap[name] = (fineMap[name] ?: 0) + fine
            }

            rentalMap[part]!!.remove(name)
        }
        else {
            // 대출
            if (rentalMap[part] == null) rentalMap[part] = hashMapOf()
            rentalMap[part]!![name] = dateToLong("$date $time")
        }
    }

    val sb = StringBuilder()
    if (fineMap.size == 0) sb.append("-1")
    else {
        fineMap.toSortedMap{ a, b -> a.compareTo(b)}.forEach { (t, u) ->
            sb.append(t).append(" ").append(u).append("\n")
        }
    }

    println(sb)
}