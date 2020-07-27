package `in`.curioustools.architectures.utils.room

import java.util.*

class UniqueStringGenerator {
    companion object{
        fun getUniqueStringID(length: Int=16): String {
            val l = if (length > 30) 30 else length
            return UUID
                .randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, l)

        }

    }
}