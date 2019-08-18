package com.github.ojh102.timary.db

import com.github.ojh102.timary.model.realm.Capsule
import io.realm.annotations.RealmModule

@RealmModule(classes = [
    Capsule::class
])
class TimaryRealmScheme
