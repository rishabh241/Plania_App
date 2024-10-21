import platform.Foundation.NSBundle
import platform.Foundation.NSString
actual object Resources {

    actual fun getString(id: String): String {
        return NSBundle.mainBundle.localizedStringForKey(id, id, null)
    }
}