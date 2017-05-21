package me.akhilnarang.eztvapp;

class Tools {

    public static String formatName(String name) {

        // API broke. Let's just return empty for now
        if (name == null)
            return "";

        int extensionIndex = name.lastIndexOf('.');

        // 0 instead of == -1, to avoid problems with possible extension-only or with some extensionless filenames
        if (extensionIndex <= 0)
            return name;

        // Is this required? Do all files have extensions?
        switch (name.toLowerCase().substring(extensionIndex)) {
            case ".mkv":
            case ".flv":
            case ".mp4":
            case ".wmv":
                name = name.substring(0, extensionIndex).replace('.', ' ');
                break;
            default:
                name = name.substring(0, extensionIndex).replace('.', ' ') + name.substring(extensionIndex);
        }

        return name;
    }
}
