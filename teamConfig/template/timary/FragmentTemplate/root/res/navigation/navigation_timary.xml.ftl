<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android">

    <fragment
        android:id="@+id/${className?uncap_first}"
        android:name="${packageName}.${packName}.${className}"
        tools:layout="@layout/${fragmentName}"
        android:label="${className}" />

</navigation>