package me.akhilnarang.eztvapp.utils

object Tools {

    fun formatName(name: String?): String {
        var localName: String? = name ?: return ""

        // API broke. Let's just return empty for now

        val extensionIndex = localName!!.lastIndexOf('.')

        // 0 instead of == -1, to avoid problems with possible extension-only or with some extensionless filenames
        if (extensionIndex <= 0)
            return localName

        // Is this required? Do all files have extensions?
        localName = when (localName.toLowerCase().substring(extensionIndex)) {
            ".mkv", ".flv", ".mp4", ".wmv" -> localName.substring(0, extensionIndex).replace('.', ' ')
            else -> localName.substring(0, extensionIndex).replace('.', ' ') + localName.substring(extensionIndex)
        }

        return localName
    }
}
