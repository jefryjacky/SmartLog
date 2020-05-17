package smart.android.smartlog

enum class LogLevel(val priority: Int, val levelName: String) {
    VERBOSE(2, "VERBOSE"),
    DEBUG(3, "DEBUG"),
    INFO(4, "INFO"),
    WARN(5, "WARN"),
    ERROR(6, "ERROR"),
    ASSERT(7, "ASSERT");
}