
fun main() = with(System.`in`.bufferedReader()) {
    var cnt = 0
    val ip = readLine().split(":").map {
        var str = it
        if (str.isNotEmpty()) {
            cnt++
            while (str.length < 4) {
                str = "0$str"
            }
        }

        str
    }

    val sb = StringBuilder()
    var isZero = false

    ip.forEach {
        if (it == "") {
            if (!isZero) {
                repeat(8 - cnt) {
                    sb.append("0000:")

                }
                isZero = true
            }
        }
        else {
            sb.append(it).append(":")
        }
    }

    print(sb.substring(0, sb.length - 1))
}