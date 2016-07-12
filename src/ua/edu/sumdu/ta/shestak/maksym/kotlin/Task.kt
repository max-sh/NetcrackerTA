package ua.edu.sumdu.ta.shestak.maksym.kotlin

/**
 * @author  Maksym Shestak
 * @since   12.07.2016
 */
/*
 class Task(
        private var title : String = "",
        private var time : Int = 0,
        private var startTime : Int = 0,
        private var endTime : Int = 0,
        private var repeatTime : Int = 0,
        private var active : Boolean = false
) {
}
        */
class Task {
    var title : String = ""
        get() = field
        set(value : String ) {
            if(value.isEmpty()) throw IllegalArgumentException()
        else
            field = value
        }

    private var time : Int = 0
    private var startTime : Int = 0
    private var endTime : Int = 0
    private var repeatTime : Int = 0
    private var active : Boolean = false


    fun isActive() = active
    fun isRepeated() = repeatTime > 0
}