package org.example

import bullets.Bullet

fun <T : Bullet> RevolverDrum<T>.removeGaps() {
    var oneNull = false
    var resFlag = false
    for (i in 0 until drum.size) {
        if (drum[i] == null) {
            if (oneNull) {
                resFlag = true
                break
            }
            oneNull = true
        } else {
            oneNull = false
        }
    }
    if (resFlag) {
        val tmp = drum.sortedWith(compareBy { it == null })
        drum.forEachIndexed { index, _ -> drum[index] = tmp[index] }
    }
}

fun <T : Bullet> RevolverDrum<T>.spinAndShoot() {
    scrollDrum()
    unloadOneBullet()
    nextBullet()
}

fun <T : Bullet> RevolverDrum<T>.oneBulletDrum(): RevolverDrum<Bullet> {
    val res = RevolverDrum<Bullet>()
    val extract = unloadOneBullet()
    nextBullet()
    if (extract != null) {
        res.addBullet(extract)
    }
    return res
}