import android.content.Context

actual object Resources {
    private lateinit var context: Context

    fun initialize(context: Context) {
        this.context = context
    }

    actual fun getString(id: String): String {
        val resourceId = context.resources.getIdentifier(id, "string", context.packageName)
        return context.getString(resourceId)
    }
}