package com.github.ojh102.timary.db

import io.realm.DynamicRealm
import io.realm.RealmMigration

class TimaryRealmMigration : RealmMigration {

    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
        val schema = realm!!.schema

        for (curVer in oldVersion.toInt() until newVersion) {
            when (curVer) {
                0L -> {
                    schema.remove("User")

                    schema.get("CapsuleDetail")!!.transform {
                        val id = it.getLong("id")
                        val content = it.getString("content")
                        val targetDate = it.getLong("targetDate")
                        val writtenDate = it.getLong("writtenDate")

                        val capsule = realm.where("Capsule").equalTo("id", id).findFirst() ?: realm.createObject("Capsule", id)

                        capsule.setString("content", content)
                        capsule.setLong("targetDate", targetDate)
                        capsule.setLong("writtenDate", writtenDate)
                    }

                    realm.where("CapsuleDetail").findAll().deleteAllFromRealm()
                }
            }
        }
    }
}
